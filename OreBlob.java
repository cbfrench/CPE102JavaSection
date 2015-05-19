import processing.core.PImage;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chanye on 4/28/2015.
 */
public class OreBlob
    extends Animated_rate
{
    private int current_img;

    public OreBlob(String name, Point position, int animation_rate, ArrayList<PImage> imgs, int rate)
    {
        super(name, position, animation_rate, imgs, rate);
    }

    public Point blob_next_position(WorldModel world, Point dest_pt)
    {
        int horiz = Actions.sign(dest_pt.getX() - get_position().getX());
        Point new_pt = new Point(get_position().getX() + horiz, get_position().getY());

        if (horiz == 0 || world.is_occupied(new_pt) && !(world.get_tile_occupant(new_pt) instanceof Ore))
        {
            int vert = Actions.sign(dest_pt.getY() - get_position().getY());
            new_pt = new Point(get_position().getX(), get_position().getY() + vert);

            if (vert == 0 || world.is_occupied(new_pt) && !(world.get_tile_occupant(new_pt) instanceof Ore))
            {
                new_pt = new Point(get_position().getX(), get_position().getY());
            }
        }
        return new_pt;
    }
    /*
    public void schedule_blob(WorldModel world, int ticks, HashMap<String, List<PImage>> i_store)
    {
        schedule_action(world, create_ore_blob_acion(world, i_store), ticks + get_rate());
        schedule_animation(world, 0);
    }
    */

    /*public AbstractMap.SimpleEntry<List<Point>, Boolean> blob_to_vein(WorldModel world, Vein vein)
    {
        Point entity_pt = get_position();
        if (vein == null)
        {
            List<Point> noVein = new ArrayList<Point>();
            noVein.add(entity_pt);
            AbstractMap.SimpleEntry<List<Point>, Boolean> noVein2 = new AbstractMap.SimpleEntry<List<Point>, Boolean>(noVein, false);
            return noVein2;
        }
        Point vein_pt = vein.get_position();
        if (Actions.adjacent(entity_pt, vein_pt))
        {
            world.remove_entity(vein);
            List<Point> nextToVein = new ArrayList<Point>();
            nextToVein.add(vein_pt);
            AbstractMap.SimpleEntry<List<Point>, Boolean> nextToVein2 = new AbstractMap.SimpleEntry<List<Point>, Boolean>(nextToVein, true);
            return nextToVein2;
        }
        else
        {
            Point new_pt = blob_next_position(world, vein_pt);
            Position old_entity = world.get_tile_occupant(new_pt);
            if(old_entity instanceof Ore)
            {
                world.remove_entity(old_entity);
            }
            AbstractMap.SimpleEntry<List<Point>, Boolean> move = new AbstractMap.SimpleEntry<List<Point>, Boolean>(world.move_entity(this, new_pt), false);
            return move;
        }
    }*/
}
