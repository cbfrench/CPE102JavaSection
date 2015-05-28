import processing.core.PImage;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import static java.lang.Math.abs;

public abstract class MobileAnimatedActor
   extends AnimatedActor
{
   private AStarComponents[][] node;
   private ArrayList<Point> path;

   public MobileAnimatedActor(String name, Point position, int rate,
      int animation_rate, List<PImage> imgs)
   {
      super(name, position, rate, animation_rate, imgs);
      this.path = new ArrayList<Point>();
   }

   public ArrayList<Point> getPath()
   {
      return path;
   }

   public void setPath(ArrayList<Point> path) {
      this.path = path;
   }

   public void AStar(Point start, Point goal, WorldModel world)
   {
      List<AStarComponents> open_set = new ArrayList<AStarComponents>();
      List<AStarComponents> closed_set = new LinkedList<AStarComponents>();
      node = new AStarComponents[world.getNumRows()][world.getNumCols()];

      for (int x = 0 ; x < world.getNumCols() ; x++)
      {
         for (int y = 0 ; y < world.getNumRows() ; y ++)
         {
            node[y][x] = new AStarComponents(new Point(x, y));
         }
      }
      path = new ArrayList<>();

      node[start.y][start.x].setG_score(0);
      node[start.y][start.x].setF_score(node[start.y][start.x].getG_score() + start.toDestination(goal.x, goal.y));

      open_set.add(node[start.y][start.x]);

      while (!open_set.isEmpty())
      {
         int currentIndex = lowestFScore(open_set);
         AStarComponents current = open_set.remove(currentIndex);

         if (current.getPt().equals(goal))
         {
            reconstruct_path(current);
            return;
         }

         open_set.remove(current);
         closed_set.add(current);

         for (AStarComponents neighbor : neighbor_nodes(current, world, goal))
         {
            if (closed_set.contains(neighbor))
            {
               continue;
            }
            int tentative_g_score = current.getG_score() + 1;

            if(!open_set.contains(neighbor) || tentative_g_score < neighbor.getG_score())
            {
               neighbor.getCameFrom(current);
               neighbor.setG_score(tentative_g_score);
               neighbor.setF_score(neighbor.getG_score() + neighbor.getPt().toDestination(goal.x, goal.y));
               if (!open_set.contains(neighbor))
               {
                  open_set.add(neighbor);
               }
            }
         }
         path = new ArrayList<>();
      }
   }

   public int lowestFScore(List<AStarComponents> open_set)
   {
      int lowestIndex = 0;
      for (int i = 1 ; i < open_set.size() ; i++)
      {
         AStarComponents current = open_set.get(i);
         if(open_set.get(lowestIndex).getF_score() > current.getF_score())
         {
            lowestIndex = i;
         }
      }
      return lowestIndex;
   }

   public void reconstruct_path(AStarComponents current)
   {
      ArrayList<Point> total_path = new ArrayList<Point>();
      total_path.add(current.getPt());
      while (current.getCameFrom() != null)
      {
         total_path.add(current.getCameFrom().getPt());
         current = current.getCameFrom();
      }
      path = total_path;
   }

   private ArrayList<AStarComponents> neighbor_nodes(AStarComponents current, WorldModel world, Point goal)
   {
      ArrayList<AStarComponents> neighbors = new ArrayList<>();
      Point currentPt = current.getPt();
      int currentX = currentPt.x;
      int currentY = currentPt.y;
      Point up = new Point(currentX, currentY - 1);
      Point right = new Point(currentX + 1, currentY);
      Point down = new Point(currentX, currentY + 1);
      Point left = new Point(currentX - 1, currentY);
      if (world.withinBounds(up) && (world.getTileOccupant(up) == null) || up.equals(goal))
      {
         neighbors.add(node[up.y][up.x]);
      }

      if (world.withinBounds(down) && (world.getTileOccupant(down) == null || down.equals(goal)))
      {
         neighbors.add(node[down.y][down.x]);
      }

      if (world.withinBounds(right) && (world.getTileOccupant(right) == null || right.equals(goal)))
      {
         neighbors.add(node[right.y][right.x]);
      }

      if (world.withinBounds(left) && (world.getTileOccupant(left) == null || left.equals(goal)))
      {
         neighbors.add(node[left.y][left.x]);
      }
      return neighbors;
   }


   protected Point nextPosition(WorldModel world, Point entity_pt, Point dest_pt)
   {
      AStar(entity_pt, dest_pt, world);

      Point newPoint = entity_pt;

      if (!getPath().isEmpty())
      {
         newPoint = getPath().get(getPath().size() - 2);
      }

      return newPoint;
   }

   protected static boolean adjacent(Point p1, Point p2)
   {
      return (p1.x == p2.x && abs(p1.y - p2.y) == 1) ||
         (p1.y == p2.y && abs(p1.x - p2.x) == 1);
   }

   protected abstract boolean canPassThrough(WorldModel world, Point new_pt);
}
