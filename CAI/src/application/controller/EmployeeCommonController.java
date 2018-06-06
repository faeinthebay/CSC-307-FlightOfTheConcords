package application.controller;

import application.model.DB;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.util.ArrayList;


public class EmployeeCommonController {

	@FXML TabPane tabs;
	@FXML Tab homeTab;
	@FXML AnchorPane homePane;
	@FXML Tab accountTab;
	@FXML AnchorPane accountPane;

	// TODO: When tab is clicked, reset to first scene?
	// TODO: Test if this will memory leak (deselected tabs keep contents in memory?)
	public void initialize(){ // Load all scenes into tabs
		try {
			Pane accountScene = FXMLLoader.load(getClass().getResource("../view/employee_accounts_manager.fxml"));
			accountTab.setContent(accountScene);
		} catch	(IOException e){ // TODO

		}
	}

}