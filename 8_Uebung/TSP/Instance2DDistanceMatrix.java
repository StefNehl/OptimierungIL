import java.io.IOException;
import java.lang.Math;

/**
 * <b>Class containing one Euclidean TSP instance.</b>
 * 
 * In this class, the complete distance matrix is created in the constructor.
 * 
 * @author Rostislav Stanek
 */
public class Instance2DDistanceMatrix extends Instance2D
{
	/**
	 * <b>Distance matrix.</b>
	 */
	private final int[][] d;
	
	/**
	 * <b>Constructor of the class Instance2DDistanceMatrix.</b>
	 *
	 * @param filename Filename of a file containing one instance.
	 * @throws IOException if the input file cannot be found or is not valid.
	 */
	public Instance2DDistanceMatrix(final String filename) throws IOException
	{
		super(filename);
		
		d = new int[n][n];
		for (int i = 0; i < n; i++)
		{
			d[i][i] = 0;
			for (int j = 0; j < i; j++) {
				final double deltaX = points2D[i].getX() - points2D[j].getX();
				final double deltaY = points2D[i].getY() - points2D[j].getY();
				d[i][j] = (int) Math.round(Math.sqrt(deltaX * deltaX + deltaY * deltaY));
				d[j][i] = d[i][j];
			}
		}
	}
	
	public int getD(final int i, final int j)
	{
		return d[i][j];
	}
	
	/**
	 * <b>The main function for testing the class Instance2DDistanceMatrix.</b>
	 * 
	 * This function is for test purpouses only.
	 * 
	 * @param args Filename of a file containing one instance.
	 * @throws IOException if the number of arguments is not 1 or if the input is not valid.
	 */
	public static void main(String[] args) throws IOException
	{
		if (args.length != 1)
		{
			throw new IOException();
		}
		
		Instance2D instance2D = new Instance2DDistanceMatrix(args[0]);
		System.out.println(instance2D);
	}
}
