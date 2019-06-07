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
import java.util.concurrent.CountDownLatch;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
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
		fileChooser.getExtensionFilters()
				.addAll(new FileChooser.ExtensionFilter("Image Files", "*.bmp", "*.png", "*.jpg", "*.gif")); // limit
																												// fileChooser
																												// options
																												// to
																												// image
																												// files

		File selectedFile = fileChooser.showOpenDialog(fileSelected.getScene().getWindow());

		if (selectedFile != null) {

			imageFile = selectedFile.toURI().toURL().toString();

			Image image = new Image(imageFile);

			imageView.setImage(image);

		}

	}

	@FXML
	public void addNewItemButtonPressed(ActionEvent event) {
//		addItemLabel.setText("");

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
			
			

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	@FXML
	public void menuButtonPressed(ActionEvent event) {

	}

	int count = 0;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		new DBConnection("onlineshop", "chris", "Otienochris5007*");

		try (Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM products")) {

			for (int i = 1; i < 4; i++) {
				if (resultSet.next()) {
					switch (i) {
					case 1:
						Image image = new Image(resultSet.getString("imagePath"));
						photo1.setImage(image);
						itemName1.setText(resultSet.getString("productName"));
						price1.setText("Kshs " + Float.toString(resultSet.getFloat("unitPrice")));
						description1.setText(resultSet.getString("description"));
						break;
					case 2:
						Image image2 = new Image(resultSet.getString("imagePath"));
						photo2.setImage(image2);
						itemName2.setText(resultSet.getString("productName"));
						price2.setText("Kshs " + Float.toString(resultSet.getFloat("unitPrice")));
						description2.setText(resultSet.getString("description"));
						break;
					case 3:
						Image image3 = new Image(resultSet.getString("imagePath"));
						photo3.setImage(image3);
						itemName3.setText(resultSet.getString("productName"));
						price3.setText("Kshs " + Float.toString(resultSet.getFloat("unitPrice")));
						description3.setText(resultSet.getString("description"));
						break;

					}
				}
			}

		} catch (Exception e) {
			// TODO: handle exception
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

		if (itemFound) {

			String idDeleteQuery = "DELETE FROM products where productId = ?";

			new DBConnection("onlineshop", "chris", "Otienochris5007*");
			try (Connection connection = DBConnection.getConnection();
					PreparedStatement preparedStatement = connection.prepareStatement(idDeleteQuery);) {

				preparedStatement.setInt(1, Integer.parseInt(deleteSearchTextField.getText()));

				preparedStatement.executeUpdate();

				successDeleteMessageLabel.setText("deletion successful");

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

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

	/**
	 * Electronics tab
	 */

	public void nextItemButtonPressed() throws SQLException {
		
		try (Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM products")) {
			
			
//			moves cursor to the current row in the result set
			for (int i = 0; i < count; i++) {
				resultSet.next();
			}

		 for (int i = 1; i < 4; i++) { 
			 if (resultSet.next()) {
				 count++;
				 switch (i) { 
				 	case 1:
				 		Image image = new Image(resultSet.getString("imagePath"));
				 		photo1.setImage(image);
				 		itemName1.setText(resultSet.getString("productName"));
				 		price1.setText( "Kshs " + Float.toString(resultSet.getFloat("unitPrice")));
				 		description1.setText(resultSet.getString("description")); 
				 		break; 
				 	case 2:
				 		Image image2 = new Image(resultSet.getString("imagePath"));
				 		photo2.setImage(image2);
				 		itemName2.setText(resultSet.getString("productName"));
				 		price2.setText("Kshs " + Float.toString(resultSet.getFloat("unitPrice")));
				 		description2.setText(resultSet.getString("description")); 
				 		break; 
				 	case 3:
						 Image image3 = new Image(resultSet.getString("imagePath"));
						 photo3.setImage(image3);
						 itemName3.setText(resultSet.getString("productName"));
						 price3.setText("Kshs " + Float.toString(resultSet.getFloat("unitPrice")));
						 description3.setText(resultSet.getString("description")); 
						 break;
		  
				 	} 
				 } 
			 }
		 } catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * previous button pressed
	 */
	public void previousButtonPressed() {
	
		if(count > 3) count -= 3; // change the position of the cursor
		try (Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM products")) {
			
//			moves cursor to the current row in the result set
			for (int i = 0; i < 3; i++) {
				resultSet.previous();
			}

		 for (int i = 1; i < 4; i++) { 
			 if (resultSet.next()) { 
				 switch (i) { 
				 	case 1:
				 		Image image = new Image(resultSet.getString("imagePath"));
				 		photo1.setImage(image);
				 		itemName1.setText(resultSet.getString("productName"));
				 		price1.setText("Kshs " + Float.toString(resultSet.getFloat("unitPrice")));
				 		description1.setText(resultSet.getString("description")); 
				 		break; 
				 	case 2:
				 		Image image2 = new Image(resultSet.getString("imagePath"));
				 		photo2.setImage(image2);
				 		itemName2.setText(resultSet.getString("productName"));
				 		price2.setText("Kshs " + Float.toString(resultSet.getFloat("unitPrice")));
				 		description2.setText(resultSet.getString("description")); 
				 		break; 
				 	case 3:
						 Image image3 = new Image(resultSet.getString("imagePath"));
						 photo3.setImage(image3);
						 itemName3.setText(resultSet.getString("productName"));
						 price3.setText("Kshs " + Float.toString(resultSet.getFloat("unitPrice")));
						 description3.setText(resultSet.getString("description")); 
						 break;
		  
				 	} 
				 } 
			 }
		 } catch (Exception e) {
			e.printStackTrace();
		}
	}
}