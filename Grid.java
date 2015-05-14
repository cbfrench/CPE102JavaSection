import java.util.ArrayList;

/**
 * Created by Chanye on 4/30/2015.
 */
public class Grid
{
    private int width;
    private int height;
    private Position[][] cells;

    public static final int EMPTY = 0;
    public static final int GATHERER = 1;
    public static final int GENERATOR = 2;
    public static final int RESOURCE = 3;

    public Grid(int width, int height, Position occupancy_value)
    {
        this.width = width;
        this.height = height;
        this.cells = new Position[height][width];

        for (int row = 0; row < height ; row ++)
        {
            for (int col = 0; col < width; col ++)
            {
                cells[row][col] = occupancy_value;
            }
        }
    }

    public void set_cell(Point point, Position value)
    {
        cells[point.y][point.x] = value;
    }

    public Position get_cell(Point point)
    {
        return cells[point.y][point.x];
    }
}
