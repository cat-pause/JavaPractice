package employees;

import java.sql.*;

import java.util.ArrayList;

public class EmployeeDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	//DB연결처리
	public void connect() {
		try {
			//드라이버 로딩
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection(
					"jdbc:oracle:thin:@localhost:1521:xe", "hr", "0000");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	} //connect()
	//자원회수 처리
	public void disconnect() {
		if( rs!=null ) 
			try { rs.close(); } catch (Exception e) {e.printStackTrace();}
		if( ps!=null )
			try {ps.close();} catch (Exception e) {e.printStackTrace();}
		if( conn!=null)
			try {conn.close();} catch (Exception e) {e.printStackTrace();}
	} //disconnect()
	//사원목록조회
	public ArrayList<EmployeeDTO> employee_list() {
		//107명의 정보를 담을 변수
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		connect();
		String sql = "SELECT e.employee_id, e.first_name||' '||e.last_name name, "
				+ "job_title, e.salary, department_name, e.hire_date, "
				+ "e.email, e.phone_number, "
				+ "m.employee_id manager_eId, m.first_name||' '||m.last_name manager_name "
				+ "FROM employees e FULL OUTER JOIN jobs j "
				+ "ON e.job_id = j.job_id "
				+ "LEFT OUTER JOIN departments d "
				+ "ON e.department_id = d.department_id "
				+ "LEFT OUTER JOIN employees m "
				+ "ON e.manager_id = m.employee_id ";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while(rs.next()) {	//커서가 이동되면 (데이터가 있으면)
				//107명의 사원정보를 담기
				EmployeeDTO dto = new EmployeeDTO();
				dto.setEmployee_id( rs.getInt("employee_id") );
				dto.setName(rs.getString("name"));
				dto.setJob_title(rs.getString("job_title"));
				dto.setSalary(rs.getInt("salary"));
				dto.setDepartment_name(rs.getString("department_name"));
				dto.setHire_date(rs.getDate("hire_date"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone_number(rs.getString("phone_number"));
				dto.setManager_eId(rs.getInt("manager_eId"));
				dto.setManager_name(rs.getString("manager_name"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return list;
	}// _list()
	
	//특정 사번의 사원정보 조회
	public EmployeeDTO employee_info(int emp_id) {
		EmployeeDTO dto = null;
		connect();
		String sql = "SELECT e.employee_id, e.first_name||' '||e.last_name name, "
				+ "job_title, e.salary, department_name, e.hire_date, "
				+ "e.email, e.phone_number, "
				+ "m.employee_id manager_eId, m.first_name||' '||m.last_name manager_name "
				+ "FROM employees e FULL OUTER JOIN jobs j "
				+ "ON e.job_id = j.job_id "
				+ "LEFT OUTER JOIN departments d "
				+ "ON e.department_id = d.department_id "
				+ "LEFT OUTER JOIN employees m "
				+ "ON e.manager_id = m.employee_id "
				+ "WHERE e.employee_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			//쿼리문에 ?가 있는지 확인
			ps.setInt(1, emp_id);
			rs = ps.executeQuery();
			if( rs.next() ) {
				dto = new EmployeeDTO();
				dto.setEmployee_id( rs.getInt("employee_id") );
				dto.setName(rs.getString("name"));
				dto.setSalary(rs.getInt("salary"));
				dto.setJob_title(rs.getString("job_title"));
				dto.setDepartment_name(rs.getString("department_name"));
				dto.setHire_date(rs.getDate("hire_date"));
				dto.setEmail(rs.getString("email"));
				dto.setPhone_number(rs.getString("phone_number"));
				dto.setManager_eId(rs.getInt("manager_eId"));
				dto.setManager_name(rs.getString("manager_name"));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return dto;
	}// _info()
	
	// 특정 부서의 사원만 조회
	public ArrayList<EmployeeDTO> ed_info(int dept_id) {
		ArrayList<EmployeeDTO> list = new ArrayList<EmployeeDTO>();
		connect();
		EmployeeDTO dto = null;
		String sql = "SELECT department_name, employee_id,"
					+ "first_name||' '||last_name name, "
					+ "job_title, salary, hire_date "
					+ "FROM employees e LEFT OUTER JOIN departments d "
					+ "ON e.department_id = d.department_id "
					+ "INNER JOIN jobs j "
					+ "ON e.job_id = j.job_id "
					+ "AND d.department_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dept_id);
			rs = ps.executeQuery();
			while( rs.next() ) {
				dto = new EmployeeDTO();
				dto.setDepartment_name(rs.getString("department_name"));
				dto.setEmployee_id(rs.getInt("employee_id"));
				dto.setName(rs.getString("name"));
				dto.setJob_title(rs.getString("job_title"));
				dto.setSalary(rs.getInt("salary"));
				dto.setHire_date(rs.getDate("hire_date"));
				list.add(dto);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}//
	
	
}//class
