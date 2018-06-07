package application.model;

import java.util.Scanner;

public class BookFlight
{
  private Flight flight;
  private int numSeats;
  Reservation reservation;
  private Scanner sc;
  DB db = DB.getDB();
  
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
      reservation = new Reservation(flight.getFlightId(), reservationHolder, numSeats);
      return true;
    }
    
    return false;
  }
  
  /**ISSUE:
   * Flight Capacity is not initialized in Flight.java, so seatNumbersLeft is treated as 0 - getEmptySeats()+1. 
   * Because seatNumbersLeft is initialized as capacity-getEmptySeats() before seatNumbers are assigned, 
   * seatNumbers end up starting beyond where they should.
   * EX: When a user reserves 3 seats on a 40 seat flight, their seat numbers are {-36, -35, -34} 
   * because 3 seats are taken and flight.emptySeats is updated immediately, so 0 - 37 + 1 = 36
   * **/
  public void generateSeatNumbers()
  {
    int seatNumbersLeft = flight.getCapacity() - flight.getEmptySeats() + 1;
    Integer seatNumbers[] = new Integer[numSeats];
    for (int i = 0; i < numSeats; i++)
    {
      seatNumbers[i] = seatNumbersLeft;
      seatNumbersLeft++;
    }
    reservation.setSeats(seatNumbers);
  }

  public int reserve()
  {
	  /*
    sc = new Scanner(System.in);
    String reservationHolder;
    int reserveSeats;

    System.out.print("Enter your name: ");
    reservationHolder = sc.next();
    System.out.println();
    
    System.out.print("Enter the number of seats you would like to reserve: ");
    reserveSeats = sc.nextInt();
    System.out.println();

    reservation = new Reservation(flight.getFlightId(), reservationHolder, reserveSeats);*/
    db.addReservation(reservation);
    return reservation.getConfirmationNumber();
  }
  /*Still need to handle seat numbers, names for group reservations*/
  public Reservation getReservation() {
	return reservation;
  }
}

