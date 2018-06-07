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

public class EmployeeSchedulerController {

	@FXML ChoiceBox<String> departureCity;
	@FXML ChoiceBox<String> arrivalCity;
	
	public void initialize(){
		ObservableList<String> availableChoices = FXCollections.observableArrayList("SLO", "LAX", "SFO", "SDO", "ARZ", "STL", "DAL"); 
		departureCity.setItems(availableChoices);
		arrivalCity.setItems(availableChoices);
		System.out.println("Test");
	}
	
	public void HandleScheduleFlightButtonClick(ActionEvent event) throws Exception{
		System.out.println("CLICK!");
		try
		{
			DB db = DB.getDB();
			String depart = departureCity.getSelectionModel().getSelectedItem();
			String arrive = arrivalCity.getSelectionModel().getSelectedItem();
			
			if(depart == null || arrive == null || depart == arrive) //Invalid selections
			{
				System.out.println("returning");
				return;
			}
			
			
		}
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}