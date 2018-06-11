package application.model;

import java.sql.*;
import java.util.*;


public class DB {

	private static DB db = null;
	private static Connection conn = null;
	private static Statement statement;
	private static PreparedStatement preparedstatement;
	private static ResultSet result;

	//public static ArrayList<Route> routes;
	//public static ArrayList<Flight> flights;
	//public static ArrayList<Reservation> reservations;

	private DB() {
		conn = connect();
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
			Class.forName("org.sqlite.JDBC");
			String url = "jdbc:sqlite:src/application/CAI_DB.db";
			conn = DriverManager.getConnection(url);
			System.out.println("Connected to db");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} catch (ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}
	
	public void addAccount(String user, String pass) throws Exception {
		try {
			String sql= "INSERT INTO accounts (username, password) VALUES (?,?)";
			preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setString(1, user);
			preparedstatement.setString(2, Utilities.getSaltedHash(pass));
			preparedstatement.executeUpdate();
			preparedstatement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public User checkAccount(String user, String pass) throws Exception {
		User acct = null;
		try {
			String sql = "SELECT password,privilege FROM accounts WHERE username=?";
			preparedstatement = conn.prepareStatement(sql);
			preparedstatement.setString(1, user);
			result = preparedstatement.executeQuery();
			if (result.next()) {
				if (Utilities.checkPassword(pass, result.getString("password"))) {
					acct = new User(user, result.getInt("privilege"));
				}
			}
			result.close();
			preparedstatement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return acct;
	}
	
	public ArrayList<User> getAccounts() {
		ArrayList<User> users = null;
		try {
			String sql = "SELECT * FROM accounts";
			statement = conn.createStatement();
			result = statement.executeQuery(sql);
			users = new ArrayList<User>();
			while (result.next()) {
				User user = new User(result.getString("username"), result.getInt("privilege"));
				users.add(user);
			}
			result.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return users;
	}
	
	public void updateUserPrivilege(User user) { // TODO: Don't handle exceptions silently; show error
		try {
			String sql= "UPDATE accounts SET privilege=? WHERE username=?";
			preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setInt(1, user.getPrivilege());
			preparedstatement.setString(2, user.getUser());
			preparedstatement.executeUpdate();
			preparedstatement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public ArrayList<Flight> getFlights() {
		ArrayList<Flight> flights = null;
		try {
			String sql = "SELECT flights.*,routes.duration, routes.origin, routes.destination FROM FLIGHTS,ROUTES WHERE flights.routeId=routes.routeId";
			statement = conn.createStatement();
			result = statement.executeQuery(sql);
			flights = new ArrayList<Flight>();
			while (result.next()) {
				Flight flight = new Flight(result.getInt("flightId"), result.getString("routeId"), result.getString("departDate"), result.getString("departTime"), result.getString("status"), result.getInt("emptySeats"), result.getInt("duration"));
			//	String[] cities = getRouteOriginDest(flight.getRoute());
				flight.setDepartCity(result.getString("origin"));
				flight.setArriveCity(result.getString("destination"));
				flights.add(flight);
			}
			result.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return flights;
	}

	public ArrayList<Flight> getFlightsForRoute(String routeId) {
		ArrayList<Flight> flights = null;
		try {
			String sql = "SELECT flights.*,routes.duration FROM FLIGHTS,ROUTES WHERE flights.routeId=?";
			preparedstatement = conn.prepareStatement(sql);
			preparedstatement.setString(1,  routeId);
			result = preparedstatement.executeQuery();
			flights = new ArrayList<Flight>();
			while (result.next()) {
				Flight flight = new Flight(result.getInt("flightId"), result.getString("routeId"), result.getString("departDate"), result.getString("departTime"), result.getString("status"), result.getInt("emptySeats"), result.getInt("duration"));
				flights.add(flight);
			}
			result.close();
			statement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return flights;
	}
	
	public Flight getFlight(int flightId) {
		Flight flight = null;
		try {
			String sql = "SELECT flights.*,routes.duration FROM FLIGHTS,ROUTES WHERE flights.flightId=?";
			preparedstatement = conn.prepareStatement(sql);
			preparedstatement.setInt(1, flightId);
			result = preparedstatement.executeQuery();
			flight = new Flight(result.getInt("flightId"), result.getString("routeId"), result.getString("departDate"), result.getString("departTime"), result.getString("status"), result.getInt("emptySeats"), result.getInt("duration"));
			result.close();
			preparedstatement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return flight;
	}
	
	public void updateFlight(Flight flight) {
		try {
			String sql= "UPDATE flights SET departDate=?,departTime=?, status=?, emptySeats=?, price=? WHERE flightId=?";
			preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setString(1, flight.getDepartDate());
			preparedstatement.setString(2, flight.getDepartTime());
			preparedstatement.setString(3, flight.getStatus());
			preparedstatement.setInt(4, flight.getEmptySeats());
			preparedstatement.setInt(5, flight.getPrice());
			preparedstatement.setInt(6, flight.getFlightId());
			preparedstatement.executeUpdate();
			preparedstatement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public void addFlight(Flight flight) {
		try {
			String sql= "INSERT INTO flights (routeId, departDate, departTime, status, emptySeats, price) VALUES (?,?,?,?,?,?)";
			preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setString(1, flight.getRoute());
			preparedstatement.setString(2, flight.getDepartDate());
			preparedstatement.setString(3, flight.getDepartTime());
			preparedstatement.setString(4, flight.getStatus());
			preparedstatement.setInt(5, flight.getEmptySeats());
			preparedstatement.setInt(6, flight.getPrice());
			preparedstatement.executeUpdate();
			preparedstatement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public int getDuration(String routeId) {
		int duration = 0;
		try {
			String sql= "SELECT duration FROM routes WHERE routeId=?";
			preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setString(1, routeId);
			result = preparedstatement.executeQuery();
			duration = result.getInt("duration");
			result.close();
			preparedstatement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return duration;
	}
	
	public int getBasePrice(String routeId) {
		int price = 0;
		try {
			String sql= "SELECT basePrice FROM routes WHERE routeId=?";
			preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setString(1, routeId);
			result = preparedstatement.executeQuery();
			price = result.getInt("basePrice");
			result.close();
			preparedstatement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return price;
	}
	
	public void addReservation(Reservation res) {
		try {
			String sql= "INSERT INTO reservations (confNum, reservationHolder, flightId, numSeats, dateReserved, pricePaid) VALUES (?,?,?,?,?,?)";
			preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setInt(1, res.getConfirmationNumber());
			preparedstatement.setString(2, res.getReservationHolder());
			preparedstatement.setInt(3, res.getFlight());
			preparedstatement.setInt(4, res.getNumSeats());
			preparedstatement.setString(5, res.getDateReserved());
			preparedstatement.setInt(6, res.getPricePaid());
			preparedstatement.executeUpdate();
			preparedstatement.close();
			String names[] = res.getNames();
			for (int i=0; i<names.length; i++) {
				sql= "INSERT INTO flyers (confNum, name) VALUES (?,?)";
				preparedstatement=conn.prepareStatement(sql);
				preparedstatement.setInt(1, res.getConfirmationNumber());
				preparedstatement.setString(2, names[i]);
				preparedstatement.executeUpdate();
				preparedstatement.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Reservation getReservation(int confNum) {
		Reservation res = null;
		ArrayList<String> names = null;
		ArrayList<Integer> seats = null;
		try {
			String sql = "SELECT * FROM reservations WHERE confNum=?";
			preparedstatement = conn.prepareStatement(sql);
			preparedstatement.setInt(1, confNum);
			result = preparedstatement.executeQuery();
			res = new Reservation(result.getInt("flightId"), result.getString("reservationHolder"), result.getInt("numSeats"));
			res.setConfirmationNumber(confNum);
			res.setDateReserved(result.getString("dateResrved"));
			res.setPricePaid(result.getInt("pricePaid"));
			result.close();
			preparedstatement.close();
			sql = "SELECT name,seat FROM flyers WHERE confNum=?";
			preparedstatement = conn.prepareStatement(sql);
			preparedstatement.setInt(1, confNum);
			result = preparedstatement.executeQuery();
			names = new ArrayList<String>();
			seats = new ArrayList<Integer>();
			while (result.next()) {
				names.add(result.getString("name"));
				seats.add(result.getInt("seat"));
			}
			res.setNames(names.toArray(new String[names.size()]));
			res.setSeats(seats.toArray(new Integer[seats.size()]));
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return res;
	}
	
	public void checkIn(Reservation res) {
		try {
			String sql= "UPDATE reservations SET isCheckedIn=1 WHERE confNum=?";
			preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setInt(1, res.getConfirmationNumber());
			preparedstatement.executeUpdate();
			preparedstatement.close();
			String names[] = res.getNames();
			Integer seats[] = res.getSeatNumbers();
			for (int i=0; i<names.length; i++) {
				sql = "UPDATE flyers SET seat=? WHERE confNum=? AND name=?";
				preparedstatement=conn.prepareStatement(sql);
				preparedstatement.setInt(1, seats[i]);
				preparedstatement.setInt(2, res.getConfirmationNumber());
				preparedstatement.setString(3, names[i]);
				preparedstatement.executeUpdate();
				preparedstatement.close();
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
	}
	
	public String[] getRouteOriginDest(String routeId) {
		String[] route = new String[2];
		try {
			String sql= "SELECT origin,destination FROM routes WHERE routeId=?";
			preparedstatement=conn.prepareStatement(sql);
			preparedstatement.setString(1, routeId);
			result = preparedstatement.executeQuery();
			route[0] = result.getString("origin");
			route[1] = result.getString("destination");
			result.close();
			preparedstatement.close();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return route;
	}
	
}
