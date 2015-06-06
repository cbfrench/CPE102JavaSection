import processing.core.PImage;

import java.util.List;

/**
 * Created by Chanye on 5/31/2015.
 */
public class Fairy
        extends MobileAnimatedActor
{
    public Fairy(String name, Point position, int rate, int animation_rate, List<PImage> imgs)
    {
        super(name, position, rate, animation_rate, imgs);
    }

    protected boolean canPassThrough(WorldModel world, Point pt)
    {
        return !world.isOccupied(pt);
    }

    private boolean move(WorldModel world, WorldEntity target)
    {
        if (target == null)
        {
            return false;
        }

        if (adjacent(getPosition(), target.getPosition()))
        {
            target.remove(world);
            return true;
        }
        else
        {
            Point new_pt = nextPosition(world, this.getPosition(), target.getPosition());
            WorldEntity old_entity = world.getTileOccupant(new_pt);
            if (old_entity != null && old_entity != this)
            {
                old_entity.remove(world);
            }
            world.moveEntity(this, new_pt);
            return false;
        }
    }

    public Action createAction(WorldModel world, ImageStore imageStore)
    {
        Action[] action = { null };
        action[0] = ticks -> {
            removePendingAction(action[0]);

            WorldEntity target = world.findNearest(getPosition(), OreBlob.class);
            long nextTime = ticks + getRate();

            if (target != null)
            {
                Point tPt = target.getPosition();

                if (move(world, target))
                {
                    Crystal crystal = createCrystal(world, tPt, ticks + 13000, imageStore);
                    world.addEntity(crystal);
                    nextTime = nextTime + getRate();
                }
            }

            scheduleAction(world, this, createAction(world, imageStore),
                    nextTime);

        };
        return action[0];
    }

    private Crystal createCrystal(WorldModel world, Point pt, long ticks,
                              ImageStore imageStore)
    {
        Crystal crystal = new Crystal("crystal", pt, getRate(), imageStore.get("crystal"));
        crystal.schedule(world, ticks, imageStore);
        return crystal;
    }
}