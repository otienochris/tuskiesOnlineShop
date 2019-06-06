package tuskiesOnlineShop;

import java.io.File;

public class Electronics extends Product{
	private String model;
	private int warranty;
	
//	constructor
	public Electronics(File imageFile ,int productId, String productName, String description, double unitPrice, int quantity,
			String subCategory, String model, int warranty) {
		super (imageFile, productId, productName, description, unitPrice, quantity, subCategory);
		this.model = model;
		this.warranty = warranty;
	}
	
//	getters and setters
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
	
	
	public int getWarranty() {
		return warranty;
	}
	public void setWarranty(int warranty) {
		this.warranty = warranty;
	}
	
	
}
