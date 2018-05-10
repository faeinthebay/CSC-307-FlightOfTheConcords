
public class Flight {

	private int capacity;
	private int emptySeats;
	private Route route;
	private int departTime;
	private float price;
	private int id;

	public Flight(int capacity, int routeId, Route route, int departTime) {
		this.capacity = capacity;
		this.route = route;
		this.departTime = departTime;
		this.id = routeId;
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

	public Route getRoute() {
		return route;
	}

	public int getRouteID() {
		return id;
	}

	public float getPrice() {
		return price;
	}

	public int getDepartTime() {
		return departTime;
	}

	public void updateSeats(int seats) {
		emptySeats = emptySeats - seats;
	}

}
