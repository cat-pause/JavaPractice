package test;

import java.util.Scanner;

public class AdminMode {
	private Scanner sc;
	AdminMode(Scanner sc){
		this.sc = sc;
	}
	
	public void display(String inputId) {
		UserDAO dao = new UserDAO();
		ProductDAO pdao = new ProductDAO();
		int menu = 0;
		do {
			View.pDisplay.display();
			System.out.println("----------------------------");
			System.out.println("1.제품검색   2.제품추가   3.제품삭제");
			System.out.println("4.제품관리   5.로그아웃   6.계정관리");
			System.out.println("7.게시글관리  0.종료");
			System.out.println("----------------------------");
			System.out.print("입력 : ");
			menu = sc.nextInt();
			if( menu == 1 ) {
				//상품 검색
				System.out.println("-----------");
				System.out.println(" 1.제품 검색");
				System.out.println("-----------");
				String inputName = null;
				System.out.println("1.이름 검색   2.제조사 검색");
				System.out.print("입력 : ");
				int menu1 = sc.nextInt();
				if( menu1 == 1 ) {
					//이름으로 검색
					System.out.print("제품명 : ");
					inputName = sc.next();
					View.pDisplay.displayName(inputName);
				}else if( menu1 == 2 ) {
					//제조사로 검색
					System.out.print("제조사 : ");
					inputName = sc.next();
					View.pDisplay.displayCom(inputName);
				}
			}else if( menu == 2 ) {
				//상품 추가
				System.out.println("-----------");
				System.out.println(" 2.제품 추가");
				System.out.println("-----------");
				System.out.println("===================");
				System.out.println("\t제품추가");
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
				pdao.insertProduct(num, name, company, price, qty);
			}else if( menu == 3 ) {
				//상품 삭제
				System.out.println("-----------");
				System.out.println(" 3.제품 삭제");
				System.out.println("-----------");
				System.out.print("삭제할 제품번호 : ");
				int num = sc.nextInt();
				sc.nextLine();
				System.out.printf("[%s]를 삭제하시겠습니까?\n", pdao.selectPname(num));
				System.out.println("(1.yes   2.no)");
				System.out.print("입력 : ");
				int checknum = sc.nextInt();
				if( checknum == 1 ) {
					pdao.deleteProduct(num);
				}
			}else if( menu == 4 ) {
				//재고 관리 ==> 제품 관리
				System.out.println("-----------");
				System.out.println(" 4.제품 관리");
				System.out.println("-----------");
				System.out.println("1.이름수정   2.가격수정   3.재고관리");
				System.out.print("입력 : ");
				int pmenu = sc.nextInt();
				sc.nextLine();
				if( pmenu == 1 ) {
					//이름 수정
					System.out.print("수정할 제품번호 : ");
					int num = sc.nextInt();
					sc.nextLine();
					System.out.printf("%s  ->  ", pdao.selectPname(num));
					String newName = sc.next();
					pdao.changeName(num, newName);
				}else if( pmenu == 2 ) {
					//가격 수정
					System.out.print("수정할 제품번호 : ");
					int num = sc.nextInt();
					sc.nextLine();
					System.out.println("-------------------");
					System.out.println("제품명 : " + pdao.selectPname(num));
					System.out.println("-------------------");
					System.out.printf("%d  ->  ", pdao.returnPrice(num));
					int newPrice = sc.nextInt();
					sc.nextLine();
					pdao.changePrice(num, newPrice);
				}else if( pmenu == 3 ) {
					//재고 관리
					System.out.print("관리할 제품번호 : ");
					int num = sc.nextInt();
					sc.nextLine();
					System.out.println("---------------------------");
					System.out.println("제품명 : " + pdao.selectPname(num));
					System.out.println("수량 : " + pdao.selectQty(num));
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
						pdao.increaseQty(num, incQty);
					}else if( menu2 == 2 ) {
						//수량 감소
						System.out.print("감소 수량 : ");
						int desQty = sc.nextInt();
						pdao.descQty(num, desQty);
					}else if( menu2 == 3 ) {
						//수량 수정
						System.out.print("수량 입력 : ");
						int upQty = sc.nextInt();
						pdao.updateQty(num, upQty);
					}
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
			}else if(menu == 7) {
				//게시글 관리
				BoardDAO bdao = new BoardDAO();
				int menuu = 0;
				do {
					View.bDisplay.BoardList();
					System.out.println("1.게시글보기   2.게시글작성   3.게시글삭제");
					System.out.println("0.돌아가기");
					System.out.print("입력 : ");
					menuu = sc.nextInt();
					if( menuu == 1 ) {
						//게시글 보기
						System.out.print("게시글 번호 : ");
						int imenu = sc.nextInt();
						View.bDisplay.BoardInfo(imenu);
						System.out.println("1.돌아가기");
						System.out.print("입력 : ");
						int rmenu = sc.nextInt();
						if( rmenu == 1 ) {
							//
						}
					}else if( menuu == 2 ) {
						//게시글 작성
						bdao = new BoardDAO();
						System.out.println("---------");
						System.out.println("게시글 작성");
						System.out.println("---------");
						System.out.print("제목 : ");
						String title = sc.next();
						sc.nextLine();
						System.out.print("내용 : ");
						String content = sc.nextLine();
						bdao.insertBoard(title, content, inputId);
						System.out.println("1.게시글 바로보기   2.돌아가기");
						System.out.print("입력 : ");
						int smenu = sc.nextInt();
						if( smenu == 1 ) {
							View.bDisplay.BoardInfo(bdao.boardNo()-1);
							System.out.println("1.돌아가기");
							System.out.print("입력 : ");
							int rmenu = sc.nextInt();
							if( rmenu == 1 ) {
								//
							}
						}else if( smenu == 2 ) {
							//
						}
					}else if( menuu == 3 ) {
						System.out.println("(0.게시글 전체 삭제)");
						System.out.print("삭제할 글 번호 : ");
						int no = sc.nextInt();
						if( no == 0 ) {
							bdao.deleteBoardAll();
						}else {
							if(bdao.checkNo(no) == 1) {
								bdao.deleteBoard(no);
							}else {
								System.out.println("없는 글 번호입니다");
							}
						}
					}
				}while(menuu != 0);//게시글 관리 끝
			}else if(menu == 0){
				System.out.println("...프로그램 종료");
				System.exit(0);
			}else {
				System.out.println(" ! 입력한 값이 잘못되었습니다 !");
			}
		}while(menu != 0);
		
	}// display()
}
