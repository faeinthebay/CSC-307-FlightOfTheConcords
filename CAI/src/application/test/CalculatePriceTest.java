package application.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import application.model.DB;
import application.model.Flight;
import application.model.User;
import application.model.Calculate_Price;

import org.junit.*;
import java.util.*;
import java.awt.*;
import java.lang.*;

public class CalculatePriceTest {

	
	/**The calculate price module is supposed to get the average VALUE of the empty seats,
	 * not the number of empty seats. 
	 * Even then, the (emptySeats/2) portion incorrectly divides two integers. empty/2 should be casted as floats **/
	@Test
	void testCalculatePrice() throws Exception {
		DB db = DB.getDB();
		String routeID = "CA01";
		float expected  = -260;
		//float actual = new Calculate_Price().calculate(routeID, 15);
		//System.out.println(actual);
	//	assertTrue(actual == expected);
	}
	
}
