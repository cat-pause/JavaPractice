package beer;

public class BeerDTO {
	//음료명, 가격, 수량
	String name;
	int price, qty;
	
	public BeerDTO(String name, int price, int qty){
		this.name = name;
		this.price = price;
		this.qty = qty;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName(String name) {
		return name;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPrice() {
		return price;
	}
	public void setQty(int qty) {
		this.qty = qty;
	}
	public int getQty() {
		return qty;
	}

	
}
