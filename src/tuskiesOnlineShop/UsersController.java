package tuskiesOnlineShop;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
//import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
//import javafx.util.Callback;

public class UsersController {
	
	@FXML
	private Button print;
	
	@FXML 
	private Button print2;
	
	@FXML
    private TableView<User> tableUser;

    @FXML
    private TableColumn<User, String> colLastname;

    @FXML
    private TableColumn<User, String> colFisrname;

    @FXML
    private TableColumn<User, Boolean> colIsadmin;

    @FXML
    private TableColumn<User, Integer> colId;

    @FXML
    private TableColumn<User, String> colUsername;

    @FXML
    private TableColumn<User, String> colEmail;

    @FXML
    private TableColumn<?,?> colDelete;

//    @FXML
//    private TableColumn colEdit;
    
    private ObservableList<User> list = FXCollections.observableArrayList();
    
    public void initialize () {
    	populateTableView();
    }
    
    @FXML
    private void backButtonPressed(ActionEvent event) throws IOException {
    	Parent homeViewParent = FXMLLoader.load(getClass().getResource("/tuskiesOnlineShop/home.fxml"));
		
		Scene homeScene = new Scene(homeViewParent);
		
		Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
		
		stage.setScene(homeScene);
		
		stage.show();

	}
    
//    method to populate the tableView
    public void populateTableView() {
    	
    	new DBConnection("onlineshop", "chris", "Otienochris5007*");
    	String selectAllUsers = "SELECT * FROM users";
    	
    	try(
    			Connection connection = DBConnection.getConnection();
    			ResultSet resultSet = connection.createStatement().executeQuery(selectAllUsers);
    			){
    		
    		while (resultSet.next()) {
				User user = new User();
				
				user.setId(resultSet.getInt("id"));
				user.setFirstName(resultSet.getString("first_name"));
				user.setLastName(resultSet.getString("last_name"));
				user.setEmail(resultSet.getString("email"));
				user.setUserName(resultSet.getString("username"));
				user.setIsAdmin(resultSet.getBoolean("is_admin"));
				
				list.add(user);
			}
    		
//    		set property to table view columns
//    		use the same property that is in the Object class
    		colId.setCellValueFactory(new PropertyValueFactory<>("id"));
    		colFisrname.setCellValueFactory(new PropertyValueFactory<>("firstName"));
    		colLastname.setCellValueFactory(new PropertyValueFactory<>("lastName"));
    		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    		colUsername.setCellValueFactory(new PropertyValueFactory<>("username"));
    		colIsadmin.setCellValueFactory(new PropertyValueFactory<>("isAdmin"));
    		
			
			 // lets create a cell factory to insert buttons in every row
			 

//    		set the columns to the tableView
    		tableUser.setItems(list);
    		
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

}

