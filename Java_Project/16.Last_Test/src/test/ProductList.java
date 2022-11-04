package test;

import java.util.*;

public class ProductList {
	private Scanner sc;
	ProductList(Scanner sc){
		this.sc = sc;
	}
	
	public void display() {
		JavaUserDAO dao = new JavaUserDAO();
		ArrayList<JavaDTO> list = dao.pList();
		System.out.println("[물건 목록]");
		for( JavaDTO dto : list ) {
			System.out.println(dto.getNum());
			System.out.println(dto.getP_name());
			System.out.println(dto.getCompany());
			System.out.println(dto.getPrice());
			System.out.println(dto.getQty());
		}
	}//display
	
	
}//class
