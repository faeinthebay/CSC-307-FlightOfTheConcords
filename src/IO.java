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

			boolean signedIn = true;
			while (signedIn) {
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
					int conf = bk.reserve();
					db.updateReservations();
					System.out.println("Confirmation: " + conf);
				} else if (a == 2) {
					System.out.println("Enter your confirmation number: ");
					int confNum = sc.nextInt();
					CheckIn ch = new CheckIn(confNum);
					while(!ch.confirmCheckIn()) {
						System.out.println("Enter your confirmation number: ");
						confNum = sc.nextInt();
						ch = new CheckIn(confNum);
					}
					System.out.println("You are checked in");
				} 
        
        else if(a == 3)
					{
						if(currentUser.privilege == 0)
						{
							System.out.println("Access denied");
							return;
						}
						System.out.println("What would you like to modify?");
						System.out.println("1. Flight capacity\n2. Flight price\n3. Departure time");
						
						boolean loop;
						do
						{
							int selection = sc.nextInt();
							loop = false;
							
							if(selection == 1)
							{
								System.out.println("Enter new flight capacity");
								int capacity = sc.nextInt();
								ModifyFlight.modifyCapacity(db.flights.get(f), capacity);
								System.out.println("Capacity successfully updated");
							}
							else if(selection == 2)
							{
								System.out.println("Enter new flight price");
								int price = sc.nextInt();
								ModifyFlight.modifyPrice(db.flights.get(f), price);
								System.out.println("Price successfully updated");
							}
							else if(selection == 3)
							{
								System.out.println("Enter new flight departure time");
								int time = sc.nextInt();
								ModifyFlight.modifyDepartTime(db.flights.get(f), time);
								System.out.println("Departure time successfully updated");
							}
							else
							{
								System.out.println("Please enter a valid number");
								loop = true;
							}
						}while(loop == true);
					}
			} else if (sc.hasNext()) {
				char c = sc.next().charAt(0);
				if (c == 'q') {
					//logout
					signedIn = false;
				}
			}
			}

		} catch (Exception e) {

		}

	}
}