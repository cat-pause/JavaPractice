public class Rect2DAO {
	Rect2 lect = new Rect2();
	
	void display(Rect2 rectNumber) {
		System.out.printf("너비 : %d \n", rectNumber.getWidth());
		System.out.printf("높이 : %d \n", rectNumber.getHeight());
		System.out.printf("둘레 : %d \n", rectNumber.getRound());
		System.out.printf("넓이 : %d \n", rectNumber.getArea());
		System.out.println("==============================");
	} //display()
}
