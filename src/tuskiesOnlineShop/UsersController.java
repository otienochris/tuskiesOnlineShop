package tuskiesOnlineShop;

import java.sql.Connection;
import java.sql.ResultSet;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
//import javafx.scene.control.Alert;
import javafx.scene.control.Button;
//import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
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
    		
			/*
			 * // lets create a cell factory to insert buttons in every row
			 * 
			 * Callback< TableColumn<User,String>, TableCell<User,String> > cellFactory =
			 * (param) -> { // make the tableCell containing the button final
			 * TableCell<User, String> cell = new TableCell<User, String>(){ // override the
			 * updateItem method
			 * 
			 * public void updateItem(String item, Boolean empty) { super.updateItem(item,
			 * empty);
			 * 
			 * // ensure that cell is created only in non-empty rows
			 * 
			 * if (empty) { setGraphic(null); setText(null); }else { // now we can create
			 * the button final Button editButton = new Button("Edit");
			 * 
			 * // attach listener to the button, what to do when it's clicked
			 * editButton.setOnAction(event -> { // extract the selected User object
			 * 
			 * User user = getTableView().getItems().get(getIndex()); // lets show which
			 * item has been selected
			 * 
			 * Alert alert = new Alert(Alert.AlertType.INFORMATION);
			 * 
			 * alert.setContentText("You have clicked on " + user.getUserName() +
			 * " with email " + user.getEmail()); alert.show();
			 * 
			 * } ); // set the created button to the cell setGraphic(editButton);
			 * setText(null); } }
			 * 
			 * };
			 * 
			 * return cell; // return the cell created };
			 * 
			 * // set the custom factory to the edit column
			 * colEdit.setCellFactory(cellFactory);
			 */

//    		set the columns to the tableView
    		tableUser.setItems(list);
    		
    		
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
    }

}

