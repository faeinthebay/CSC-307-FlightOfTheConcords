package application.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumingThat;

import org.junit.jupiter.api.Test;

import application.model.DB;
import application.model.Flight;
import application.model.User;

import org.junit.*;
import java.util.*;
import java.awt.*;
import java.lang.*;


class FlightTest {

	@Test
	void TestUpdateSeats() throws Exception {
		DB db = DB.getDB();
		Flight testFlight = db.getFlight(1);
		int seats = 10;
		int expected = testFlight.getEmptySeats() - seats;
		testFlight.updateSeats(seats);
		
		assertTrue(testFlight.getEmptySeats() == expected);
	}
	
	/**Works fine, but capacity isn't initialized. Additionally, the newCapacity is ADDED to the old capacity, it does not change it **/
	@Test
	void TestUpdateCapacity() throws Exception {
		DB db = DB.getDB();
		Flight testFlight = db.getFlight(1);
		int newCapacity = 10;
		int expected = testFlight.getCapacity() + newCapacity;
		System.out.println(expected);
		testFlight.updateCapacity(newCapacity);
		
		System.out.println(testFlight.getCapacity());
		
		assertTrue(testFlight.getCapacity() == expected);
	}

	/** departTime is a string in the Flight class, so updating the departTime by adding the newTime doesn't work **/
	@Test
	void TestUpdateDT() throws Exception {
		DB db = DB.getDB();
		Flight testFlight = db.getFlight(1);
		int newTIme = 10;
		//int expected = 10 + testFlight.getDepartTime();
		//testFlight.updateSeats(seats);
		
		//assertTrue(testFlight.getEmptySeats() == expected);
	}
	
	@Test
	void TestUpdateROute() throws Exception {
		DB db = DB.getDB();
		Flight testFlight = db.getFlight(1);
		String newRoute = "CA02";
		assertFalse(testFlight.getRoute().equals(newRoute));
		testFlight.updateRoute(newRoute);
		assertTrue(testFlight.getRoute().equals(newRoute));
	}
	
}