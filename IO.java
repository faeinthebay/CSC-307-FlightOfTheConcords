import java.io.*;
import java.util.*;

public class IO {


	public static void main(String args[]) {
		Scanner sc;
		String user;
		String pass;
		int empl;
		User currentUser = null;
		DB db = DB.getDB();

		try {
		
			sc = new Scanner(System.in);

			//Loop until user successfully logs in
			while (currentUser == null) {
				System.out.println("Please login...\nEnter Username:");
				String username = sc.next();
				System.out.println("\nEnter Password:");
				String password = sc.next();
				currentUser = db.checkUser(username, password);

				if (currentUser == null) {
					System.out.println("Your username or password is incorrect");
				} else {
					System.out.printf("Welcome %s\n", username);
				}
			}

			//create route and flight and reservation arrays
			db.createRoutes();
			db.createFlights();
			db.createReservations();

			System.out.println("Browse flights:");

			for (int i=1; i<db.flights.size(); i++) {
				System.out.printf("Flight %d: %s to %s departing at %d\n", i, db.flights.get(i).getRoute().getOrigin(), db.flights.get(i).getRoute().getDestination(), db.flights.get(i).getDepartTime());
			}

			System.out.println("Actions:\n1. Book flight\n2. Check in\n3. Modify Flight");

			System.out.println("Enter a flight number and an action number like this: \'1 1\' or q to logout and quit:");

			if (sc.hasNextInt()) {
				int f = sc.nextInt();
				int a = sc.nextInt();
				if (a == 1) {
					System.out.println("How many seats do you want to book? ");
					int numSeats = sc.nextInt();
					BookFlight bk = new BookFlight(db.flights.get(f), numSeats);
				}
			} else if (sc.hasNext()) {
				char c = sc.next().charAt(0);
				if (c == 'q') {
					//logout
				}
			}


		} catch (Exception e) {

		}

	}
}