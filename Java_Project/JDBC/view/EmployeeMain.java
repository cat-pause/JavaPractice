package view;

import java.util.Scanner;

public class EmployeeMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		View.home = new EmployeeHome(sc);
		View.list = new EmployeeList(sc);
		View.info = new EmployeeInfo(sc);
		View.dList = new DeptList(sc);
		View.dInfo = new DeptInfo(sc);
		
		View.home.display();
		sc.close();
	}//main
}//class
