package tuskiesOnlineShop;

import java.io.File;
import java.net.MalformedURLException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

public class Controller {

    @FXML private ImageView imageView;
    @FXML private Label fileSelected;

    private String imageFile;

    public void pickImage (ActionEvent actionEvent) throws MalformedURLException {

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Select Image File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files",
                        "*.bmp", "*.png", "*.jpg", "*.gif")); // limit fileChooser options to image files
        File selectedFile = fileChooser.showOpenDialog(fileSelected.getScene().getWindow());

        if (selectedFile != null) {

            imageFile = selectedFile.toURI().toURL().toString();

            Image image = new Image(imageFile);
//            imageView.setImage(image);
        } else {
            fileSelected.setText("Image file selection cancelled.");
        }

    }
}

