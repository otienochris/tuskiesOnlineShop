package tuskiesOnlineShop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.TitledPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {

	@FXML
    private Text title;
	
	@FXML
	private Text loginMessage;

    @FXML
    private TextField firstName;
    
    @FXML
    private Text firstnameError;

    @FXML
    private TextField lastName;
    
    @FXML
    private Text lastnameError;

    @FXML
    private PasswordField passwordSignUp;

    @FXML
    private PasswordField confirmPassword;
    
    @FXML
    private Text confirmError;

    @FXML
    private TextField username_login;
    
    @FXML
    private PasswordField password_login;

    @FXML
    private TitledPane login;

    @FXML
    private Button login_btn;

    @FXML
    private TitledPane signup;

    @FXML
    private TextField usernameSignUp;
    
    @FXML
    private Text usernameError;

    @FXML
    private TextField email; 
    
    @FXML
    private Text emailError;
    
    @FXML
    private Text successfulLogin;
    
    @FXML
    private Button backButton;

    @FXML
    private void handleLogin(ActionEvent event) throws IOException {
    	
    	loginMessage.setText("");
    	
    	new DBConnection("onlineshop", "chris", "Otienochris5007*");
    	
    	try(	Connection connection = DBConnection.getConnection();
    			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
    			ResultSet resultSet = statement.executeQuery("SELECT * FROM users;")) {
    		
    		Boolean valid = false;
    		
    		
    		while (resultSet.next()) {
				
    			if (validUser(resultSet.getString("username"), resultSet.getString("password"))) {
					resultSet.beforeFirst();
					valid = true;
					break;
    			}
    		}
    		
//    		empty credentials are invalid
    		if (username_login.getText().equals("") || password_login.getText().equals("")) {
				valid = false; 
			}
    		
    		
//    		if valid go to the home page
    		if (valid) {
    			Parent homeViewParent = FXMLLoader.load(getClass().getResource("/tuskiesOnlineShop/home.fxml"));
    			
    			Scene homeScene = new Scene(homeViewParent);
    			
    			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    			
    			stage.setScene(homeScene);
    			
    			stage.show();
    		}
    			
    		else loginMessage.setText("Log in failed");
		
    	} catch (Exception e) {
    		
    		e.printStackTrace();
		}
    }
    
    
    @FXML
    private void handleSignup(ActionEvent event) {
    	emailError.setText("");
    	confirmError.setText("");
    	usernameError.setText("");
    	lastnameError.setText("");
    	firstnameError.setText("");
    	
    	new DBConnection("onlineshop", "chris", "Otienochris5007*"); 
    	
    	try(	Connection connection = DBConnection.getConnection(); 
    			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
    			ResultSet.CONCUR_READ_ONLY); 
    			ResultSet resultSet = statement.executeQuery("SELECT username FROM users;")) {
    	
    	 Boolean invalidDetails = false;
    	 if (firstName.getText().equals("")) {
    		 invalidDetails = true;
			firstnameError.setText("*required field");
		}
    	 
    	 if (lastName.getText().equals("")) {
    		 invalidDetails = true;
			lastnameError.setText("*required field");
		}
    	 
    	 
    	 if ( email.getText().equals("") || !validEmail(email.getText())) { 
    		 invalidDetails = true;
    		 emailError.setText("Invalid Email"); 
    		 }
    	 
    	 
    	 if (!passwordsMatch(passwordSignUp.getText(), confirmPassword.getText())) {
    		 invalidDetails = true; 
    		 confirmError.setText("Passwords don't match"); 
    	 	}
    	 
    	 while (resultSet.next()) {
    		 if (resultSet.getString("username").equals(usernameSignUp.getText())) {
				invalidDetails = true;
				usernameError.setText("Username exists");
				break;
			}
    	 }
    	 
    	 if (!invalidDetails) {
			String insertIntoUsers = "INSERT INTO users (first_name, last_name , username, email, is_admin, password ) "
					+ "VALUES (?, ?, ?, ? , ?, ?)";
			PreparedStatement preparedStatement = connection.prepareStatement(insertIntoUsers);
			
			preparedStatement.setString(1, firstName.getText());
			preparedStatement.setString(2, lastName.getText());
			preparedStatement.setString(3, usernameSignUp.getText());
			preparedStatement.setString(4, email.getText());
			preparedStatement.setBoolean(5, false);
			preparedStatement.setString(6, passwordSignUp.getText());
			
			preparedStatement.executeUpdate();
			
			/**
			 * this takes you to the home view
			 */
			
			Parent homeViewParent = FXMLLoader.load(getClass().getResource("/tuskiesOnlineShop/home.fxml"));
			
			Scene homeScene = new Scene(homeViewParent);
			
			Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
			
			stage.setScene(homeScene);
			
			stage.show();
			
			
		}
    	 
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    }
    
    
    /**
     * This is the back button method
     * @param event
     * @throws IOException
     */
    public void backButtonPushed(ActionEvent event) throws IOException {
    	Parent homeViewParent = FXMLLoader.load(getClass().getResource("/tuskiesOnlineShop/home.fxml"));
    	
    	Scene scene = new Scene(homeViewParent);
    	
    	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
    	
    	stage.setScene(scene);
    	
    	stage.show();
    	
    }
    
//    aiding methods
    public Boolean validUser(String username, String password){
    	return username_login.getText().equalsIgnoreCase(username) && 
    			password_login.getText().equals(password);
    }
    
    public Boolean usernameExist(String username1, String username2) {
		return username1.equals(username2);
    }
    
    public Boolean passwordsMatch(String pass1,String pass2) {
    	return pass1.equals(pass2);
    }
    
    public Boolean validEmail(String email) {
    	if (	!email.contains("@") || !email.contains(".") || email.indexOf("@") == 0 || 
    			email.indexOf(".") == 0 || email.equals("") || 
    			email.indexOf("@") == (email.indexOf(".") + 1) ||
    			email.indexOf("@") == (email.indexOf(".") - 1) 
    			) return false;
    	
    	return true;
    }
}
