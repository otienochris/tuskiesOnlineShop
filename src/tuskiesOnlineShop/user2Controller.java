package tuskiesOnlineShop;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;

public class user2Controller implements Initializable{

    @FXML
    private TableColumn<Users2, String> colName;

    @FXML
    private TableColumn<Users2, String> colNotes;

    @FXML
    private TableView<Users2> userTable;

    @FXML
    private TableColumn<Users2, String> colId;

    @FXML
    private TableColumn<Users2, String> colEmail;

    @FXML
    private TableColumn<Users2, Button> colUpdate;
    
    
    private void initTable() {
		// TODO Auto-generated method stub
    	initCols();
	}
    
    private void initCols() {
		// TODO Auto-generated method stub
    	colId.setCellValueFactory(new PropertyValueFactory<>("id"));
    	colName.setCellValueFactory(new PropertyValueFactory<>("name"));
    	colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
    	colNotes.setCellValueFactory(new PropertyValueFactory<>("notes"));
    	colUpdate.setCellValueFactory(new PropertyValueFactory<>("updateButton"));
    	
    	editableCols();
	}
    
    private void editableCols() {
		// TODO Auto-generated method stub
    	colId.setCellFactory(TextFieldTableCell.forTableColumn());
    	colId.setOnEditCommit(e -> {
    		int row = e.getTablePosition().getRow();
    		e.getTableView().getItems().get(row).setId(e.getNewValue());
    	});
   
    	colName.setCellFactory(TextFieldTableCell.forTableColumn());
    	colName.setOnEditCommit(e -> {
    		int row = e.getTablePosition().getRow();
    		e.getTableView().getItems().get(row).setName(e.getNewValue());
    	});
    	
    	colEmail.setCellFactory(TextFieldTableCell.forTableColumn());
    	colEmail.setOnEditCommit(e -> {
    		int row = e.getTablePosition().getRow();
    		e.getTableView().getItems().get(row).setEmail(e.getNewValue());
    	});
    	
    	colNotes.setCellFactory(TextFieldTableCell.forTableColumn());
    	colNotes.setOnEditCommit(e -> {
    		int row = e.getTablePosition().getRow();
    		e.getTableView().getItems().get(row).setNotes(e.getNewValue());
    	});
    	
    	userTable.setEditable(true);
	}
    
    private void loadData() {
		ObservableList<Users2> tableData = FXCollections.observableArrayList();
		
		for (int i = 0; i < 7; i++) {
			tableData.add(new Users2(String.valueOf(i), "name" + i, "email" + i, "notes " + i, 
					new Button("Update")));
		}
		userTable.setItems(tableData);
		
	}

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		// TODO Auto-generated method stub
		initTable();
		loadData();
	}
}
