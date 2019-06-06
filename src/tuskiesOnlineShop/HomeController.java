package tuskiesOnlineShop;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import javafx.scene.control.MenuButton;
import javafx.scene.control.Pagination;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Callback;

public class HomeController implements Initializable {

	@FXML
	private Button signinSignupBtn;
	
	 @FXML
	 private Tab electronicsTab;
	
	 @FXML
	 private Pagination electronicsPagination;

	 @FXML
	 private Pagination foodsAndDrinkPagination;

	 @FXML
	 private Pagination clothingPagination;

	 @FXML
	 private Pagination sportsAndOutDoorPagation;

	 @FXML
	 private Pagination furniturePagination;
	 

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
	
	public VBox createPage(int pageIndex) {
		VBox box1 = new VBox();
		
		new DBConnection("onlineshop", "chris", "Otienochris5007*");
		try(
				Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet resultSet = statement.executeQuery("SELECT * FROM products");
				)
		{
			for (int i = 0; i < 4; i++) {
				if (resultSet.next()) {
					BorderPane borderPane = new BorderPane();
					
					Image image = new Image(resultSet.getString("imagePath"));
					
					ImageView imageView = new ImageView();
					
					imageView.setImage(image);
					
					AnchorPane anchorPane = new AnchorPane();
					anchorPane.setMaxHeight(100);
					anchorPane.setMaxWidth(100);
					
					anchorPane.getChildren().add(imageView);
//					
//					VBox vBox = new VBox();
//					vBox.getChildren().add(new Label().setText(resultSet.getString("productName")));
					borderPane.setLeft(anchorPane);
//					borderPane.setMaxHeight(100);
//					borderPane.setMinHeight(100);
//					box1.getChildren().add(borderPane);
					box1.getChildren().add(anchorPane);
				}
			}
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
//		for (int i = 0; i < 4; i++) {
//			  BorderPane bPane = new BorderPane();  
//
//		      bPane.setTop(new TextField("Top")); 
//		      bPane.setBottom(new Button("add to cart")); 
//		      bPane.setLeft(new TextField("Leeeeft")); 
//		      bPane.setRight(new TextField("Description")); 
//		      bPane.setCenter(new TextField("Center"));
//		      box1.getChildren().add(bPane);
//		}
		
		box1.setSpacing(20);
		
//		VBox elementVBox = new VBox();
//		elementVBox.getChildren().addAll(box1);
//		
//		elementVBox.setFillWidth(false);
//		elementVBox.setStyle("-fx-background-color: #795548;"
//				+ "-fx-padding: 20;");
//		elementVBox.setSpacing(20);

		return box1;
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
	 
//	@FXML
//	TextField subCategoryTextField;
	
//	@FXML
//	Button addButton;
	
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
	
	@FXML private Label fileSelected;
	
	
	String imageFile;
	
	@FXML
	public void addImageButtonPressed(ActionEvent event) throws MalformedURLException  {
		
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif")); // limit fileChooser options to image files
        
        File selectedFile = fileChooser.showOpenDialog(fileSelected.getScene().getWindow());

        if (selectedFile != null) {

            imageFile = selectedFile.toURI().toURL().toString();
            
            Image image =  new Image(imageFile);
            
            imageView.setImage(image);
  
        }
        
	}
	
	
	@FXML
	public void addNewItemButtonPressed(ActionEvent event) {
//		addItemLabel.setText("");
		
		String addItem = "INSERT INTO products" + 
		"(productName, imagePath, serialNumber,unitPrice,quantity, category, subCategory)" + 
				"VALUES (?,?,?,?,?,?,?)";
		
		
		new DBConnection("onlineshop", "chris", "Otienochris5007*");
		try(
				Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(addItem);
				) {
			
			preparedStatement.setString(1, productNameTextField.getText());
			preparedStatement.setString(2, imageFile);
			preparedStatement.setString(3, serialNumberTextField.getText());
			preparedStatement.setString(4, unitPriceTextField.getText());
			preparedStatement.setInt(5, Integer.parseInt(quantityTextField.getText()));
			preparedStatement.setString(6, categoryComboBox.getValue());
			preparedStatement.setString(7, subCategoryComboBox.getValue());
			
			preparedStatement.executeUpdate();
			
			addItemLabel.setText("Request Successful");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
	
	@FXML
	public void menuButtonPressed(ActionEvent event) {
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		electronicsPagination.setStyle("-fx-border-color:red;");
		electronicsPagination.setPageFactory(new Callback<Integer, Node>() {
			
			@Override
			public Node call(Integer pageIndex) {
				// TODO Auto-generated method stub
				return createPage(pageIndex);
			}
		});
		
		
//		combo boxes
		categoryComboBox.getItems().addAll("Electronics","Furniture", "Clothing", "Foodsatff", "Sports and Outdoor");
		categoryComboBox.setEditable(true);
		subCategoryComboBox.setEditable(true);
		subCategoryComboBox.setDisable(true);
		categoryComboBox.setOnAction( e -> {
			if (categoryComboBox.getValue().equals("Electronics")) {
				subCategoryComboBox.getItems().clear();
				subCategoryComboBox.setDisable(false);
				subCategoryComboBox.getItems().addAll("Phones", "Tablets", "Tv", "Woofers");
			} else if (categoryComboBox.getValue().equals("Furniture")) {
				subCategoryComboBox.getItems().clear();
				subCategoryComboBox.setDisable(false);
				subCategoryComboBox.getItems().addAll("Office", "Home");
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
		
		try(
				Connection connection = DBConnection.getConnection();
				Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				ResultSet resultSet = statement.executeQuery(searchQuery);
				) {
			
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
			try(
				Connection connection = DBConnection.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(idDeleteQuery);
				) {
			
					preparedStatement.setInt(1, Integer.parseInt(deleteSearchTextField.getText()));
			
					preparedStatement.executeUpdate();
					
					successDeleteMessageLabel.setText("deletion successful");
				
			}catch (Exception e) {
				e.printStackTrace();
			}		
		}
	}
	
}
