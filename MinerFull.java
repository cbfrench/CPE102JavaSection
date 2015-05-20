import processing.core.PImage;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Chanye on 4/28/2015.
 */
public class MinerFull
    extends Miner
{
    public MinerFull(String name, Point position, int animation_rate,
                     List<PImage> imgs, int rate, int resource_limit)
    {
        super(name, position, animation_rate, imgs, rate, resource_limit);
    }
}
