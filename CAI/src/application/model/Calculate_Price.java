package application.model;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Calculate_Price {
	
	public static void updatePrice(String routeId, Flight flight) {
		float totalSeats = 0;
		float emptySeats = 0;
		
		DB db = DB.getDB();
		ArrayList<Flight> flights = db.getFlightsForRoute(routeId);
		
		Calendar cal1 = Calendar.getInstance();
    	int week1 = cal1.get(Calendar.WEEK_OF_YEAR);
    	int week2 = week1-1;
    	
    	for(int i = 0; i < flights.size(); i++)
    	{
    		Flight flight2 = flights.get(i);
    		
    		Calendar cal2 = Calendar.getInstance();
    		cal2.setTime(flight2.getDate());
    		int weekCheck = cal2.get(Calendar.WEEK_OF_YEAR);
    		
    		if(weekCheck == week1 || weekCheck == week2)
    		{
    			totalSeats += flight2.getCapacity();
    			emptySeats += flight2.getEmptySeats();
    		}
    	}
		
		int price = (int)Math.floor((db.getBasePrice(routeId) - (((emptySeats/totalSeats) / 2) * db.getBasePrice(routeId))));
		flight.updatePrice(price);
	}
}
