import processing.core.PApplet;
import processing.core.PConstants;
import processing.core.PImage;

import java.io.File;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.*;

/**
 * Created by Chanye on 5/13/2015.
 */
public class Image_store
{
    PApplet Papplet;
    public static final String DEFAULT_IMAGE_NAME = "background_default";
    protected static final int[] DEFAULT_IMAGE_COLOR = {128, 128, 128, 0};
    private static final int COLOR_MASK = 0xffffff;
    private Map<String, ArrayList<PImage>> images = new HashMap();

    public HashMap<String, List<PImage>> load_images(String filename, int tile_width, int tile_height)
    {
        Scanner lines;
        URL path = ClassLoader.getSystemResource(filename);

        try
        {
            lines = new Scanner(new File(path.getFile()));
        }
        catch(FileNotFoundException e)
        {
            System.out.println(e);
            return null;
        }
        HashMap<String, List<PImage>> imgs = new HashMap<>();
        String line;

        while (lines.hasNextLine())
        {
            line = lines.nextLine();
            process_image_line(imgs, line);
        }
        return imgs;
    }

    public void process_image_line(HashMap<String, List<PImage>> images, String line)
    {
        String[] attrs = line.split(" ");
        if (attrs.length >= 2)
        {
            String key = attrs[0];
            PImage img;
            if (attrs.length == 2)
            {
                img = new PApplet().loadImage(attrs[1]);
            }
            else
            {
                img = set_alpha(new PApplet().loadImage(attrs[1]), new PApplet().color(Integer.parseInt(attrs[2]), Integer.parseInt(attrs[3]),
                        Integer.parseInt(attrs[4])), Integer.parseInt(attrs[5]));
            }
            if (img != null)
            {
                List<PImage> imgs = get_images_internal(images, key);
                imgs.add(img);
                images.put(key, imgs);
            }
        }
    }

    public List<PImage> get_images_internal(HashMap<String, List<PImage>> images, String key)
    {
        if (images.containsKey(key))
        {
            return images.get(key);
        }
        else
        {
            return new ArrayList<PImage>();
        }
    }

    public ArrayList<PImage> get_images(String key)
    {
        if (images.containsKey(key))
        {
            return images.get(key);
        }
        else
        {
            return images.get(DEFAULT_IMAGE_NAME);
        }
    }

    public PImage set_alpha(PImage img, int mask_color, int alpha)
    {
        mask_color &= COLOR_MASK;
        img.format = PConstants.ARGB;
        img.loadPixels();
        for (int i = 0 ; i < img.pixels.length; i++)
        {
            if ((img.pixels[i] & COLOR_MASK) == mask_color)
            {
                img.pixels[i] = (alpha << 24) | mask_color;
            }
        }
        img.updatePixels();
        return img;
    }
}
