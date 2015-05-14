import java.util.ArrayList;

/**
 * Created by Chanye on 4/27/2015.
 */
public class Position
    extends Name
{
    private Point position;
    private ArrayList<Integer> pending_actions;

    public Position(String name, Point position)
    {
        super (name);
        this.position = position;
        this.pending_actions = new ArrayList<Integer>();
    }

    public void set_position(Point point)
    {
        position = point;
    }
    public Point get_position()
    {
        return position;
    }
    protected void remove_pending_action(int action)
    {
        pending_actions.remove(action);
    }

    protected void add_pending_actions(int action)
    {
        pending_actions.add(action);
    }

    protected ArrayList<Integer> get_pending_actions()
    {
        return pending_actions;
    }

    protected void clear_pending_actions()
    {
        pending_actions = new ArrayList<Integer>();
    }
}
