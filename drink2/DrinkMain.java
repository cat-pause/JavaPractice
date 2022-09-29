package drink2;

import java.util.Scanner;

public class DrinkMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//배열변수	
		DrinkDTO[] drinks = new DrinkDTO[10];
		drinks[0] = new DrinkDTO("콜라", 1250, 10);
		drinks[1] = new DrinkDTO("캔커피", 1500, 5);
		drinks[2] = new DrinkDTO("코코팜", 1300, 15);
		
//		InsertDAO insert = new InsertDAO(drinks, sc);
//		ListDAO list = new ListDAO(drinks, sc);
//		UpdateDAO update = new UpdateDAO(drinks, sc);
//		DeleteDAO delete = new DeleteDAO(drinks, sc);
		DrinkControl control = new DrinkControl(drinks, sc);
		
		System.out.println("음료관리 프로그램");
		int fnKey = 0;
		do {
			System.out.println("----------------------------");
			System.out.println(
					"1.음료등록  2.음료목록보기  3.음료변경  4.음료삭제");
			System.out.println("0.프로그램 종료");
			System.out.println("----------------------------");
			System.out.print("기능번호 선택: ");
			fnKey = sc.nextInt();
			if( fnKey==1 ) {
				control.insert();
				
			}else if( fnKey==2 ) {
				control.list();
				
			}else if( fnKey==3 ) {
				control.list();
				control.update();
				
			}else if( fnKey==4 ) {
				control.list();
				control.delete();
			}
		}while(fnKey != 0);
		System.out.println("프로그램 종료...");
	}
}
