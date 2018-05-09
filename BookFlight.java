public class BookFlight
{
  private Flight flight;
  private int numSeats;
  Reservation reservation;
  
  public BookFlight(Flight flight, int numSeats)
  {
    this.flight = flight;
    this.numSeats = numSeats;
  }
  
  public boolean validReservation()
  {
    if(numSeats <= flight.getEmptySeats())
    {
      return true;
    }
    
    return false;
  }
  
  public boolean createReservation(String reservationHolder)
  {
    if(validReservation())
    {
      flight.updateSeats(numSeats);
      reservation = new Reservation(flight, reservationHolder, numSeats);
      return true;
    }
    
    return false;
  }  
  
  /*Still need to handle seat numbers, names for group reservations*/
}
