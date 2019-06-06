package tuskiesOnlineShop;

import java.io.File;

public abstract class Product {
	private File imageFile;
	private int productId;
	private String productName;
	private String description;
	private double unitPrice;
	private int quantity;
	private String subCategory;
	
	
public Product(File imageFile ,int productId, String productName, String description, double unitPrice, int quantity,
			String subCategory) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.description = description;
		this.unitPrice = unitPrice;
		this.quantity = quantity;
		this.subCategory = subCategory;
		this.imageFile = imageFile;
		
	}


//	getters and setters
	public File getImageFile() {
		return imageFile;
	}
	public void setImageFile(File imageFile) {
		this.imageFile = imageFile;
	}

	public int getProductId() {
		return productId;
	}
	public void setProductId(int productId) {
		this.productId = productId;
	}
	
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public String getSubCategory() {
		return subCategory;
	}
	public void setSubCategory(String subCategory) {
		this.subCategory = subCategory;
	}
	
	
}
