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


public class TicketholderController {

	@FXML Button nextStep;

	public void initialize(Flight flight, CommonController parentToNotify){
		// Load next scene with next button
		nextStep.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				parentToNotify.goToEnd(flight);
			}
		});
	}
}