/**
 * Created by Chanye on 5/3/2015.
 */
public class ListItem
{
    protected long ord;
    protected Object item;

    public ListItem(Object item, long ord)
    {
        this.ord = ord;
        this.item = item;
    }

    public boolean __eq__(ListItem a, ListItem b)
    {
        return a.item == b.item && a.ord == b.ord;
    }
}
