import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Chanye on 4/28/2015.
*/
public class Miner
        extends Animated_rate
{
    private int resource_limit;
    private int resource_count;


    public Miner(String name, Point position, int animation_rate,
                 List<PImage> imgs, int rate, int resource_limit)
    {
        super(name, position, animation_rate, imgs, rate);
        this.resource_limit = resource_limit;
        this.resource_count = 0;
    }

    public String entity_string()
    {
        return "miner " + get_name() + " " + get_position().x + " " + get_position().y
                + " " + resource_limit + " " + get_rate() + " " + get_animation_rate();
    }

    public int get_resource_limit()
    {
        return resource_limit;
    }

    public void set_resource_count(int n)
    {
        resource_count = n;
    }

    public int get_resource_count()
    {
        return resource_count;
    }

    public Point next_position(WorldModel world, Point dest_pt)
    {
        int horiz = Actions.sign(dest_pt.getX() - get_position().getX());
        Point new_pt = new Point(get_position().getX() + horiz, get_position().getY());

        if (horiz == 0 || world.is_occupied(new_pt))
        {
            int vert = Actions.sign(dest_pt.getY() - get_position().getY());
            new_pt = new Point(get_position().getX(), get_position().getY());

            if (vert == 0 || world.is_occupied(new_pt))
            {
                new_pt = new Point(get_position().getX(), get_position().getY());
            }
        }
        return new_pt;
    }
    /*
    public void schedule_miner(WorldModel world, int ticks, HashMap<String, List<PImage>> i_store)
    {
        schedule_action(world, create_entity_action(world, i_store), ticks + get_rate());
        schedule_animation(world, 0);
    }

    public create_miner_action(WorldModel world, HashMap<String, List<PImage>> i_store)
    {
        return create_miner_not_full_action(world, i_store);
    }

    public Position try_transform_miner(WorldModel world, Function transform)
    {
        Animated new_entity = transform(world);
        if (this != new_entity)
        {
            world.clear_pending_actions_world(this);
            world.remove_entity_at(get_position());
            world.add_entity(new_entity);
            new_entity.schedule_animation(world, 0);
        }
        return new_entity;
    }
    */
}
