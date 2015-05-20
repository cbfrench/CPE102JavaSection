import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chanye on 4/28/2015.
 */
public class Background
    extends Position
{
    Point pt;
    public Background(String name, List<PImage> imgs)
    {
        super(name, null, imgs);
        this.imgs = imgs;
    }
}
