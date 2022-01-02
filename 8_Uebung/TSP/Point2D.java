/**
 * <b>Class containing one point in an Euclidean plane.</b>
 * 
 * @author Rostislav Stanek
 */
public final class Point2D {
	/**
	 * The x coordinate.
	 */
	private double x;
	
	/**
	 * The y coordinate.
	 */
	private double y;
	
	/**
	 * Constructor.
	 * @param x The x coordinate.
	 * @param y The y coordinate.
	 */
	public Point2D(final double x, final double y)
	{
		this.x = x;
		this.y = y;
	}
	
	public String toString()
	{
		return x + ", " + y;
	}
	
	/**
	 * Getter to the variable x (the x coordinate).
	 * @return The x coordinate.
	 */
	public final double getX()
	{
		return x;
	}
	
	/**
	 * Getter to the variable y (the y coordinate).
	 * @return The y coordinate.
	 */
	public final double getY()
	{
		return y;
	}
}
