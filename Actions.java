import java.util.function.Function;

/**
 * Created by Chanye on 5/11/2015.
 */
public class Actions
{
    protected static int BLOB_RATE_SCALE = 4;
    protected static int BLOB_ANIMATION_RATE_SCALE = 50;
    protected static int BLOB_ANIMATION_MIN = 1;
    protected static int BLOB_ANIMATION_MAX = 3;
    protected static int ORE_CORRUPT_MIN = 20000;
    protected static int ORE_CORRUPT_MAX = 30000;
    protected static int QUAKE_STEPS = 10;
    protected static int QUAKE_DURATION = 1100;
    protected static int QUAKE_ANIMATION_RATE = 100;
    protected static int VEIN_SPAWN_DELAY = 500;
    protected static int VEIN_RATE_MIN = 8000;
    protected static int VEIN_RATE_MAX = 17000;

    public static boolean adjacent(Point pt1, Point pt2)
    {
        return  ((pt1.getX() == pt2.getX() && Math.abs(pt1.getY() - pt2.getY()) == 1) ||
                (pt1.getY() == pt2.getY() && Math.abs(pt1.getX() - pt2.getX()) == 1));
    }

    public static int sign(int x)
    {
        if (x < 0)
        {
            return -1;
        }
        else if (x > 0)
        {
            return 1;
        }
        else
        {
            return 0;
        }
    }

    public void clear_pending_actions_world(WorldModel world, Position entity)
    {
        for (Object action : entity.get_pending_actions())
        {
            world.unschedule_action(action);
        }
        entity.clear_pending_actions();
    }
}
