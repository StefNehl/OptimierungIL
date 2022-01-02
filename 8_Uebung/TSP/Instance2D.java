import java.io.IOException;
import java.io.FileInputStream;
import java.util.Scanner;
import java.util.Arrays;
import java.awt.Color;

/**
 * <b>Abstract class containing one Euclidean TSP instance.</b>
 * 
 * @author Rostislav Stanek
 */
public abstract class Instance2D {
	/**
	 * <b>Filename extension for the files from the TSPLIB.</b>
	 */
	public static final String INPUT_FILE_TSPLIB_FILENAME_EXTENSION = ".tsp";

	/**
	 * <b>Tag for the TSPLIB: "NAME:".</b>
	 */
	public static final String TAG_NAME = "NAME:";

	/**
	 * <b>Tag for the TSPLIB: "TYPE:".</b>
	 */
	public static final String TAG_TYPE = "TYPE:";

	/**
	 * <b>Tag for the TSPLIB: "COMMENT:".</b>
	 */
	public static final String TAG_COMMENT = "COMMENT:";

	/**
	 * <b>Tag for the TSPLIB: "DIMENSION:".</b>
	 */
	public static final String TAG_DIMENSION = "DIMENSION:";

	/**
	 * <b>Tag for the TSPLIB: "EDGE_WEIGHT_TYPE:".</b>
	 */
	public static final String TAG_EDGE_WEIGHT_TYPE = "EDGE_WEIGHT_TYPE:";

	/**
	 * <b>Tag for the TSPLIB: "NODE_COORD_SECTION".</b>
	 */
	public static final String TAG_NODE_COORD_SECTION = "NODE_COORD_SECTION";

	/**
	 * <b>Tag for the TSPLIB: "EOF:".</b>
	 */
	public static final String TAG_EOF = "EOF";

	/**
	 * <b>Value for the tag "TYPE:" for the TSPLIB.</b>
	 */
	public static final String VALUE_TYPE = "TSP";

	/**
	 * <b>Value "EUC_2D" for the tag "EDGE_WEIGHT_TYPE:" for the TSPLIB.</b>
	 */
	public static final String VALUE_EDGE_WEIGHT_TYPE_EUC_2D = "EUC_2D";
	
	/**
	 * <b>Filename of the file with the instance contained in this object.</b>
	 */
	protected final String filename;

	/**
	 * <b>Name of the test instance.</b>
	 */
	protected final String name;

	/**
	 * <b>Comment describing the test instance.</b>
	 */
	protected final String comment;

	/**
	 * <b>Number of vertices.</b>
	 */
	protected final int n;

	/**
	 * <b>Array containing all graph vertices corresponding to points in the Euclidean plane.</b>
	 */
	protected final Point2D[] points2D;
	
