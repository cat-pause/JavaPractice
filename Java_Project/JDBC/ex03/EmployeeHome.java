package ex03;

import java.util.Scanner;

public class EmployeeHome {
	private Scanner sc;
	public EmployeeHome(Scanner sc) {
		this.sc = sc;
		System.out.println("== EmployeeHome ==");
		System.out.println("1.사원목록   2.부서목록   0.종료");
		System.out.print("입력 : ");
		int menu = sc.nextInt();
		if( menu == 1 ) {
			//사원목록
			EmployeeList list = new EmployeeList(sc);
			list.display();
		}else if( menu == 2 ) {
			//부서목록
			
		}else if ( menu == 0 ) {
			System.out.println("프로그램 종료...");
		}
	}
	
}
