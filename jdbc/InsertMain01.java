package ex01;

import java.sql.*;
import java.util.Scanner;

public class InsertMain01 {
	public static void main(String[] args) {
		// 새로운 사원 등록 (사번, 이름, 이메일, 업무코드, 부서코드, 입사일(오늘날짜), 급여)
		// 키보드로 입력
		Scanner sc = new Scanner(System.in);
		
		try {
			// 드라이브 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// DB 연결
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe","hr","0000");
			System.out.println("<< 사원 등록 >>");
			
			System.out.print("성 : ");
			String fname = sc.next();
			System.out.print("이름 : ");
			String lname = sc.next();
			System.out.print("이메일 : ");
			String email = sc.next();
			System.out.print("업무 코드 : ");
			String job_id = sc.next();
			System.out.print("부서 코드 : ");
			int dept_id = sc.nextInt();
			System.out.print("급여 : ");
			int salary = sc.nextInt();
			
			String sql = "insert into employees ("
						+ "employee_id, first_name, last_name, email,"
						+ "job_id, department_id, salary) "
						+ "VALUES (employees_seq.nextval, "
						+ "?, ?, ?, upper(?), ?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, fname);
			ps.setString(2, lname);
			ps.setString(3, email);
			ps.setString(4, job_id);
			ps.setInt(5, dept_id);
			ps.setInt(6, salary);
			
			int count = ps.executeUpdate();
			if( count > 0 ) {
				System.out.println("데이터 삽입 완료");
			}
			
			sc.close();
			conn.close();
			ps.close();
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
