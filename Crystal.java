import processing.core.PImage;

import java.util.List;

/**
 * Created by Chanye on 6/3/2015.
 */
public class Crystal
    extends Actor
{
    public Crystal(String name, Point position, int rate, List<PImage> imgs)
    {
        super(name, position, rate, imgs);
    }

    public String toString()
    {
        return String.format("crystal %s %d %d %d", this.getName(),
                this.getPosition().x, this.getPosition().y, this.getRate());
    }

    public Action createAction(WorldModel world, ImageStore imageStore)
    {
        Action[] action = { null };
        action[0] = ticks -> {
            removePendingAction(action[0]);
            Vein vein = createVein(world, getName() + " -- vein",
                    getPosition(), getRate(), Vein.getResourceDistance(), ticks, imageStore);

            remove(world);
            world.addEntity(vein);
        };
        return action[0];
    }

    private static Vein createVein(WorldModel world, String name,
                                      Point pt, int rate, int resourceDistance, long ticks, ImageStore imageStore)
    {
        Vein vein = new Vein(name, pt, rate,
               Vein.getResourceDistance(),
                imageStore.get("vein"));
        vein.schedule(world, ticks, imageStore);
        return vein;
    }
}
