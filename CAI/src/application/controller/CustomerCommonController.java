package application.controller;

import application.model.Flight;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;


public class CustomerCommonController implements CommonController {

	@FXML TabPane tabs;
	@FXML Tab homeTab;
	@FXML AnchorPane homePane;
	@FXML Tab flightsTab;
	@FXML Tab myFlightTab;
	@FXML Tab flightStatusTab;
	@FXML Tab checkinTab;
	@FXML AnchorPane flightsPane;


	public void initialize(){ // Load all scenes into tabs
		try {
			FXMLLoader flightsLoader = new FXMLLoader(getClass().getResource("../view/employee_all_flights.fxml"));
			flightsTab.setContent(flightsLoader.load());
			FlightsViewController flightsViewController = flightsLoader.getController();
			flightsViewController.parentToNotify = this;
			FXMLLoader myFlightsLoader = new FXMLLoader(getClass().getResource("../view/customer_my_flights.fxml"));
			myFlightTab.setContent(myFlightsLoader.load());
			FXMLLoader flightStatusLoader = new FXMLLoader(getClass().getResource("../view/flight_status.fxml"));
			flightStatusTab.setContent(flightStatusLoader.load());
			FXMLLoader checkinLoader = new FXMLLoader(getClass().getResource("../view/check_in.fxml"));
			checkinTab.setContent(checkinLoader.load());
			// Preload other scenes


		} catch	(IOException e){

		}
	}
	
	public void HandleLogout(ActionEvent event) {
		Pane login;
		try {
			login = FXMLLoader.load(getClass().getResource("../view/Login.fxml"));
			Stage stage = new Stage();
			stage.setScene(new Scene(login));
			stage.show();
			Stage currentStage = (Stage) tabs.getScene().getWindow();
		    currentStage.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void handleFlightNext(int numSeats) {
		try {
			Pane flightsCheckoutScene = FXMLLoader.load(getClass().getResource("../view/employee_all_flights.fxml"));
			flightsTab.setContent(flightsCheckoutScene);
		} catch (IOException e){

		}
	}

	@Override
	public void handleBeginCheckout(Flight flight) {
		try {
			FXMLLoader flightsCheckoutBeginLoader = new FXMLLoader(getClass().getResource("../view/customer_flight_checkout.fxml"));
			flightsTab.setContent(flightsCheckoutBeginLoader.load());
			FlightCheckoutController checkoutController = flightsCheckoutBeginLoader.getController();
			//checkoutController.parentToNotify = this;
			checkoutController.initialize(flight, this);
		} catch (IOException e){

		}
	}

	@Override
	public void goToEnd(Flight flight) {
		try {
			FXMLLoader flightsCheckoutBeginLoader = new FXMLLoader(getClass().getResource("../view/purchase_confirmation.fxml"));
			flightsTab.setContent(flightsCheckoutBeginLoader.load());
			PurchaseConfirmationController checkoutController = flightsCheckoutBeginLoader.getController();
			//checkoutController.parentToNotify = this;
			checkoutController.initialize(flight, this);
		} catch (IOException e){

		}
	}
}