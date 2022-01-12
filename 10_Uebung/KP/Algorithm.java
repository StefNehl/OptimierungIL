import java.util.ArrayList;
import java.util.Arrays;

/**
 * <b>Abstract class containing one algorithm for the knapsack problem.</b>
 * 
 * @author Rostislav Stanek
 */
public abstract class Algorithm {
	/**
	 * <b>Objective function value.</b>
	 */
	private int oV;

	/**
	 * <b>Running time.</b>
	 */
	private double time;

	/**
	 * <b>Solution as an ArrayList of all items contained in the knapsack. This member variable is set to null by the constructor!</b>
	 */
	protected ArrayList<Item> solution;
	
	/**
	 * <b>Method implementing one solution algorithm. This method must set the protected variable solution.</b>
	 *
	 * @param instance Instance.
	 */
	protected abstract void algorithm(final Instance instance);
	
	/**
	 * Constructor of the class Algorithm.
	 */
	public Algorithm()
	{
		oV = 0;
		time = 0;
		solution = null;
	}
	
    public String toString()
    {
    	return getClass().getSimpleName() + ": ov: \"" + oV + "\", time: \"" + time + "\", solution: " + Arrays.toString(solution.toArray());
    }
    
	/**
	 * <b>Getter to the variable ov (objective function value).</b>
	 *
	 * @return Objective function value.
	 */
	int getOV()
	{
		return oV;
	}

	/**
	 * <b>Getter to the variable time (running time of the algorithm).</b>
	 *
	 * @return Running time of the algorithm.
	 */
	double getTime()
	{
		return time;
	}

	/**
	 * <b>Getter to the variable solution (solution as an ArrayList of all items contained in the knapsack).</b>
	 *
	 * @return Solution as an ArrayList of all items contained in the knapsack.
	 */
	ArrayList<Item> getSolution()
	{
		return solution;
	}
    
	/**
	 * <b>Method calling the method algorithm.</b>
	 *
	 * The method automatically miss the elapsed time and computes the objective value.
	 *
	 * @param instance Instance.
	 */
	void solve(final Instance instance)
	{
		solution = new ArrayList<Item>();

		long start = System.currentTimeMillis();
		
		algorithm(instance);
		oV = instance.oV(solution);
		
		long end = System.currentTimeMillis();
		time = (end - start) / 1000.0;
	}
}
