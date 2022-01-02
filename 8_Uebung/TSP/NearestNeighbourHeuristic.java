import java.io.IOException;
import java.util.LinkedList;
import java.util.HashSet;

/**
 * <b>Class implementing the nearest neighbour heuristic.</b>
 * 
 * @author Rostislav Stanek
 */
public class NearestNeighbourHeuristic extends Algorithm
{
	
	/**
	 * <b>Vertex, from which the nearest neighbour heuristic should start.</b>
	 */
	int startVertex;

	/**
	 * <b>Switch determining, whether the nearest neighbour heuristic should be two-directional or not.</b>
	 * 
	 * <ul>
	 *   <li>true The nearest neighbour heuristic should be two-directional.</li>
	 *   <li>false The nearest neighbour heuristic should not be two-directional.</li>
	 * </ul>
	 */
	boolean twoDirectional;
	
	/**
	 * <b>Method implementing the nearest neighbour heuristic.</b>
	 *
	 * @param instance2D Test instance.
	 */
	protected void algorithm(final Instance2D instance2D)
	{	
		LinkedList<Integer> tour = new LinkedList<Integer>();
        tour.add(startVertex);
		HashSet<Integer> free = new HashSet<Integer>();
		for (int i = 0; i < instance2D.getN(); i++)
		{
			if (i != startVertex)
			{
				free.add(i);
			}
		}
		while (!free.isEmpty())
		{
			int minimum = Integer.MAX_VALUE;
			int minimumIndex = -1;
			boolean after = false;
			for (Integer k : free)
			{
				if (instance2D.getD(tour.get(tour.size() - 1), k) < minimum)
				{
					minimum = instance2D.getD(tour.get(tour.size() - 1), k);
					minimumIndex = k;
					after = true;
				}
				
				if (twoDirectional)
				{
					if (instance2D.getD(k, tour.get(0)) < minimum)
					{
						minimum = instance2D.getD(k, tour.get(0));
						minimumIndex = k;
						after = false;
					}
				}
			}
            
            if (after)
            {
                tour.addLast(minimumIndex);
            }
            else
            {
                tour.addFirst(minimumIndex);
            }
            
            free.remove(minimumIndex);
		}
		
		solution = tour.stream().mapToInt(i -> i).toArray();
	}
	
	/**
	 * <b>Constructor of the class NearestNeighbourHeuristic.</b>
	 *
	 * @param startVertex Vertex, from which the nearest neighbour heuristic should start.
	 * @param twoDirectional Switch determining, whether the nearest neighbour heuristic should be two-directional or not.
	 *   <ul>
	 *     <li>true The nearest neighbour heuristic should be two-directional.</li>
	 *     <li>false The nearest neighbour heuristic should not be two-directional.</li>
	 *   </ul>
	 */
	public NearestNeighbourHeuristic(final int startVertex, final boolean twoDirectional)
	{
		this.startVertex = startVertex;
		this.twoDirectional = twoDirectional;
	}
	
	public String toString()
	{
		return super.toString() + ", startVertex: " + startVertex + ", twoDirectional: " + twoDirectional;
	}
	
	/**
	 * <b>Getter to the variable startVertex (vertex, from which the nearest neighbour heuristic should start)</b>
	 *
	 * @return Vertex, from which the nearest neighbour heuristic should start.
	 */
	int getStartVertex()
	{
		return (startVertex);
	}
	
	/**
	 * <b>Setter to the variable startVertex (vertex, from which the nearest neighbour heuristic should start)</b>
	 *
	 * @param startVertex Vertex, from which the nearest neighbour heuristic should start.
	 */
	void setStartVertex(int startVertex)
	{
		this.startVertex = startVertex;
	}

	/**
	 * <b>Getter to the variable twoDirectional (Switch determining, whether the nearest neighbour heuristic should be two-directional or not)</b>
	 *
	 * @return Switch determining, whether the nearest neighbour heuristic should be two-directional or not.
	 *   <ul>
	 *     <li>true The nearest neighbour heuristic should be two-directional.</li>
	 *     <li>false The nearest neighbour heuristic should not be two-directional.</li>
	 *   </ul>
	 */
	boolean getTwoDirectional()
	{
		return (twoDirectional);
	}
	
	/**
	 * <b>Setter to the variable twoDirectional (Switch determining, whether the nearest neighbour heuristic should be two-directional or not)</b>
	 *
	 * @param twoDirectional Switch determining, whether the nearest neighbour heuristic should be two-directional or not.
	 *   <ul>
	 *     <li>true The nearest neighbour heuristic should be two-directional.</li>
	 *     <li>false The nearest neighbour heuristic should not be two-directional.</li>
	 *   </ul>
	 */
	void setTwoDirectional(boolean twoDirectional)
	{
		this.twoDirectional = twoDirectional;
	}
	
	/**
	 * <b>The main function of the class NearestNeighbourHeuristic.</b>
	 * 
	 * @param args
	 *   <ul>
	 *   	<li>Filename of a file containing one instance,</li>
	 *   	<li>vertex, from which the nearest neighbour heuristic should start, and</li>
	 *   	<li>
	 *   		Switch determining, whether the nearest neighbour heuristic should be two-directional or not:
	 *   			<ul>
	 *   				<li>true if the nearest neighbour heuristic should be two-directional, and</li>
	 *   				<li>false if the nearest neighbour heuristic should not be two-directional.</li>
	 *   			</ul>
	 *   	</li>
	 *   </ul> 
	 * @throws IOException if the number of arguments is not 3 or if the input is not valid.
	 */
	public static void main(String[] args) throws IOException
	{
		if (args.length != 3)
		{
			throw new IOException();
		}
		
		Instance2D instance2D = new Instance2DDistanceMatrix(args[0]);
		int startVertex = Integer.parseInt(args[1]);
		boolean twoDirectional;
		if (args[2].equals("true"))
		{
			twoDirectional = true;
		}
		else if (args[2].equals("false"))
		{
			twoDirectional = false;
		}
		else
		{
			throw new IOException();
		}
		NearestNeighbourHeuristic nearestNeighbourHeuristic = new NearestNeighbourHeuristic(startVertex, twoDirectional);
		nearestNeighbourHeuristic.solve(instance2D);
		instance2D.draw(800, 800, nearestNeighbourHeuristic.getSolution());
		System.out.println(nearestNeighbourHeuristic);
	}
}
