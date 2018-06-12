package application.controller;

import application.model.DB;
import application.model.Flight;
import application.model.FlightScheduler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;


public class ModifyFlightController {

	@FXML TableView<Flight> flightTable;
	@FXML TableColumn<Flight, Integer> flightNumCol;
	@FXML TableColumn<Flight, String> departCityCol;
	@FXML TableColumn<Flight, String> dateCol;
	@FXML TableColumn<Flight, String> departTimeCol;
	@FXML TableColumn<Flight, String> arriveCityCol;
	@FXML TableColumn<Flight, String> arriveTimeCol;
	@FXML TableColumn<Flight, Integer> seatsAvailable;

	@FXML DatePicker departureDateField;
	@FXML ChoiceBox<String> departureTimeField;
	@FXML ComboBox statusField;
	@FXML Button submitButton;

	EmployeeCommonController parentToNotify;

	public void initialize(Flight flight, EmployeeCommonController parentToNotify){
		// Associate Flight fields with table columns with JavaFX magic that somehow figures out method names
		flightNumCol.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("FlightId"));
		dateCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("DepartDate"));
		departTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("DepartTime"));
		arriveTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("ArrivalTime"));
		seatsAvailable.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("EmptySeats"));
		// Populate table
		flightTable.getItems().add(flight);
		// Prepare fields
		ObservableList<String> statuses = FXCollections.observableArrayList("ONTIME", "DELAYED", "CANCELED");
		statusField.setItems(statuses);
		ObservableList<String> times = FXCollections.observableArrayList(FlightScheduler.getRunwayTimesList());
		departureTimeField.setItems(times);
		statusField.getSelectionModel().select(statuses.indexOf(flight.getStatus()));
		departureTimeField.getSelectionModel().select(times.indexOf(flight.getDepartTime()));
		departureDateField.setValue(flight.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
		Flight finalFlight = flight;
		submitButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) { // TODO: Prefill fields
				finalFlight.changeDate(Date.from(departureDateField.getValue().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant()));
				finalFlight.setDepartTime(departureTimeField.getValue());
				finalFlight.updateStatus((String)(statusField.getValue()));
				DB db = DB.getDB();
				db.updateFlight(finalFlight);
				parentToNotify.backToMainModify();
			}
		});
	}
}