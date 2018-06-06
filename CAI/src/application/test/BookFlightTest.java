package application.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.model.DB;
import application.model.Flight;
import application.model.User;
import application.model.BookFlight;

import org.junit.*;
import java.util.*;
import java.awt.*;
import java.lang.*;


class BookFlightTest {

	@Test
	void testValidReservation() throws Exception {
		DB db = DB.getDB();
		Flight testFlight = db.getFlight(1);
		BookFlight testBook = new BookFlight(testFlight, 20);
		assertFalse(testBook.validReservation() == false);
	}
	
	/*
	@Test
	void testCheckAccount() throws Exception {
		DB db = DB.getDB();
		User testuser = db.checkAccount("testnouser", "testnopass");
		assertTrue(testuser == null);
	}
	
	@Test
	void testCheckUserPrivilege() throws Exception {
		DB db = DB.getDB();
		User testuser = db.checkAccount("ayan", "patel");
		assertFalse(testuser == null);
		assertEquals(testuser.getUser(), "ayan");
		assertEquals(testuser.getPrivilege(), 0);
	}
	
	@Test
	void testFlights() {
		DB db = DB.getDB();
		ArrayList<Flight> flights = db.getFlights();
		assertEquals(flights.get(0).toString(), "CA01 2018-25-05 04:20 ONTIME 40");
	}*/

}
