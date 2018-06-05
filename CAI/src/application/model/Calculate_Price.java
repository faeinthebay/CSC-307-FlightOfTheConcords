package application.model;

public class Calculate_Price {
	
	public static float calculate(String routeId, int emptySeats) {
		DB db = DB.getDB();
		float price = db.getBasePrice(routeId);
		//for all flights in route, use empty seats to figure out price
		//base price in route info
		price = price - ((emptySeats / 2) * price);
		return price;
	}
}
