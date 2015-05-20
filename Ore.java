import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chanye on 4/28/2015.
 */
public class Ore
    extends Not_animated
{
    private int rate;

    public Ore(String name, List<PImage> imgs, Point position, int rate)
    {
        super(name, imgs, position, rate);
        this.rate = 5000;
    }

    public String entity_string()
    {
        return "Ore " + get_name() + " " + get_position().x + " " + get_position().y + " " + rate;
    }
    /*
    public void schedule_ore(WorldModel world, int ticks, HashMap<String, List<PImage>> i_store)
    {
        schedule_action(world, create_ore_transform_action(world, i_store), ticks + get_rate());
    }
    */
}
