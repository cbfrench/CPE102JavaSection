/**
 * Created by Chanye on 5/15/2015.
 */
import javafx.geometry.Pos;
import processing.core.*;
import processing.core.PImage;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Main
    extends PApplet
{
    private static final long TIMER = 100;
    private long next_time;
    private WorldModel world;
    private WorldView view;

    private final int BGND_COLOR = color(220, 230, 245);

    private static boolean RUN_AFTER_LOAD = true;

    private final static String IMAGE_LIST_FILE_NAME = "imagelist";
    private final static String WORLD_FILE = "gaia.sav";

    private static final int WORLD_WIDTH_SCALE = 2;
    private static final int WORLD_HEIGHT_SCALE = 2;

    private static final int SCREEN_WIDTH = 640;
    private static final int SCREEN_HEIGHT = 480;
    private static final int TILE_WIDTH = 32;
    private static final int TILE_HEIGHT = 32;

    public Background create_default_background(List<PImage> img)
    {
        Background Default_background = new Background(Image_store.DEFAULT_IMAGE_NAME, (ArrayList<PImage>) img);
        return Default_background;
    }

    public void setup()
    {
        HashMap<String, List<PImage>> i_store = new Image_store().load_images(IMAGE_LIST_FILE_NAME, TILE_WIDTH, TILE_HEIGHT);
        size(SCREEN_WIDTH, SCREEN_HEIGHT);

        System.out.println(i_store.get("rocks"));
        System.out.println(i_store.get("grass"));

        int num_cols = SCREEN_WIDTH / TILE_WIDTH * WORLD_WIDTH_SCALE;
        int num_rows = SCREEN_HEIGHT / TILE_HEIGHT * WORLD_HEIGHT_SCALE;

        background(BGND_COLOR);
        Background default_background = create_default_background(new Image_store().get_images(Image_store.DEFAULT_IMAGE_NAME));

        this.world = new WorldModel(num_rows, num_cols, default_background);
        this.view = new WorldView(this, SCREEN_WIDTH / TILE_WIDTH, SCREEN_HEIGHT / TILE_HEIGHT, world, TILE_WIDTH, TILE_HEIGHT);

        try
        {
            SaveLoad.load_world(world, i_store, WORLD_FILE, RUN_AFTER_LOAD);
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    private void next_images()
    {
        for (Position entity : this.world.get_entities())
        {
            entity.next_image();
        }
    }

    public void draw()
    {
        long time = System.currentTimeMillis();
        if (time >= next_time)
        {
            next_images();
            next_time = time + TIMER;
            move();
        }

        this.view.draw_viewport();
    }

    private void move()
    {
        for (int x = 0; x < world.getNumCols() ; x ++)
        {
            for (int y = 0; y < world.getNumRows() ; y ++)
            {
                Point pt = new Point(x, y);
                if (world.is_occupied(pt))
                {
                    try
                    {
                        Position entity = (Position) world.get_tile_occupant(pt);
                        if (entity.time(System.currentTimeMillis()));
                        {
                            Time action = entity.get_pending_actions().get(0);
                            action.getOperation().run(System.currentTimeMillis());
                            break;
                        }
                    }
                    catch (ClassCastException e)
                    {

                    }
                }
            }
        }
    }

    public void keyPressed()
    {
        int dx = 0;
        int dy = 0;

        switch(keyCode)
        {
            case UP:
                dy += -1;
                break;

            case DOWN:
                dy++;
                break;

            case LEFT:
                dx += -1;
                break;

            case RIGHT:
                dx++;
                break;
        }
        int[] delta = {dx, dy};
        view.update_view(delta);
    }

    public static void main(String[] args)
    {
        PApplet.main("Main");
    }
}
