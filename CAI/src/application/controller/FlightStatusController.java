package application.controller;

import application.model.DB;
import application.model.Flight;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TextField;

import java.util.ArrayList;

public class FlightStatusController {

		@FXML TableView<Flight> flightsTable;
		@FXML TableColumn<Flight, Integer> flightNumCol;
		@FXML TableColumn<Flight, String> departCityCol;
		@FXML TableColumn<Flight, String> dateCol;
		@FXML TableColumn<Flight, String> departTimeCol;
		@FXML TableColumn<Flight, String> arriveCityCol;
		@FXML TableColumn<Flight, String> arriveTimeCol;
		@FXML TableColumn<Flight, Integer> seatsAvailable;
		@FXML TableColumn<Flight, String> status;
		
		@FXML TextField flightNumber;
		@FXML Button statusButton;

		public void initialize(){
			// Associate Flight fields with table columns with JavaFX magic that somehow figures out method names
			// TODO: Lookup depart city by route
			flightNumCol.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("FlightId"));
			dateCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("DepartDate"));
			departTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("DepartTime"));
			arriveTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("ArrivalTime"));
			seatsAvailable.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("EmptySeats"));
			departCityCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("DepartCity"));
			arriveCityCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("ArriveCity"));
			status.setCellValueFactory(new PropertyValueFactory<Flight, String>("Status"));
		}
		
		public void UpdateStatusTable() {
			DB db = DB.getDB();
			// Populate table
			Flight flight = db.getFlight(Integer.parseInt(flightNumber.getText()));
			flightsTable.getItems().clear();
			flightsTable.getItems().add(flight);
		}
	
}
