import java.util.ArrayList;

public class SolutionWithValue
{
    private ArrayList<Integer> _newItemIds;
    private int _totalProfit;
    private int _totalWeight;
    private int _size;

    private  SolutionWithValue _oldSolution;

    public SolutionWithValue(ArrayList<Integer> newItemIds, int newTotalProfit, int newTotalWeight, SolutionWithValue oldSolution)
    {
        _newItemIds = newItemIds;
        _oldSolution = oldSolution;
        _totalProfit = newTotalProfit;
        _totalWeight = newTotalWeight;
        _size = newItemIds.size();

        if(_oldSolution != null)
        {
            _totalProfit += _oldSolution.getTotalProfit();
            _totalWeight += _oldSolution.getTotalWeight();
            _size += _oldSolution.getSize();
        }
    }

    public boolean containsItem(Integer itemId)
    {
        if(_newItemIds.contains(itemId))
            return true;

        if(_oldSolution == null)
            return false;

        return _oldSolution.containsItem(itemId);
    }

    public int getSize()
    {
        return _size;
    }

    public int getTotalProfit()
    {
        return _totalProfit;
    }

    public int getTotalWeight()
    {
        return _totalWeight;
    }

    public String toString()
    {
        return "[" + getSize() + "/" + getTotalProfit() + "/" + getTotalWeight() + "]";
    }

    public ArrayList<Integer> cloneCurrentSolution()
    {
        var arrayList = new ArrayList<Integer>();

        if(_oldSolution != null)
            arrayList.addAll(_oldSolution.cloneCurrentSolution());

        arrayList.addAll(_newItemIds);
        return arrayList;
    }

    public String toString_1()
    {
        return hashCode() + "";
    }
}
