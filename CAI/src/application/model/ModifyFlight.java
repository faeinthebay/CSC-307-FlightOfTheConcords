package application.model;

public class ModifyFlight
{
	//Gap required between landing and takeoff in minutes
	private static final float GAP = 40;
	
	
	
	//modifies the capacity of the flight
	/*public static void modifyCapacity(Flight flight, int newCapacity) {
		flight.updateCapacity(newCapacity);
	}*/
	/*
	//modifies the route of the plane (simply WHICH route, not the route itself)
	public static void modifyRoute(Flight flight, String newRoute) {
		flight.updateRoute(newRoute);
	}
	*/
	public static void modifyPrice(Flight flight, int newPrice) {
		flight.updatePrice(newPrice);
		DB db = DB.getDB();
		db.updateFlight(flight);
	}
	
	//modifies departure time, only changes the departure time 
	public static void modifyDepartTime(Flight flight, int newDepartTime, String status) {
		//if(timeConflict == false)
			flight.updateDT(newDepartTime);
			flight.updateStatus(status);
			DB db = DB.getDB();
			db.updateFlight(flight);
		
		//else
			//System.out.println("New departure time too close to an arriving flight, no changes to departure time were made");
	}

	/*
	public boolean timeConflict(ArrayList<Flight>[] flight, int NewDepartTime) {
		
		int length = flight.size();
		
		for(int i = 0; i < length; i++) {
			if(NewDepartTime - flight.get(i).getArrivalTime() < GAP && flight.get(i).route.destination.equals("SLO") ){
				return true;
			}
			
			if(Math.abs(departTime - flight.get(i).departTime) < 40 && flight.get(i).route.origin.equals("SLO")){	
				return true;
			}
		}
		
		return false;
	}
	*/
}
