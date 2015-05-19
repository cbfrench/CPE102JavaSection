import javafx.geometry.Pos;
import processing.core.PImage;

import javax.swing.text.html.parser.Entity;
import java.awt.font.NumericShaper;
import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;

/**
 * Created by Chanye on 4/29/2015.
 */
public class WorldModel
    extends Image_store
{
    protected int num_rows;
    protected int num_cols;
    private Position[][] occupancy;
    private Background[][] background;
    private ArrayList<Position> entities;
    private OrderedList action_queue;
    private Random random;

    public WorldModel(int num_rows, int num_cols, Background background) {
        this.background = new Background[num_rows][num_cols];
        this.num_rows = num_rows;
        this.num_cols = num_cols;
        this.occupancy = new Position[num_rows][num_cols];
        this.entities = new ArrayList<>();
        this.action_queue = new OrderedList();
    }

    public int getNumRows()
    {
        return this.num_rows;
    }

    public int getNumCols()
    {
        return this.num_cols;
    }

    public boolean within_bounds(Point pt) {
        return (pt.x >= 0 && pt.x < num_cols && pt.y >= 0 && pt.y < num_rows);
    }

    public Position[][] get_occupancy()
    {
        return this.occupancy;
    }

    public Position get_tile_occupant(Point pt)
    {
        if (within_bounds(pt))
        {
            return occupancy[pt.getY()][pt.getX()];
        }
        return null;
    }

    public void set_tile_occupant(Point pt, Position entity)
    {
        if (within_bounds(pt))
        {
            occupancy[pt.getY()][pt.getX()] = entity;
        }
    }

    public boolean is_occupied(Point pt)
    {
        return (within_bounds(pt) && get_tile_occupant(pt) != null);
    }

    public int distance_sq(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
    }

    public void schedule_action(Object action, long time)
    {
        action_queue.insert(action, time);
    }

    public Position nearest_entity(ArrayList<Position> entity_dists, ArrayList<Integer> dist) {
        if (entity_dists.size() > 0) {
            int pair = dist.get(0);
            int idx = 0;
            for (int i = 0; i < entity_dists.size(); i++) {
                if (dist.get(i) < pair) {
                    pair = dist.get(i);
                    idx = i;
                }
            }
            return entity_dists.get(idx);
        } else {
            return null;
        }
    }

    public Name find_nearest(Point pt, Position type) {
        ArrayList<Position> oftype = new ArrayList<Position>();
        ArrayList<Integer> distance = new ArrayList<Integer>();
        Position nearest = entities.get(0);
        for (Position ent : entities) {
            if (type instanceof Position) {
                oftype.add(ent);
                distance.add(distance_sq(pt, ent.get_position()));
            }
        }
        return nearest_entity(oftype, distance);
    }

    public void add_entity(Position entity)
    {
        entities.add(entity);
    }

    public void remove_entity(Position entity) {
        remove_entity_at(entity.get_position());
    }

    public void remove_entity_at(Point pt) {
        if(is_occupied(pt))
        {
            Position entity = get_tile_occupant(pt);
            entity.set_position(new Point(-1, -1));
            get_entities().remove(entity);
            set_tile_occupant(pt, null);
        }
    }

    public Background get_background(Point pt)
    {
        if (within_bounds(pt))
        {
            return background[pt.getY()][pt.getX()];
        }
        else
        {
            return null;
        }
    }

    public void set_background(Point pt, Background bgnd)
    {
        if (within_bounds(pt))
        {
            background[pt.getY()][pt.getX()] = bgnd;
        }
    }

    public ArrayList<Position> get_entities()
    {
        return entities;
    }

    public void unschedule_action(Object action)
    {
        action_queue.remove(action);
    }
    /*
    public ArrayList<Integer> update_on_time(int ticks)
    {
        ArrayList<Integer> tiles = new ArrayList<Integer>();

        ArrayList<ListItem> next = action_queue.head();
        while (next && next.ord < 1)
    }
    */

    public PImage get_background_image(Point pt)
    {
        Position background = get_background(pt);
        if (background != null)
        {
            return background.get_image();
        }
        else
        {
            return null;
        }
    }
    /*
    public Quake create_quake(Point pt, int ticks, HashMap<String, List<PImage>> i_store)
    {
        Quake quake = new Quake("quake", pt, Actions.QUAKE_ANIMATION_RATE, new Image_store().get_images("quake"));
        quake.schedule_quake(this, ticks);
        return quake;
    }

    public OreBlob create_blob(String name, Point pt, int rate, int ticks, HashMap<String, List<PImage>> i_store)
    {
        OreBlob blob = new OreBlob(name, pt, (random.nextInt(Actions.BLOB_ANIMATION_MAX - Actions.BLOB_ANIMATION_MIN + 1) + Actions.BLOB_ANIMATION_MIN)
                                              * Actions.BLOB_ANIMATION_RATE_SCALE, Image_store.get_images(i_store, "blob"), rate);
        blob.schedule_blob(this, ticks, i_store);
        return blob;
    }
    */

    public Vein create_vein(String name, Point pt, int ticks, HashMap<String, List<PImage>> i_store)
    {
        Vein vein = new Vein("vein" + name, new Image_store().get_images("vein"), pt, (random.nextInt(Actions.VEIN_RATE_MAX - Actions.VEIN_RATE_MIN + 1)
                + Actions.VEIN_RATE_MIN));
        return vein;
    }
    /*
    public Ore create_ore(String name, Point pt, int ticks, HashMap<String, List<PImage>> i_store)
    {
        Ore ore = new Ore(name, Image_store.get_images(i_store, "blob"), pt, (random.nextInt(Actions.ORE_CORRUPT_MAX - Actions.ORE_CORRUPT_MIN + 1)));
        ore.schedule_ore(this, ticks, i_store);
        return ore;
    }
    */
    public Point find_open_around(Point pt, int distance)
    {
        for (int dy = -distance ; dy <= distance + 1; dy ++)
        {
            for (int dx = -distance ; dx <= distance +1; dx ++ )
            {
                Point new_pt = new Point(pt.getX() + dx, pt.getY() + dy);

                if (within_bounds(new_pt) && !(is_occupied(pt)))
                {
                    return new_pt;
                }
            }
        }
        return null;
    }

    public void clear_pending_actions_world(Position entity)
    {
        for (Object action : entity.get_pending_actions())
        {
            unschedule_action(action);
        }
        entity.clear_pending_actions();
    }
}
