package application.model;

public class Flight {

	private int capacity;
	private int emptySeats;
	//private Route route;
	private int flightId;
	private String routeId;
	private String departTime;
	private String departDate;
	private String status;
	private float price;
	private int arrivalTime;
	private int duration;

	public Flight(int flightId, String routeId, String departDate, String departTime, String status, int emptySeats, int duration) {
		this.flightId = flightId;
		this.routeId = routeId;
		this.departDate = departDate;
		this.departTime = departTime;
		this.status = status;
		this.emptySeats = emptySeats;
		this.duration = duration;
		//this.arrivalTime = this.durationTime + duration;
		//this.price = route.getBasePrice();
	}

	/*public void calculatePrice() {
		price = Calculate_Price.calculate(route, emptySeats);
	}*/
	
	public void updatePrice(float newPrice) {
		/*Eventually delete this method*/
		price = newPrice;
	}

	public int getCapacity() {
		return capacity;
	}

	public int getEmptySeats() {
		return emptySeats;
	}

	public String getRoute() {
		return routeId;
	}
	
	public int getFlightId() {
		return flightId;
	}

	public float getPrice() {
		return price;
	}

	public String getDepartTime() {
		return departTime;
	}
	
	public String getDepartDate() {
		return departDate;
	}
	
	public String getStatus() {
		return status;
	}
	
	public int getDuration() {
		return duration;
	}
	
	/*public int getArrivalTime() {
		return departTime + route.getDuration();
	}*/

	public void updateSeats(int seats) {
		emptySeats = emptySeats - seats;
	}

	public void updateCapacity(int newCapacity) {
		capacity = capacity + newCapacity;
	}

	public void updateDT(int newTime) {
		departTime = departTime + newTime;
	}
	
	public void updateRoute(String newRoute) {
		routeId = newRoute;
	}
	
	public String toString() {
		return (this.routeId+" "+this.departDate+" "+this.departTime+" "+this.status+" "+this.emptySeats);
	}


}

