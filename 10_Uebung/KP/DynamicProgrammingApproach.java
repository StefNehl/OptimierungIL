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
		var bestSolution = new SolutionWithValue(new ArrayList<>(), 0, 0);

		if(itemIndex != 0)
			pastItemSolution = solutions[itemIndex-1][currentCapacity];

		if(currentCapacity != 0)
			pastCapacitySolution = solutions[itemIndex][currentCapacity - 1];


		var remainingCap = currentCapacity - currentItem.getW();
		if(remainingCap > 0 && remainingCap != currentCapacity)
		{
			var fittingSol = solutions[itemIndex][remainingCap];

			for(int i = 0; i < itemIndex; i++)
			{
				if(pastItemSolution.getTotalProfit() > fittingSol.getTotalProfit())
					fittingSol = pastCapacitySolution;
			}
			if(fittingSol.getItemIds().contains(currentItem.getId()))
				bestSolution = fittingSol;
			else
			{
				var newItems = new ArrayList<Integer>();
				newItems.addAll(fittingSol.getItemIds());
				newItems.add(currentItem.getId());
				bestSolution = new SolutionWithValue(newItems,
						fittingSol.getTotalProfit() + currentItem.getP(),
						fittingSol.getTotalWeight() + currentItem.getW());
			}
		}
		else
		{
			if(currentItem.getW() <= currentCapacity)
			{
				var currentProfit = currentItem.getP();
				var currentItems = new ArrayList<Integer>();
				currentItems.add(currentItem.getId());
				bestSolution = new SolutionWithValue(currentItems, currentProfit, currentItem.getW());
			}
		}

		if(bestSolution.getTotalProfit() < pastItemSolution.getTotalProfit())
			bestSolution = pastItemSolution;
		else if(bestSolution.getTotalProfit() < pastCapacitySolution.getTotalProfit())
			bestSolution = pastCapacitySolution;


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
