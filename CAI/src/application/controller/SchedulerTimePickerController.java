package application.controller;

import application.model.DB;
import javafx.beans.*;
import application.model.Flight;
import application.model.FlightScheduler;
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


public class SchedulerTimePickerController {

	@FXML Button confirm;
	@FXML ListView list;

	public void HandleConfirm(ActionEvent event) {
		String time = FlightScheduler.getRunwayTimes().get(list.getSelectionModel().getSelectedIndex());
		FlightScheduler.createFlight(time);
		
		Stage currentStage = (Stage) confirm.getScene().getWindow();
	    currentStage.close();
	}

	public void initialize(){
		 
		ArrayList<String> runwayTimes = FlightScheduler.getRunwayTimes();
		for(String time : runwayTimes)
		list.getItems().add(time);		
	}

}