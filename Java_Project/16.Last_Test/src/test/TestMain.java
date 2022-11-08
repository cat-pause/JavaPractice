package test;

import java.util.Scanner;

public class TestMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		View.login = new Login(sc);
		View.product_List = new ProductList(sc);
		View.adminmode = new AdminMode(sc);
		View.usermode = new UserMode(sc);
		View.pInfo = new ProductInfo(sc);
		View.userM = new UserMoney(sc);
		View.accountD = new AccountDisplay(sc);
		
		View.login.display();
		sc.close();
	}//main()
}//class
