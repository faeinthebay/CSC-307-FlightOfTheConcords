import java.util.Scanner;

public class CheckIn
{
	private int confirmationNumber;
	private Reservation reservation;
	private boolean valid = false;
	DB db = DB.getDB();
	private Scanner sc;
  
	public CheckIn(int confirmationNumber)
	{
		this.confirmationNumber = confirmationNumber;
	}
  
	public void confirmCheckIn()
	{
		if(!validReservation())
		{
			System.out.println("Confirmation number is invalid.  Please try again.");
		}
		
		reserveBags();
		printThankYouMessage();
	}
	
	private boolean validReservation()
	{
		if(valid == true)
			return true;
		
		for(int i = 0; i < DB.reservations.size(); i++)
		{
			Reservation res = DB.reservations.get(i);
			if(res.getConfirmationNumber() == this.confirmationNumber)
			{
				this.reservation = res;
				valid = true;
				return true;
			}
		}		
		return false;
	}
	
	private void reserveBags()
	{
		int maxBags = reservation.getNumSeats()*2;
		sc = new Scanner(System.in);
		
		System.out.println("Cal Air reservation for " + reservation.getReservationHolder() + " found.");
		System.out.println("We see that you have booked " +  reservation.getNumSeats() + " seats.  Cal Air allows you to check"
				+ "up to two bags per seat, at a charge of $25 per bag.\nHow many bags would you like to check (maximum of "
				+ maxBags + ")?");
		
		int numBags = sc.nextInt();
		
		while(numBags > maxBags)
		{
			System.out.println("Please enter a valid number of bags (maximum of "
					+ maxBags + ")");
			numBags = sc.nextInt();
		}
		
		System.out.println("Bags confirmed.  " + numBags*25 + " has been billed to your account.\n"
				+ "");
	}
	
	private void printThankYouMessage()
	{
		System.out.println("You have successfully checked in.  Please remember to get your boarding passes at the Cal Air\n"
				+ "kiosk before arriving at your gate.  Thank you for choosing Cal Air!");
	}
}