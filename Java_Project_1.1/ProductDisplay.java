package test;

import java.util.*;

public class ProductDisplay {
	private Scanner sc;
	ProductDisplay(Scanner sc){
		this.sc = sc;
	}
	
	public void display() {
		ProductDAO dao = new ProductDAO();
		ArrayList<ProductDTO> list = dao.pList();
		System.out.println("===========================================");
		System.out.println("\t\t 상품 목록");
		System.out.println("===========================================");
		System.out.println("상품번호\t상품명\t\t제조사\t가격\t수량");
		System.out.println("-------------------------------------------");
		for( ProductDTO dto : list ) {
			System.out.print(dto.getNum() + "\t");
			System.out.print(dto.getP_name() + "\t");
			System.out.print(dto.getCompany() + "\t");
			System.out.print(dto.getPrice() + "\t");
			System.out.print(dto.getQty() + "\n");
		}
		System.out.println("===========================================");
	}//display
	
	void displayFromNum(int num, int money, String inputId) {
		ProductDAO dao = new ProductDAO();
		ArrayList<ProductDTO> list = dao.searchNum(num);
		System.out.println("[ 제품 정보 ]");
		
		System.out.println("-----------------------");
		for( ProductDTO dto : list ) {
			System.out.println("제품번호 : " + dto.getNum());
			System.out.println("제품명 : " + dto.getP_name());
			System.out.println("제조사 : " + dto.getCompany());
			System.out.println("제품가격 : " + dto.getPrice());
			System.out.println("제품수량 : " + dto.getQty());
			System.out.println("-----------------------");
			if(dto.getQty() == 0) {
				System.out.println("[해당 제품은 품절되었습니다]");
			}else {
				System.out.println("1.주문하기   2.돌아가기");
				System.out.print("입력 : ");
				int menu = sc.nextInt();
				if( menu == 1 ) {
					View.uDisplay.orderDisplay(num, money, dto.getQty(), inputId);
					break;
				}else if( menu == 2 ) {
					//돌아가기
				}
			}
		}//for
	}//
	
	void displayName(String inputName) {
		
		ProductDAO dao = new ProductDAO();
		ArrayList<ProductDTO> list = dao.searchProduct(inputName);
		System.out.println("[ 검색 결과 ]");
		System.out.println("-----------------------");
		for( ProductDTO dto : list ) {
			System.out.println("제품번호 : " + dto.getNum());
			System.out.println("제품명 : " + dto.getP_name());
			System.out.println("제조사 : " + dto.getCompany());
			System.out.println("제품가격 : " + dto.getPrice());
			System.out.println("제품수량 : " + dto.getQty());
			System.out.println("-----------------------");
		}
		System.out.println("1.돌아가기   0.종료");
		System.out.print("입력 : ");
		int menu = sc.nextInt();
		if( menu == 1 ) {
			//돌아가기
		}else if( menu == 0 ) {
			System.out.println("프로그램 종료...");
			System.exit(0);
		}
	}//display
	void displayCom(String inputName) {
		ProductDAO dao = new ProductDAO();
		ArrayList<ProductDTO> list = dao.searchCom(inputName);
		System.out.println("[ 검색 결과 ]");
		System.out.println("-----------------------");
		for( ProductDTO dto : list ) {
			System.out.println("제품번호 : " + dto.getNum());
			System.out.println("제품명 : " + dto.getP_name());
			System.out.println("제조사 : " + dto.getCompany());
			System.out.println("제품가격 : " + dto.getPrice());
			System.out.println("제품수량 : " + dto.getQty());
			System.out.println("-----------------------");
		}
		System.out.println("1.돌아가기   0.종료");
		System.out.print("입력 : ");
		int menu = sc.nextInt();
		if( menu == 1 ) {
			//돌아가기
		}else if( menu == 0 ) {
			System.out.println("프로그램 종료...");
			System.exit(0);
		}
	}//display
	
}//class
