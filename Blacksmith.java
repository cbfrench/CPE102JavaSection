import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chanye on 4/28/2015.
 */
public class Blacksmith
    extends Position
{
    private int resource_limit;
    private int rate;
    private int resource_distance;
    private ArrayList<Integer> imgs;

    public Blacksmith(String name, Point position, List<PImage> imgs, int resource_limit, int rate, int resource_distance)
    {
        super(name, position, imgs);
        this.resource_distance = 1;
    }

    public String entity_string()
    {
        return "Blacksmith " + get_name() + " " + get_position().x + " " + get_position().y;
    }
}
