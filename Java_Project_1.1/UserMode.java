package test;

import java.util.Scanner;

public class UserMode {
	private Scanner sc;
	UserMode(Scanner sc){
		this.sc = sc;
	}
	
	public void display(String inputId) {
		
		System.out.println("-+-+-+-+-+-+-+-+-+-+-");
		System.out.println("   " + inputId + "님, 환영합니다");
		System.out.println("-+-+-+-+-+-+-+-+-+-+-");
		UserDAO dao = null;
		BoardDAO bdao = null;
		int inputKey = 0;
		do {
			View.pDisplay.display();
			int money = View.uDisplay.displayMoney(inputId);
			System.out.println("--------------");
			System.out.println("소지금 : " + money);
			System.out.println("--------------");
			System.out.println("1.금액충전   2.상품주문   3.게시판");
			System.out.println("4.로그아웃");
			System.out.print("입력 : ");
			inputKey = sc.nextInt();
			if( inputKey == 1 ) {
				//금액충전
				System.out.print("충전할 금액 : ");
				int addMoney = sc.nextInt();
				dao = new UserDAO();
				dao.chargeMoney(inputId, addMoney);
			}else if( inputKey == 2 ) {
				//상품주문
				//상품 번호 입력
				System.out.print("제품번호 : ");
				int num = sc.nextInt();
				View.pDisplay.displayFromNum(num, money, inputId);
			}else if( inputKey == 3 ) {
				//게시판
				int bmenu = 0;
				do {
					View.bDisplay.BoardList();
					System.out.println("1.게시글보기   2.게시글작성   3.돌아가기");
					System.out.print("입력 : ");
					bmenu = sc.nextInt();
					if( bmenu == 1 ) {
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
					}else if( bmenu == 2 ) {
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
					}else if( bmenu == 3 ) {
						//돌아가기
						break;
					}
				}while(bmenu != 0); //게시판 메뉴 끝
			}else if( inputKey == 4 ) {
				//로그아웃
				View.login.display();
				break;
			}
		} while(inputKey != 0);
		
	}//display()
}
