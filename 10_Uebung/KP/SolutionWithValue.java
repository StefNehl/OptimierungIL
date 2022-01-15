import java.util.ArrayList;

public class SolutionWithValue
{
    private ArrayList<Item> _items;
    private int _totalProfit;
    private int _totalWeight;

    public SolutionWithValue(ArrayList<Item> items, int totalProfit, int totalWeight)
    {
        _items = items;
        _totalProfit = totalProfit;
        _totalWeight = totalWeight;
    }

    public ArrayList<Item> getItems()
    {
        return _items;
    }

    public int getTotalProfit()
    {
        return _totalProfit;
    }

    public int getTotalWeight(){ return _totalWeight; }

    public String toString()
    {
        return "[" +_totalProfit + "/" + _items.size() + "/" + _totalWeight + "]";
    }
}
