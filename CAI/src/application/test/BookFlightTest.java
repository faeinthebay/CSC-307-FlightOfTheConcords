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
	void testCreatedReservation() throws Exception {
		DB db = DB.getDB();
		Flight testFlight = db.getFlight(1);
		BookFlight testBook = new BookFlight(testFlight, 40);
		assertTrue(testBook.createReservation("Michael") == true);
		
		BookFlight testBook2 = new BookFlight(testFlight, 1);
		assertFalse(testBook2.createReservation("Ayan") == true);
	}
	
	void testValidReservation() throws Exception {
		DB db = DB.getDB();
		Flight testFlight = db.getFlight(1);
		BookFlight testBook = new BookFlight(testFlight, 20);
		assertFalse(testBook.validReservation() == false);
	}
	


}
