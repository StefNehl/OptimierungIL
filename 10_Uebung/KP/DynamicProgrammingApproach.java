import java.io.IOException;
import java.util.ArrayList;

/**
 * <b>Class implementing the dynamic programming approach.</b>
 * 
 * @author Your name.
 */

public class DynamicProgrammingApproach extends Algorithm
{
	private boolean _withLog;
	private boolean _showItemIndicator;
	public DynamicProgrammingApproach(boolean withLog, boolean showItemIndicator)
	{
		_withLog = withLog;
		_showItemIndicator = showItemIndicator;
	}
	/**
	 * <b>Method implementing the dynamic programming approach.</b>
	 *
	 * @param instance Test instance.
	 */
	protected void algorithm(final Instance instance)
	{
		var capacity = instance.getC();
		var numberOfItems = instance.n();

		var solutions = new SolutionWithValue[numberOfItems + 1][capacity + 1];
		var items = instance.getItems();
		var possibleItems = new ArrayList<Item>();

		for(int i = 0; i < capacity + 1; i++)
		{
			var sol = new SolutionWithValue(new ArrayList<>(), 0, 0);
			solutions[0][i] = sol;
			LogString(sol + "    ");
		}
		ConsoleOutLineBreak();

		for(int i = 0; i < items.length; i++)
		{
			var item = items[i];
			possibleItems.add(item);

			for(int c = 0; c < solutions[0].length; c++)
			{
				var sol = findBestSolutionForCurrentSettings(item,i+1, c,solutions);
				solutions[i+1][c] = sol;
				LogString(sol + "    ");
			}
			ConsoleOutLineBreak();

			if(_showItemIndicator)
				System.out.println("Items handled: " + i);
		}

		var itemIds = solutions[items.length][capacity].getItemIds();
		var selectedItems = new ArrayList<Item>();

		for(var i : itemIds)
		{
			solution.add(items[i]);
		}
	}

	//Check first solution above with the same capacity
	//Check current item alone
	//check a solution with remaining cap (solution plus currentitem)
	private SolutionWithValue findBestSolutionForCurrentSettings(Item currentItem, int itemIndex,int currentCapacity, SolutionWithValue[][] solutions)
	{
		var pastItemSolution = new SolutionWithValue(new ArrayList<>(), 0, 0);
		var pastCapacitySolution = new SolutionWithValue(new ArrayList<>(), 0, 0);

		if(itemIndex != 0)
			pastItemSolution = solutions[itemIndex-1][currentCapacity];

		if(currentCapacity != 0)
			pastCapacitySolution = solutions[itemIndex][currentCapacity - 1];

		var bestSolution = pastItemSolution;

		if(currentItem.getP() > bestSolution.getTotalProfit() && currentItem.getW() <= currentCapacity)
		{
			var ids = new ArrayList<Integer>();
			ids.add(currentItem.getId());
			bestSolution = new SolutionWithValue(ids, currentItem.getP(), currentItem.getW());
		}

		var remainingCap = currentCapacity - currentItem.getW();
		if(remainingCap > 0)
		{
			for(int i = 0; i < itemIndex; i++)
			{
				var fittingSolution = solutions[i][remainingCap];
				if(fittingSolution.getItemIds().contains(currentItem.getId()))
					continue;

				var newProfit = fittingSolution.getTotalProfit() + currentItem.getP();
				if(newProfit > bestSolution.getTotalProfit())
				{
					var itemIds = (ArrayList<Integer>) fittingSolution.getItemIds().clone();
					itemIds.add(currentItem.getId());
					bestSolution = new SolutionWithValue(itemIds, newProfit, fittingSolution.getTotalWeight() + currentItem.getW());
				}
			}
		}

		return bestSolution;
	}

	private void LogString(String string)
	{
		if(_withLog)
			System.out.print(string);
	}

	private void ConsoleOutLineBreak()
	{
		if(_withLog)
			System.out.println();
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
		
		var instance = new Instance(args[0]);
		DynamicProgrammingApproach dynamicProgrammingApproach = new DynamicProgrammingApproach(true, false);
		dynamicProgrammingApproach.solve(instance);
		System.out.println(dynamicProgrammingApproach);
	}
}
