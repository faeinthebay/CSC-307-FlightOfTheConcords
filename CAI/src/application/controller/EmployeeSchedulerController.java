package application.controller;

import java.util.ArrayList;
import application.model.DB;
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
import java.time.LocalDate;
import java.util.ArrayList;

public class EmployeeSchedulerController {

	@FXML ChoiceBox<String> departureCity;
	@FXML ChoiceBox<String> arrivalCity;
	@FXML DatePicker flightDate;
	@FXML Label label;
	
	public void initialize(){
		ObservableList<String> availableChoices = FXCollections.observableArrayList("SLO", "LAX", "SFO", "SDO", "ARZ", "STL", "DAL"); 
		departureCity.setItems(availableChoices);
		arrivalCity.setItems(availableChoices);	
	}
	
	public void HandleScheduleFlightButtonClick(ActionEvent event) throws Exception{
		try
		{
			label.setText("");
			DB db = DB.getDB();
			String depart = departureCity.getSelectionModel().getSelectedItem();
			String arrive = arrivalCity.getSelectionModel().getSelectedItem();
			
			LocalDate date = flightDate.getValue();
			
			if(date.isBefore(LocalDate.now())) //Before today's date
			{
				label.setText("Date invalid");
				return;
			}
			
			if(depart == null || arrive == null || depart == arrive) //Invalid airport selections
			{
				label.setText("Route invalid");
				return;
			}
			else if(depart != "SLO" && arrive != "SLO") //Neither airport is SLO
			{
				label.setText("Route invalid");
				return;
			}
			
			
			if(FlightScheduler.AvailableFlight(depart, arrive, date) == false) //Already 2 for that route this week
			{
				label.setText("Already two flights from " + depart + " to " + arrive + " this week.");
			}
			else
			{
				System.out.println("ELSE");
			}
			
			Pane selectTimes = FXMLLoader.load(getClass().getResource("../view/scheduler_time_picker.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(selectTimes));
			stage.show();
		}
		catch (Exception e)
		{
			return;
		}
	}

}