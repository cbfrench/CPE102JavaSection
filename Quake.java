import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.function.LongConsumer;

/**
 * Created by Chanye on 4/28/2015.
 */
public class Quake
    extends Animated
{
    public Quake(String name, Point position, int animation_rate, ArrayList<PImage> imgs)
    {
        super(name, position, animation_rate, imgs);
    }
    /*
    public void schedule_quake(WorldModel world, int ticks)
    {
        schedule_animation(world, Actions.QUAKE_STEPS);
        schedule_action(world, create_entity_death_action(world), ticks + Actions.QUAKE_DURATION);
    }
*/
    public void remove_entity(WorldModel world)
    {
        for (Object action : get_pending_actions())
        {
            world.unschedule_action(action);
        }
        clear_pending_actions();
        world.remove_entity(this);
    }

    public LongConsumer create_entity_death_action(WorldModel world)
    {
        LongConsumer[] action = {};
        action[0] = (long currentTicks) ->
        {
            remove_pending_action(action[0]);
            Point pt = get_position();
            remove_entity(world);
            List<Point> newList = new ArrayList<Point>();
            newList.add(pt);
        };
        return action[0];
    }
}
