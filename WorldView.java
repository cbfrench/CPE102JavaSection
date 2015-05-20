import processing.core.PApplet;
import processing.core.PGraphics;
import processing.core.PImage;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Chanye on 5/18/2015.
 */
public class WorldView
{
    public static int MOUSE_HOVER_ALPHA = 120;
    public int MOUSE_HOVER_EMPTY_COLOR;

    private Rectangle viewport;
    private WorldModel world;
    private int tile_width;
    private int tile_height;
    private int num_rows;
    private int num_cols;
    private PApplet master;

    public WorldView(PApplet master, int view_cols, int view_rows, WorldModel world, int tile_width, int tile_height)
    {
        this.master = master;
        MOUSE_HOVER_EMPTY_COLOR = master.color(0, 255, 0);
        this.viewport = new Rectangle(0, 0, view_cols, view_rows);
        this.world = world;
        this.tile_width = tile_width;
        this.tile_height = tile_height;
        this.num_rows = world.num_rows;
        this.num_cols = world.num_cols;
    }

    private void draw_background()
    {
        for (int y = 0 ; y < viewport.getHeight() ; y ++)
        {
            for (int x = 0; x < viewport.getWidth() ; x++)
            {
                Point pt = viewport_to_world(viewport, new Point(x, y));
                PImage img = world.get_background_image(pt);
                master.image(img, x * tile_width, y * tile_height);
            }
        }
    }

    private void draw_entities()
    {
        for (Position entity : world.get_entities())
        {
            if (viewport.collide_point(entity.get_position().getX(), entity.get_position().getY()))
            {
                Point v_pt = world_to_viewport(viewport, entity.get_position());
                master.image(entity.get_image(), v_pt.getX() * this.tile_width, v_pt.getY() * this.tile_height);
            }
        }
    }

    public void draw_viewport()
    {
        this.draw_background();
        this.draw_entities();
    }

    public void update_view(int[] delta)
    {
        viewport = create_shifted_viewport(viewport, delta, num_rows, num_cols);
    }

    public void update_view_tiles(ArrayList<Point> tiles)
    {
        for (Point tile : tiles)
        {
            if (viewport.collide_point(tile.getX(), tile.getY()))
            {
                Point v_pt = world_to_viewport(viewport, tile);
                PImage img = get_tile_image(v_pt);
                update_tile(v_pt, img);
            }
        }
    }

    public Rectangle update_tile(Point pt, PImage surface)
    {
        int x = pt.getX() * tile_width;
        int y = pt.getY() * tile_height;

        master.image(surface, x, y);

        return new Rectangle(x, y, tile_width, tile_height);
    }

    private PImage get_tile_image(Point pt)
    {
        Point pt2 = viewport_to_world(viewport, pt);
        PImage bgnd = world.get_background_image(pt2);
        Position occupant = world.get_tile_occupant(pt2);
        if (occupant != null)
        {
            PGraphics temp = master.createGraphics(tile_width, tile_height);
            temp.image(bgnd, 0, 0);
            temp.image(occupant.get_image(), 0, 0);
            return temp.get();
        }
        else
        {
            return bgnd;
        }
    }

    public static int clamp(int v, int low, int high)
    {
        return Math.min(high, Math.max(v, low));
    }

    public Point viewport_to_world(Rectangle viewport, Point pt)
    {
        return new Point(pt.getX() + viewport.getLeft(), pt.getY() + viewport.getTop());
    }

    public Point world_to_viewport(Rectangle viewport, Point pt)
    {
        return new Point(pt.getX() - viewport.getLeft(), pt.getY() - viewport.getTop());
    }

    public Rectangle create_shifted_viewport(Rectangle viewport, int[] delta, int num_rows, int num_cols)
    {
        int new_x = clamp(viewport.getLeft() + delta[0], 0, num_cols - viewport.getWidth());
        int new_y = clamp(viewport.getTop() + delta[1], 0, num_rows - viewport.getHeight());
        return new Rectangle(new_x, new_y, viewport.getWidth(), viewport.getHeight());
    }

    public Rectangle getViewport()
    {
        return this.viewport;
    }
}
