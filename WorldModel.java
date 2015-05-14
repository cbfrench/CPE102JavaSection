import javafx.geometry.Pos;

import javax.swing.text.html.parser.Entity;
import java.util.ArrayList;

/**
 * Created by Chanye on 4/29/2015.
 */
public class WorldModel {
    private int num_rows;
    private int num_cols;
    private Grid occupancy;
    private Grid background;
    private ArrayList<Position> entities;
    private OrderedList action_queue;

    public WorldModel(int num_rows, int num_cols, Position background) {
        this.background = new Grid(num_cols, num_rows, background);
        this.num_rows = num_rows;
        this.num_cols = num_cols;
        this.occupancy = new Grid(num_cols, num_rows, null);
        this.entities = new ArrayList<Position>();
        this.action_queue = new OrderedList();
    }

    public boolean within_bounds(Point pt) {
        return (pt.x >= 0 && pt.x < num_cols && pt.y >= 0 && pt.y < num_rows);
    }

    public boolean is_occupied(Point pt)
    {
        return (within_bounds(pt) && occupancy.get_cell(pt) != null);
    }

    public int distance_sq(Point p1, Point p2) {
        return (p1.x - p2.x) * (p1.x - p2.x) + (p1.y - p2.y) * (p1.y - p2.y);
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

    public void add_entity(Position entity) {
        Point pt = entity.get_position();
        if (within_bounds(pt)) {
            Position old_entity = occupancy.get_cell(pt);
            if (old_entity != null) {
                old_entity.clear_pending_actions();
            }
            occupancy.set_cell(pt, entity);
            entities.add(entity);
        }
    }

    public ArrayList<Point> move_entity(Position entity, Point pt) {
        ArrayList<Point> tiles = new ArrayList<Point>();
        if (within_bounds(pt)) {
            Point old_pt = entity.get_position();
            occupancy.set_cell(old_pt, null);
            tiles.add(old_pt);
            occupancy.set_cell(pt, entity);
            tiles.add(pt);
            entity.set_position(pt);
        }
        return tiles;
    }

    public void remove_entity(Position entity) {
        remove_entity_at(entity.get_position());
    }

    public void remove_entity_at(Point pt) {
        if (within_bounds(pt) && occupancy.get_cell(pt) != null) {
            Position entity = occupancy.get_cell(pt);
            Point point = new Point(-1, -1);
            entity.set_position(point);
            entities.remove(entity);
            occupancy.set_cell(pt, null);
        }
    }

    public Position get_background(Point pt)
    {
        if (within_bounds(pt))
        {
            return background.get_cell(pt);
        }
        else
        {
            return null;
        }
    }

    public void set_background(Point pt, Position bgnd)
    {
        if (within_bounds(pt))
        {
            background.set_cell(pt, bgnd);
        }
    }

    public Position get_tile_occupant(Point pt)
    {
        if (within_bounds(pt))
        {
            return occupancy.get_cell(pt);
        }
        else
        {
            return null;
        }
    }

    public ArrayList<Position> get_entities()
    {
        return entities;
    }
}
