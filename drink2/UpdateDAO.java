package drink2;

import java.util.Scanner;

public class UpdateDAO extends Drink implements DrinkDAO{
	UpdateDAO(DrinkDTO[] drinks, Scanner sc){
		super(drinks, sc);
	}
	
	@Override
	public void insert() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void list() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update() {
		System.out.print("음료선택: ");
		int idx = sc.nextInt()-1;
		
		System.out.printf("음료명: %s -> ", drinks[idx].name );
		String name = sc.next();
		
		System.out.printf("가격: %d -> ", drinks[idx].price );
		int price 
		= Common.minNumber(sc.nextInt(), "가격", sc);

		System.out.printf("수량: %d -> ", drinks[idx].quantity );
		int quantity 
		= Common.minNumber(sc.nextInt(), "수량", sc);
		
		drinks[idx].name = name;
		drinks[idx].price = price;
		drinks[idx].quantity = quantity;		
	}

	@Override
	public void delete() {
		// TODO Auto-generated method stub
		
	}

}
