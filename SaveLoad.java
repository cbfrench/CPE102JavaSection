import processing.core.PImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Scanner;

/**
 * Created by Chanye on 5/18/2015.
 */
public class SaveLoad
{
    public static final int PROPERTY_KEY = 0;

    public static final String BGDN_KEY = "background";
    public static final int BGND_NUM_PROPERTIES = 4;
    public static final int BGND_NAME = 1;
    public static final int BGND_COL = 2;
    public static final int BGND_ROW = 3;

    public static final String MINER_KEY = "miner";
    public static final int MINER_NUM_PROPERTIES = 7;
    public static final int MINER_NAME = 1;
    public static final int MINER_LIMIT = 4;
    public static final int MINER_COL = 2;
    public static final int MINER_ROW = 3;
    public static final int MINER_RATE = 5;
    public static final int MINER_ANIMATION_RATE = 6;

    public static final String OBSTACLE_KEY = "obstacle";
    public static final int OBSTACLE_NUM_PROPERTIES = 4;
    public static final int OBSTACLE_NAME = 1;
    public static final int OBSTACLE_COL = 2;
    public static final int OBSTACLE_ROW = 3;

    public static final String ORE_KEY = "ore";
    public static final int ORE_NUM_PROPERTIES = 5;
    public static final int ORE_NAME = 1;
    public static final int ORE_COL = 2;
    public static final int ORE_ROW = 3;
    public static final int ORE_RATE = 4;

    public static final String SMITH_KEY = "blacksmith";
    public static final int SMITH_NUM_PROPERTIES = 7;
    public static final int SMITH_NAME = 1;
    public static final int SMITH_COL = 2;
    public static final int SMITH_ROW = 3;
    public static final int SMITH_LIMIT = 4;
    public static final int SMITH_RATE = 5;
    public static final int SMITH_REACH = 6;

    public static final String VEIN_KEY = "vein";
    public static final int VEIN_NUM_PROPERTIES = 6;
    public static final int VEIN_NAME = 1;
    public static final int VEIN_RATE = 4;
    public static final int VEIN_COL = 2;
    public static final int VEIN_ROW = 3;
    public static final int VEIN_REACH = 5;

    public static void load_world(WorldModel world, HashMap<String, List<PImage>> i_store, String file, boolean run)throws FileNotFoundException {
        Scanner lines;
        URL path = ClassLoader.getSystemResource(file);
        lines = new Scanner(new File(path.getFile()));
        String[] properties;

        while (lines.hasNextLine())
        {
            properties = lines.nextLine().split(" ");
            if (properties.length > 0)
            {
                if (properties[PROPERTY_KEY].compareTo(BGDN_KEY) == 0)
                {
                    add_background(world, properties, i_store);
                }
                else
                {
                    add_entity(world, properties, i_store, run);
                }
            }
        }
    }

    public static void add_background(WorldModel world, String[] properties, HashMap<String, List<PImage>> i_store)
    {
        if (properties.length >= BGND_NUM_PROPERTIES)
        {
            Point pt = new Point(Integer.parseInt(properties[BGND_COL]), Integer.parseInt(properties[BGND_ROW]));
            String name = properties[BGND_NAME];
            world.set_background(pt, new Background(name, i_store.get(name)));
        }
    }

    public static void add_entity(WorldModel world, String[] properties, HashMap<String, List<PImage>> i_store, boolean run)
    {
        Position new_entity = create_from_properties(properties, i_store);
        if (new_entity != null)
        {
            world.add_entity(new_entity);
            if (run)
            {
                schedule_entity(world, new_entity, i_store);
            }
        }
    }

    public static Position create_from_properties(String[] properties, HashMap<String, List<PImage>> i_store)
    {
        String key = properties[PROPERTY_KEY];
        if (properties.length > 0)
        {
            if (key.equals(MINER_KEY))
            {
                return create_miner(properties, i_store);
            }
            if (key.equals(VEIN_KEY))
            {
                return create_vein(properties, i_store);
            }
            if (key.equals(ORE_KEY))
            {
                return create_ore(properties, i_store);
            }
            if (key.equals(SMITH_KEY))
            {
                return create_blacksmith(properties, i_store);
            }
            if (key.equals(OBSTACLE_KEY))
            {
                return create_obstacles(properties, i_store);
            }
            else
            {
                return null;
            }
        }
        else
        {
            return null;
        }
    }

    public static MinerNotFull create_miner(String[] properties, HashMap<String, List<PImage>> i_store)
    {
        if (properties.length == MINER_NUM_PROPERTIES)
        {
            MinerNotFull miner = new MinerNotFull(properties[MINER_NAME],
                    new Point(Integer.parseInt(properties[MINER_COL]), Integer.parseInt(properties[MINER_ROW])),
                    Integer.parseInt(properties[MINER_ANIMATION_RATE]),
                    i_store.get(MINER_KEY),
                    Integer.parseInt(properties[MINER_RATE]),
                    Integer.parseInt(properties[MINER_LIMIT]));
            return miner;
        }
        else
        {
            return null;
        }
    }

    public static Vein create_vein(String[] properties, HashMap<String, List<PImage>> i_store)
    {
        if (properties.length == VEIN_NUM_PROPERTIES)
        {
            Vein vein = new Vein(properties[VEIN_NAME],
                    i_store.get(VEIN_KEY),
                    new Point(Integer.parseInt(properties[VEIN_COL]), Integer.parseInt(properties[VEIN_ROW])),
                    Integer.parseInt(properties[VEIN_RATE]));
            return vein;
        }
        else
        {
            return null;
        }
    }

    public static Ore create_ore(String[] properties, HashMap<String, List<PImage>> i_store)
    {
        if (properties.length == ORE_NUM_PROPERTIES)
        {
            Ore ore = new Ore(properties[ORE_NAME],
                    i_store.get(ORE_KEY),
                    new Point(Integer.parseInt(properties[ORE_COL]), Integer.parseInt(properties[ORE_ROW])),
                    Integer.parseInt(properties[ORE_RATE]));
            return ore;
        }
        else
        {
            return null;
        }
    }

    public static Blacksmith create_blacksmith(String[] properties, HashMap<String, List<PImage>> i_store)
    {
        if (properties.length == SMITH_NUM_PROPERTIES)
        {
            Blacksmith smith = new Blacksmith(properties[SMITH_NAME],
                    new Point(Integer.parseInt(properties[SMITH_COL]), Integer.parseInt(properties[SMITH_ROW])),
                    i_store.get(SMITH_KEY),
                    Integer.parseInt(properties[SMITH_LIMIT]),
                    Integer.parseInt(properties[SMITH_RATE]),
                    Integer.parseInt(properties[SMITH_REACH]));
            return smith;
        }
        else
        {
            return null;
        }
    }

    public static Obstacle create_obstacles(String[] properties, HashMap<String, List<PImage>> i_store)
    {
        if (properties.length == OBSTACLE_NUM_PROPERTIES)
        {
            Obstacle obs = new Obstacle(properties[OBSTACLE_NAME],
                    new Point(Integer.parseInt(properties[OBSTACLE_COL]), Integer.parseInt(properties[OBSTACLE_ROW])),
                    i_store.get(OBSTACLE_KEY));
            return obs;
        }
        else
        {
            return null;
        }
    }

    public static void schedule_entity(WorldModel world, Position entity, HashMap<String, List<PImage>> i_store)
    {
        if (entity instanceof MinerNotFull)
        {
            return;
        }
        if (entity instanceof  Vein)
        {
            return;
        }
        if (entity instanceof Ore)
        {
            return;
        }
    }
}
