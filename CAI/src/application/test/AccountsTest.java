package application.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.model.DB;
import application.model.Flight;
import application.model.User;

import org.junit.*;
import java.util.*;
import java.awt.*;
import java.lang.*;


class AccountsTest {

	@Test
	void testAddAccount() throws Exception {
		DB db = DB.getDB();
		db.addAccount("userTest", "passTest");
		User testuser = db.checkAccount("userTest", "passTest");
		assertFalse(testuser == null);
	}
	
	@Test
	void testCheckAccount() throws Exception {
		DB db = DB.getDB();
		User testuser = db.checkAccount("testnouser", "testnopass");
		assertTrue(testuser == null);
	}
	
	@Test
	void testCheckUserPrivilege() throws Exception {
		DB db = DB.getDB();
		User testuser = db.checkAccount("user", "pass");
		assertFalse(testuser == null);
		assertEquals(testuser.getUser(), "user");
		assertEquals(testuser.getPrivilege(), 0);
	}
	
	@Test
	void testFlights() {
		DB db = DB.getDB();
		ArrayList<Flight> flights = db.getFlights();
		assertEquals(flights.get(0).toString(), "CA01 2018-25-05 04:20 ONTIME 40");
	}

}
