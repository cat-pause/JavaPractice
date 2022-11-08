package test;

import java.sql.*;
import java.util.ArrayList;

public class JavaDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	private String id;
	
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
//					result = 1;
					//관리자 확인
					String checkAdminSql = "SELECT admin FROM tbl_java_user WHERE id = ?";
					ps = conn.prepareStatement(checkAdminSql);
					ps.setString(1, id);
					rs = ps.executeQuery();
					if( rs.next() ) {	// ADMIN이 Y면 관리자
						if( rs.getString(1).equals("Y")) {
							result = 2;
						}else {
//							this.id = id;
//							saveId(id);
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
	
	ArrayList<JavaDTO> pList() {
		ArrayList<JavaDTO> list = new ArrayList<JavaDTO>();
		connect();
		try {
			String sql = "SELECT num, name as product_name, "
					+ "company, price, qty "
					+ "FROM tbl_java_product";
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {
				//모든 물건 정보 담기
				JavaDTO dto = new JavaDTO();
				dto.setNum(rs.getInt("num"));
				dto.setP_name(rs.getString("product_name"));
				dto.setCompany(rs.getString("company"));
				dto.setPrice(rs.getInt("price"));
				dto.setQty(rs.getInt("qty"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//제품 이름으로 검색하기
	ArrayList<JavaDTO> searchProduct(String inputName) {
		connect();
		JavaDTO dto = null;
		ArrayList<JavaDTO> list = new ArrayList<JavaDTO>();
		String sql = "SELECT num, REPLACE(name, ' ', '') name, REPLACE(company, ' ','') company, price, qty "
				+ "FROM tbl_java_product WHERE name LIKE ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+inputName.trim()+"%");
//			if(no == 1) {		//이름 검색 선택시
//				System.out.println("===이름으로 검색===");
//				ps.setString(1, "name");
//				ps.setString(2, "%"+inputName.trim()+"%");
//			}else if(no == 2) {	//제조사 검색 선택시
//				System.out.println("===제조사로 검색===");
//				ps.setString(1, "company");
//				ps.setString(2, "%"+inputName.trim()+"%");
//			}
			rs = ps.executeQuery();
			while( rs.next() ) {
				dto = new JavaDTO();
				dto.setNum(rs.getInt("num"));
				dto.setP_name(rs.getString("name"));
				dto.setCompany(rs.getString("company"));
				dto.setPrice(rs.getInt("price"));
				dto.setQty(rs.getInt("qty"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}//searchForName
	
	//제품번호로 제품 정보 조회
	ArrayList<JavaDTO> searchNum(int num) {
		connect();
		JavaDTO dto = null;
		ArrayList<JavaDTO> list = new ArrayList<JavaDTO>();
		String sql = "SELECT num, name, company, price, qty "
				+ "FROM tbl_java_product WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			while( rs.next() ) {
				dto = new JavaDTO();
				dto.setNum(rs.getInt("num"));
				dto.setName(rs.getString("name"));
				dto.setCompany(rs.getString("company"));
				dto.setPrice(rs.getInt("price"));
				dto.setQty(rs.getInt("qty"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}//searchNum
	
	//제조사 검색
	ArrayList<JavaDTO> searchCom(String inputName) {
		connect();
		JavaDTO dto = null;
		ArrayList<JavaDTO> list = new ArrayList<JavaDTO>();
		String sql = "SELECT num, name, company, price, qty "
				+ "FROM tbl_java_product WHERE company LIKE ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%"+inputName+"%");
			rs = ps.executeQuery();
			while( rs.next() ) {
				dto = new JavaDTO();
				dto.setNum(rs.getInt("num"));
				dto.setP_name(rs.getString("name"));
				dto.setCompany(rs.getString("company"));
				dto.setPrice(rs.getInt("price"));
				dto.setQty(rs.getInt("qty"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}//searchCom
	
	//유저 로그인시 소지금 조회
	int checkMoney(String inputId) {
		connect();
		JavaDTO dto = new JavaDTO();
		int money = 0;
		String sql = "SELECT name, money FROM tbl_java_user WHERE id = ? ";
		try {
			ps = conn.prepareStatement(sql);
//			ps.setString(1, "user1");
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
	
	//상품 주문
	void orderProduct(int num, int orderCount) {
		connect();
		String sql = "UPDATE tbl_java_product "
					+ "SET qty = qty - ? "
					+ "WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, orderCount);
			ps.setInt(2, num);
			rs = ps.executeQuery();
			System.out.println("...주문 완료");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}//orderProduct
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
	
	//주문상품의 가격 조회
	int returnPrice(int num) {
		connect();
//		JavaDTO dto = null;
		int price = 0;
		String sql = "SELECT price FROM tbl_java_product WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if( rs.next() ) {
				price = rs.getInt("price");
			}
//			dto = new JavaDTO();
//			dto.setPrice(rs.getInt("price"));
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return price;
	}//returnPrice
	//
	
	//제품 등록
	void insertProduct(int num, String name, String company, int price, int qty) {
		connect();
		String sql = "INSERT INTO tbl_java_product "
					+ "VALUES (?, ?, ?, ?, ?)";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			ps.setString(2, name);
			ps.setString(3, company);
			ps.setInt(4, price);
			ps.setInt(5, qty);
			rs = ps.executeQuery();
			System.out.println("[등록 완료]");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		
	}//insertProduct
	
	//상품 삭제
	void deleteProduct(int num) {
		connect();
		String sql = "DELETE FROM tbl_java_product "
					+ "WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			System.out.println("[삭제 완료]");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}//deleteProduct
	
	//수량 증가
	void increaseQty(int num, int incQty) {
		connect();
		String sql = "UPDATE tbl_java_product SET qty = qty + ? "
					+ "WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, incQty);
			ps.setInt(2, num);
			rs = ps.executeQuery();
			System.out.println("[수량 추가 완료]");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	//수량 감소
	void descQty(int num, int desQty) {
		connect();
		String sql = "UPDATE tbl_java_product SET qty = qty - ? "
					+ "WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, desQty);
			ps.setInt(2, num);
			rs = ps.executeQuery();
			System.out.println("[수량 감소 완료]");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	//수량 수정
	void updateQty(int num, int upQty) {
		connect();
		String sql = "UPDATE tbl_java_product SET qty = ? "
				+ "WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, upQty);
			ps.setInt(2, num);
			rs = ps.executeQuery();
			System.out.println("[수량 입력 완료]");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
	}
	
	//상품 수량만 조회
	int selectQty(int num) {
		connect();
		int qty = 0;
		String sql = "SELECT qty FROM tbl_java_product "
					+ "WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if( rs.next() ) {
				qty = rs.getInt("qty");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return qty;
	}//selectQty
	
	//상품 이름만 조회
	String selectPname(int num) {
		connect();
		String name = null;
		String sql = "SELECT name FROM tbl_java_product "
					+ "WHERE num = ? ";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, num);
			rs = ps.executeQuery();
			if( rs.next() ) {
				name = rs.getString("name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return name;
	}
	
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
	}
	
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
	ArrayList<JavaDTO> accountList() {
		connect();
		String sql = "SELECT id, pw, name, email, money "
					+ "FROM tbl_java_user ";
		ArrayList<JavaDTO> list = new ArrayList<JavaDTO>();
		try {
			Statement st = conn.createStatement();
			rs = st.executeQuery(sql);
			while( rs.next() ) {
				JavaDTO dto = new JavaDTO();
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
