package Project01;

public class RuntimeRow
{
    private int n;
    private String time;

    public RuntimeRow(int n, String time)
    {
        this.n = n;
        this.time = time;
    }

    public int getN()
    {
        return n;
    }

    public String getTime()
    {
        return time;
    }

    public void setTime(double time)
    {
        this.time = String.valueOf(time);
    }

    public void setTime(String time)
    {
        this.time = time;
    }
}
