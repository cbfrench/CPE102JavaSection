import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedList;

import org.junit.Test;

import static org.junit.Assert.*;

/*public class TestCases
{
   private static final double DELTA = 0.00001;

   Point pt = new Point(1, 1);
   Point pt2 = new Point(2, 3);
   Object item = new Object();
   Position background = new Position("BACKGROUND", pt);
   Background bgdn = new Background("BACKGROUND");
   MinerNotFull miner = new MinerNotFull("MINER", pt, 1, 6, 4, 2);
   MinerFull minerfull = new MinerFull("MINER", pt, 1, 6, 4, 2);
   Vein vein = new Vein("VEIN", 0, pt, 3, 1);
   Ore ore = new Ore("ORE", 1, pt, 0, 3);
   Blacksmith bs = new Blacksmith("BLACKSMTH", pt, 2, 1, 3);
   Obstacle obs = new Obstacle("OBSTACLE", pt);
   OreBlob blob = new OreBlob("BLOB", pt, 3, 3, 3);
   Quake quake = new Quake("QUAKE", pt, 2, 3);
   WorldModel world = new WorldModel(5, 5, background);

   @Test
   public void testGetPosition()
   {
      assertEquals(pt, miner.get_position());
   }

   @Test
   public void testSetPosition()
   {
      Point newpt = new Point(1, 2);
      minerfull.set_position(newpt);

      assertEquals(newpt, minerfull.get_position());
   }

   @Test
   public void testGetAnimationRate()
   {
      assertEquals(3, blob.get_animation_rate());
   }

   @Test
   public void testGetImages()
   {
      assertEquals(3, quake.get_images());
   }

   @Test
   public void testGetRateAnimated()
   {
      assertEquals(4, miner.get_rate());
   }

   @Test
   public void testGetRateNotAnimated()
   {
      assertEquals(3, vein.get_rate());
   }

   @Test
   public void testWithinBoundsTRUE()
   {
      assertTrue(world.within_bounds(pt));
   }

   @Test
   public void testWithinBoundsFALSE()

   {
      Point newpt = new Point(-1, -2);
      assertFalse(world.within_bounds(newpt));
   }

   @Test
   public void testIsOccupiedFALSE()
   {
      assertFalse(world.is_occupied(pt));
   }

   @Test
   public void testAddEntityTrue()
   {
      world.add_entity(miner);
      assertTrue(world.is_occupied(pt));
   }

   @Test
   public void testAddEntityFalse()
   {
      world.add_entity(miner);
      Point newpt = new Point(1, 5);
      assertFalse(world.is_occupied(newpt));
   }

   @Test
   public void testMoveEntityTRUE()
   {
      assertFalse(world.is_occupied(pt2));
      world.move_entity(miner, pt2);
      assertTrue(world.is_occupied(pt2));
   }

   @Test
   public void testRemoveEntity()
   {
      world.add_entity(miner);
      assertTrue(world.is_occupied(pt));
      world.remove_entity(miner);
      assertFalse(world.is_occupied(pt));
   }

   @Test
   public void testRemoveEntityAt()
   {
      world.add_entity(miner);
      assertTrue(world.is_occupied(pt));
      world.remove_entity_at(pt);
      assertFalse(world.is_occupied(pt));
   }

   @Test
   public void testGetEntities()
   {
      ArrayList<Position> l1 = new ArrayList<Position>();
      l1.add(miner);
      assertFalse(world.is_occupied(pt));
      world.add_entity(miner);
      assertEquals(world.get_entities(), l1);
   }

   @Test
   public void testDistanceDistanceSq()
   {
      assertEquals (world.distance_sq (pt2, pt), 5, DELTA);
   }
}
*/