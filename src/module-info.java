module javaFxDemo2 {
	requires javafx.graphics;
	requires javafx.controls;
	requires javafx.fxml;
	requires java.sql;
	requires javafx.base;
	opens tuskiesOnlineShop to javafx.graphics, javafx.fxml, javafx.controls, javafx.base;
}