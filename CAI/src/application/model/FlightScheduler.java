package application.model;

import java.util.ArrayList;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class FlightScheduler
{
	private static String routeID; 
	private static LocalDate date;
	private static DB db;
	private static ArrayList<Flight> flights;
	private static ArrayList<String> runwayTimes;
    
	public static boolean AvailableFlight(String departCity, String arriveCity, LocalDate ldate)
	{			
		date = ldate;
		
		setRouteID(departCity, arriveCity);
		
		db = DB.getDB();
		flights = db.getFlights();	
		
		
    	if(checkRoute(routeID) == false) //2 or more flights already scheduled
    	{
    		return false;
    	}
    	return true;
	}
	
	public static ArrayList<String> getRunwayTimes()
	{
		ArrayList<String> times = new ArrayList<String>();   	
    	times.add("06:00");
    	times.add("06:40");
    	times.add("07:20");
    	times.add("08:00");
    	times.add("08:40");
    	times.add("09:20");
    	times.add("10:00");
    	times.add("10:40");
    	times.add("11:20");
    	times.add("12:00");
    	times.add("12:40");
    	times.add("13:20");
    	times.add("14:00");
    	times.add("14:40");
    	times.add("15:20");
    	times.add("16:00");
    	times.add("16:40");
    	times.add("17:20");
    	times.add("18:00");
    	times.add("18:40");
    	times.add("19:20");
    	times.add("20:00");
    	times.add("20:40");
    	times.add("21:20");
    	times.add("22:00");
    	times.add("22:40");
    	times.add("23:20");
    	times.add("24:00");
    	
    	runwayTimes = getRunwayTimes(flights, times);
    	
    	return runwayTimes;
	}
    
	private static void setRouteID(String departCity, String arriveCity)
	{
		if(departCity == "SLO")
		{
			if(arriveCity == "LAX")
				routeID = "CA01";
			else if(arriveCity == "SFO")
				routeID = "CA02";
			else if(arriveCity == "SDO")
				routeID = "CA03";
			else if(arriveCity == "ARZ")
				routeID = "CA04";
			else if(arriveCity == "STL")
				routeID = "CA05";
			else if(arriveCity == "DAL")
				routeID = "CA06";
		}
		else //arriveCity is SLO
		{
			if(departCity == "LAX")
				routeID = "CA07";
			else if(departCity == "SFO")
				routeID = "CA08";
			else if(departCity == "SDO")
				routeID = "CA09";
			else if(departCity == "ARZ")
				routeID = "CA10";
			else if(departCity == "STL")
				routeID = "CA11";
			else if(departCity == "DAL")
				routeID = "CA12";
		}
	}
	
    private static boolean checkRoute(String routeID)
    {    	
    	int counter = 0;
    	
    	Calendar cal1 = Calendar.getInstance();
    	cal1.setTime(java.sql.Date.valueOf(date));
    	int week1 = cal1.get(Calendar.WEEK_OF_YEAR);
    	
    	ArrayList<Flight> routeFlights = db.getFlightsForRoute(routeID); 
    	
    	for(int i = 0; i < routeFlights.size(); i++)
    	{
    		Flight flight = routeFlights.get(i);
    		
    		Calendar cal2 = Calendar.getInstance();
    		cal2.setTime(flight.getDate());
    		int week2 = cal2.get(Calendar.WEEK_OF_YEAR);
    		
    		if(week1 == week2)
    		{
    			counter++;
    		}
    		
    		if(counter >= 2)
    		{
    			return false; //Route is full
    		}
    	}
    	
    	return true; //Route can have more flights
    }
	
    private static ArrayList<String> getRunwayTimes(ArrayList<Flight> flights, ArrayList<String> runwayTimes)
    {
    	for(int i = 0; i < flights.size(); i++) //Check each flight
    	{
    		Flight flight = flights.get(i);
    		
    		if(flight.getDate().equals(java.sql.Date.valueOf(date))) //The flight is using the airport on the date you are looking at
    		{
    			if(flight.getRoute().equals("CA01") || flight.getRoute().equals("CA02") || flight.getRoute().equals("CA03") || flight.getRoute().equals("CA04") || flight.getRoute().equals("CA05") || flight.getRoute().equals("CA06"))
    	    	{
    				//Flight departing from SLO
    				runwayTimes.remove(flight.getDepartTime());
    	    	}
    			else //Arriving in SLO
    			{
    				runwayTimes.remove(flight.getArrivalTime());
    			}
    		}
    	}
    	
    	return runwayTimes;
    }
    
    public static void createFlight(String time)
    {
    	if(routeID == "CA01" || routeID == "CA02" || routeID == "CA03" || routeID == "CA04" || routeID == "CA05" || routeID == "CA06")
    	{
    		//Departing from SLO
    		
    		int duration = db.getDuration(routeID);
    		//calculate the arrival time (if we need it)
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
    		String formattedDate = date.format(formatter);
    		Flight flight = new Flight(flights.size(), routeID, formattedDate, time, "ONTIME", 20, duration);
    		Calculate_Price.updatePrice(routeID, flight);
    		//Create flight and add it to DB, flights array
    		db.addFlight(flight);
    	}
    	else
    	{
    		//Arriving in SLO
    		
    		int duration = db.getDuration(routeID);
	  		//calculate the departure time (if we need it)
    		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yy");
    		String formattedDate = date.format(formatter);
    		Flight flight = new Flight(flights.size(), routeID, formattedDate, time, "ONTIME", 20, duration);
    		Calculate_Price.updatePrice(routeID, flight);
    		//Create flight and add it to DB, flights array
    		db.addFlight(flight);
    	}
			
    }

}
