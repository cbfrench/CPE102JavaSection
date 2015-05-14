/**
 * Created by Chanye on 4/28/2015.
 */
public class Obstacle
    extends Position
{
    public Obstacle(String name, Point position)
    {
        super(name, position);
    }

    public String entity_string()
    {
        return "Obstacle " + get_name() + " " + get_position().x + " " + get_position().y;
    }
}
