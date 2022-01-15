import java.io.IOException;
import java.util.ArrayList;

/**
 * <b>Class implementing the dynamic programming approach.</b>
 * 
 * @author Your name.
 */

public class DynamicProgrammingApproach extends Algorithm
{
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
			System.out.print(sol + "    ");
		}
		System.out.println();

		for(int i = 0; i < items.length; i++)
		{
			var item = items[i];
			possibleItems.add(item);

			for(int c = 0; c < solutions[0].length; c++)
			{
				var sol = findBestSolutionForCurrentSettings(item,i+1, c,solutions);
				solutions[i+1][c] = sol;
				System.out.print(sol + "    ");
			}
			System.out.println();
		}

		solution.addAll(solutions[items.length-1][capacity].getItems());
	}

	private SolutionWithValue findBestSolutionForCurrentSettings(Item currentItem, int itemIndex,int currentCapacity, SolutionWithValue[][] solutions)
	{
		var bestSolution = new SolutionWithValue(new ArrayList<>(), 0, 0);

		if(currentItem.getW() <= currentCapacity)
		{
			var currentProfit = currentItem.getP();
			var currentItems = new ArrayList<Item>();
			currentItems.add(currentItem);
			bestSolution = new SolutionWithValue( currentItems, currentProfit, currentItem.getW());
		}

		for(int i = 0; i < itemIndex; i++)
		{
			var bestSolutionForCapacity = solutions[i][0];
			for(int c = 0; c <= currentCapacity; c++)
			{
				var solution = solutions[i][c];

				if(solution.getTotalWeight() + currentItem.getW() <= currentCapacity)
				{
					if(solution.getTotalProfit() + currentItem.getW() < bestSolutionForCapacity.getTotalProfit())
						continue;

					var solutionItems = (ArrayList<Item>) solution.getItems().clone();
					solutionItems.add(currentItem);

					bestSolutionForCapacity = new SolutionWithValue(solutionItems,
							solution.getTotalProfit() + currentItem.getP(),
							solution.getTotalWeight() + currentItem.getW());
				}
				else bestSolutionForCapacity = solution;
			}

			if(bestSolutionForCapacity.getTotalProfit() > bestSolution.getTotalProfit())
				bestSolution = bestSolutionForCapacity;

		}

		return bestSolution;
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
		DynamicProgrammingApproach dynamicProgrammingApproach = new DynamicProgrammingApproach();
		dynamicProgrammingApproach.solve(instance);
		System.out.println(dynamicProgrammingApproach);
	}
}
