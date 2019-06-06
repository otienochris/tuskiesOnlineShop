package tuskiesOnlineShop;

import java.io.File;

public class Clothing extends Product {

	private String model;
	
	//	constructor	
	public Clothing(File imageFile,int productId, String productName, String description, double unitPrice, int quantity,
			String subCategory, String model) {
		super(imageFile, productId, productName, description, unitPrice, quantity, subCategory);
		this.model = model;
	}
	
//	getter and setter
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