	/**
	 * <b>Constructor of the class Instance2D.</b>
	 *
	 * @param filename Filename of a file containing one instance.
	 * @throws IOException if the input file cannot be found or is not valid.
	 */
    Instance2D(final String filename) throws IOException
    {
    	this.filename = filename;
    	
    	if (filename.length() < INPUT_FILE_TSPLIB_FILENAME_EXTENSION.length())
    	{
    		throw new IOException();
    	}
		String suffix = filename.substring(filename.length() - INPUT_FILE_TSPLIB_FILENAME_EXTENSION.length()).trim().toLowerCase();
		if (!suffix.equals(INPUT_FILE_TSPLIB_FILENAME_EXTENSION))
		{
			throw new IOException();
		}
		
		FileInputStream fileInputStream = new FileInputStream(filename);       
		Scanner scanner = new Scanner(fileInputStream);
		
		try
		{
			String inputLine;
	
			//NAME.
			inputLine = scanner.nextLine().trim();
			if (!inputLine.substring(0, TAG_NAME.length()).equals(TAG_NAME))
			{
				throw new IOException();
			}
			inputLine = inputLine.substring(TAG_NAME.length()).trim();
			if (inputLine == "") {
				throw new IOException();
			}
			name = inputLine;
	
			//TYPE.
			inputLine = scanner.nextLine().trim();
			if (!inputLine.substring(0, TAG_TYPE.length()).equals(TAG_TYPE))
			{
				throw new IOException();
			}
			inputLine = inputLine.substring(TAG_TYPE.length()).trim();
			if (!inputLine.equals(VALUE_TYPE))
			{
				throw new IOException();
			}
	
			//COMMENT.
			inputLine = scanner.nextLine().trim();
			if (!inputLine.substring(0, TAG_COMMENT.length()).equals(TAG_COMMENT))
			{
				throw new IOException();
			}
			inputLine = inputLine.substring(TAG_COMMENT.length()).trim();
			comment = inputLine;
	
			//DIMENSION.
			inputLine = scanner.nextLine().trim();
			if (!inputLine.substring(0, TAG_DIMENSION.length()).equals(TAG_DIMENSION)) {
				throw new IOException();
			}
			inputLine = inputLine.substring(TAG_DIMENSION.length()).trim();
			n = Integer.parseInt(inputLine);
			if (n < 3)
			{
				throw new IOException();
			}
	
			//EDGE_WEIGHT_TYPE.
			inputLine = scanner.nextLine().trim();
			if (!inputLine.substring(0, TAG_EDGE_WEIGHT_TYPE.length()).equals(TAG_EDGE_WEIGHT_TYPE)) {
				throw new IOException();
			}
			inputLine = inputLine.substring(TAG_EDGE_WEIGHT_TYPE.length()).trim();
			if (!inputLine.equals(VALUE_EDGE_WEIGHT_TYPE_EUC_2D))
			{
				throw new IOException();
			}
	
			//NODE_COORD_SECTION.
			inputLine = scanner.nextLine().trim();
			if (!inputLine.equals(TAG_NODE_COORD_SECTION)) {
				throw new IOException();
			}
	
			points2D = new Point2D[n];
			for (int i = 0; i < n; i++) {
				inputLine = scanner.nextLine().trim();
				String[] inputLineSplit = inputLine.split("\\s+");
				if (inputLineSplit.length != 3)
				{
					throw new IOException();
				}
				int j = Integer.parseInt(inputLineSplit[0]);
				if (j != i + 1) {
					throw new IOException();
				}
				points2D[i] = new Point2D(Double.parseDouble(inputLineSplit[1]), Double.parseDouble(inputLineSplit[2]));
			}
	
			//EOF.
			inputLine = scanner.nextLine().trim();
			if (!inputLine.equals(TAG_EOF)) {
				throw new IOException();
			}
			
			//Reminder.
			while(scanner.hasNextLine())  
			{
				inputLine = scanner.nextLine().trim();
				if (!inputLine.isEmpty())
				{
					throw new IOException();
				}
			}
		} finally
		{
			scanner.close();
		}
    }
    
    public String toString()
    {
    	return getClass().getSimpleName() + ": name: \"" + name + "\", comment: \"" + comment + "\", n: " + n;
    }
    
	/**
	 * <b>Getter to the variable filename (filename of the file with the instance contained in this object).</b>
	 *
	 * @return Filename of the file with the instance contained in this object.
	 */
	public final String getFilename()
	{
		return filename;
	}

	/**
	 * <b>Getter to the variable name (name of the test instance).</b>
	 *
	 * @return Name of the test instance.
	 */
	public final String getName()
	{
		return name;
	}

	/**
	 * <b>Getter to the variable comment (comment describing the instance).</b>
	 *
	 * @return Comment describing the test instance.
	 */
	public final String getComment()
	{
		return comment;
	}

	/**
	 * <b>Getter to the variable n (number of vertices).</b>
	 *
	 * @return Number of vertices.
	 */
	public final int getN()
	{
		return n;
	}

