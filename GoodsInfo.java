import java.text.DecimalFormat;

public class GoodsInfo {
	
	String company, name;
	int standardPrice, sale, goodsCode;
	double price;
	
	GoodsInfo(String company, int goodsCode, String name, int standardPrice){
		this.company = company;
		this.goodsCode = goodsCode;
		this.name = name;
		this.standardPrice = standardPrice;
	}
	GoodsInfo(String company, int goodsCode, String name, int standardPrice, int sale){
		this(company, goodsCode, name, standardPrice);
		this.sale = sale;
	}
	
	//할인율 변경/반환 메소드
	void setSale(int inputSale) {
		sale = inputSale;
	} //getSale()
	
	String getPrice() {
		DecimalFormat df = new DecimalFormat("0");
		if(sale==0) {
			this.price = standardPrice;
		}else {
			this.price = standardPrice * ((100 - (double)sale)/100);
//			System.out.println("★"+price+"★"+standardPrice);
		}
		return df.format(price);
	} //getPrice()
	
} //class