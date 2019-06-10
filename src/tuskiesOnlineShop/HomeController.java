package tuskiesOnlineShop;
import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class HomeController implements Initializable {
	
	
	@FXML
	private Button signinSignupBtn;

	@FXML
	private Tab electronicsTab;

	/**
	 * this method changes the view from home to the sign up/ sign in view.
	 * 
	 * @param event
	 * @throws IOException
	 */

	public void signinSignupButtonPushed(ActionEvent event) throws IOException {

		adminTab.setDisable(true);
		
		Parent signinSignupParent = FXMLLoader.load(getClass().getResource("/tuskiesOnlineShop/login.fxml"));

		Scene signinSignupScene = new Scene(signinSignupParent);

//		this gets the stage's information
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		stage.setScene(signinSignupScene);
		stage.show();
	}

	/**
	 * 
	 */

	@FXML
	ImageView imageView;

	@FXML
	TextField productNameTextField;

	@FXML
	TextArea descriptionTextArea;

	@FXML
	TextField unitPriceTextField;

	@FXML
	TextField quantityTextField;

	@FXML
	TextField serialNumberTextField;


	@FXML
	Button addImageButton;

	@FXML
	Button addNewItemButton;

	@FXML
	Label addItemLabel;

	@FXML
	ComboBox<String> categoryComboBox;

	@FXML
	ComboBox<String> subCategoryComboBox;

	@FXML
	private Label fileSelected;

	String imageFile;

	@FXML
	public void addImageButtonPressed(ActionEvent event) throws MalformedURLException {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Select Image File");
		
		// limit file chooser to image file
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png","*.jpeg", "*.jpg", "*.gif")); 

		File selectedFile = fileChooser.showOpenDialog(fileSelected.getScene().getWindow());

		if (selectedFile != null) {
			imageFile = selectedFile.toURI().toURL().toString();
			Image image = new Image(imageFile);
			imageView.setImage(image);
		}
	}

	@FXML
	public void addNewItemButtonPressed(ActionEvent event) {

		String addItem = "INSERT INTO products"
				+ "(productName, imagePath, serialNumber,unitPrice,quantity, category, subCategory, description)"
				+ "VALUES (?,?,?,?,?,?,?,?)";

		new DBConnection("onlineshop", "chris", "Otienochris5007*");
		try (Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(addItem);) {

			preparedStatement.setString(1, productNameTextField.getText());
			preparedStatement.setString(2, imageFile);
			preparedStatement.setString(3, serialNumberTextField.getText());
			preparedStatement.setString(4, unitPriceTextField.getText());
			preparedStatement.setInt(5, Integer.parseInt(quantityTextField.getText()));
			preparedStatement.setString(6, categoryComboBox.getValue());
			preparedStatement.setString(7, subCategoryComboBox.getValue());
			preparedStatement.setString(8, descriptionTextArea.getText());
			
			preparedStatement.executeUpdate();

			addItemLabel.setText("Request Successful");
			
			imageView.setImage(null);
			productNameTextField.setText("");
			descriptionTextArea.setText("");
			unitPriceTextField.setText("");
			serialNumberTextField.setText("");
			quantityTextField.setText("");
			categoryComboBox.setValue("");
			subCategoryComboBox.setValue("");

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	int count = 3; // keeps track of the current row in the result set
	int productId1, productId2, productId3; // temporary variables for the current product ids
	@FXML
	private Tab adminTab;
	@FXML
	private Button allUsersButton;

//	a variable that stores true if logged in user is administrator else false
	public static boolean isAdmin;
	
	public boolean isAdmin() {
		return isAdmin;
	}

	public static void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
	
		
		adminTab.setDisable(true); // login with administrators credentials to enable
		previousItemButton.setDisable(true); // no previous items initially
		allUsersButton.setDisable(true); // only admin can view users
		
		if (isAdmin) {
//			give administrative privileges
			adminTab.setDisable(false);
			allUsersButton.setDisable(false);
		}
		
		new DBConnection("onlineshop", "chris", "Otienochris5007*");

		try (Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				ResultSet resultSet = statement.executeQuery("SELECT * FROM products")) {

			for (int i = 1; i < 4; i++) {
				if (resultSet.next()) {
					switch (i) {
					case 1:
						productId1 = resultSet.getInt("productId");
						setProductAttributes(photo1, itemName1, price1, description1, resultSet);
						break;
					case 2:
						productId2 = resultSet.getInt("productId");
						setProductAttributes(photo2, itemName2, price2, description2, resultSet);
						break;
					case 3:
						productId3 = resultSet.getInt("productId");
						setProductAttributes(photo3, itemName3, price3, description3, resultSet);
						break;
					}
				}
			}
			

		} catch (Exception e) {
			e.printStackTrace();
		}

//		combo boxes
		categoryComboBox.getItems().addAll("Electronics", "Furniture", "Clothing", "Foodsatff", "Sports and Outdoor");
		categoryComboBox.setEditable(true);
		subCategoryComboBox.setEditable(true);
		subCategoryComboBox.setDisable(true);
		categoryComboBox.setOnAction(e -> {
			if (categoryComboBox.getValue().equals("Electronics")) {
				subCategoryComboBox.getItems().clear();
				subCategoryComboBox.setDisable(false);
				subCategoryComboBox.getItems().addAll("Phones", "Tablets", "Tv", "Woofers");
			} else if (categoryComboBox.getValue().equals("Furniture")) {
				subCategoryComboBox.getItems().clear();
				subCategoryComboBox.setDisable(false);
				subCategoryComboBox.getItems().addAll("Office", "Home");
			} else if (categoryComboBox.getValue().equals("Clothing")) {
				subCategoryComboBox.getItems().clear();
				subCategoryComboBox.setDisable(false);
				subCategoryComboBox.getItems().addAll("men","women","children","casual", "official","shoes");
			}
		});
		
		
		// initialize cart
		CartTable2 = cartTable;
		fillCheckOutLabel();
		initTable();
		loadData();
		
	}

	/**
	 * delete item section
	 */

	@FXML
	TextField deleteSearchTextField;

	@FXML
	Button deleteSearchButton;

	@FXML
	ImageView deleteImageView;

	@FXML
	Label deleteLabel;

	@FXML
	Button deleteItemButton;

	@FXML
	Label deleteItemInfoLabel;

	@FXML
	Label successDeleteMessageLabel;

	Boolean itemFound = false;

	@FXML
	public void deleteSearchButtonPressed(ActionEvent event) {
		deleteItemInfoLabel.setText("");
		successDeleteMessageLabel.setText("");
		
		String searchQuery = "SELECT * FROM products";

		new DBConnection("onlineshop", "chris", "Otienochris5007*");

		try (Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				ResultSet resultSet = statement.executeQuery(searchQuery);) {

			resultSet.beforeFirst();

			while (resultSet.next()) {
				if (resultSet.getInt("productId") == Integer.parseInt(deleteSearchTextField.getText())) {
					itemFound = true;
					break;
				}
			}

			// show the details of the items to be deleted
			if (itemFound) {
				Image image = new Image(resultSet.getString("imagePath"));

				deleteImageView.setImage(image);
				deleteLabel.setText(resultSet.getString("productName"));

			}

			if (!itemFound) {
				deleteItemInfoLabel.setText("Item not found");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@FXML
	public void deleteItemButtonPressed(ActionEvent event) {
		successDeleteMessageLabel.setText("");
		
		if (itemFound) {
			String idDeleteQuery = "DELETE FROM products where productId = ?";

			new DBConnection("onlineshop", "chris", "Otienochris5007*");
			try (Connection connection = DBConnection.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(idDeleteQuery);) {

				preparedStatement.setInt(1, Integer.parseInt(deleteSearchTextField.getText()));
				preparedStatement.executeUpdate();

				deleteImageView.setImage(null);
				deleteLabel.setText("");
				successDeleteMessageLabel.setText("deletion successful");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * Admin login
	 */
	@FXML
	Button allUsers;
	@FXML
	private void allUsersButtonPressed(ActionEvent event) throws IOException {
		
		Parent allUsersParent = FXMLLoader.load(getClass().getResource("/tuskiesOnlineShop/Users.fxml"));

		Scene allScene = new Scene(allUsersParent);

//		this gets the stage's information
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

		stage.setScene(allScene);
		stage.show();
	}
	
	@FXML
	Button addTocart1;
	@FXML
	Button addToCart2;
	@FXML
	Button addToCart3;

	@FXML
	ImageView photo1;
	@FXML
	ImageView photo2;
	@FXML
	Label description1;
	@FXML
	Label description2;
	@FXML
	Label price1;
	@FXML
	Label price2;
	@FXML
	ImageView photo3;
	@FXML
	Label description3;
	@FXML
	Label price3;
	@FXML
	Button nextItemButton;
	@FXML
	Button previousItemButton;
	@FXML
	Label itemName1;
	@FXML
	Label itemName2;
	@FXML
	Label itemName3;

	ResultSet resultSet1;
	
	String searchCriteriaString =  " ";

	/**
	 * Electronics tab
	 */
	@FXML
	public void nextItemButtonPressed() throws SQLException {
		
		try (Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM products " + searchCriteriaString )) {
			
//			move cursor to the current item location
			for (int i = 0; i < count; i++) {
				resultSet.next();
			}
			
//			no more previous items
			if (count >= 3) {
				previousItemButton.setDisable(false);
			}
			
		 for (int i = 1; i < 4; i++) {
//			 move to next row
			 if (resultSet.next()) {
				 count++; // move cursor to the next row in resultSet
				 switch (i) { 
				 	case 1:
				 		productId1 = resultSet.getInt("productId");
				 		setProductAttributes(photo1, itemName1, price1, description1, resultSet);
				 		break; 
				 	case 2:
				 		productId2 = resultSet.getInt("productId");
				 		setProductAttributes(photo2, itemName2, price2, description2, resultSet);
				 		break; 
				 	case 3:
				 		productId3 = resultSet.getInt("productId");
						setProductAttributes(photo3, itemName3, price3, description3, resultSet);
						break;
		  
				 	} 
				 } else nextItemButton.setDisable(true);
			 
			 }
		 } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * previous button pressed
	 */
	@FXML
	public void previousButtonPressed() {
		
		if (count > 3) { 
			count -= 3;
		}
//		no more previous items to be viewed
		if (count <= 3) {
			previousItemButton.setDisable(true);
			
		}
		
		try (Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM products " + searchCriteriaString)) {
			
//			moves cursor to the current row in the result set
			for (int i = 0; i < count; i++) {
				resultSet.next();
			}

		 for (int i = 1; i < 4; i++) { 
			 if (resultSet.previous()) { 
				 nextItemButton.setDisable(false);
				 switch (i) { 
				 	case 3:
				 		productId1 = resultSet.getInt("productId");
				 		setProductAttributes(photo1, itemName1, price1, description1, resultSet);
				 		break; 
				 	case 2:
				 		productId2 = resultSet.getInt("productId");
				 		
				 		setProductAttributes(photo2, itemName2, price2, description2, resultSet);
				 		
				 		break; 
				 	case 1:
				 		productId3 = resultSet.getInt("productId");
				 		setProductAttributes(photo3, itemName3, price3, description3, resultSet);  
						break;  
				 	} 
				 } 
			 }
		 } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	sets up the products for view
	private void setProductAttributes(ImageView photo, Label itemName, Label price, 
			Label description, ResultSet resultSet) throws SQLException {
		
		Image image = new Image(resultSet.getString("imagePath"));
		photo.setImage(image);
		 
		itemName.setText(resultSet.getString("productName"));
		price.setText("Kshs " + Float.toString(resultSet.getFloat("unitPrice")));
		description.setText(resultSet.getString("description"));
	}

	/**
	 * The cart section
	 */
	@FXML
    private TableView<CartProduct> cartTable;
	
	public static TableView<CartProduct> CartTable2;
	
    @FXML
    private TableColumn<CartProduct, String> colUnits;
	@FXML
    private TableColumn<CartProduct, String> colUnitPrice;
	@FXML
	private TableColumn<CartProduct, Button> colDelete;
	@FXML
    private TableColumn<CartProduct, String> colTotal;
	@FXML
	private TableColumn<CartProduct, Button> colUpdate;
	@FXML
	private TableColumn<CartProduct, String> colDescription;
	@FXML
	private TableColumn<CartProduct, String> colTitle;
	
	public static ObservableList<CartProduct> tableData = FXCollections.observableArrayList();
	
	private void initTable() {
		initCols();
	}
	
	private void initCols() {

		colTitle.setCellValueFactory(new PropertyValueFactory<>("title"));
		colDescription.setCellValueFactory(new PropertyValueFactory<>("description"));
		colUnits.setCellValueFactory(new PropertyValueFactory<>("units"));
		colUnitPrice.setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
		
		editableCols();
	}
	
	 private void editableCols() {
		 cartTable.setEditable(true);
		 colUnits.setCellFactory(TextFieldTableCell.forTableColumn());
		 colUnits.setOnEditCommit(e -> {
			 int row = e.getTablePosition().getRow();
			 e.getTableView().getItems().get(row).setUnits(e.getNewValue());
			 fillCheckOutLabel();
			 
		 });
	 }
	 
	
	
	/**
	 * load to cart
	 * @param cartProduct
	 */
	public void addToCart1ButtonPressed() {
		
		addToCartAidingFunction(productId1);
	}
	public void addToCart2ButtonPressed() {
		
		addToCartAidingFunction(productId2);
	
	}
	public void addToCart3ButtonPressed() {
		
		addToCartAidingFunction(productId3);
	}
	
	@FXML
	private void loadData() {	
		cartTable.setItems(tableData);
	}

	
	@FXML
	Label totalLabel;
	
	
	private void addToCartAidingFunction(int productId) {
		
		new DBConnection("onlineshop", "chris", "Otienochris5007*");
		try(Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				ResultSet resultSet = statement.executeQuery("SELECT * FROM products WHERE productId = " + productId);
				) {
			
			while (resultSet.next()) {
				tableData.add(new CartProduct(resultSet.getString("productName"), "1", resultSet.getString("description"), 
					resultSet.getString("unitPrice")));
				fillCheckOutLabel();
				
				inforAlert();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	Alert function
	private void inforAlert() {
		Alert confirmationAlert = new Alert(AlertType.INFORMATION);
		confirmationAlert.setContentText("Added successfully");
		confirmationAlert.setHeaderText("Hurray");
		confirmationAlert.show();
	}
	
	// total for checkout
	private void fillCheckOutLabel() {
		double total = 0.0;
		
		for (CartProduct cartProduct : tableData) {
			total += Double.parseDouble(cartProduct.getUnitPrice()) * Integer.parseInt(cartProduct.getUnits());
		}
		totalLabel.setText("Ksh. " + Double.toString(total));
	}
	
	@FXML
	private void handleCheckOut(ActionEvent event) throws IOException {
		Parent checkoutParent = FXMLLoader.load(getClass().getResource("/tuskiesOnlineShop/sceneTwo.fxml"));
		Scene checkOutScene = new Scene(checkoutParent);
//		this gets the stage's information
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		stage.setScene(checkOutScene);
		stage.show();
	}
	
	@FXML
	private Button deleteRowButton;
	
	@FXML
	private void deleteRowButtonPressed(ActionEvent event) {
		double total = 0.0;
//		get selected item
		CartProduct deleteCartProduct = cartTable.getSelectionModel().getSelectedItem();
//		remove from the table
		cartTable.getItems().remove(deleteCartProduct);
//		compute new total
		for (CartProduct cartProduct : tableData) {
			total += Double.parseDouble(cartProduct.getUnitPrice()) * Integer.parseInt(cartProduct.getUnits());
		}
//		set new total
		totalLabel.setText("Ksh. " + Double.toString(total));
//		load the data on the table
		loadData();
	}
	
//	shop tab : Filter section
	@FXML
	private Button filterButton;
	@FXML
	private ComboBox<String> filterBYComboBox;
	@FXML
	private ComboBox<String> filterByComboBox2;
	
	@FXML
	private void filterButtonPressed() {
		// TODO 
		filterBYComboBox.setVisible(true);
		filterByComboBox2.setVisible(true);
		
		filterByComboBox2.setDisable(true);
		filterBYComboBox.getItems().addAll("Electronics", "Furniture", "Clothing", "Foodsatff", "Sports and Outdoor");
		
		filterBYComboBox.setOnAction(e -> {
			String valueString = filterBYComboBox.getValue();
			if (valueString.equals("Electronics")) {
				filterByComboBox2.setDisable(false);
				filterByComboBox2.getItems().clear();
				filterByComboBox2.getItems().addAll("Phones", "Tablets", "Tv", "Woofers");
			}else if (valueString.equals("Furniture")) {
				filterByComboBox2.setDisable(false);
				filterByComboBox2.getItems().clear();
				filterByComboBox2.getItems().addAll("Office", "Home");
			}else if (valueString.equals("Clothing")) {
				filterByComboBox2.setDisable(false);
				filterByComboBox2.getItems().clear();
				filterByComboBox2.getItems().addAll("men","women","children","casual", "official","shoes");
			}else if (valueString.equals("Foodstaff")) {
				filterByComboBox2.getItems().clear();
				filterByComboBox2.setDisable(false);
			}else if (valueString.equals("Sports and Outdoor")) {
				filterByComboBox2.setDisable(false);
				filterByComboBox2.getItems().clear();
			}
		});
		
		searchCriteriaString = "WHERE category = " + filterBYComboBox.getValue() + " AND " + "subCategory = "
		+ filterByComboBox2.getValue();
	}
	
}