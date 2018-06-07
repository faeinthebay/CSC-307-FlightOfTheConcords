package application.controller;

import application.model.DB;
import application.model.Flight;
import application.model.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class AccountsManagerController {

	@FXML TextField username;
	@FXML Button promotebutton;
	@FXML Button demotebutton;
	@FXML TableView<User> userTable;
	@FXML TableColumn<User, String> usernameColumn;
	@FXML TableColumn<User, String> firstnameColumn;
	@FXML TableColumn<User, String> lastnameColumn;
	@FXML TableColumn<User, String> isEmployeeColumn;

	public void HandlePromotion(ActionEvent event) {
		DB db = DB.getDB();
		db.updateUserPrivilege(new User(username.getText(), 1));
		username.clear();
	}

	public void HandleDemotion(ActionEvent event) {
		DB db = DB.getDB();
		db.updateUserPrivilege(new User(username.getText(), 0));
		username.clear();
	}

	public void initialize(){
		// Associate User fields with table columns with JavaFX magic that somehow figures out method names
		usernameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("User"));
		firstnameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Firstname"));
		lastnameColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Lastname"));
		isEmployeeColumn.setCellValueFactory(new PropertyValueFactory<User, String>("Privilege"));
		// Populate table
		DB db = DB.getDB();
		ArrayList<User> userList = db.getAccounts();
		userTable.getItems().addAll(userList);
	}

}