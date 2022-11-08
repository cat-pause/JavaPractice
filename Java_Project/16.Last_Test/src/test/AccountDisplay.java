package test;

import java.util.*;

public class AccountDisplay {
	private Scanner sc;
	AccountDisplay(Scanner sc){
		this.sc = sc;
	}
	
	//계정 목록
	void displayAccount() {
		JavaDAO dao = new JavaDAO();
		ArrayList<JavaDTO> list = dao.accountList();
		System.out.println("==============================================");
		System.out.println("\t\t계정 목록");
		System.out.println("==============================================");
		System.out.println("ID \tPW \tNAME\tEmail\t\tMoney");
		System.out.println("----------------------------------------------");
		for( JavaDTO dto : list ) {
			System.out.print(dto.getId() + "\t");
			System.out.print(dto.getPw() + "\t");
			System.out.print(dto.getName() + "\t");
			System.out.print(dto.getEmail() + "\t");
			System.out.print(dto.getMoney() + "\n");
		}
		System.out.println("==============================================");
	}
}//class


