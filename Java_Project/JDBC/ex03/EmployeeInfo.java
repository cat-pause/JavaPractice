package ex03;

import java.util.Scanner;

import employees.EmployeeDAO;
import employees.EmployeeDTO;

public class EmployeeInfo {
	private Scanner sc;
	public EmployeeInfo(Scanner sc) {
		this.sc = sc;
	}
	public void display(int employee_id) {
		System.out.println("<< 사원정보 >>");
		//DB에서 해당 사원정보를 가져오기
		EmployeeDAO dao = new EmployeeDAO();
		EmployeeDTO dto = dao.employee_info(employee_id);
		//화면에 출력
		if( dto == null ) {
			System.out.println("사원 정보가 없습니다");
		}else {
			System.out.printf("사번 [%d] 사원정보\n", employee_id);
			System.out.println("사번 : "+dto.getEmployee_id()); 
			System.out.println("성명 : "+dto.getName()); 
			System.out.println("부서 : "+dto.getDepartment_name()); 
			System.out.println("업무 : "+dto.getJob_title()); 
			System.out.println("급여 : "+dto.getSalary()); 
			System.out.println("입사일 : "+dto.getHire_date()); 
			System.out.println("이메일 : "+dto.getEmail());
			System.out.println("전화번호 : "+dto.getPhone_number());
			System.out.println("상급자 사번 : "+dto.getManager_eId());
			if( dto.getManager_name().equals(" ") ) 
				dto.setManager_name("상급자 없음");
			System.out.println("상급자 성명 : "+dto.getManager_name());
		}
		System.out.println("==================================");
		System.out.println("1.목록으로   0.종료");
		System.out.print("입력 : ");
		int menu = sc.nextInt();
		if( menu == 1 ) {
			EmployeeList list = new EmployeeList(sc);
			list.display();
		}else {
			System.exit(0);
		}
	}
}
