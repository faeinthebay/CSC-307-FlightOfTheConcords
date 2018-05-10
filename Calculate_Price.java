public class Calculate_Price {
	
	public static float calculate(Route route, int emptySeats) {
		float price = route.getBasePrice();
		//for all flights in route, use empty seats to figure out price
		//base price in route info
		price = price - ((emptySeats / 2) * price);
		return price;
	}
}