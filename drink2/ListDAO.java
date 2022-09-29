package drink2;

import java.util.Scanner;


public class ListDAO extends Drink implements DrinkDAO {
	ListDAO(DrinkDTO drinks[], Scanner sc){
		super(drinks, sc);
	}
	
	@Override
	public void insert() {
		// TODO Auto-generated method stub

	}

	@Override
	public void list() {
		System.out.println("------------------------");
		System.out.println("번호  음료명     가격   수량");
		System.out.println("------------------------");
		
		int no = 0;
		for(DrinkDTO dto : drinks) {
			if( dto != null ) {
				System.out.printf("%d. %s\t %d\t %d \n"
					, ++no, dto.name, dto.price, dto.quantity);
			}
		}
		if( no==0 ) System.out.println("등록된 음료가 없습니다");

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
