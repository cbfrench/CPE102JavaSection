import java.util.ArrayList;

/**
 * Created by Chanye on 4/28/2015.
 */
public class Animated
    extends Position
{
    private int animation_rate;
    private int imgs;

    public Animated(String name, Point position, int animation_rate, int imgs)
    {
        super (name, position);
        this.animation_rate = animation_rate;
        this.imgs = imgs;
    }

    protected int get_animation_rate()
    {
        return animation_rate;
    }

    protected int get_images()
    {
        return imgs;
    }
}
