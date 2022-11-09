package test;

import java.util.*;

public class AccountDisplay {
	
	//계정 목록
	void displayAccount() {
		UserDAO dao = new UserDAO();
		ArrayList<UserDTO> list = dao.accountList();
		System.out.println("==============================================");
		System.out.println("\t\t계정 목록");
		System.out.println("==============================================");
		System.out.println("ID \tPW \tNAME\tEmail\t\tMoney");
		System.out.println("----------------------------------------------");
		for( UserDTO dto : list ) {
			System.out.print(dto.getId() + "\t");
			System.out.print(dto.getPw() + "\t");
			System.out.print(dto.getName() + "\t");
			System.out.print(dto.getEmail() + "\t");
			System.out.print(dto.getMoney() + "\n");
		}
		System.out.println("==============================================");
	}//displayAccount
	
}//class


