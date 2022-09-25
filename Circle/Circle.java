package Circle;

public class Circle {
	private int radius;
	
	Circle(){}
	Circle(int radius){
		this.radius = radius;
	}
	int getRadius() {
		return radius;
	}
	//넓이 구하는 메소드
	double getArea() {
		return radius * radius * 3.14;
	}
	
} //class
