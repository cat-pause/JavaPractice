package test;

import java.util.*;

public class UserMoney {
	private Scanner sc;
	UserMoney(Scanner sc){
		this.sc = sc;
	}

	int displayMoney(String inputId) {
		JavaDAO dao = new JavaDAO();
//		System.out.println("소지금 : " + dao.checkMoney(inputId).getMoney());
		return dao.checkMoney(inputId);
	}//display()
	
	void orderDisplay(int num, int money, int orderCount, String inputId) {
		JavaDAO dao = new JavaDAO();
		int price = dao.returnPrice(num);
		
		System.out.print("주문할 상품 갯수 : ");
		int count = sc.nextInt();
		if( orderCount < count ) {
			System.out.println("재고가 부족합니다");
		}else if( (price*count) > money ) {
			System.out.println("잔액이 부족합니다");
		}else {
			//상품 주문
			dao.orderProduct(num, count);
			dao.decsMoney((price * count), inputId);
		}
	}//orderProduct
	
}//class
