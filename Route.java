/* This is the route class */

public class Route
{
   private int duration;
   private int basePrice;
   private String origin;
   private String destination;
   
   public Route(int dur, int price, String origin, String destination)
   {
      this.duration = duration;
      this.price = price;
      this.origin = origin;
      this.destination = destination;
   }
   
   public void ChangeDuration(int newDuration)
   {
      duration = newDuration;
   }
   
   public void ChangePrice(int newBasePrice)
   {
      basePrice = newBasePrice;
   }
}
