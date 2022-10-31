package ex01;

import java.sql.*;
// Cartesian product
public class SelectMain02 {
	public static void main(String[] args) {
		try {
			//드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			//DB 연결
			Connection conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "hr", "0000");
			//쿼리문 작성 : 부서가 60번인 사원 정보(사번,성명,업무코드,업무명,급여,입사일,부서명) 조회
			String sql = "select employee_id, first_name||' '||last_name name"
						+ ", j.job_id, job_title, salary SAL, hire_date "
						+ "FROM employees e, jobs j "
						+ "WHERE e.job_id = j.job_id AND department_id = 60";
			//쿼리문 실행 : Statement
			Statement st = conn.createStatement();
			ResultSet rs = st.executeQuery(sql);
			while( rs.next() ) {
				int emp_id = rs.getInt("employee_id");
				String name = rs.getString("name");
				String job_id = rs.getString("job_id");
				String job_title = rs.getString("job_title");
				int salary = rs.getInt("SAL");
				Date hire_date = rs.getDate("hire_date");
				
				System.out.printf(
						"%d %s %s %s %d %s\n",
						emp_id, name, job_id, job_title, salary, hire_date
						);
			}
			conn.close();
			rs.close();
			st.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
