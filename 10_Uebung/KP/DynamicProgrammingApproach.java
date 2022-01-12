import java.io.IOException;

/**
 * <b>Class implementing the dynamic programming approach.</b>
 * 
 * @author Your name.
 */

public class DynamicProgrammingApproach extends Algorithm
{
	/**
	 * <b>Method implementing the dynamic programming approach.</b>
	 *
	 * @param instance Test instance.
	 */
	protected void algorithm(final Instance instance)
	{
		
		/*
		 * 
		 * An example demonstrating how to use the framework inside of the method algorithm. 
		 *
		solution.add(instance.getItems()[0]);
		solution.add(instance.getItems()[1]);
		
		if (instance.isFeasible(solution))
		{
			System.out.println("The solution is feasible.");
		}
		else
		{
			System.out.println("The solution is not feasible.");
		}
		
		System.out.println("Objective function value: " + instance.oV(solution));
		*/
		
	}
	
	/**
	 * <b>The main function of the class DynamicProgrammingApproach.</b>
	 * 
	 * @param args Filename of a file containing one instance.
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
		if (args.length != 1)
		{
			throw new IOException();
		}
		
		Instance instance = new Instance(args[0]);
		DynamicProgrammingApproach dynamicProgrammingApproach = new DynamicProgrammingApproach();
		dynamicProgrammingApproach.solve(instance);
		System.out.println(dynamicProgrammingApproach);
	}
}
