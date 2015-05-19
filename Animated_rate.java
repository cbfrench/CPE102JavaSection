import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chanye on 4/28/2015.
 */
public class Animated_rate
    extends Animated
{
    protected int rate;

    public Animated_rate(String name, Point position, int animation_rate, ArrayList<PImage> imgs, int rate)
    {
        super(name, position, animation_rate, imgs);
        this.rate = rate;
    }

    protected int get_rate()
    {
        return rate;
    }
}
