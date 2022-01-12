import java.io.FileInputStream;
import java.io.IOException;
import java.util.Scanner;
import java.util.ArrayList;

/**
 * <b>Abstract class containing one knapsack problem instance.</b>
 * 
 * @author Rostislav Stanek
 */
public class Instance {
	/**
	 * <b>Filename extension for the test instance files.</b>
	 */
	public static final String TEST_INSTANCE_FILE_FILENAME_EXTENSION = ".dat";
	
	/**
	 * Minimum instance size.
	 */
	public static final int MIN_N = 1;
	
	/**
	 * <b>Filename of the file with the instance contained in this object.</b>
	 */
	protected final String filename;
	
	/**
	 * <b>Capacity.</b>
	 */
	int c;
	
	/**
	 * <b>Array containing all items of the knapsack problem.</b>
	 */
	private final Item[] items;
	
	/**
	 * <b>Constructor of the class Instance.</b>
	 *
	 * @param filename Filename of a file containing one instance.
	 * @throws IOException 
	 */
    Instance(final String filename) throws IOException
    {
    	this.filename = filename;
    	
    	if (filename.length() < TEST_INSTANCE_FILE_FILENAME_EXTENSION.length())
    	{
    		throw new IOException();
    	}
		String suffix = filename.substring(filename.length() - TEST_INSTANCE_FILE_FILENAME_EXTENSION.length()).trim().toLowerCase();
		if (!suffix.equals(TEST_INSTANCE_FILE_FILENAME_EXTENSION))
		{
			throw new IOException();
		}
		
		FileInputStream fileInputStream = new FileInputStream(filename);       
		Scanner scanner = new Scanner(fileInputStream);  
	
		try
		{
			String inputLine;
			
			//Instance size.
			do
			{
				inputLine = scanner.nextLine().trim();
			} while (inputLine == "");
			int n = Integer.parseInt(inputLine);
			if (n < MIN_N)
			{
				throw new IOException();
			}
			
			//Capacity.
			do
			{
				inputLine = scanner.nextLine().trim();
			} while (inputLine == "");
			this.c = Integer.parseInt(inputLine);
			if (c <= 0)
			{
				throw new IOException();
			}
			
			//Particular items.
			items = new Item[n];
			for (int i = 0; i < n; i++) {
				inputLine = scanner.nextLine().trim();
				String[] inputLineSplit = inputLine.split("\\s+");
				if (inputLineSplit.length != 3)
				{
					throw new IOException();
				}
				if (Integer.parseInt(inputLineSplit[0]) != i) {
					throw new IOException();
				}
				items[i] = new Item(Integer.parseInt(inputLineSplit[0]), Integer.parseInt(inputLineSplit[1]), Integer.parseInt(inputLineSplit[2]));
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
    	String result = getClass().getSimpleName() + ": filename: \"" + filename + ":\ninstance size: " + n() + "\nc: " + c + "\nitems:\n";
    	for (int i = 0; i < n(); i++)
    	{
    		result += "\tw: " + items[i].getW() + ", p: " + items[i].getP() + (i < n() - 1 ? "\n" : "");
    	}
    	return result;
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
	 * <b>Getter to the variable c (capacity).</b>
	 *
	 * @return Capacity.
	 */
	public final int getC()
	{
		return c;
	}
	
	/**
	 * <b>Method returning all items of the knapsack problem as an array.</b>
	 *
	 * @return All items of the knapsack problem as an array.
	 */
	public final Item[] getItems()
	{
		return items;
	}
	
	/**
	 * <b>Returns the instance size.</b>
	 *
	 * @return Instance size.
	 */
	public final int n()
	{
		return items.length;
	}
    
	/**
	 * <b>Computes the objective function value of a given solution.</b>
	 *
	 * @param solution Solution as an ArrayList of all items contained in the knapsack.
	 * @return Objective function value.
	 */
	public int oV(final ArrayList<Item> solution)
	{
		int result = 0;
		for (Item item : solution)
		{
			result += item.getP();
		}
		return result;
	}
	
	/**
	 * <b>Computes the total weight of a given solution.</b>
	 *
	 * @param solution Solution as an ArrayList of all items contained in the knapsack.
	 * @return Total weight.
	 */
	public int totalWeight(final ArrayList<Item> solution)
	{
		int result = 0;
		for (Item item : solution)
		{
			result += item.getW();
		}
		return result;
	}

	/**
	 * <b>Check whether a given solution is correct.</b>
	 *
	 * @param solution Solution as an ArrayList of all items contained in the knapsack.
	 * @return
	 *   <ul>
	 *     <li>true Given solution is correct.</li>
	 *     <li>false Given solution is not correct.</li>
	 *   </ul>
	 */
	public boolean isFeasible(final ArrayList<Item> solution)
	{
		return totalWeight(solution) <= c;
	}
    
    public static void main(String[] args) throws IOException
    {
    	Instance instance = new Instance(args[0]);
    	System.out.println(instance);
    }

}
