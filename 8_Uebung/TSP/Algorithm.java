import java.util.Arrays;

/**
 * <b>Abstract class containing one algorithm for the Euclidean TSP.</b>
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
	 * <b>Solution as a array. This member variable is set to null by the constructor!</b>
	 */
	protected int[] solution;
	
	/**
	 * <b>Method implementing one solution algorithm. This method must set the protected variable solution.</b>
	 *
	 * @param instance2D Instance.
	 */
	protected abstract void algorithm(final Instance2D instance2D);
	
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
    	return getClass().getSimpleName() + ": ov: \"" + oV + "\", time: \"" + time + "\", solution: " + Arrays.toString(solution);
    }
    
	/**
	 * <b>Getter to the variable ov (objective function value).</b>
	 *
	 * @return Objective function value.
	 */
	public int getOV()
	{
		return oV;
	}

	/**
	 * <b>Getter to the variable time (running time of the algorithm).</b>
	 *
	 * @return Running time of the algorithm.
	 */
	public double getTime()
	{
		return time;
	}

	/**
	 * <b>Getter to the variable solution (solution as a dynamic array).</b>
	 *
	 * @return Solution as a dynamic array.
	 */
	public int[] getSolution()
	{
		return solution;
	}
    
	/**
	 * <b>Method calling the method algorithm.</b>
	 *
	 * The method automatically miss the elapsed time and computes the objective value.
	 *
	 * @param instance2D Instance.
	 */
	public void solve(final Instance2D instance2D)
	{
		solution = new int[instance2D.getN()];

		long start = System.currentTimeMillis();
		
		algorithm(instance2D);
		oV = instance2D.oV(solution);
		
		long end = System.currentTimeMillis();
		time = (end - start) / 1000.0;
	}
}
