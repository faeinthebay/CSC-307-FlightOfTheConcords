package application.controller;

import application.model.Flight;

public interface CommonController {

	public void handleFlightNext(int numSeats);

	public void handleBeginCheckout(Flight flight);

	public void goToEnd(Flight flight);

}