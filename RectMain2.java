import java.util.Scanner;

public class RectMain2 {
	public static void main(String[] args) {
		
		try {
			Rect2 rect1 = new Rect2();
			Rect2DAO dao = new Rect2DAO();
			Scanner sc1 = new Scanner(System.in);
			
			System.out.print("너비를 입력하세요 : ");
			int num1 = sc1.nextInt();
			rect1.setWidth(num1);
			
			System.out.print("높이를 입력하세요 : ");
			int num2 = sc1.nextInt();
			rect1.setHeight(num2);
			
			dao.display(rect1);
			sc1.close();
		} catch(Exception e) {
			System.out.println(e.getMessage());
		}
		
	} //main()
	/*
	static void display(Lect2 lectNumber) {
		System.out.printf("너비 : %d \n", lectNumber.getWidth());
		System.out.printf("높이 : %d \n", lectNumber.getHeight());
		System.out.printf("둘레 : %d \n", lectNumber.getRound());
		System.out.printf("넓이 : %d \n", lectNumber.getArea());
		System.out.println("==============================");
	} //display()
	*/
}//class