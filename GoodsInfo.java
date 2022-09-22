//* 다음 조건을 맞춰 상품정보 클래스 선언하기
//3. 상품정보(GoodsInfo) 클래스 선언하기
//	필드 : 상품명, 상품코드, 제조사, 표준단가, 할인율(ex_10%)
//	생성자 : 할인율을 제외한 필드를 모두 초기화하는 생성자
//			 모든 필드를 초기화하는 생성자(할인율이 없는 경우 추가)
//	메소드 : 할인율을 변경하는 메소드(입력한 값 만큼 변경)
//			 판매가를 계산하고 반환하는 메소드(판매가 = 표준단가에 할인율 적용) 
//			 
//4. 3의 클래스를 사용하는 main이 있는 클래스(GoodsInfoMain)선언
//
//	[출력 예시]
//	제조사		상품코드	상품명		표준단가	할인율		판매가
//	NB			nb01		운동화		70000		0			70000
//	NIKE		nk01		모자		30000		30			21000
//	
public class GoodsInfo {
	
	String company, goodsCode, name;
	int standardPrice, price, sale;
	
	GoodsInfo(String company, String goodsCode, String name, int standardPrice, int price){
		this.company = company;
		this.goodsCode = goodsCode;
		this.name = name;
		this.standardPrice = standardPrice;
		this.price = price;
	}
	GoodsInfo(String company, String goodsCode, String name, int standardPrice, int price, int sale){
		this(company, goodsCode, name, standardPrice, price);
		this.sale = sale;
	}
	
	//할인율 변경/반환 메소드
	int getSale(int inputSale) {
		sale = inputSale;
		return sale;
	} //getSale()
	
	int getPrice() {
		return price * ((100 - sale)/100);
	} //getPrice()
	
	
} //class