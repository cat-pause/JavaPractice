package test;

import java.sql.*;
import java.util.ArrayList;

public class UserDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	
	//DB연결
	void connect() {
		//드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@211.223.59.99:1521:xe","smart02","0000");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//connect()
	
	void disconnect() {
		if( rs!=null ) 
			try { rs.close(); } catch (Exception e) {e.printStackTrace();}
		if( ps!=null )
			try {ps.close();} catch (Exception e) {e.printStackTrace();}
		if( conn!=null)
			try {conn.close();} catch (Exception e) {e.printStackTrace();}
	}//disconnect()
	
	//아이디,비밀번호 체크
	int idpwCheck(String id, String pw) {
		int result = 0;
		connect();
		try {
			String checkIdSql = "SELECT pw FROM tbl_java_user WHERE id = ?";
			ps = conn.prepareStatement(checkIdSql);
			ps.setString(1, id);
			rs = ps.executeQuery();
			if( rs.next() ) {
				if( rs.getString(1).equals(pw)) {
					System.out.println("로그인 성공");
					String checkAdminSql = "SELECT admin FROM tbl_java_user WHERE id = ?";
					ps = conn.prepareStatement(checkAdminSql);
					ps.setString(1, id);
					rs = ps.executeQuery();
					if( rs.next() ) {	// ADMIN이 Y면 관리자
						if( rs.getString(1).equals("Y")) {
							result = 2;
						}else {
							result = 3;
						}
					}
				}else {
					System.out.println("비밀번호 틀림");
				}
			}else {
				System.out.println("아이디 틀림");
				result = 9;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}//Check
	
	//유저 로그인시 소지금 조회
	int checkMoney(String inputId) {
		connect();
		int money = 0;
		String sql = "SELECT name, money FROM tbl_java_user WHERE id = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, inputId);
			rs = ps.executeQuery();
			if( rs.next() ) {
				money = rs.getInt("money");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return money;
	}//checkMoney
	
	//금액 충전
	void chargeMoney(String id, int addMoney) {
		connect();
		String sql = "UPDATE tbl_java_user "
					+ "SET money = money + ? "
					+ "WHERE id = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, addMoney);
			ps.setString(2, id);
			rs = ps.executeQuery();
			System.out.println("충전 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}//chargeMoney
	
	//주문 후 소지금 차감
	void decsMoney(int fprice, String inputId) {
		connect();
		String sql = "UPDATE tbl_java_user "
					+ "SET money = money - ? "
					+ "WHERE id = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, fprice);
			ps.setString(2, inputId);
			rs = ps.executeQuery();
			System.out.println("...결제 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}//descMoney
	
	//회원가입시 아이디 중복검사
	int mulIdCheck(String joinId) {
		connect();
		int result = 0;
		String sql = "SELECT id FROM tbl_java_user WHERE id = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, joinId);
			rs = ps.executeQuery();
			if( rs.next() ) {
				result = 0;		// 중복 아이디 발견!
			}else {
				result = 1;		// 중복 검사 통과!
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return result;
	}//mulIdCheck
	
	//회원가입
	void newAccount(String id, String pw, String joinAdmin) {
		connect();
		String joinName = null;
		if( joinAdmin == "Y" ) {
			joinName = "관리자";
		}else {
			joinName = "사용자";
		}
		String sql = "INSERT INTO tbl_java_user "
					+ "VALUES (?, ?, ?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, id);
			ps.setString(2, pw);
			ps.setString(3, joinName);
			ps.setString(4, joinAdmin);
			ps.setString(5, id+"@naver.com");
			ps.setInt(6, 0);
			rs = ps.executeQuery();
			System.out.println(" - 회원가입 완료 - ");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}//newAccount
	
	//계정 목록 조회
	ArrayList<UserDTO> accountList() {
		connect();
		String sql = "SELECT id, pw, name, email, money "
					+ "FROM tbl_java_user ";
		ArrayList<UserDTO> list = new ArrayList<UserDTO>();
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
			while( rs.next() ) {
				UserDTO dto = new UserDTO();
				dto.setId(rs.getString("id"));
				dto.setPw(rs.getString("pw"));
				dto.setName(rs.getString("name"));
				dto.setEmail(rs.getString("email"));
				dto.setMoney(rs.getInt("money"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}//accountList
	
	//계정 삭제
	void deleteAccount(String delId) {
		connect();
		String sql = "DELETE FROM tbl_java_user "
					+ "WHERE id = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, delId);
			rs = ps.executeQuery();
			System.out.println("[계정 삭제 완료]");
		} catch (Exception e) {
			System.out.println("삭제 실패! 아이디를 확인하세요");
		}
	}//deleteAccount
	
}//class
