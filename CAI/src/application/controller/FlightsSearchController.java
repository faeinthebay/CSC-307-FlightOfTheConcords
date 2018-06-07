package application.controller;

import application.model.DB;
import application.model.Flight;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;


public class FlightsSearchController {

	@FXML TableView<Flight> flightsTable;
	@FXML TableColumn<Flight, Integer> flightNumCol;
	@FXML TableColumn<Flight, String> departCityCol;
	@FXML TableColumn<Flight, String> dateCol;
	@FXML TableColumn<Flight, String> departTimeCol;
	@FXML TableColumn<Flight, String> arriveCityCol;
	@FXML TableColumn<Flight, String> arriveTimeCol;
	@FXML TableColumn<Flight, Integer> seatsAvailable;

	public void initialize(){
		DB db = DB.getDB();
		// Associate Flight fields with table columns with JavaFX magic that somehow figures out method names
		// TODO: Lookup depart city by route
		flightNumCol.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("FlightId"));
		dateCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("DepartDate"));
		departTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("DepartTime"));
		arriveTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("ArrivalTime"));
		seatsAvailable.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("EmptySeats"));
		departCityCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("DepartCity"));
		arriveCityCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("ArriveCity"));
		// Populate table
		ArrayList<Flight> flightList = db.getFlights();
		flightsTable.getItems().addAll(flightList);
	}

}