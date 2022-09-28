package beer;

import java.util.Scanner;

public class BeerDAO {
	BeerDTO[] beers;
	Scanner sc;
	
	BeerDAO(BeerDTO[] beers, Scanner sc){
		this.beers = beers;
		this.sc = sc;
	}
	int checkStorage() {
		int position = -1;
		for(int i = 0; i < beers.length; i++) {
			if( beers[i] == null ) {
				position = i;
				break;
			}
		}
		return position;
	}
	//상품등록, 상품목록보기, 상품변경, 상품삭제
	void insert() {
		//음료정보가 저장될 인덱스 찾기
		int idx = checkStorage();
		if(idx == -1) {
			System.out.println("저장공간 부족으로 등록불가!");
			return;
		}
//		System.out.println("등록처리");
		System.out.print("음료명 : ");
		String name = sc.next();
		System.out.print("가격 : ");
		int price = sc.nextInt();
		System.out.print("수량 : ");
		int qty = sc.nextInt();
		beers[checkStorage()] = new BeerDTO(name, price, qty);
	}
	void list() {
		System.out.println("목록보기");
		System.out.println("-----------------------------");
		System.out.println("번호\t음료명\t 가격\t 수량");
		System.out.println("-----------------------------");
		
		int no = 0;
		for( BeerDTO dto : beers ) {
			if( dto != null ) {
				System.out.printf("%d.\t%s\t %d\t %d \n", ++no, dto.name, dto.price, dto.qty);
			}
		}
		if( no == 0 )System.out.println("등록된 음료가 없습니다");
	}
	void update() {
//		System.out.println("변경처리");
		list();
		System.out.print("변경할 음료 선택 : ");
		int no = sc.nextInt()-1;
		System.out.printf("음료명 : %s -> ", beers[no].name);
		String name = sc.next();
		
		System.out.printf("가격 : %d -> ", beers[no].price);
		int price = sc.nextInt();
		
		System.out.printf("수량 : %d -> ", beers[no].qty);
		int qty = sc.nextInt();
		
		beers[no].name = name;
		beers[no].price = price;
		beers[no].qty = qty;
	}
	void delete() {
//		System.out.println("삭제처리");
		list();
		System.out.print("삭제할 음료를 선택 : ");
		int inputNo = checkRange(sc.nextInt()-1);
		if( inputNo == -1 ) {
			System.out.println("번호를 잘못 입력했습니다");
		}else if( inputNo == -2 ) {
			System.out.println("삭제할 음료가 없습니다");
		}else {
			beers[inputNo] = null;
		}
	} //delete()
	int checkRange(int no) {
		if( no >= beers.length ) return -1;
		if( beers[no] == null ) {
			//삭제할 음료정보가 없는 경우
			return -2;
		}else {
			//삭제할 음료정보 인덱스 리턴
			return no;
		}
	}
}//class







