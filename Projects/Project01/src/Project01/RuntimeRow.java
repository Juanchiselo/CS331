package Project01;

import javafx.beans.property.SimpleStringProperty;

public class RuntimeRow
{
    private final SimpleStringProperty n;
    private final SimpleStringProperty time;

    public RuntimeRow(int n, double time)
    {
        this.n = new SimpleStringProperty(String.valueOf(n));
        this.time = new SimpleStringProperty(String.valueOf(time));
    }

    public String getN()
    {
        return n.get();
    }

    public String getTime()
    {
        return time.get();
    }
}
