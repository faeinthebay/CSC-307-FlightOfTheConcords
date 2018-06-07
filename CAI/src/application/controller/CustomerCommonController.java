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
	@FXML Tab accountTab;
	@FXML Tab schedulerTab;
	@FXML AnchorPane flightsPane;
	@FXML AnchorPane accountPane;
	@FXML AnchorPane schedulerPane;


	public void initialize(){ // Load all scenes into tabs
		try {
			Pane accountScene = FXMLLoader.load(getClass().getResource("../view/employee_accounts_manager.fxml"));
			accountTab.setContent(accountScene);
			FXMLLoader flightsLoader = new FXMLLoader(getClass().getResource("../view/employee_all_flights.fxml"));
			flightsTab.setContent(flightsLoader.load());
			FlightsViewController flightsViewController = flightsLoader.getController();
			flightsViewController.parentToNotify = this;
			Pane schedulerScene = FXMLLoader.load(getClass().getResource("../view/employee_scheduler.fxml"));
			schedulerTab.setContent(schedulerScene);
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
			FXMLLoader flightsCheckoutBeginLoader = new FXMLLoader(getClass().getResource("../view/flight_checkout.fxml"));
			flightsTab.setContent(flightsCheckoutBeginLoader.load());
			FlightCheckoutController checkoutController = flightsCheckoutBeginLoader.getController();
			//checkoutController.parentToNotify = this;
			checkoutController.initialize(flight, this);
		} catch (IOException e){

		}
	}
}