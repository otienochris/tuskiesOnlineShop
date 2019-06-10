package tuskiesOnlineShop;

import javafx.scene.control.Button;
import javafx.scene.image.ImageView;

public class CartProduct {
	String title, description;
	double unitPrice, total;
	int units;
	Button update, delete;
	
//	constructor
	public CartProduct(String title,int units, String description, double unitPrice, double total,
			Button update, Button delete) {
		super();
		this.title = title;
		this.units = units;
		this.description = description;
		this.unitPrice = unitPrice;
		this.total = total;
		this.update = update;
		this.delete = delete;
	}


	//	getters and setters
	public int getUnits() {
		return units;
	}
	public void setUnits(int units) {
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
	public double getUnitPrice() {
		return unitPrice;
	}
	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	
	public Button getUpdate() {
		return update;
	}
	public void setUpdate(Button update) {
		this.update = update;
	}
	public Button getDelete() {
		return delete;
	}
	public void setDelete(Button delete) {
		this.delete = delete;
	}
	
	
	
}
