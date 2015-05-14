/**
 * Created by Chanye on 4/28/2015.
 */
public class Ore
    extends Not_animated
{
    private int rate;

    public Ore(String name, int imgs, Point position, int rate, int resource_distance)
    {
        super(name, imgs, position, rate);
        this.rate = 5000;
    }

    public String entity_string()
    {
        return "Ore " + get_name() + " " + get_position().x + " " + get_position().y + " " + rate;
    }
}
