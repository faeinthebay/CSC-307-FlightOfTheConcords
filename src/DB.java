
import java.io.*;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.*;
import java.io.*;
import java.sql.*;

public class DB {
	
	private static DB db = null;
	public static ArrayList<Route> routes;
	public static ArrayList<Flight> flights;
	public static ArrayList<Reservation> reservations;

	private DB() {
		connect();
	}

	public static DB getDB() {
		if (db == null) {
			db = new DB();
		}
		return db;
	}

	private Connection connect() {
		Connection conn = null;
		try {
			String url = "jdbc:sqlite:CAI_DB.db";
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		return conn;
	}

	public void createRoutes() {
		File rtFile;
		Scanner routeSc;
		routes = new ArrayList<Route>();
		routes.add(0, new Route(0,0,"boom", "boom"));
		int routeId;
		String departDest;
		String arriveDest;
		int duration;
		int basePrice;

		String line;
		String[] toks;

		try {
			rtFile = new File("routes.txt");
			routeSc = new Scanner(rtFile);
			//routeSc.useDelimiter(" ");
			while (routeSc.hasNextLine()) {
				line = routeSc.nextLine();
				//System.out.println(line);
				toks = line.split(" ");
				routeId = Integer.valueOf(toks[0]);
				departDest = toks[1];
				arriveDest = toks[2];
				duration = Integer.valueOf(toks[3]);
				basePrice = Integer.valueOf(toks[4]);
				//System.out.println("before");
				routes.add(routeId, new Route(duration, (float)basePrice, departDest, arriveDest));
				//System.out.println("after");
			}
			routeSc.close();
		} catch (Exception e) {

		}
	}

	public void updateRoutes() {
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

	public void createFlights() {
		File flFile;
		Scanner flightSc;
		flights = new ArrayList<Flight>();
		flights.add(0, null);
		int flightId;
		int capacity;
		int route;
		int departTime;

		String line;
		String[] toks;
		try {
			flFile = new File("flights.txt");
			flightSc = new Scanner(flFile);
			//flightSc.useDelimiter("\t");
			while (flightSc.hasNextLine()) {
				line = flightSc.nextLine();
				//System.out.println(line);
				toks = line.split(" ");
				flightId = Integer.valueOf(toks[0]);
				capacity = Integer.valueOf(toks[1]);
				route = Integer.valueOf(toks[2]);
				departTime = Integer.valueOf(toks[3]);
				flights.add(flightId, new Flight(capacity, routes.get(route), departTime));
			}
			flightSc.close();
		} catch (Exception e) {

		}
	}

	public void updateFlights() {
		File flFile;
		PrintWriter flWriter;
		try {
			flFile = new File("flights.txt");
			flWriter = new PrintWriter(flFile);
			for (int i=1; i<flights.size(); i++) {
				flWriter.printf("%d\t%d\t%d\t%d\n", i, flights.get(i).getCapacity(), routes.indexOf(flights.get(i).getRoute()), flights.get(i).getDepartTime());
			}
			flWriter.close();
		} catch (Exception e) {

		}	
	}

	public void createReservations() {
		File rsFile;
		Scanner reservationSc;
		reservations = new ArrayList<Reservation>();
		reservations.add(0, null);
		int reservationId;
		String reservationHolder;
		int flightId;
		int confirmationNum;
		int numSeats;
		String[] names;

		String line;
		String[] toks;
		try {
			rsFile = new File("reservations.txt");
			reservationSc = new Scanner(rsFile);
			//reservationSc.useDelimiter("\t");
			while (reservationSc.hasNextLine()) {
				line = reservationSc.nextLine();
				toks = line.split(" ");
				reservationId = Integer.valueOf(toks[0]);
				reservationHolder = toks[1];
				flightId = Integer.valueOf(toks[2]);
				confirmationNum = Integer.valueOf(toks[3]);
				numSeats = Integer.valueOf(toks[4]);
				names = new String[numSeats];
				for (int i=0; i<numSeats; i++) {
					names[i] = toks[i+5];
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
		Writer rsFile;
		PrintWriter rsWriter;
		String line = "";
		try {
			rsFile = new FileWriter("reservations.txt", false);
			//rsWriter = new PrintWriter(rsFile);
			for (int i=1; i<reservations.size(); i++) {
				line += Integer.toString(i) + " " + reservations.get(i).getReservationHolder() + " " + Integer.toString(flights.indexOf(reservations.get(i).getFlight())) + " " + Integer.toString(reservations.get(i).getConfirmationNumber()) + " " + Integer.toString(reservations.get(i).getNumSeats()) + "\n";
			//	for (int j=0; j<reservations.get(i).getNumSeats(); j++) {
			//		rsWriter.print(" " + reservations.get(i).getNames()[j]);
			//	}
			//	rsWriter.print("\n");
			//	System.out.println("Hererere");

			}
			rsFile.write(line);
			//rsWriter.print("Hello");
			//rsFile.write("Hi");

			rsFile.close();
			//rsWriter.close();
		} catch (Exception e) {

		}
	}	

	public User checkUser(String username, String password) {
		Scanner accountSc;
		File acFile;
		String user;
		String pass;
		String empl;
		User acct = null;
		boolean access = false;
		try {
			acFile = new File("accounts.txt");
			//System.out.println(acFile.getAbsolutePath());
			accountSc = new Scanner(acFile);
			accountSc.useDelimiter(" ");
			while (accountSc.hasNext()) {
				//System.out.println("Inside");
				user = accountSc.next();
				//System.out.println("Inside2 user:" + user);
				pass = accountSc.next();
				//System.out.println("Inside3 pass:" + pass);
				//System.out.println("Inside3");
				empl = accountSc.next();
				//System.out.println("Inside4 empl:" + empl);
				//System.out.println("Inside4");
				//System.out.println(user + " " + pass);
				if (user.equals(username) && pass.equals(password)) {
					if (empl.equals("true")) {
						acct = new User(user, 1);
					}else {
						acct = new User(user,0);
					}
				}
			}
			accountSc.close();
		} catch (Exception e) {
	
		}
			
		return acct;
	}

}