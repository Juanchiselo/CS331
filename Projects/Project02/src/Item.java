public class Item
{
    private int weight;
    private int profit;
    private int itemNumber;

    public Item(int itemNumber, int weight, int profit)
    {
        this.itemNumber = itemNumber;
        this.weight = weight;
        this.profit = profit;
    }

    public int getWeight()
    {
        return weight;
    }

    public void setWeight(int weight)
    {
        this.weight = weight;
    }

    public int getProfit()
    {
        return profit;
    }

    public void setProfit(int profit)
    {
        this.profit = profit;
    }

    public double getRatio()
    {
        return (double) profit/ weight;
    }

    public int getItemNumber()
    {
        return itemNumber;
    }
}
