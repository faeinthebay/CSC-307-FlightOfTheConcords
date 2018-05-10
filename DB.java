public class DB {
	
	private DB db = NULL;
	public Route[] routes;
	public ArrayList<Flight>[] flights;

	private DB() {

	}

	public static getDB() {
		if (db == NULL) {
			db = new DB();
		}
		return db;
	}

	public static void createRoutes() {
		File rtFile;
		Scanner routeSc;
		routes = new Route[13];
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
				routes[routeId] = new Route(duration, departDest, arriveDest, basePrice);
			}
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
				rtWriter.printf("%d\t%s\t%s\t%d\t%d\n", i, routes[i].getOrigin(), routes[i].getDestination(), routes[i].getDuration(), routes[i].getBasePrice());
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
				flights.add(flightId, new Flight(capacity, route, routes[route], departTime));
			}
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
				flWriter.printf("%d\t%d\t%d\t%d\n", i, flights.get(i).getCapacity(), flights.get(i).getRouteID(), flights.get(i).getDepartTime());
			}
			flWriter.close();
		} catch (Exception e) {

		}	
	}

	public User checkUser(String username, String password) {
		Scanner accountSc;
		File acFile;
		String user;
		String pass;
		int empl;
		User acct = NULL;
		boolean access = false;
		try {
			acFile = new File("accounts.txt");
			accountSc = new Scanner(accFile);
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