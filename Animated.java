import processing.core.PImage;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.LongConsumer;

/**
 * Created by Chanye on 4/28/2015.
 */
public class Animated
    extends Position
{
    private int animation_rate;

    public Animated(String name, Point position, int animation_rate, ArrayList<PImage> imgs)
    {
        super (name, position, imgs);
        this.animation_rate = animation_rate;
    }

    public int get_animation_rate()
    {
        return animation_rate;
    }

    public List<PImage> get_images()
    {
        return imgs;
    }


/*
    public LongConsumer create_animation_action(WorldModel world, Time repeat_count)
    {
        LongConsumer[] action = {null};
        action[0] = (long current_ticks) ->
        {
            remove_pending_action(action[0]);
            next_image();
            if (repeat_count != 1)
            {
                schedule_action(world, create_animation_action(world, Math.max(repeat_count.getTime() - 1, 0)), current_ticks + get_animation_rate());
            }
            List<Point> newList = new ArrayList<Point>();
            newList.add(get_position());
        };
        return action[0];
    }

    public void schedule_animation(WorldModel world, int repeat_count)
    {
        schedule_action(world, create_animation_action(world, repeat_count), get_animation_rate());
    }*/
}
