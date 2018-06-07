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
	@FXML Tab flightsTab;
	@FXML Tab accountTab;
	@FXML Tab schedulerTab;
	@FXML AnchorPane flightsPane;
	@FXML AnchorPane accountPane;
	@FXML AnchorPane schedulerPane;

	// TODO: When tab is clicked, reset to first scene?
	// TODO: Test if this will memory leak (deselected tabs keep contents in memory?)
	public void initialize(){ // Load all scenes into tabs
		try {
			Pane accountScene = FXMLLoader.load(getClass().getResource("../view/employee_accounts_manager.fxml"));
			accountTab.setContent(accountScene);
			Pane flightsScene = FXMLLoader.load(getClass().getResource("../view/employee_all_flights.fxml"));
			flightsTab.setContent(flightsScene);
			Pane schedulerScene = FXMLLoader.load(getClass().getResource("../view/employee_scheduler.fxml"));
			schedulerTab.setContent(schedulerScene);
		} catch	(IOException e){ // TODO

		}
	}

}