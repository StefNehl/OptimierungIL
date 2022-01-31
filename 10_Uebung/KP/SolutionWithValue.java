import java.util.ArrayList;

public class SolutionWithValue
{
    private ArrayList<Integer> _itemIds;
    private int _totalProfit;
    private int _totalWeight;

    public SolutionWithValue(ArrayList<Integer> itemIds, int totalProfit, int totalWeight)
    {
        _itemIds = itemIds;
        _totalProfit = totalProfit;
        _totalWeight = totalWeight;
    }

    public ArrayList<Integer> getItemIds()
    {
        return _itemIds;
    }

    public int getTotalProfit()
    {
        return _totalProfit;
    }

    public int getTotalWeight(){ return _totalWeight; }

    public String toString()
    {
        return "[" + _itemIds.size() + "/" + _totalProfit + "/" + _totalWeight + "]";
    }

    public String toString_1()
    {
        return hashCode() + "";
    }
}
