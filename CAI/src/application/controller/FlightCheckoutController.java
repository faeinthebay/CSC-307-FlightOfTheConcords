package application.controller;

import application.model.DB;
import application.model.Flight;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.ArrayList;


public class FlightCheckoutController {

	@FXML TableView<Flight> flightTable;
	@FXML TableColumn<Flight, Integer> flightNumCol;
	@FXML TableColumn<Flight, String> departCityCol;
	@FXML TableColumn<Flight, String> dateCol;
	@FXML TableColumn<Flight, String> departTimeCol;
	@FXML TableColumn<Flight, String> arriveCityCol;
	@FXML TableColumn<Flight, String> arriveTimeCol;
	@FXML TableColumn<Flight, Integer> seatsAvailable;
	@FXML Label seatPrice;
	@FXML Label totalPrice;
	@FXML ComboBox<Integer> numTicketsDesired;
	@FXML Button beginCheckout;

	public void initialize(Flight flight, CommonController parentToNotify){
		// Associate Flight fields with table columns with JavaFX magic that somehow figures out method names
		// TODO: Lookup depart city by route
		flightNumCol.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("FlightId"));
		dateCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("DepartDate"));
		departTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("DepartTime"));
		arriveTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("ArrivalTime"));
		seatsAvailable.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("EmptySeats"));
		// Populate table
		flightTable.getItems().clear();
		flightTable.getItems().add(flight);
		// Populate seats drop-down
		ObservableList<Integer> seatOptions = FXCollections.observableArrayList();
		for(int i = 0; i < flight.getEmptySeats(); i++){
			seatOptions.add(i);
		}
	//	numTicketsDesired.getItems().setAll(1,2,3,4,5,6,7,8,9,10);
		numTicketsDesired.setItems(seatOptions);
		numTicketsDesired.getSelectionModel().selectedItemProperty().addListener(
				new ChangeListener<Integer>() {
					@Override
					public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
						totalPrice.setText(Integer.toString(flight.getPrice() * newValue));
					}
				}
		);
		// Populate form fields
		seatPrice.setText(Integer.toString(flight.getPrice()));
		// Load next scene with next button
		beginCheckout.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				parentToNotify.handleFlightNext(numTicketsDesired.getValue());
			}
		});
	}
}