package ex02;

import java.util.ArrayList;

import employees.EmployeeDAO;
import employees.EmployeeDTO;

//사원 목록 화면
public class EmployeeList {
	public static void main(String[] args) {
		// DB에서 사원목록 정보 조회
		
		// 출력
		
		// DB 연결 처리할 클래스 : DAO(DataAccessObject)
		EmployeeDAO dao = new EmployeeDAO();
		ArrayList<EmployeeDTO> list = 
		dao.employee_list();
		System.out.println("--------------------------------------------------");
		System.out.printf("사번\t 성명\t 부서\t 업무\t 급여\t 입사일\n");
		System.out.println("--------------------------------------------------");
		
		for(EmployeeDTO dto : list) {
			System.out.println( 
					dto.getEmployee_id() + "\t" +
					dto.getName() + "\t" +
					dto.getDepartment_name() + "\t" +
					dto.getJob_title() + "\t" +
					dto.getSalary() + "\t" +
					dto.getHire_date()
			);
		}
		
	}//main()
}
