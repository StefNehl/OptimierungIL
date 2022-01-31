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

		var emptySolution = new SolutionWithValue(new ArrayList<>(), 0, 0);
		for(int i = 0; i < capacity + 1; i++)
		{
			solutions[0][i] = emptySolution;
			LogString(emptySolution + "    ");
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
		if(itemIndex == 0)
			return null;

		var bestSolution = solutions[itemIndex-1][currentCapacity];
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
		DynamicProgrammingApproach dynamicProgrammingApproach = new DynamicProgrammingApproach(false, false);
		dynamicProgrammingApproach.solve(instance);
		System.out.println(dynamicProgrammingApproach);
	}
}

//   5: DynamicProgrammingApproach: ov: "12", time: "0.019", solution: [(id: 0, w: 3, p: 4), (id: 3, w: 1, p: 5), (id: 4, w: 5, p: 3)]
//  10: DynamicProgrammingApproach: ov: "270", time: "0.021", solution: [(id: 0, w: 1, p: 100), (id: 1, w: 2, p: 90), (id: 2, w: 3, p: 80)]
// 100: DynamicProgrammingApproach: ov: "963", time: "1.621", solution: [(id: 0, w: 99, p: 12), (id: 1, w: 20, p: 19), (id: 2, w: 48, p: 15), (id: 3, w: 43, p: 6), (id: 5, w: 67, p: 11), (id: 6, w: 76, p: 20), (id: 7, w: 86, p: 17), (id: 8, w: 20, p: 11), (id: 11, w: 37, p: 18), (id: 12, w: 69, p: 14), (id: 13, w: 89, p: 17), (id: 14, w: 19, p: 19), (id: 16, w: 13, p: 14), (id: 18, w: 35, p: 6), (id: 20, w: 31, p: 8), (id: 21, w: 90, p: 13), (id: 22, w: 30, p: 4), (id: 24, w: 15, p: 6), (id: 25, w: 38, p: 5), (id: 27, w: 55, p: 16), (id: 28, w: 68, p: 7), (id: 29, w: 62, p: 8), (id: 30, w: 80, p: 12), (id: 31, w: 6, p: 17), (id: 32, w: 50, p: 18), (id: 34, w: 10, p: 1), (id: 35, w: 39, p: 9), (id: 36, w: 24, p: 7), (id: 37, w: 62, p: 18), (id: 39, w: 96, p: 18), (id: 40, w: 73, p: 18), (id: 41, w: 54, p: 18), (id: 43, w: 44, p: 15), (id: 44, w: 82, p: 12), (id: 45, w: 27, p: 9), (id: 46, w: 36, p: 6), (id: 47, w: 76, p: 13), (id: 48, w: 9, p: 14), (id: 49, w: 50, p: 12), (id: 50, w: 90, p: 17), (id: 52, w: 70, p: 10), (id: 53, w: 33, p: 13), (id: 54, w: 89, p: 20), (id: 55, w: 73, p: 8), (id: 56, w: 75, p: 10), (id: 57, w: 42, p: 6), (id: 61, w: 45, p: 16), (id: 62, w: 27, p: 12), (id: 63, w: 58, p: 9), (id: 64, w: 7, p: 19), (id: 65, w: 4, p: 8), (id: 66, w: 16, p: 20), (id: 67, w: 53, p: 19), (id: 71, w: 41, p: 5), (id: 73, w: 79, p: 17), (id: 74, w: 25, p: 20), (id: 75, w: 5, p: 6), (id: 77, w: 22, p: 18), (id: 80, w: 15, p: 16), (id: 83, w: 4, p: 18), (id: 84, w: 77, p: 9), (id: 85, w: 44, p: 17), (id: 86, w: 92, p: 19), (id: 87, w: 88, p: 19), (id: 88, w: 1, p: 1), (id: 89, w: 8, p: 12), (id: 90, w: 9, p: 9), (id: 91, w: 19, p: 19), (id: 92, w: 1, p: 13), (id: 93, w: 1, p: 13), (id: 94, w: 53, p: 8), (id: 95, w: 88, p: 20), (id: 96, w: 13, p: 17), (id: 98, w: 51, p: 8), (id: 99, w: 76, p: 9)]
//1000: Heap space error at item 200