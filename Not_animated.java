import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chanye on 4/28/2015.
 */
public class Not_animated
    extends Position
{
    private int rate;
    private List<PImage> imgs;

    public Not_animated(String name, List<PImage> imgs, Point position, int rate)
    {
        super(name, position, imgs);
        this.rate = rate;
        this.imgs = imgs;
    }

    protected int get_rate()
    {
        return rate;
    }
}
