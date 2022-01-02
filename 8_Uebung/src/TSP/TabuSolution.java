package TSP;

public class TabuSolution {

    private int remainingTabu;
    private final int length;
    private final int[] route;

    public TabuSolution(int[] route, int tabu, int result)
    {
        this.route = route;
        this.remainingTabu = tabu;
        this.length = result;
    }


    public int[] getRoute() { return route; }

    public int getRemainingTabu() { return remainingTabu; }

    public void reduceRemainingTabu() { remainingTabu--; }

    public int getLength() { return length; }

}
