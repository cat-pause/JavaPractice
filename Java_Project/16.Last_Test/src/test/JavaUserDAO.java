package test;

import java.sql.*;
import java.util.ArrayList;

public class JavaUserDAO {
	private Connection conn;
	private ResultSet rs;
	private PreparedStatement ps;
	
	//DB연결
	public void connect() {
		//드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@211.223.59.99:1521:xe","smart02","0000");
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("DB연결 성공");
	}//connect()
	
	public void disconnect() {
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
	
	public ArrayList<JavaDTO> pList() {
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
	
}//class
