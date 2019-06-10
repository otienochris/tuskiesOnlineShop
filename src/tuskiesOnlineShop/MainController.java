package tuskiesOnlineShop;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.MenuItem;
import javafx.scene.control.SplitMenuButton;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class MainController {
	@FXML
	private TextField FirstName;
	@FXML
	private TextField LastName;
	@FXML
	private TextField PhoneNumber;
	@FXML
	private TextField Address;
	@FXML
	private TextField Email;
	
	@FXML
	private TextField City;
	@FXML
	private TextField County;
	@FXML
	private Button Submit;
	@FXML
	private Button Cancel;
	
@FXML
private void handleClose(ActionEvent event)throws Exception{
	Parent homeViewParent = FXMLLoader.load(getClass().getResource("/tuskiesOnlineShop/home.fxml"));
	
	Scene homeScene = new Scene(homeViewParent);
	
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	
	stage.setScene(homeScene);
	
	stage.show();
}

public void Scene() throws Exception{
	Stage primaryStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("/application/Scene.fxml"));
	Scene scene = new Scene(root,400,400);
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	primaryStage.setScene(scene);
	primaryStage.show();
}
@FXML
public void sceneTwo(ActionEvent event) throws Exception{
//	Stage primaryStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("/application/sceneTwo.fxml"));
	Scene scene = new Scene(root);
//	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	
	Stage stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
	
	stage.setScene(scene);
	stage.show();
}
@FXML
public void sceneThree(ActionEvent event) throws Exception{
	Parent sceneThreeParent = FXMLLoader.load(getClass().getResource("/tuskiesOnlineShop/sceneThree.fxml"));
	
	Scene scene = new Scene(sceneThreeParent);
	
	Stage stage = (Stage) ((Node)event.getSource()).getScene().getWindow();
	
	stage.setScene(scene);
	
	stage.show();
}

@FXML
public void sceneFour(ActionEvent event) throws Exception{
	//Stage primaryStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("/tuskiesOnlineShop/sceneFour.fxml"));
	Scene scene = new Scene(root,400,400);
//	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
Stage stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
	
	stage.setScene(scene);
	stage.show();
}
@FXML
public void sceneFive(ActionEvent event) throws Exception{
	//Stage primaryStage = new Stage();
	Parent root = FXMLLoader.load(getClass().getResource("/application/sceneFive.fxml"));
	Scene scene = new Scene(root,400,400);
	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
 Stage stage =(Stage) ((Node)event.getSource()).getScene().getWindow();
	
	stage.setScene(scene);
	stage.show();
}
}
