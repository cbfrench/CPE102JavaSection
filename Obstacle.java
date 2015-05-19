import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chanye on 4/28/2015.
 */
public class Obstacle
    extends Position
{
    public Obstacle(String name, Point position, ArrayList<PImage> imgs)
    {
        super(name, position, imgs);
    }

    public String entity_string()
    {
        return "Obstacle " + get_name() + " " + get_position().x + " " + get_position().y;
    }
}
