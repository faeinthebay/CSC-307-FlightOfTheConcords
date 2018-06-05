package application.model;

import java.util.*;

public class Reservation
{
  private int confirmationNumber;
  private int flightId;
  private int numSeats;
  private String reservationHolder;
  private String[] names;
  private Integer[] seatNumbers;
  private String dateReserved;
  private int pricePaid;
  private int isCheckIn;
  
  public Reservation(int flightId, String reservationHolder, int numSeats)
  {
    this.flightId = flightId;
    this.reservationHolder = reservationHolder;
    this.numSeats = numSeats;
    generateConfirmationNumber();
  }
  
  private void generateConfirmationNumber()
  {
    int temp = 0;
    temp = (int) System.currentTimeMillis();
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
  
  public int setSeats(Integer[] seatNumbers)
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
  
  public int getFlight()
  {
    return flightId;
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
  
  public Integer[] getSeatNumbers()
  {
    return seatNumbers;
  }
  
  public String getDateReserved() {
	  return dateReserved;
  }
  
  public int getPricePaid() {
	  return pricePaid;
  }
  
  public void setDateReserved(String dateReserved) {
	  this.dateReserved = dateReserved;
  }
  
  public void setPricePaid(int pricePaid) {
	  this.pricePaid = pricePaid;
  }
  
}

