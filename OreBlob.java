/**
 * Created by Chanye on 4/28/2015.
 */
public class OreBlob
    extends Animated_rate
{
    private int current_img;

    public OreBlob(String name, Point position, int animation_rate, int imgs, int rate)
    {
        super(name, position, animation_rate, imgs, rate);
        this.current_img = 0;
    }
}
