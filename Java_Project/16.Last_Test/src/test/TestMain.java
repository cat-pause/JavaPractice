package test;

import java.util.Scanner;

public class TestMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		View.login = new Login(sc);
		
		View.login.display();
		sc.close();
	}//main()
}//class
