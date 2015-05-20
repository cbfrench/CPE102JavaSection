import processing.core.PImage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

/**
 * Created by Chanye on 4/27/2015.
 */
public class Position
    extends Name
{
    private Point position;
    private ArrayList<Time> pending_actions;
    protected List<PImage> imgs;
    protected int current_img;

    public Position(String name, Point position, List<PImage> imgs)
    {
        super(name);
        this.position = position;
        this.pending_actions = new ArrayList<Time>();
        this.imgs = imgs;
        this.current_img = 0;
    }

    public void set_position(Point point)
    {
        position = point;
    }
    public Point get_position()
    {
        return position;
    }
    public void remove_pending_action(Object action)
    {
        pending_actions.remove(action);
    }

    public void add_pending_actions(Time action)
    {
        pending_actions.add(action);
    }
    public void schedule_action(WorldModel world, Time action, long time)
    {
        add_pending_actions(action);
        world.schedule_action(action, (int) time);
    }

    public List<PImage> get_images()
    {
        return imgs;
    }

    public PImage get_image()
    {
        return imgs.get(current_img);
    }

    public void next_image()
    {
        this.current_img = (this.current_img + 1) % (this.imgs.size());
    }

    public ArrayList<Time> get_pending_actions()
    {
        return pending_actions;
    }

    public void clear_pending_actions()
    {
        pending_actions = new ArrayList<Time>();
    }

    public Position create_entity_action(WorldModel world, HashMap<String, List<PImage>> i_store)
    {
        return null;
    }

    public boolean time(long current_time)
    {
        return get_pending_actions().size() != 0 && current_time >= get_pending_actions().get(0).getTime();
    }
}