	/**
	 * <b>Method returning one vertex corresponding to a point in the Euclidean plane.</b>
	 *
	 * @param i Vertex index.
	 * @return Vertex corresponding to a point in the Euclidean plane.
	 */
	public final Point2D getPoint2D(final int i)
	{
		return points2D[i];
	}
    
	/**
	 * <b>Method returning the Euclidean distance between two vertices corresponding to two points in the Euclidean plane.</b>
	 *
	 * @param i Index of the first vertex.
	 * @param j Index of the second vertex.
	 * @return Euclidean distance between two vertices corresponding to two points in the Euclidean plane.
	 */
	public abstract int getD(final int i, final int j);

	/**
	 * <b>Computes the objective function value of a given solution.</b>
	 *
	 * @param solution Solution as an array.
	 * @return Objective function value.
	 */
	public int oV(final int[] solution)
	{
		int result = getD(solution[n - 1], solution[0]);
		for (int i = 0; i < n - 1; i++)
		{
			result += getD(solution[i], solution[i + 1]);
		}
		return result;
	}

	/**
	 * <b>Check whether a given solution is correct.</b>
	 *
	 * @param solution Solution as an array.
	 * @return
	 *   <ul>
	 *     <li>true Given solution is correct.</li>
	 *     <li>false Given solution is not correct.</li>
	 *   </ul>
	 */
	public boolean isTour(final int[] solution)
	{
		boolean[] used = new boolean[n];
		Arrays.fill(used, false);

		for (int i = 0; i < solution.length; i++)
		{
			used[solution[i]] = true;
		}

		for (int i = 0; i < n; i++)
		{
			if (!used[i])
			{
				return false;
			}
		}

		return true;
	}
	
	/**
	 * <b>Draw a solution</b>
	 * 
	 * @param width Width of the drawing area.
	 * @param height Width of the drawing area.
	 * @param solution Solution as an array.
	 */
	public void draw(int width, int height, final int[] solution)
	{
		double minX = Double.MAX_VALUE;
		double maxX = Double.MIN_VALUE;
		double minY = Double.MAX_VALUE;
		double maxY = Double.MIN_VALUE;
		for (int i = 0; i < n; i++)
		{
			if (points2D[i].getX() < minX)
			{
				minX = points2D[i].getX();
			}
			if (points2D[i].getX() > maxX)
			{
				maxX = points2D[i].getX();
			}
			if (points2D[i].getY() < minY)
			{
				minY = points2D[i].getY();
			}
			if (points2D[i].getY() > maxY)
			{
				maxY = points2D[i].getY();
			}
		}
		double ratioX = (maxX - minX) / width / 0.9;
		double ratioY = (maxY - minY) / height / 0.9;
		
		DrawingTool drawingTool = new DrawingTool(width, height, Color.YELLOW, false);
		drawingTool.setColor(Color.BLUE);
		Point2D point2DOld = points2D[solution[0]];
		for (int i = 1; i < n; i++)
		{
			Point2D point2D = points2D[solution[i]];
			drawingTool.line(
					(int) Math.round(width * 0.05 + (point2DOld.getX() - minX) / ratioX),
					(int) Math.round(height * 0.95 - (point2DOld.getY() - minY) / ratioY),
					(int) Math.round(width * 0.05 + (point2D.getX() - minX) / ratioX),
					(int) Math.round(height * 0.95 - (point2D.getY() - minY) / ratioY));
			point2DOld = point2D;
		}
		
		drawingTool.line(
				(int) Math.round(width * 0.05 + (point2DOld.getX() - minX) / ratioX),
				(int) Math.round(height * 0.95 - (point2DOld.getY() - minY) / ratioY),
				(int) Math.round(width * 0.05 + (points2D[solution[0]].getX() - minX) / ratioX),
				(int) Math.round(height * 0.95 - (points2D[solution[0]].getY() - minY) / ratioY));
	}
}
