package application.model;

import java.util.ArrayList;

public class FlightScheduler
{
	private String routeID; 
	private String date;
	private ArrayList<Flight> flights;
	private int SLOTime;
	private ArrayList<String> runwayTimes;
	
    public FlightScheduler(String routeID, String date, ArrayList<Flight> flights)
    {
    	this.routeID = routeID;
    	this.date = date;
    	this.flights = flights;
    	
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
    	
    	runwayTimes = times;
    }
    
	/*Ask user for what day to schedule flight*/
	/*Ask for the route*/
	/*Ensure the route doesn't already have 2 round trip flights that week*/
	/*Check each 40 minute interval starting at 6AM ending at midnight.*/
    
    private boolean checkRoute(int routeID)
    {
    	int counter = 0;
    	
    	/*Go through all entries of the database and get their datetime field*/
    	/*If the flight date = this.date, check the route id*/
    	/*If the flight route id = this.route, counter++*/
    	
    	if(counter < 2)
    		return true;
    	else
    		return false;
    }
	
    private ArrayList<String> getRunwayTimes(ArrayList<Flight> flights, ArrayList<String> runwayTimes)
    {
    	for(int i = 0; i < flights.size(); i++) //Check each flight
    	{
    		Flight flight = flights.get(i);
    		
    		if(flight.getDepartDate().equals(date)) //The flight is using the airport on the date you are looking at
    		{
    			if(flight.getRoute().equals("CA01") || flight.getRoute().equals("CA02") || flight.getRoute().equals("CA03") || flight.getRoute().equals("CA04") || flight.getRoute().equals("CA05") || flight.getRoute().equals("CA06"))
    	    	{
    				//Flight arriving in SLO
    				runwayTimes.remove(flight.getArrivalTime());
    	    	}
    			else //Departing from SLO
    			{
    				runwayTimes.remove(flight.getDepartTime());
    			}
    		}
    	}
    	
    	return runwayTimes;
    }
    /*
    private int relevantTime(String routeID)
    {
    	if(routeID.equals("CA01") || routeID.equals("CA02") || routeID.equals("CA03") || routeID.equals("CA04") || routeID.equals("CA05") || routeID.equals("CA06"))
    	{
    		return departureTime;
    	}
    	else //If arrival time is the relevant time
    	{
    		//int duration = [QUERY DATABASE FOR ROUTE DURATION]
    		
    		
    	}
    }*/

}
