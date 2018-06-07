package application.model;

public class Calculate_Price {
	
	
	/*Price of each flight should be determined two weeks in advance (Tuesday of each week).
     * The pricing process is as follows: The system keeps track of the percentage of empty seats
     * during the last two weeks for the destination of the interest. Next, the system should calculate
     * an average value of the empty seats, denoted by X, and use that to determine a fair price for the
     * flight using the base flight price (P) according to the following formula:
     * P = P- ((X/2)*P)
	 */
	public static float calculate(String routeId, int emptySeats) {
		DB db = DB.getDB();
		float price = db.getBasePrice(routeId);
		//for all flights in route, use empty seats to figure out price
		//base price in route info
		price = price - (((float)emptySeats / (float)2) * price);
		return price;
	}
}
