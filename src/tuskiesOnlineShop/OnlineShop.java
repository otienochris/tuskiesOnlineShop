package tuskiesOnlineShop;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class OnlineShop extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {
		
		Parent root =  FXMLLoader.load(getClass().getResource("/tuskiesOnlineShop/home.fxml"));
		Scene scene = new Scene (root);
		primaryStage.setScene(scene);
		
		primaryStage.setHeight(700);
		primaryStage.setWidth(800);
		
		primaryStage.setTitle("Tuskies Online Shop");
        
		primaryStage.show();
	}
	
	public static void main(String[] args) {
		launch(args);
	}

}
