import processing.core.PImage;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Created by Chanye on 6/1/2015.
 */
public class Tree
    extends Actor
{
    public static int distance;

    private static final int FAIRY_RATE_SCALE = 12;
    private static final int FAIRY_ANIMATION_RATE_SCALE = 50;
    private static final int FAIRY_ANIMATION_MIN = 1;
    private static final int FAIRY_ANIMATION_MAX = 3;

    private static final Random rand = new Random();

    private static List<Fairy> fairies;

    public Tree(String name, Point position, int rate, int distance, List<PImage> imgs)
    {
        super(name, position, rate, imgs);
        this.distance = distance;
        this.fairies = new ArrayList<>();
    }

    public String toString()
    {
        return String.format("vein %s %d %d %d %d", this.getName(),
                this.getPosition().x, this.getPosition().y, this.getRate());
    }

    public Action createAction(WorldModel world, ImageStore imageStore)
    {
        Action[] action = { null };
        action[0] = ticks -> {
            removePendingAction(action[0]);

            Point openPt = findOpenAround(world, getPosition(), distance);
            if (openPt != null && fairies.size() < 4)
            {
                Fairy fairy = createFairy(world, "Fairy --" + getName(),
                        openPt, getRate() / FAIRY_RATE_SCALE, ticks, imageStore);
                fairies.add(fairy);
                world.addEntity(fairy);
            }

            scheduleAction(world, this, createAction(world, imageStore),
                    ticks + getRate());
        };
        return action[0];
    }

    private Fairy createFairy(WorldModel world, String name, Point pt,
                              int rate, long ticks, ImageStore imageStore)
    {
        Fairy Fairy = new Fairy(name, pt, rate,
                FAIRY_ANIMATION_RATE_SCALE * (FAIRY_ANIMATION_MIN +
                                rand.nextInt(FAIRY_ANIMATION_MAX - FAIRY_ANIMATION_MIN)),
                        imageStore.get("Fairy"));
        Fairy.schedule(world, ticks, imageStore);
        return Fairy;
    }

    private Point findOpenAround(WorldModel world, Point pt, int distance)
    {
        Point bottom = new Point(pt.x, pt.y + 4);
        if(world.withinBounds(bottom) && (!(world.isOccupied(bottom))))
        {
            return bottom;
        }

        Point left = new Point(pt.x - 1, pt.y);
        if(world.withinBounds(left) && (!(world.isOccupied(left))))
        {
            return left;
        }
        Point up = new Point(pt.x, pt.y - 1);
        if(world.withinBounds(up) && (!(world.isOccupied(up))))
        {
            return up;
        }

        Point right = new Point(pt.x + 4, pt.y);
        if(world.withinBounds(right) && (!(world.isOccupied(right))))
        {
            return right;
        }

        return null;
    }
}
