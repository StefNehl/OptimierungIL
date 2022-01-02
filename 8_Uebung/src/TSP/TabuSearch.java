package TSP;
import java.io.IOException;
/**
 * <b>Class implementing the tabu search.</b>
 * 
 * @author Your name.
 */
public class TabuSearch extends Algorithm
{
	/*
	 * Your parameters as private variables.
	 */
	
	/**
	 * <b>Method implementing the tabu search.</b>
	 *
	 * @param instance2D Test instance.
	 */
	protected void algorithm(final Instance2D instance2D)
	{
		Algorithm startHeuristic = new NearestNeighbourHeuristic(0, true);
		startHeuristic.solve(instance2D);
		solution = startHeuristic.getSolution();
		
		/*
		 * Your algorithm.
		 */
	}
	
	/*
	 * Constructor and the method toString (like in the class NearestNeighbourHeuristic).
	 */
	
	/**
	 * <b>The main function of the class TabuSearch.</b>
	 * 
	 * @param args Filename of a file containing one instance.
	 * @throws IOException if the number of arguments is not 1.
	 */
	public static void main(String[] args) throws IOException
	{
		if (args.length != 1)
		{
			throw new IOException();
		}
		
		Instance2D instance2D = new Instance2DDistanceMatrix(args[0]);
		TabuSearch tabuSearch = new TabuSearch();
		tabuSearch.solve(instance2D);
		instance2D.draw(800, 800, tabuSearch.getSolution());
		System.out.println(tabuSearch);
	}
}
