package view;

import java.sql.*;
import java.util.*;

public class DeptDAO {
	private Connection conn;
	private PreparedStatement ps;
	private ResultSet rs;
	
	//DB연결
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
	//자원회수
	public void disconnect() {
		if( rs!=null ) 
			try { rs.close(); } catch (Exception e) {e.printStackTrace();}
		if( ps!=null )
			try {ps.close();} catch (Exception e) {e.printStackTrace();}
		if( conn!=null)
			try {conn.close();} catch (Exception e) {e.printStackTrace();}
	} //disconnect()
	
	//부서목록 조회
	public ArrayList<DeptDTO> DeptList() {
		ArrayList<DeptDTO> dList = new ArrayList<DeptDTO>();
		connect();
		String sql = "SELECT * FROM departments WHERE manager_id IS NOT NULL";
		try {
			ps = conn.prepareStatement(sql);
			rs = ps.executeQuery();
			while( rs.next() ) {
				DeptDTO dto = new DeptDTO();
				dto.setDepartment_id( rs.getInt("department_id"));
				dto.setDepartment_name( rs.getString("department_name"));
				dto.setManager_id( rs.getInt("manager_id"));
				dto.setLocation_id( rs.getInt("location_id"));
				dList.add(dto);
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return dList;
	}//DeptList
	
	//특정 부서 정보 조회
	DeptDTO DeptInfo(int dept_id) {
		DeptDTO dto = null;
		connect();
		String sql = "SELECT d.department_id, department_name, "
					+ "first_name||' '||last_name manager_name, "
					+ "city "
					+ "FROM departments d INNER JOIN employees e "
					+ "ON d.manager_id = e.employee_id "
					+ "INNER JOIN locations l "
					+ "ON d.location_id = l.location_id "
					+ "AND d.department_id = ?";
		try {
			ps = conn.prepareStatement(sql);
			ps.setInt(1, dept_id);
			rs = ps.executeQuery();
			if( rs.next() ) {
				dto = new DeptDTO();
				dto.setDepartment_id(rs.getInt("department_id"));
				dto.setDepartment_name(rs.getString("department_name"));
				dto.setManager_name(rs.getString("manager_name"));
				dto.setCity(rs.getString("city"));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			disconnect();
		}
		return dto;
	}//DeptInfo
	
}//class






