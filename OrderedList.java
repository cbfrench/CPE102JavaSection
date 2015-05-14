import java.util.ArrayList;

/**
 * Created by Chanye on 5/3/2015.
 */
public class OrderedList
{
    private ArrayList<ListItem> list;

    public OrderedList()
    {
        this.list = new ArrayList<ListItem>();
    }

    public void insert(Object item, int ord)
    {
        int size = list.size();
        int idx = 0;
        while (idx < size && list.get(idx).ord < ord)
        {
            idx++;
        }
        list.set(idx, new ListItem(item, ord));
    }

    public void remove(Object item)
    {
        int size = list.size();
        int idx = 0;
        while (idx < size && list.get(idx).item != item)
        {
            idx++;
        }

        if (idx < size)
        {
            list.remove(idx + 1);
        }
    }

    public ListItem head()
    {
        if (list != new ArrayList<ListItem>())
        {
            return list.get(0);
        }
        else
        {
            return null;
        }
    }

    public ListItem pop()
    {
        if (list != new ArrayList<ListItem>())
        {
            ListItem x = list.get(0);
            list.remove(0);
            return x;
        }
        else
        {
            return null;
        }
    }
}
