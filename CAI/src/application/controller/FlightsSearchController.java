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
	@FXML TableColumn<Flight, Integer> priceCol;
	@FXML ChoiceBox<Integer> numSeats;
	@FXML Label priceDisplay;

	public void initialize(){
		DB db = DB.getDB();
		ObservableList<Integer> availableChoices = FXCollections.observableArrayList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10);
		numSeats.setItems(availableChoices);
		// Associate Flight fields with table columns with JavaFX magic that somehow figures out method names
		flightNumCol.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("FlightId"));
		dateCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("DepartDate"));
		departTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("DepartTime"));
		arriveTimeCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("ArrivalTime"));
		seatsAvailable.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("EmptySeats"));
		departCityCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("DepartCity"));
		arriveCityCol.setCellValueFactory(new PropertyValueFactory<Flight, String>("ArriveCity"));
		priceCol.setCellValueFactory(new PropertyValueFactory<Flight, Integer>("Price"));
		// Populate table
		ArrayList<Flight> flightList = db.getFlights();
		flightsTable.getItems().addAll(flightList);
	}

	public void HandleCheckoutClick(ActionEvent event) throws Exception{
		try {
			TablePosition pos = flightsTable.getSelectionModel().getSelectedCells().get(0);
			int row = pos.getRow();
			int id = flightsTable.getItems().get(row).getFlightId();

			System.out.println(id);
			DB db = DB.getDB();
			Flight flight = db.getFlight(id);

			ObservableList<TablePosition> selectedCells = flightsTable.getSelectionModel().getSelectedCells();
			//int flightNum = selectedCells;

			FXMLLoader Loader = new FXMLLoader();
			Loader.setLocation(getClass().getResource("../view/purchase_confirmation.fxml"));
			try {
				Loader.load();
			}
			catch(Exception e){

			}

			PurchaseConfirmationController controller = Loader.getController();
			controller.setFlight(flight);


			FXMLLoader flightsCheckoutBeginLoader = new FXMLLoader(getClass().getResource("../view/purchase_confirmation.fxml"));
			PurchaseConfirmationController checkoutController = flightsCheckoutBeginLoader.getController();
			//checkoutController.parentToNotify = this;
			checkoutController.initialize(flight, new CustomerCommonController());

			Pane selectTimes = FXMLLoader.load(getClass().getResource("../view/purchase_confirmation.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(selectTimes));
			stage.show();
		}
		catch(Exception e)
		{
			return;
		}
	}
}