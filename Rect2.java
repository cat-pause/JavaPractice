public class Rect2 {
	private int width, height;
	
//	Lect2(){}
	/*
	Lect2(int width, int height) throws Exception{
		if(width <= 0) throw new Exception("너비가 0 이하입니다");
		this.width = width;
		if(height <= 0) throw new Exception("높이가 0 이하입니다");
		this.height = height;
	}
	*/
	
	void setWidth(int width) throws Exception {
		if(width <= 0) throw new Exception("너비는 0보다 커야 합니다!");
		this.width = width;
	} //setWidth
	void setHeight(int height) throws Exception {
		if(height <= 0) throw new Exception("높이는 0보다 커야 합니다!");
		this.height = height;
	} //setWidth
	
	int getWidth() {
		return width;
	} //getWidth()
	int getHeight() {
		return height;
	} //getHeight()
	
	int getRound(){
		return (width + height) * 2;
	}
	int getArea() {
		return width * height;
	}
	
} //class
