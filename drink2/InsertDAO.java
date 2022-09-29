package drink2;

import java.util.Scanner;


public class InsertDAO extends Drink implements DrinkDAO{
	InsertDAO(DrinkDTO[] drinks, Scanner sc){
		super(drinks, sc);
	}
	
	int checkStorage() {
		int position = -1; //빈공간 없으면 -1 리턴
		for(int idx=0; idx < drinks.length; idx++) {
			if( drinks[idx]==null ) {
				position = idx; //빈공간 존재시 해당 인덱스 리턴
				break;
			}
		}
		return position;
	}
	
	
	@Override
	public void insert() {
		//음료정보를 저장할 인덱스를 찾는다
		int idx = checkStorage();
		if( idx==-1 ) {
			System.out.println("저장공간 부족으로 등록불가!");
			return;
		}
		
		System.out.print("음료명: ");
		String name = sc.next();
		System.out.print("가격: ");
		int price 
		= Common.minNumber(sc.nextInt(), "가격", sc);
		System.out.print("수량: ");
		int quantity 
		= Common.minNumber(sc.nextInt(), "수량", sc);
		
		drinks[idx] = new DrinkDTO(name, price, quantity);
		
	}

	@Override
	public void list() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
