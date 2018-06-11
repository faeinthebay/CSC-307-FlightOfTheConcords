package application.controller;

import java.awt.Desktop;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import application.model.CheckIn;
import application.model.DB;
import application.model.Flight;
import application.model.Reservation;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
<<<<<<< HEAD
import javafx.scene.control.ChoiceBox;
=======
>>>>>>> e7bc2f2642d990487aad396e68480fb5d8fe9021
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

public class CheckInController {

	@FXML TextField confirmationNumber;
	@FXML Button confirmationbutton;
	@FXML Button checkinbutton;
	@FXML TableView<Reservation> resTable;
	@FXML TableColumn<Reservation, Integer> confnumcol;
	@FXML TableColumn<Reservation, Integer> flightidcol;
	@FXML TableColumn<Reservation, Integer> pricecol;
	@FXML TableView<Reservation> flyerTable;
	@FXML TableColumn<Reservation, String>passengers;
	@FXML TableColumn<Reservation, String>seats;
<<<<<<< HEAD
	@FXML ChoiceBox<Integer> bags;
=======
	@FXML ComboBox<Integer> bags;
>>>>>>> e7bc2f2642d990487aad396e68480fb5d8fe9021
	
	
	public void initialize(){
		// Associate Flight fields with table columns with JavaFX magic that somehow figures out method names
		// TODO: Lookup depart city by route
		confnumcol.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("confNum"));
		flightidcol.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("flightId"));
		pricecol.setCellValueFactory(new PropertyValueFactory<Reservation, Integer>("price"));
		passengers.setCellValueFactory(new PropertyValueFactory<Reservation, String>("names"));
		seats.setCellValueFactory(new PropertyValueFactory<Reservation, String>("seats"));
	}
	
	public void checkInFlight() {
		CheckIn checkin = new CheckIn(Integer.parseInt(confirmationNumber.getText()));
		if (checkin.validReservation()) {
			Reservation reservation = checkin.getRes();
			DB db = DB.getDB();
			db.checkIn(reservation);
			UpdateTables(reservation);
		}
	}
		
	public void UpdateTables(Reservation reservation) {
		resTable.getItems().clear();
		flyerTable.getItems().clear();
		resTable.getItems().add(reservation);
		flyerTable.getItems().add(reservation);
		
	}
	
	public void baggagePolicy() {
		try {
            Desktop.getDesktop().browse(new URI("https://www.united.com/ual/en/us/fly/travel/baggage.html"));
        } catch (IOException e1) {
            e1.printStackTrace();
        } catch (URISyntaxException e1) {
            e1.printStackTrace();
        }
	}
		
}
