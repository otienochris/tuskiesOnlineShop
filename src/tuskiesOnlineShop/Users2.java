package tuskiesOnlineShop;

import javafx.scene.control.Button;

public class Users2 {
	String id, name, email, notes;
	Button updateButton;
	
	public Users2(String id, String name, String email, String notes, Button updateButton) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.notes = notes;
		this.updateButton = updateButton;
		
		updateButton.setOnAction(e -> {
			
		});
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public Button getUpdateButton() {
		return updateButton;
	}
	public void setUpdateButton(Button updateButton) {
		this.updateButton = updateButton;
	}
	
	
}
