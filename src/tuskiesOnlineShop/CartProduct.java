package tuskiesOnlineShop;

public class CartProduct {
	String title, description;
	String unitPrice, total;
	String units;
	
	
//	constructor
	public CartProduct(String title,String units, String description, String unitPrice) {
		super();
		this.title = title;
		this.units = units;
		this.description = description;
		this.unitPrice = unitPrice;
		
	}


	//	getters and setters
	public String getUnits() {
		return units;
	}
	public void setUnits(String units) {
		this.units = units;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(String unitPrice) {
		this.unitPrice = unitPrice;
	}
	public String getTotal() {
		return total;
	}
	public void setTotal(String total) {
		this.total = total;
	}
	
	
}
