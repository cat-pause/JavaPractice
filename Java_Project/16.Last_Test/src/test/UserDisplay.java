package test;

import java.util.*;

public class UserDisplay {
	private Scanner sc;
	UserDisplay(Scanner sc){
		this.sc = sc;
	}

	int displayMoney(String inputId) {
		UserDAO dao = new UserDAO();
//		System.out.println("소지금 : " + dao.checkMoney(inputId).getMoney());
		return dao.checkMoney(inputId);
	}//display()
	
	void orderDisplay(int num, int money, int orderCount, String inputId) {
		UserDAO dao = new UserDAO();
		ProductDAO pdao = new ProductDAO();
		int price = pdao.returnPrice(num);
		
		System.out.print("주문할 상품 갯수 : ");
		int count = sc.nextInt();
		if( orderCount < count ) {
			System.out.println("재고가 부족합니다");
		}else if( (price*count) > money ) {
			System.out.println("잔액이 부족합니다");
		}else {
			//상품 주문
			System.out.println("----------------");
			System.out.printf("총 금액 : %d원\n", (price*count));
			System.out.println("(1.결제   2.취소)");
			System.out.println("----------------");
			System.out.print("입력 : ");
			int menu = sc.nextInt();
			sc.nextLine();
			if( menu == 1 ) {
				pdao.orderProduct(num, count);
				dao.decsMoney((price * count), inputId);
			}else if( menu == 2 ) {
				System.out.println("\n결제 취소\n");
			}
		}
	}//orderProduct
	
}//class
