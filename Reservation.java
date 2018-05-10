import java.util.*;

public class Reservation
{
  private int confirmationNumber;
  private Flight flight;
  private int numSeats;
  private String reservationHolder;
  private String[] names;
  private int[] seatNumbers;
  
  public Reservation(Flight flight, String reservationHolder, int numSeats)
  {
    this.flight = flight;
    this.reservationHolder = reservationHolder;
    this.numSeats = numSeats;
    generateConfirmationNumber();
  }
  
  private void generateConfirmationNumber()
  {
    int temp = 0;
    temp = System.currentTimeMillis();
    confirmationNumber = temp;
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
  
  public int setSeats(int[] seatNumbers)
  {
    if(seatNumbers.length != numSeats)
    {
      return -1;
    }
    this.seatNumbers = seatNumbers;
    return 0;
  }

  public void setConfirmationNumber(int confNum) {
    this.confirmationNumber = confNum;
  }
  
  public int getConfirmationNumber()
  {
    return confirmationNumber;
  }

  public void printConfirmationNumber()
  {
    System.out.printf("Confirmation number: %d\n", confirmationNumber);
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
  
  public int[] getSeatNumbers()
  {
    return seatNumbers;
  }
}
