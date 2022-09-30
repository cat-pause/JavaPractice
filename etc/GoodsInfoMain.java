import java.util.Scanner;

public class GoodsInfoMain {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		GoodsInfo p1 = new GoodsInfo("아디다스", 1, "운동화", 70000);
		display(p1);
		GoodsInfo p2 = new GoodsInfo("쿠크다스", 2, "런닝화", 30000, 30);
		display(p2);
		
		System.out.print("할인율 변경(상품 코드 입력) :");
		int inputStart = Integer.parseInt(sc.nextLine());
		System.out.print("할인율(%) 설정 :");
		int inputSale = Integer.parseInt(sc.nextLine());
		System.out.println();
		
		if(inputStart == 1) {
			p1.setSale(inputSale);
			display(p1);
		}else if(inputStart == 2) {
			p2.setSale(inputSale);
			display(p2);
		}
		
		sc.close();
		
	} //main()
	
	static void display(GoodsInfo info) {
		System.out.println("제조사\t\t상품코드\t상품명");
		System.out.print(info.company+"\t"+info.goodsCode+"\t\t"+info.name+"\n\n");
		System.out.println("표준단가\t할인율\t\t판매가");
		System.out.print(info.standardPrice+"\t\t"+info.sale+"\t\t"+info.getPrice()+"\n");
		System.out.println("======================================\n");
	} //display()
	
} //class
