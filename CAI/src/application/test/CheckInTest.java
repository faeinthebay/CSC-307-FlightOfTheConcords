package application.test;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.model.*;

import org.junit.*;
import java.util.*;
import java.awt.*;
import java.lang.*;


class CheckInTest {

	/** Making a reservation is not properly updating the database, test does not pass **/
	@Test
	void testConfirmCheckIn() throws Exception {
		DB db = DB.getDB();
		CheckIn testCI = new CheckIn(012345);
		Boolean testCCI = testCI.confirmCheckIn();
		assertTrue(testCCI == false);
		
		BookFlight testBF = new BookFlight(db.getFlight(1), 1);
		testBF.createReservation("Michael");
		Reservation testRes = testBF.getReservation();
		testCI = new CheckIn(testRes.getConfirmationNumber());
		testCCI = testCI.confirmCheckIn();
		assertTrue(testCCI == true);
	}
	
}
