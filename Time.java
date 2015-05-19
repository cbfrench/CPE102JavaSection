import java.util.ArrayList;

/**
 * Created by Chanye on 5/18/2015.
 */
public class Time
{
    private long run_time;
    private Operation actOperation;

    public Time(Operation actOperation, long run_time)
    {
        this.actOperation = actOperation;
        this.run_time = run_time;
    }

    public long getTime()
    {
        return run_time;
    }

    public Operation getOperation()
    {
        return actOperation;
    }
}
