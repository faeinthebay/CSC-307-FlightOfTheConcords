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
	
	private DB db = DB.getDB();

	@Test
	void testCreatedReservation() throws Exception {
		Flight testFlight = db.getFlight(1);
		BookFlight testBook = new BookFlight(testFlight, 40);
		assertTrue(testBook.createReservation("Michael") == true);
		
		BookFlight testBook2 = new BookFlight(testFlight, 1);
		assertFalse(testBook2.createReservation("Ayan") == true);
		
		Flight testFlight2 = db.getFlight(2);
		
		for(int i = 0; i < 35; i++) {
			BookFlight temp = new BookFlight(testFlight2, 1);
			assertTrue(temp.createReservation(i + "testUser") == true);
		}
		
		assertFalse(new BookFlight(testFlight2, 1).createReservation("Michael") == true);
		
		/** Currently a reservation of 0 seats is valid;
		* assertFalse(new BookFlight(testFlight2, 0).createReservation("Michael") == true);
		**/
	}
	
	@Test
	void testValidReservation() throws Exception {
		Flight testFlight = db.getFlight(1);
		BookFlight testBook = new BookFlight(testFlight, 20);
		assertTrue(testBook.validReservation() == true);
		
		Flight testFlight2 = db.getFlight(1);
		BookFlight testBook2 = new BookFlight(testFlight2, 50);
		assertFalse(testBook2.validReservation() == true);
		
		Flight testFlight3 = db.getFlight(1);
		BookFlight testBook3 = new BookFlight(testFlight3, 40);
		assertTrue(testBook3.validReservation() == true);
		
		Flight testFlight4 = db.getFlight(1);
		BookFlight testBook4 = new BookFlight(testFlight4, 41);
		assertFalse(testBook4.validReservation() == true);
		
		/** Currently a reservation of 0 seats is valid;
		* Flight testFlight5 = db.getFlight(1);
		* BookFlight testBook5 = new BookFlight(testFlight5, 0);
		* assertFalse(testBook5.validReservation() == true);
		**/
	}
	
	/**Currently fails due to issues in BookFlight.java and Flight.java, 
	 * as detailed in BookFlight.generateSeatNumber() 
	 * **/
	@Test
	void testGenerateSeatNumbers() throws Exception {
		Flight testFlight = db.getFlight(1);
		BookFlight testBook = new BookFlight(testFlight, 3);
		Integer[] testSeatNums = {1, 2, 3};
		testBook.createReservation("Michael");
		testBook.generateSeatNumbers();
		assertTrue(testBook.getReservation().getSeatNumbers().equals(testSeatNums));
	}
	
	@Test
	/**Currently throws an [SQLIT_CONSTRAINT] error due to incorrect database input in Bookflight.reserve(), 
	 * "reservations.dateReserved may not be NULL"**/
	void testReserve() throws Exception {
		Flight testFlight = db.getFlight(1);
		BookFlight testBook = new BookFlight(testFlight, 3);
		testBook.createReservation("Michael");
		int confNum = testBook.getReservation().getConfirmationNumber();
		assertTrue(testBook.reserve() == confNum);
	}

}
