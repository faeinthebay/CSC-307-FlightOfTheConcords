package application.controller;

import application.model.DB;
import application.model.Flight;
import application.model.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;


public class EmployeeCommonController implements CommonController {

	@FXML TabPane tabs;
	@FXML Tab homeTab;
	@FXML AnchorPane homePane;
	@FXML Tab flightsTab;
	@FXML Tab modifyFlightsTab;
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
			FXMLLoader modifyListLoader = new FXMLLoader(getClass().getResource("../view/employee_flight_modify_list.fxml"));
			modifyFlightsTab.setContent(modifyListLoader.load());
			FlightsModifyListController modifyListController = modifyListLoader.getController();
			modifyListController.parentToNotify = this;
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
			Pane flightsCheckoutScene = FXMLLoader.load(getClass().getResource("../view/ticketholder_info.fxml"));
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

	public void handleBeginModify(Flight flight) {
		try {
			FXMLLoader modifyLoader = new FXMLLoader(getClass().getResource("../view/modify_flight.fxml"));
			modifyFlightsTab.setContent(modifyLoader.load());
			ModifyFlightController modifyController = modifyLoader.getController();
			//checkoutController.parentToNotify = this;
			modifyController.initialize(flight, this);
		} catch (IOException e){

		}
	}

	public void backToMainModify(){
		try {
			FXMLLoader modifyLoader = new FXMLLoader(getClass().getResource("../view/employee_flight_modify_list.fxml"));
			modifyFlightsTab.setContent(modifyLoader.load());
			FlightsModifyListController modifyListController = modifyLoader.getController();
			modifyListController.parentToNotify = this;
		}catch (IOException e){

		}
	}
}