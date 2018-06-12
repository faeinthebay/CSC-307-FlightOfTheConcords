package application.controller;

import application.model.Flight;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


public class PurchaseConfirmationController {

	@FXML TableView<Flight> flightTable;
	@FXML TableColumn<Flight, Integer> flightNumCol;
	@FXML TableColumn<Flight, String> departCityCol;
	@FXML TableColumn<Flight, String> dateCol;
	@FXML TableColumn<Flight, String> departTimeCol;
	@FXML TableColumn<Flight, String> arriveCityCol;
	@FXML TableColumn<Flight, String> arriveTimeCol;
	@FXML TableColumn<Flight, Integer> seatsAvailable;
	Flight flight;

	public void initialize(Flight flight, CommonController parentToNotify){
		// Associate Flight fields with table columns with JavaFX magic that somehow figures out method names
		// TODO: Lookup depart city by route
		flightNumCol.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("FlightId"));
		dateCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("DepartDate"));
		departTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("DepartTime"));
		arriveTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("ArrivalTime"));
		seatsAvailable.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("EmptySeats"));
		// Populate table
		flightTable.getItems().add(flight);
		// Populate seats drop-down
		ObservableList<Integer> seatOptions = FXCollections.observableArrayList();
		for(int i = 0; i < flight.getEmptySeats(); i++){
			seatOptions.add(i);
		}
	}

	public void setFlight(Flight flight){
		this.flight = flight;
	}
}