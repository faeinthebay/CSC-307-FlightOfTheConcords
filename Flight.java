public class Flight {

	private int capacity;
	private int emptySeats;
	private Route route;
	private int departTime;
	private float price;
	private int arrivalTime;

	public Flight(int capacity, Route route, int departTime) {
		this.capacity = capacity;
		this.route = route;
		this.departTime = departTime;
		this.price = route.basePrice;
	}

	public void calculatePrice() {
		price = Calculate_Price.calculate(route, emptySeats);
	}

	public int getCapacity() {
		return capacity;
	}

	public int getEmptySeats() {
		return emptySeats;
	}

	public int getRoute() {
		return route;
	}

	public float getPrice() {
		return price;
	}

	public int getDepartTime() {
		return departTime;
	}
	
	public int getArrivalTime() {
		return departTime + route.duration;
	}

	public void updateSeats(int seats) {
		emptySeats = emptySeats - seats;
	}

	public void updateCapacity(int newCapacity) {
		capacity = capacity + newCapacity;
	}

	public void updateDT(int newTime) {
		departTime = departTime + newTime;
	}

}
