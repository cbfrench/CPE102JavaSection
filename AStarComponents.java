/**
 * Created by Chanye on 5/23/2015.
 */
public class AStarComponents
{
    private AStarComponents came_from;
    private int g_score;
    private double f_score;
    private Point pt;

    public AStarComponents(Point pt)
    {
        this.pt = pt;
    }

    public Point getPt()
    {
        return pt;
    }

    public AStarComponents getCameFrom()
    {
        return came_from;
    }

    public void setCameFrom(AStarComponents newFrom)
    {
        came_from = newFrom;
    }

    public int getG_score()
    {
        return g_score;
    }

    public void setG_score(int newG_score)
    {
        g_score = newG_score;
    }

    public double getF_score()
    {
        return f_score;
    }

    public void setF_score(double newF_score)
    {
        f_score = newF_score;
    }
}
