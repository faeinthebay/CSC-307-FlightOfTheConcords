import java.io.*;
import java.util.*;

public class DB {
	
	private static DB db = null;
	public static ArrayList<Route> routes;
	public static ArrayList<Flight> flights;
	public static ArrayList<Reservation> reservations;

	private DB() {

	}

	public static DB getDB() {
		if (db == null) {
			db = new DB();
		}
		return db;
	}

	public static void createRoutes() {
		File rtFile;
		Scanner routeSc;
		routes = new ArrayList<Route>();
		int routeId;
		String departDest;
		String arriveDest;
		int duration;
		int basePrice;
		try {
			rtFile = new File("routes.txt");
			routeSc = new Scanner(rtFile);
			routeSc.useDelimiter("\t");
			while (routeSc.hasNext()) {
				routeId = routeSc.nextInt();
				departDest = routeSc.next();
				arriveDest = routeSc.next();
				duration = routeSc.nextInt();
				basePrice = routeSc.nextInt();
				routes.add(routeId, new Route(duration, (float)basePrice, departDest, arriveDest));
			}
			routeSc.close();
		} catch (Exception e) {

		}	
	}

	public static void updateRoutes() {
		File rtFile;
		PrintWriter rtWriter;
		try {
			rtFile = new File("routes.txt");
			rtWriter = new PrintWriter(rtFile);
			for (int i=1; i<13; i++) {
				rtWriter.printf("%d\t%s\t%s\t%d\t%d\n", i, routes.get(i).getOrigin(), routes.get(i).getDestination(), routes.get(i).getDuration(), routes.get(i).getBasePrice());
			}
			rtWriter.close();
		} catch (Exception e) {

		}	
	}

	public static void createFlights() {
		File flFile;
		Scanner flightSc;
		flights = new ArrayList<Flight>();
		int flightId;
		int capacity;
		int route;
		int departTime;
		try {
			flFile = new File("flights.txt");
			flightSc = new Scanner(flFile);
			flightSc.useDelimiter("\t");
			while (flightSc.hasNext()) {
				flightId = flightSc.nextInt();
				capacity = flightSc.nextInt();
				route = flightSc.nextInt();
				departTime = flightSc.nextInt();
				flights.add(flightId, new Flight(capacity, routes.get(route), departTime));
			}
			flightSc.close();
		} catch (Exception e) {

		}	
	}

	public static void updateFlights() {
		File flFile;
		PrintWriter flWriter;
		try {
			flFile = new File("flights.txt");
			flWriter = new PrintWriter(flFile);
			for (int i=1; i<flights.size(); i++) {
				flWriter.printf("%d\t%d\t%d\t%d\n", i, flights.get(i).getCapacity(), routes.indexOf(flights.get(i)), flights.get(i).getDepartTime());
			}
			flWriter.close();
		} catch (Exception e) {

		}	
	}

	public static void createReservations() {
		File rsFile;
		Scanner reservationSc;
		reservations = new ArrayList<Reservation>();
		int reservationId;
		String reservationHolder;
		int flightId;
		int confirmationNum;
		int numSeats;
		String[] names;
		try {
			rsFile = new File("reservations.txt");
			reservationSc = new Scanner(rsFile);
			reservationSc.useDelimiter("\t");
			while (reservationSc.hasNext()) {
				reservationId = reservationSc.nextInt();
				reservationHolder = reservationSc.next();
				flightId = reservationSc.nextInt();
				confirmationNum = reservationSc.nextInt();
				numSeats = reservationSc.nextInt();
				names = new String[numSeats];
				for (int i=0; i<numSeats; i++) {
					names[i] = reservationSc.next();
				}
				reservations.add(reservationId, new Reservation(flights.get(flightId), reservationHolder, numSeats));
				reservations.get(reservationId).setNames(names);
				reservations.get(reservationId).setConfirmationNumber(confirmationNum);
			}
			reservationSc.close();
		} catch (Exception e) {

		}
	}

	public static void updateReservations() {
		File rsFile;
		PrintWriter rsWriter;
		try {
			rsFile = new File("reservations.txt");
			rsWriter = new PrintWriter(rsFile);
			for (int i=1; i<reservations.size(); i++) {
				rsWriter.printf("%d\t%s\t%d\t%d\t%d", i, reservations.get(i).getReservationHolder(), flights.indexOf(reservations.get(i).getFlight()), reservations.get(i).getConfirmationNumber(), reservations.get(i).getNumSeats());
				for (int j=0; j<reservations.get(i).getNumSeats(); j++) {
					rsWriter.printf("\t%s", reservations.get(i).getNames()[j]);
				}
				rsWriter.printf("\n");
			}
			rsWriter.close();
		} catch (Exception e) {

		}	
	}

	public User checkUser(String username, String password) {
		Scanner accountSc;
		File acFile;
		String user;
		String pass;
		int empl;
		User acct = null;
		boolean access = false;
		try {
			acFile = new File("accounts.txt");
			accountSc = new Scanner(acFile);
			accountSc.useDelimiter("\t");
			while (accountSc.hasNext()) {
				user = accountSc.next();
				pass = accountSc.next();
				empl = accountSc.nextInt();
				if (user.equals(username) && pass.equals(password)) {
					acct = new User(user, empl);
				}
			}
			accountSc.close();
		} catch (Exception e) {
	
		}
		return acct;
	}

}