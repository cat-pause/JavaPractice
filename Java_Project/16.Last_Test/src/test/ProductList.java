package test;

import java.util.*;

public class ProductList {
	private Scanner sc;
	ProductList(Scanner sc){
		this.sc = sc;
	}
	
	public void display() {
		JavaDAO dao = new JavaDAO();
		ArrayList<JavaDTO> list = dao.pList();
		System.out.println("=============================================================");
		System.out.println("\t\t\t물건 목록");
		System.out.println("=============================================================");
		System.out.println("상품번호\t상품명\t\t제조사\t가격\t수량");
		System.out.println("-------------------------------------------------------------");
		for( JavaDTO dto : list ) {
			System.out.print(dto.getNum() + "\t");
			System.out.print(dto.getP_name() + "\t");
			System.out.print(dto.getCompany() + "\t");
			System.out.print(dto.getPrice() + "\t");
			System.out.print(dto.getQty() + "\n");
		}
		System.out.println("=============================================================");
	}//display
	
	
}//class
