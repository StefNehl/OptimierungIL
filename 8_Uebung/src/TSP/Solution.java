package TSP;

import java.util.Arrays;

public class Solution
{
    private final int length;
    private final int[] route;
    private int reverseStartIndex = -1;
    private int reverseEndIndex = -1;

    public Solution(int[] route, int length, int reverseStartIndex, int reverseEndIndex)
    {
        this.route = route;
        this.length = length;
        this.reverseStartIndex = reverseStartIndex;
        this.reverseEndIndex = reverseEndIndex;
    }

    public int[] getRoute() { return route; }

    public int getLength() { return length; }

    public int getReverseStartIndex() { return reverseStartIndex; }

    public int getReverseEndIndex() { return reverseEndIndex; }

    public int[] getStartNeighbour()
    {
        return new int[]
                {
                        route[reverseStartIndex-1],
                        route[reverseStartIndex]
                };
    }

    public int[] getEndNeighbour()
    {
        return new int[]
                {
                        route[reverseEndIndex],
                        route[reverseEndIndex+1]
                };
    }

    public String toString()
    {
        return getClass().getSimpleName() + ": ov: \"" + length + "\", solution: " + Arrays.toString(route);
    }
}
