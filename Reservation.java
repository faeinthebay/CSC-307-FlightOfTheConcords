public class Reservation
{
  private int confirmationNumber;
  private Flight flight;
  private int numSeats;
  private String reservationHolder;
  private String[] names;
  private String[] seats;
  
  public Reservation(Flight flight, String reservationHolder, int numSeats)
  {
    this.flight = flight;
    this.reservationHolder = reservationHolder;
    this.numSeats = numSeats;
    generateConfirmationNumber();
  }
  
  private void generateConfirmationNumber()
  {
    /*Generate a random int*/
    //confirmationNumber = randInt;
  }
  
  public int setNames(String[] names)
  {
    if(names.length != numSeats)
    {
      return -1;
    }
    this.names = names;
    return 0;
  }
  
  public int setSeats(String[] seats)
  {
    if(seats.length != numSeats)
    {
      return -1;
    }
    this.seats = seats;
    return 0;
  }
  
  public int getConfirmationNumber()
  {
    return confirmationNumber;
  }
  
  public Flight getFlight()
  {
    return flight;
  }
  
  public int getNumSeats()
  {
    return numSeats;
  }
  
  public String getReservationHolder()
  {
    return reservationHolder;
  }
  
  public String[] getNames()
  {
    return names;
  }
  
  public String[] getSeats()
  {
    return seats;
  }
}
