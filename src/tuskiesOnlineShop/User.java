package tuskiesOnlineShop;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.control.Button;

public class User {
	private final IntegerProperty id = new SimpleIntegerProperty();
	private final StringProperty firstName = new SimpleStringProperty();
	private final StringProperty lastName = new SimpleStringProperty();
	private final StringProperty email = new SimpleStringProperty();
	private final StringProperty userName = new SimpleStringProperty();
	private final BooleanProperty isAdmin = new SimpleBooleanProperty();
	
	Button updateButton;

//	property types
	public StringProperty emailProperty() {
		return email;	
	}
	public IntegerProperty idProperty() {
		return id;
	}
	public StringProperty firstNameProperty() {
		return firstName;
	}
	public StringProperty lastNameProperty() {
		return lastName;
	}
	public StringProperty usernameProperty() {
		return userName;
	}
	public BooleanProperty isAdminProperty() {
		return isAdmin;
	}
	
//	getters
	public String getEmail() {
		return email.get();
	}
	public int getId() {
		return id.get();
	}
	public String getFirstName() {
		return firstName.get();
	}
	public String getLastName() {
		return lastName.get();
	}
	public String getUserName() {
		return userName.get();
	}
	public Boolean getIsAdmin() {
		return isAdmin.get();
	}

//	setters
	public void setEmail(String email) {
		this.email.set(email);
	}
	public void setId(int id) {
		this.id.set(id);
	}
	public void setFirstName(String fname) {
		firstName.set(fname);
	}
	public void setLastName(String lname) {
		lastName.set(lname);
	}
	public void setUserName(String username) {
		userName.set(username);
	}
	public void setIsAdmin(Boolean isadmin) {
		isAdmin.set(isadmin);
	}
	
	
}
