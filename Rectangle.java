/**
 * Created by Chanye on 5/18/2015.
 */
public class Rectangle
{
    private int top;
    private int height;
    private int left;
    private int width;

    public Rectangle(int left, int top, int width, int height)
    {
        this.left = left;
        this.top = top;
        this.width = width;
        this.height = height;
    }

    public int getLeft()
    {
        return left;
    }

    public int getRight()
    {
        return left + width;
    }

    public int getTop()
    {
        return top;
    }

    public int getHeight()
    {
        return height;
    }

    public int getBottom()
    {
        return top + height;
    }

    public int getWidth()
    {
        return width;
    }

    public void setLeft(int x)
    {
        left = x;
    }

    public void setWidth(int x)
    {
        width = x;
    }

    public void setHeight(int x)
    {
        height = x;
    }

    public void setTop(int x)
    {
        top = x;
    }

    public boolean collide_point(Point pt)
    {
        return collide_point(pt.getX(), pt.getY());
    }

    public boolean collide_point(int x, int y)
    {
        return x >= getLeft() && y >= getTop() && x < getRight() && y < getBottom();
    }
}
