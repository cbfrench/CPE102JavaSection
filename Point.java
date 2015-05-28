public class Point
{
   public final int x;
   public final int y;

   public Point(int x, int y)
   {
      this.x = x;
      this.y = y;
   }

   public String toString()
   {
      return "(" + x + "," + y + ")";
   }

   public double toDestination(int x2, int y2)
   {
      return Math.sqrt(((x - x2) * (x - x2)) + ((y - y2) * (y - y2)));
   }

   public boolean equals(Object obj)
   {
      if(obj instanceof Point)
      {
         if(obj == this)
         {
            return true;
         }
         else
         {
            Point other = (Point) obj;
            return x == other.x && y == other.y;
         }
      }
      else
      {
         return false;
      }
   }
}
