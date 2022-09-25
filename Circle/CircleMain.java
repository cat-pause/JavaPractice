package Circle;

public class CircleMain {
	public static void main(String[] args) {
		CircleDAO dao = new CircleDAO();
		
		Circle c1 = new Circle(5);
		dao.display(c1);
		
		Circle c2 = new Circle(10);
		dao.display(c2);
	} //main()
}
