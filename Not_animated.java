import java.util.ArrayList;

/**
 * Created by Chanye on 4/28/2015.
 */
public class Not_animated
    extends Position
{
    private int rate;
    private int imgs;

    public Not_animated(String name, int imgs, Point position, int rate)
    {
        super(name, position);
        this.rate = rate;
        this.imgs = imgs;
    }

    protected int get_rate()
    {
        return rate;
    }
}
