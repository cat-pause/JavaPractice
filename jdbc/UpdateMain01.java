package ex01;

import java.sql.*;

public class UpdateMain01 {
	public static void main(String[] args) {
		// 100번 사원의 email, phone_number, salary 변경
		
		// 드라이버 로딩
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "hr", "0000"
					);
			String email = "king@naver.com";
			String phone_number = "010.1234.5678";
			int salary = 8000;
			int employee_id = 100;
			
//			String sql 
//					= "update employees "
//					+ "set email = '"+email+"', phone_number = '"+phone_number+"',"
//					+ "salary = " + salary + " "
//					+ "where employee_id = 100";
//			
			// 쿼리문을 미리 준비하는 기능을 가진 클래스 : PreparedStatement - Statement를 상속 
			String sql 
				= "update employees "
				+ "set email = ?, phone_number = ?, salary = ? "
				+ "where employee_id = ?";
			PreparedStatement ps =  conn.prepareStatement(sql);
			//준비된 문장의 ?에 값을 담는 처리
			ps.setString(1, email);
			ps.setString(2, phone_number);
			ps.setInt(3, salary);
			ps.setInt(4, employee_id);
			//쿼리문 실행
			// 1. select - executeQuery
			// 2. insert/update/delete - executeUpdate
			int count = ps.executeUpdate();	//update된 데이터행의 갯수가 반환됨
			if ( count>0 ) {
				System.out.println("변경 저장 완료");
			}
			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
}
