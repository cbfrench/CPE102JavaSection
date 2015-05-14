/**
 * Created by Chanye on 4/28/2015.
*/
public class Miner
        extends Animated_rate
{
    private int resource_limit;
    private int resource_count;
    private int current_img;

    public Miner(String name, Point position, int animation_rate,
                 int imgs, int rate, int resource_limit)
    {
        super(name, position, animation_rate, imgs, rate);
        this.resource_limit = resource_limit;
        this.resource_count = 0;
        this.current_img = 0;
    }

    protected String entity_string()
    {
        return "miner " + get_name() + " " + get_position().x + " " + get_position().y
                + " " + resource_limit + " " + get_rate() + " " + get_animation_rate();
    }

    protected int get_resource_limit()
    {
        return resource_limit;
    }

    protected void set_set_resource_count(int n)
    {
        resource_count = n;
    }
}
