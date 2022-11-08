package test;

import java.util.Scanner;

public class UserMode {
	private Scanner sc;
	UserMode(Scanner sc){
		this.sc = sc;
	}
	
	public void display(String inputId) {
		
		System.out.println("-+-+-+-+-+-+-+-+-+-+-");
		System.out.println("   " + inputId + "님, 환영합니다");
		System.out.println("-+-+-+-+-+-+-+-+-+-+-");
		JavaDAO dao = null;
		int inputKey = 0;
		do {
			View.product_List.display();
			int money = View.userM.displayMoney(inputId);
			System.out.println("--------------");
			System.out.println("소지금 : " + money);
			System.out.println("--------------");
			System.out.println("1.금액충전   2.상품주문   3.로그아웃");
			System.out.print("입력 : ");
			inputKey = sc.nextInt();
			if( inputKey == 1 ) {
				//금액충전
				System.out.print("충전할 금액 : ");
				int addMoney = sc.nextInt();
				dao = new JavaDAO();
				dao.chargeMoney(inputId, addMoney);
			}else if( inputKey == 2 ) {
				//상품주문
				//상품 번호 입력
				System.out.print("제품번호 : ");
				int num = sc.nextInt();
				View.pInfo.displayFromNum(num, money, inputId);
			}else if( inputKey == 3 ) {
				//로그아웃
				View.login.display();
				break;
			}
		} while(inputKey != 0);
		
	}//display()
}
