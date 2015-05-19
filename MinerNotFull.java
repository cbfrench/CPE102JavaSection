import processing.core.PImage;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.LongConsumer;

/**
 * Created by Chanye on 4/28/2015.
 */
public class MinerNotFull
    extends Miner
{
    public MinerNotFull(String name, Point position, int animation_rate,
                        ArrayList<PImage> imgs, int rate, int resource_limit)
    {
        super(name, position, animation_rate, imgs, rate, resource_limit);
    }

    /*public AbstractMap.SimpleEntry<List<Point>, Boolean> miner_to_ore(WorldModel world, Ore ore)
    {
        Point entity_pt = get_position();
        if (ore == null)
        {
            List<Point> falsePoints = new ArrayList<Point>();
            falsePoints.add(entity_pt);
            AbstractMap.SimpleEntry<List<Point>, Boolean> falseList = new AbstractMap.SimpleEntry<List<Point>, Boolean>(falsePoints, false);
            return falseList;
        }
        Point ore_pt = ore.get_position();
        if (Actions.adjacent(entity_pt, ore_pt))
        {
            set_resource_count(1 + get_resource_count());
            world.remove_entity(ore);
            List<Point> orePoints = new ArrayList<Point>();
            orePoints.add(ore_pt);
            AbstractMap.SimpleEntry<List<Point>, Boolean> oreList = new AbstractMap.SimpleEntry<List<Point>, Boolean>(orePoints, true);
            return oreList;
        }
        else
        {
            Point new_pt = next_position(world, ore_pt);
            AbstractMap.SimpleEntry<List<Point>, Boolean> nextPoints = new AbstractMap.SimpleEntry<List<Point>, Boolean>(world.move_entity(this, new_pt), false);
            return nextPoints;
        }
    }*/
 /*
    public Position try_transform_miner_not_full(WorldModel world)
    {
        if (get_resource_count() < get_resource_limit())
        {
            return this;
        }
        else
        {
            Position new_entity = new MinerFull(get_name(), get_position(), get_resource_limit(),
                                                get_images(), get_rate(),get_animation_rate());
            return new_entity;
        }
    }
    /*
    public LongConsumer create_miner_not_full_action(WorldModel world, HashMap<String, List<PImage>> i_store)
    {
        LongConsumer[] action = {};
        action[0] = (long currentTicks) ->
        {
            remove_pending_action(action);

            Point entity_pt = get_position();
            Ore ore = world.find_nearest(entity_pt, Ore);
        };
    }
    */
}
