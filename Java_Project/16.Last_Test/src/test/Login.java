package test;

import java.util.Scanner;

public class Login {
	private Scanner sc;

	Login(Scanner sc) {
		this.sc = sc;
	}
	
	public void display() {
		JavaDAO dao = new JavaDAO();
		int menu = 0;
		do {
			System.out.println("=================");
			System.out.println("1.로그인   2.회원가입");
			System.out.println("=================");
			System.out.print("입력 : ");
			menu = sc.nextInt();
			sc.nextLine();
			if( menu == 1 ) {
				System.out.println("<< 로그인 >>");
				System.out.print("아이디 : ");
				String inputId = sc.next();
				System.out.print("비밀번호 : ");
				String inputPw = sc.next();
				int result = dao.idpwCheck(inputId, inputPw);
				if( result == 2 ) {	
					System.out.println("-+-+- 관리자 로그인 -+-+-");
					View.adminmode.display();
				}else if( result == 3 ) {
					System.out.println("- 사용자 로그인 -");
					View.usermode.display(inputId);
				}else if( result == 0 ) {
					System.out.println("로그인 실패");
				}
			}else if( menu == 2 ) {
				System.out.println("<< 회원가입 >>");
				do {
					System.out.print("아이디 : ");
					String joinId = sc.next();
					int resultId = dao.mulIdCheck(joinId);
					if( resultId == 0 ) {
						System.out.println("중복된 아이디가 있습니다.");
						continue;
					}
					System.out.print("비밀번호 : ");
					String joinPw = sc.next();
					System.out.print("관리자 인증 : ");
					String checkAdmin = sc.next();
					String joinAdmin = null;
					if( checkAdmin == "admin") {	//관리자 인증. 시간남으면 수정
						System.out.println("[관리자 계정으로 진행]");
						joinAdmin = "Y";
					}else {
						System.out.println("[사용자 계정으로 진행]");
						joinAdmin = "N";
					}
					dao.newAccount(joinId, joinPw, joinAdmin);
					break;
				}while(menu != 0);
			}
		}while(menu != 0);
		
	}//display()
	
}//class
