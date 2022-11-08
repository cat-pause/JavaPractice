package test;

import java.util.Scanner;

public class AdminMode {
	private Scanner sc;
	AdminMode(Scanner sc){
		this.sc = sc;
	}
	
	public void display() {
		JavaDAO dao = new JavaDAO();
		int menu = 0;
		do {
			View.product_List.display();
			System.out.println("-------------------------------------------------------------");
			System.out.println("1.상품검색   2.상품추가   3.상품삭제");
			System.out.println("4.재고관리   5.로그아웃   6.계정관리");
			System.out.println("0.종료");
			System.out.print("입력 : ");
			menu = sc.nextInt();
			if( menu == 1 ) {
				//상품 검색
				String inputName = null;
				System.out.println("1.이름 검색   2.제조사 검색");
				System.out.print("입력 : ");
				int menu1 = sc.nextInt();
				if( menu1 == 1 ) {
					//이름으로 검색
					System.out.print("제품명 : ");
					inputName = sc.next();
					View.pInfo.display(inputName);
				}else if( menu1 == 2 ) {
					//제조사로 검색
					System.out.print("제조사 : ");
					inputName = sc.next();
					View.pInfo.displayCom(inputName);
				}
			}else if( menu == 2 ) {
				//상품 추가
				System.out.println("===================");
				System.out.println("\t상품추가");
				System.out.println("===================");
				System.out.print("제품번호 : ");
				int num = sc.nextInt();
				sc.nextLine();
				System.out.print("제품명 : ");
				String name = sc.nextLine();
				System.out.print("제조사 : ");
				String company = sc.nextLine();
				System.out.print("가격 : ");
				int price = sc.nextInt();
				sc.nextLine();
				System.out.print("수량 : ");
				int qty = sc.nextInt();
				sc.nextLine();
				dao.insertProduct(num, name, company, price, qty);
			}else if( menu == 3 ) {
				//상품 삭제
				System.out.print("삭제할 제품번호 : ");
				int num = sc.nextInt();
				sc.nextLine();
				System.out.printf("[%s]를 삭제하시겠습니까?\n", dao.selectPname(num));
				System.out.println("(1.yes   2.no)");
				System.out.print("입력 : ");
				int checknum = sc.nextInt();
				if( checknum == 1 ) {
					dao.deleteProduct(num);
				}
			}else if( menu == 4 ) {
				//재고 관리
				System.out.print("관리할 제품번호 : ");
				int num = sc.nextInt();
				System.out.println("---------------------------");
				System.out.println("제품명 : " + dao.selectPname(num));
				System.out.println("수량 : " + dao.selectQty(num));
				sc.nextLine();
				System.out.println("---------------------------");
				System.out.println("1.추가   2.감소   3.수정");
				System.out.println("---------------------------");
				System.out.print("입력 : ");
				int menu2 = sc.nextInt();
				if( menu2 == 1 ) {
					//수량 추가
					System.out.print("추가 수량 : ");
					int incQty = sc.nextInt();
					dao.increaseQty(num, incQty);
				}else if( menu2 == 2 ) {
					//수량 감소
					System.out.print("감소 수량 : ");
					int desQty = sc.nextInt();
					dao.descQty(num, desQty);
				}else if( menu2 == 3 ) {
					//수량 수정
					System.out.print("수량 입력 : ");
					int upQty = sc.nextInt();
					dao.updateQty(num, upQty);
				}
			}else if ( menu == 5 ) {
				//로그아웃
				View.login.display();
				break;
			}else if( menu == 6 ) {
				//계정관리
				View.accountD.displayAccount();
				System.out.println("1.삭제   2.금액추가   3.돌아가기");
				System.out.print("입력 : ");
				int no = sc.nextInt();
				if( no == 1 ) {
					System.out.print("삭제할 계정 ID : ");
					String delId = sc.next();
					System.out.printf("%s의 계정을 삭제하시겠습니까?\n", delId);
					System.out.println("(1.yes   2.no)");
					System.out.print("입력 : ");
					int checkNum = sc.nextInt();
					if( checkNum == 1 ) {
						dao.deleteAccount(delId);
					}else {
						
					}
				}else if(no == 2) {
					System.out.print("금액추가할 계정 ID : ");
					String incId = sc.next();
					System.out.print("추가할 금액 : ");
					int incMoney = sc.nextInt();
					dao.chargeMoney(incId, incMoney);
				}
			}else {
				System.out.println(" ! 입력한 값이 잘못되었습니다 !");
			}
		}while(menu != 0);
		
	}// display()
}
