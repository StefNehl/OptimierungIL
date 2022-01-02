package TSP;

public class TabuSolution extends Solution {

    private int remainingTabu;

    public TabuSolution(int[] route, int tabu)
    {
        super(route, -1, -1,-1);
        this.remainingTabu = tabu;
    }

    public int getRemainingTabu() { return remainingTabu; }

    public void reduceRemainingTabu()
    {
        if(remainingTabu > 0)
            remainingTabu--;
    }

}
