package beer;

import java.util.Scanner;

public class BeerMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		//배열변수
		BeerDTO[] beers = new BeerDTO[10];
		beers[0] = new BeerDTO("한맥", 2000, 10);
		beers[1] = new BeerDTO("OB", 2100, 99);
		beers[2] = new BeerDTO("카스", 2200, 20);
		
		BeerDAO dao = new BeerDAO(beers, sc);
		int inputNum = 0;
		while(true) {
			System.out.println();
			System.out.println("음료관리 프로그램");
			System.out.println("-----------------------------------------------------");
			System.out.println("1.음료등록   2.음료목록보기   3.음료변경   4.음료삭제");
			System.out.println("0.프로그램 종료");
			System.out.println("-----------------------------------------------------");
			System.out.print("번호를 입력하세요 : ");
			inputNum = sc.nextInt();
			if( inputNum == 1) {
				dao.insert();
			}else if( inputNum == 2) {
				dao.list();
			}else if( inputNum == 3) {
				dao.update();
			}else if( inputNum == 4) {
				dao.delete();
			}else if(inputNum == 0) {
				System.out.println("프로그램을 종료합니다");
				break;
			}
		}
	}
}
