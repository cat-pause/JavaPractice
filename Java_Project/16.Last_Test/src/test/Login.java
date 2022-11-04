package test;

import java.util.Scanner;

public class Login {
	private Scanner sc;

	Login(Scanner sc) {
		this.sc = sc;
	}
	
	public void display() {
		JavaUserDAO dao = new JavaUserDAO();
		System.out.println("<< 로그인 >>");
		System.out.print("아이디 : ");
		String inputId = sc.next();
		System.out.print("비밀번호 : ");
		String inputPw = sc.next();
		int result = dao.idpwCheck(inputId, inputPw);
		if( result == 2 ) {	
			System.out.println("- 관리자 로그인 -");
			View.product_List.display();
		}else if( result == 3 ) {
			System.out.println("- 사용자 로그인 -");
		}else if( result == 0 ) {
			System.out.println("로그인 실패");
		}
		
	}//display()
	
}//class
