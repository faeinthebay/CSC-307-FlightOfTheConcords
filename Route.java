/* This is the route class */

public class Route
{
   private int duration;
   private float basePrice;
   private String origin;
   private String destination;
   
   public Route(int duration, float price, String origin, String destination)
   {
      this.duration = duration;
      this.basePrice = price;
      this.origin = origin;
      this.destination = destination;
   }
   
   public void changeDuration(int newDuration)
   {
      duration = newDuration;
   }
   
   public void changePrice(float newBasePrice)
   {
      basePrice = newBasePrice;
   }
   
   public float getBasePrice()
   {
      return basePrice;
   }
   
   public int getDuration()
   {
      return duration;
   }
   
   public String getOrigin()
   {
      return origin;
   }
   
   public String getDestination()
   {
      return destination;
   }
}
