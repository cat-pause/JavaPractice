package ex01;

import java.sql.*;

public class SelectMain01 {
	public static void main(String[] args) {
		
		try {
			// 드라이버 연결
			// 1. 드라이버 생성 : 드라이버 관리자가 생성된다
			Class.forName("oracle.jdbc.driver.OracleDriver");
			System.out.println("드라이버 로딩 완료!");
			
			// DB연결
			// 2. DB위치 지정 - 드라이버 관리자를 통해
			//localhost=127.0.0.x=192.138.0.x
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe"
					, "hr", "0000");
			System.out.println("DB 연결 성공!");
			
			// 쿼리문 실행
			// 3. 쿼리문 작성
			String sql = "select * from employees";
			// 3-1. 쿼리문 실행 : ctrl + enter
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql); 
			while( rs.next() ) {	//커서가 이동되는지 확인(데이터 유무 확인)
				int employee_id = rs.getInt("employee_id"); // 사번
				String name = rs.getString("last_name")+" "
								+rs.getString("first_name");
				int dept_id = rs.getInt("department_id");
				Date hire_date = rs.getDate("hire_date");
				System.out.printf("%d %s %d %s\n", employee_id, name, dept_id,hire_date);
			}
			
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
