import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chanye on 4/28/2015.
 */
public class Vein
    extends  Not_animated
{
    private int resource_distance;

    public Vein(String name, List<PImage> imgs, Point position, int rate)
    {
        super(name, imgs, position, rate);
        this.resource_distance = 1;
    }

    public String entity_string()
    {
        return "vein " + get_name() + " " + get_position().x + " " + get_position().y + " " + get_rate() + " " + resource_distance;
    }

    public int get_resource_distance()
    {
        return resource_distance;
    }
}
