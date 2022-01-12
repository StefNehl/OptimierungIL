/**
 * <b>Class containing one item of the knapsack problem.</b>
 * 
 * @author Rostislav Stanek
 */
public final class Item {
	/**
	 * Identification number.
	 */
	private int id;
	
	/**
	 * Weight.
	 */
	private int w;
	
	/**
	 * Profit.
	 */
	private int p;
	
	/**
	 * Constructor.
	 * @param id Identification number.
	 * @param w Weight.
	 * @param p Profit.
	 */
	public Item(final int id, final int w, final int p)
	{
		this.id = id;
		this.w = w;
		this.p = p;
	}
	
	public String toString()
	{
		return "(id: " + id + ", w: " + w + ", p: " + p + ")";
	}
	
	/**
	 * Getter to the variable id (identification number).
	 * @return Identification number.
	 */
	public final int getId()
	{
		return id;
	}
	
	/**
	 * Getter to the variable w (weight).
	 * @return Weight.
	 */
	public final int getW()
	{
		return w;
	}
	
	/**
	 * Getter to the variable p (profit).
	 * @return Profit.
	 */
	public final int getP()
	{
		return p;
	}
}
