package test;

import java.util.ArrayList;
import java.util.Scanner;

public class ProductInfo {
	private Scanner sc;
	
	ProductInfo(Scanner sc){
		this.sc = sc;
	}
	
	void displayFromNum(int num, int money, String inputId) {
		JavaDAO dao = new JavaDAO();
		ArrayList<JavaDTO> list = dao.searchNum(num);
		System.out.println("[ 제품 정보 ]");
		
		System.out.println("-----------------------");
		for( JavaDTO dto : list ) {
			System.out.println("제품번호 : " + dto.getNum());
			System.out.println("제품명 : " + dto.getName());
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
					View.userM.orderDisplay(num, money, dto.getQty(), inputId);
					break;
				}else if( menu == 2 ) {
					//돌아가기
				}
			}
		}//for
	}//
	
	void display(String inputName) {
		
		JavaDAO dao = new JavaDAO();
		ArrayList<JavaDTO> list = dao.searchProduct(inputName);
		System.out.println("[ 검색 결과 ]");
		System.out.println("-----------------------");
		for( JavaDTO dto : list ) {
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
		
		JavaDAO dao = new JavaDAO();
		ArrayList<JavaDTO> list = dao.searchCom(inputName);
		System.out.println("[ 검색 결과 ]");
		System.out.println("-----------------------");
		for( JavaDTO dto : list ) {
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
}
