/**
 * Created by Chanye on 4/28/2015.
 */
public class Blacksmith
    extends Position
{
    private int resource_limit;
    private int rate;
    private int resource_distance;

    public Blacksmith(String name, Point position, int resource_limit, int rate, int resource_distance)
    {
        super(name, position);
        this.resource_distance = 1;
    }

    public String entity_string()
    {
        return "Blacksmith " + get_name() + " " + get_position().x + " " + get_position().y;
    }
}
