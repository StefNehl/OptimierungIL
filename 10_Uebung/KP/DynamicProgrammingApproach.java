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

		var emptySolution = new SolutionWithValue(new ArrayList<>(), 0, 0, null);
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

		var itemIds = solutions[items.length][capacity].cloneCurrentSolution();
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

		var currentBestSolution = solutions[itemIndex-1][currentCapacity];
		var bestSolution = solutions[itemIndex-1][currentCapacity];

		if(currentItem.getP() > bestSolution.getTotalProfit() && currentItem.getW() <= currentCapacity)
		{
			var ids = new ArrayList<Integer>();
			ids.add(currentItem.getId());
			bestSolution = new SolutionWithValue(ids, currentItem.getP(), currentItem.getW(), currentBestSolution);
		}

		var remainingCap = currentCapacity - currentItem.getW();
		if(remainingCap > 0)
		{
			for(int i = 0; i < itemIndex; i++)
			{
				var fittingSolution = solutions[i][remainingCap];
				//if(fittingSolution.containsItem(currentItem.getId()))
				//	continue;

				var newProfit = fittingSolution.getTotalProfit() + currentItem.getP();
				if(newProfit > bestSolution.getTotalProfit())
				{
					var itemIds = new ArrayList<Integer>();
					itemIds.add(currentItem.getId());
					bestSolution = new SolutionWithValue(itemIds, currentItem.getP(), currentItem.getW(), fittingSolution);
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
		DynamicProgrammingApproach dynamicProgrammingApproach = new DynamicProgrammingApproach(false, true);
		dynamicProgrammingApproach.solve(instance);
		System.out.println(dynamicProgrammingApproach);
	}
}

//   5: DynamicProgrammingApproach: ov: "12", time: "0.019", solution: [(id: 0, w: 3, p: 4), (id: 3, w: 1, p: 5), (id: 4, w: 5, p: 3)]
//  10: DynamicProgrammingApproach: ov: "270", time: "0.021", solution: [(id: 0, w: 1, p: 100), (id: 1, w: 2, p: 90), (id: 2, w: 3, p: 80)]
// 100: DynamicProgrammingApproach: ov: "963", time: "1.621", solution: [(id: 0, w: 99, p: 12), (id: 1, w: 20, p: 19), (id: 2, w: 48, p: 15), (id: 3, w: 43, p: 6), (id: 5, w: 67, p: 11), (id: 6, w: 76, p: 20), (id: 7, w: 86, p: 17), (id: 8, w: 20, p: 11), (id: 11, w: 37, p: 18), (id: 12, w: 69, p: 14), (id: 13, w: 89, p: 17), (id: 14, w: 19, p: 19), (id: 16, w: 13, p: 14), (id: 18, w: 35, p: 6), (id: 20, w: 31, p: 8), (id: 21, w: 90, p: 13), (id: 22, w: 30, p: 4), (id: 24, w: 15, p: 6), (id: 25, w: 38, p: 5), (id: 27, w: 55, p: 16), (id: 28, w: 68, p: 7), (id: 29, w: 62, p: 8), (id: 30, w: 80, p: 12), (id: 31, w: 6, p: 17), (id: 32, w: 50, p: 18), (id: 34, w: 10, p: 1), (id: 35, w: 39, p: 9), (id: 36, w: 24, p: 7), (id: 37, w: 62, p: 18), (id: 39, w: 96, p: 18), (id: 40, w: 73, p: 18), (id: 41, w: 54, p: 18), (id: 43, w: 44, p: 15), (id: 44, w: 82, p: 12), (id: 45, w: 27, p: 9), (id: 46, w: 36, p: 6), (id: 47, w: 76, p: 13), (id: 48, w: 9, p: 14), (id: 49, w: 50, p: 12), (id: 50, w: 90, p: 17), (id: 52, w: 70, p: 10), (id: 53, w: 33, p: 13), (id: 54, w: 89, p: 20), (id: 55, w: 73, p: 8), (id: 56, w: 75, p: 10), (id: 57, w: 42, p: 6), (id: 61, w: 45, p: 16), (id: 62, w: 27, p: 12), (id: 63, w: 58, p: 9), (id: 64, w: 7, p: 19), (id: 65, w: 4, p: 8), (id: 66, w: 16, p: 20), (id: 67, w: 53, p: 19), (id: 71, w: 41, p: 5), (id: 73, w: 79, p: 17), (id: 74, w: 25, p: 20), (id: 75, w: 5, p: 6), (id: 77, w: 22, p: 18), (id: 80, w: 15, p: 16), (id: 83, w: 4, p: 18), (id: 84, w: 77, p: 9), (id: 85, w: 44, p: 17), (id: 86, w: 92, p: 19), (id: 87, w: 88, p: 19), (id: 88, w: 1, p: 1), (id: 89, w: 8, p: 12), (id: 90, w: 9, p: 9), (id: 91, w: 19, p: 19), (id: 92, w: 1, p: 13), (id: 93, w: 1, p: 13), (id: 94, w: 53, p: 8), (id: 95, w: 88, p: 20), (id: 96, w: 13, p: 17), (id: 98, w: 51, p: 8), (id: 99, w: 76, p: 9)]
//1000: Heap space error at item 200

//With storing the past Solution with items
// 100: DynamicProgrammingApproach: ov: "965", time: "0.396", solution: [(id: 0, w: 99, p: 12), (id: 1, w: 20, p: 19), (id: 2, w: 48, p: 15), (id: 3, w: 43, p: 6), (id: 5, w: 67, p: 11), (id: 6, w: 76, p: 20), (id: 7, w: 86, p: 17), (id: 8, w: 20, p: 11), (id: 11, w: 37, p: 18), (id: 12, w: 69, p: 14), (id: 13, w: 89, p: 17), (id: 14, w: 19, p: 19), (id: 16, w: 13, p: 14), (id: 18, w: 35, p: 6), (id: 20, w: 31, p: 8), (id: 21, w: 90, p: 13), (id: 22, w: 30, p: 4), (id: 24, w: 15, p: 6), (id: 25, w: 38, p: 5), (id: 27, w: 55, p: 16), (id: 28, w: 68, p: 7), (id: 29, w: 62, p: 8), (id: 30, w: 80, p: 12), (id: 31, w: 6, p: 17), (id: 32, w: 50, p: 18), (id: 35, w: 39, p: 9), (id: 36, w: 24, p: 7), (id: 37, w: 62, p: 18), (id: 39, w: 96, p: 18), (id: 40, w: 73, p: 18), (id: 41, w: 54, p: 18), (id: 43, w: 44, p: 15), (id: 44, w: 82, p: 12), (id: 45, w: 27, p: 9), (id: 46, w: 36, p: 6), (id: 47, w: 76, p: 13), (id: 48, w: 9, p: 14), (id: 49, w: 50, p: 12), (id: 50, w: 90, p: 17), (id: 52, w: 70, p: 10), (id: 53, w: 33, p: 13), (id: 54, w: 89, p: 20), (id: 55, w: 73, p: 8), (id: 56, w: 75, p: 10), (id: 57, w: 42, p: 6), (id: 61, w: 45, p: 16), (id: 62, w: 27, p: 12), (id: 63, w: 58, p: 9), (id: 64, w: 7, p: 19), (id: 65, w: 4, p: 8), (id: 66, w: 16, p: 20), (id: 67, w: 53, p: 19), (id: 73, w: 79, p: 17), (id: 74, w: 25, p: 20), (id: 75, w: 5, p: 6), (id: 77, w: 22, p: 18), (id: 80, w: 15, p: 16), (id: 82, w: 76, p: 8), (id: 83, w: 4, p: 18), (id: 84, w: 77, p: 9), (id: 85, w: 44, p: 17), (id: 86, w: 92, p: 19), (id: 87, w: 88, p: 19), (id: 88, w: 1, p: 1), (id: 89, w: 8, p: 12), (id: 90, w: 9, p: 9), (id: 91, w: 19, p: 19), (id: 92, w: 1, p: 13), (id: 93, w: 1, p: 13), (id: 94, w: 53, p: 8), (id: 95, w: 88, p: 20), (id: 96, w: 13, p: 17), (id: 98, w: 51, p: 8), (id: 99, w: 76, p: 9)]
//1000: DynamicProgrammingApproach: ov: "9708", time: "368.178", solution: [(id: 0, w: 14, p: 19), (id: 1, w: 65, p: 14), (id: 3, w: 63, p: 9), (id: 4, w: 56, p: 11), (id: 5, w: 12, p: 4), (id: 6, w: 83, p: 19), (id: 7, w: 55, p: 12), (id: 8, w: 71, p: 18), (id: 9, w: 100, p: 15), (id: 10, w: 80, p: 15), (id: 11, w: 20, p: 13), (id: 12, w: 22, p: 17), (id: 13, w: 56, p: 16), (id: 14, w: 12, p: 19), (id: 15, w: 78, p: 18), (id: 16, w: 54, p: 7), (id: 17, w: 99, p: 11), (id: 19, w: 39, p: 14), (id: 20, w: 20, p: 5), (id: 21, w: 3, p: 16), (id: 22, w: 27, p: 14), (id: 24, w: 96, p: 15), (id: 25, w: 100, p: 15), (id: 26, w: 52, p: 17), (id: 28, w: 54, p: 6), (id: 29, w: 82, p: 14), (id: 30, w: 46, p: 15), (id: 32, w: 99, p: 14), (id: 33, w: 28, p: 6), (id: 34, w: 32, p: 11), (id: 35, w: 98, p: 12), (id: 37, w: 18, p: 2), (id: 38, w: 41, p: 7), (id: 39, w: 69, p: 8), (id: 40, w: 42, p: 18), (id: 41, w: 37, p: 17), (id: 43, w: 39, p: 16), (id: 44, w: 40, p: 7), (id: 45, w: 8, p: 12), (id: 46, w: 6, p: 2), (id: 47, w: 39, p: 5), (id: 48, w: 83, p: 13), (id: 49, w: 15, p: 20), (id: 50, w: 79, p: 15), (id: 51, w: 73, p: 14), (id: 54, w: 73, p: 19), (id: 55, w: 65, p: 15), (id: 56, w: 24, p: 11), (id: 57, w: 46, p: 8), (id: 59, w: 8, p: 8), (id: 60, w: 54, p: 20), (id: 61, w: 46, p: 12), (id: 62, w: 53, p: 18), (id: 63, w: 6, p: 19), (id: 64, w: 98, p: 17), (id: 65, w: 22, p: 14), (id: 66, w: 62, p: 18), (id: 68, w: 22, p: 14), (id: 69, w: 13, p: 8), (id: 70, w: 3, p: 4), (id: 71, w: 69, p: 17), (id: 72, w: 49, p: 19), (id: 73, w: 35, p: 6), (id: 77, w: 20, p: 3), (id: 78, w: 34, p: 13), (id: 80, w: 66, p: 8), (id: 81, w: 46, p: 14), (id: 82, w: 10, p: 4), (id: 83, w: 60, p: 9), (id: 84, w: 26, p: 5), (id: 85, w: 66, p: 19), (id: 86, w: 13, p: 18), (id: 87, w: 22, p: 6), (id: 88, w: 25, p: 20), (id: 89, w: 24, p: 16), (id: 90, w: 55, p: 13), (id: 91, w: 39, p: 15), (id: 92, w: 58, p: 12), (id: 93, w: 8, p: 14), (id: 95, w: 65, p: 18), (id: 96, w: 31, p: 20), (id: 100, w: 82, p: 14), (id: 101, w: 48, p: 20), (id: 102, w: 61, p: 9), (id: 103, w: 65, p: 14), (id: 104, w: 44, p: 11), (id: 105, w: 58, p: 8), (id: 107, w: 11, p: 8), (id: 108, w: 38, p: 19), (id: 109, w: 5, p: 15), (id: 110, w: 90, p: 14), (id: 111, w: 86, p: 15), (id: 112, w: 62, p: 7), (id: 113, w: 97, p: 19), (id: 114, w: 44, p: 9), (id: 115, w: 63, p: 20), (id: 116, w: 15, p: 5), (id: 120, w: 21, p: 17), (id: 121, w: 9, p: 11), (id: 122, w: 37, p: 12), (id: 123, w: 54, p: 18), (id: 124, w: 80, p: 11), (id: 125, w: 28, p: 7), (id: 127, w: 35, p: 17), (id: 128, w: 32, p: 9), (id: 129, w: 95, p: 15), (id: 130, w: 15, p: 19), (id: 131, w: 8, p: 15), (id: 132, w: 12, p: 11), (id: 133, w: 25, p: 15), (id: 134, w: 22, p: 20), (id: 135, w: 17, p: 19), (id: 138, w: 40, p: 10), (id: 139, w: 29, p: 12), (id: 140, w: 1, p: 13), (id: 141, w: 7, p: 20), (id: 142, w: 33, p: 12), (id: 143, w: 43, p: 18), (id: 144, w: 50, p: 11), (id: 148, w: 83, p: 18), (id: 149, w: 8, p: 12), (id: 150, w: 31, p: 15), (id: 152, w: 31, p: 10), (id: 153, w: 75, p: 20), (id: 154, w: 35, p: 16), (id: 155, w: 55, p: 15), (id: 156, w: 48, p: 15), (id: 157, w: 26, p: 10), (id: 158, w: 63, p: 17), (id: 159, w: 73, p: 14), (id: 160, w: 20, p: 6), (id: 161, w: 9, p: 6), (id: 162, w: 4, p: 10), (id: 164, w: 51, p: 13), (id: 165, w: 27, p: 16), (id: 167, w: 53, p: 14), (id: 168, w: 43, p: 10), (id: 169, w: 96, p: 19), (id: 170, w: 71, p: 13), (id: 171, w: 6, p: 19), (id: 173, w: 64, p: 18), (id: 174, w: 61, p: 15), (id: 176, w: 1, p: 15), (id: 177, w: 8, p: 13), (id: 179, w: 29, p: 4), (id: 180, w: 54, p: 13), (id: 181, w: 17, p: 10), (id: 182, w: 53, p: 9), (id: 185, w: 23, p: 14), (id: 186, w: 4, p: 11), (id: 187, w: 71, p: 11), (id: 189, w: 94, p: 18), (id: 190, w: 2, p: 9), (id: 191, w: 3, p: 18), (id: 192, w: 46, p: 7), (id: 193, w: 73, p: 16), (id: 195, w: 10, p: 2), (id: 196, w: 4, p: 4), (id: 197, w: 16, p: 2), (id: 198, w: 28, p: 17), (id: 199, w: 35, p: 6), (id: 201, w: 92, p: 11), (id: 203, w: 23, p: 7), (id: 205, w: 66, p: 11), (id: 206, w: 29, p: 4), (id: 207, w: 14, p: 7), (id: 208, w: 40, p: 18), (id: 210, w: 3, p: 13), (id: 211, w: 85, p: 13), (id: 213, w: 42, p: 11), (id: 215, w: 12, p: 19), (id: 216, w: 78, p: 14), (id: 217, w: 58, p: 11), (id: 218, w: 15, p: 15), (id: 219, w: 45, p: 16), (id: 220, w: 96, p: 14), (id: 221, w: 46, p: 6), (id: 222, w: 5, p: 12), (id: 224, w: 62, p: 20), (id: 225, w: 30, p: 11), (id: 227, w: 6, p: 17), (id: 228, w: 33, p: 13), (id: 229, w: 34, p: 15), (id: 231, w: 60, p: 19), (id: 232, w: 39, p: 7), (id: 233, w: 41, p: 13), (id: 235, w: 3, p: 11), (id: 236, w: 4, p: 4), (id: 237, w: 67, p: 13), (id: 238, w: 10, p: 15), (id: 239, w: 74, p: 19), (id: 240, w: 86, p: 10), (id: 241, w: 100, p: 12), (id: 242, w: 14, p: 5), (id: 243, w: 43, p: 12), (id: 244, w: 16, p: 7), (id: 245, w: 3, p: 1), (id: 246, w: 54, p: 7), (id: 248, w: 54, p: 16), (id: 249, w: 69, p: 19), (id: 250, w: 75, p: 20), (id: 251, w: 79, p: 18), (id: 252, w: 9, p: 18), (id: 253, w: 76, p: 19), (id: 254, w: 8, p: 2), (id: 256, w: 88, p: 10), (id: 257, w: 75, p: 20), (id: 258, w: 7, p: 3), (id: 260, w: 11, p: 8), (id: 261, w: 45, p: 18), (id: 262, w: 31, p: 7), (id: 263, w: 64, p: 17), (id: 264, w: 57, p: 19), (id: 265, w: 21, p: 7), (id: 266, w: 43, p: 17), (id: 267, w: 75, p: 11), (id: 268, w: 86, p: 15), (id: 269, w: 22, p: 9), (id: 271, w: 5, p: 1), (id: 273, w: 87, p: 15), (id: 274, w: 20, p: 8), (id: 275, w: 59, p: 16), (id: 277, w: 5, p: 10), (id: 278, w: 99, p: 18), (id: 280, w: 52, p: 18), (id: 282, w: 20, p: 8), (id: 283, w: 41, p: 8), (id: 284, w: 39, p: 11), (id: 285, w: 32, p: 6), (id: 286, w: 21, p: 20), (id: 287, w: 50, p: 20), (id: 288, w: 16, p: 5), (id: 289, w: 87, p: 20), (id: 291, w: 74, p: 12), (id: 292, w: 91, p: 16), (id: 293, w: 77, p: 9), (id: 294, w: 67, p: 18), (id: 295, w: 70, p: 15), (id: 296, w: 67, p: 19), (id: 297, w: 35, p: 20), (id: 298, w: 50, p: 19), (id: 300, w: 35, p: 17), (id: 302, w: 62, p: 15), (id: 305, w: 83, p: 13), (id: 307, w: 36, p: 14), (id: 309, w: 57, p: 16), (id: 311, w: 71, p: 17), (id: 312, w: 56, p: 8), (id: 313, w: 19, p: 20), (id: 315, w: 23, p: 8), (id: 316, w: 32, p: 4), (id: 317, w: 96, p: 12), (id: 318, w: 2, p: 17), (id: 319, w: 23, p: 14), (id: 320, w: 10, p: 20), (id: 321, w: 49, p: 13), (id: 325, w: 1, p: 17), (id: 326, w: 37, p: 5), (id: 327, w: 92, p: 20), (id: 328, w: 54, p: 19), (id: 329, w: 10, p: 13), (id: 330, w: 5, p: 3), (id: 331, w: 39, p: 20), (id: 332, w: 34, p: 9), (id: 333, w: 89, p: 13), (id: 334, w: 65, p: 18), (id: 335, w: 23, p: 17), (id: 336, w: 75, p: 18), (id: 337, w: 92, p: 16), (id: 338, w: 99, p: 19), (id: 339, w: 39, p: 5), (id: 340, w: 13, p: 15), (id: 341, w: 11, p: 16), (id: 342, w: 35, p: 8), (id: 343, w: 94, p: 12), (id: 345, w: 15, p: 7), (id: 347, w: 13, p: 4), (id: 348, w: 37, p: 11), (id: 349, w: 38, p: 18), (id: 350, w: 41, p: 17), (id: 352, w: 88, p: 20), (id: 353, w: 75, p: 13), (id: 354, w: 13, p: 8), (id: 356, w: 72, p: 8), (id: 357, w: 75, p: 19), (id: 358, w: 25, p: 15), (id: 359, w: 55, p: 7), (id: 360, w: 9, p: 16), (id: 361, w: 97, p: 16), (id: 362, w: 4, p: 12), (id: 363, w: 44, p: 5), (id: 365, w: 50, p: 10), (id: 366, w: 88, p: 11), (id: 368, w: 58, p: 7), (id: 369, w: 80, p: 12), (id: 372, w: 59, p: 17), (id: 373, w: 45, p: 19), (id: 374, w: 15, p: 8), (id: 375, w: 65, p: 18), (id: 376, w: 44, p: 16), (id: 377, w: 34, p: 9), (id: 378, w: 18, p: 13), (id: 379, w: 97, p: 13), (id: 380, w: 6, p: 10), (id: 381, w: 2, p: 1), (id: 382, w: 80, p: 20), (id: 383, w: 72, p: 10), (id: 384, w: 6, p: 12), (id: 385, w: 16, p: 19), (id: 386, w: 2, p: 14), (id: 387, w: 1, p: 17), (id: 388, w: 21, p: 15), (id: 389, w: 80, p: 18), (id: 391, w: 68, p: 8), (id: 393, w: 3, p: 16), (id: 395, w: 72, p: 13), (id: 396, w: 70, p: 20), (id: 397, w: 47, p: 13), (id: 399, w: 91, p: 18), (id: 400, w: 37, p: 15), (id: 401, w: 5, p: 10), (id: 403, w: 27, p: 4), (id: 404, w: 74, p: 9), (id: 405, w: 38, p: 10), (id: 406, w: 32, p: 6), (id: 407, w: 81, p: 9), (id: 408, w: 100, p: 13), (id: 410, w: 18, p: 7), (id: 411, w: 41, p: 17), (id: 412, w: 3, p: 14), (id: 413, w: 3, p: 16), (id: 417, w: 3, p: 9), (id: 418, w: 8, p: 16), (id: 420, w: 73, p: 20), (id: 422, w: 8, p: 7), (id: 423, w: 6, p: 9), (id: 424, w: 46, p: 9), (id: 426, w: 60, p: 9), (id: 428, w: 73, p: 8), (id: 429, w: 51, p: 10), (id: 430, w: 16, p: 9), (id: 431, w: 5, p: 5), (id: 434, w: 100, p: 14), (id: 436, w: 33, p: 20), (id: 437, w: 32, p: 16), (id: 438, w: 73, p: 20), (id: 439, w: 99, p: 20), (id: 440, w: 82, p: 14), (id: 441, w: 75, p: 20), (id: 442, w: 10, p: 12), (id: 443, w: 51, p: 12), (id: 444, w: 1, p: 13), (id: 445, w: 19, p: 10), (id: 446, w: 93, p: 10), (id: 447, w: 8, p: 4), (id: 448, w: 52, p: 15), (id: 449, w: 23, p: 17), (id: 451, w: 2, p: 7), (id: 452, w: 26, p: 11), (id: 453, w: 75, p: 15), (id: 454, w: 24, p: 3), (id: 455, w: 21, p: 4), (id: 458, w: 29, p: 20), (id: 462, w: 38, p: 19), (id: 463, w: 86, p: 15), (id: 464, w: 56, p: 12), (id: 466, w: 70, p: 18), (id: 467, w: 57, p: 18), (id: 469, w: 82, p: 13), (id: 470, w: 93, p: 15), (id: 471, w: 93, p: 20), (id: 472, w: 79, p: 12), (id: 473, w: 65, p: 14), (id: 475, w: 94, p: 14), (id: 476, w: 89, p: 10), (id: 479, w: 66, p: 18), (id: 480, w: 19, p: 8), (id: 481, w: 48, p: 15), (id: 482, w: 28, p: 14), (id: 483, w: 34, p: 17), (id: 484, w: 84, p: 12), (id: 487, w: 34, p: 10), (id: 489, w: 54, p: 17), (id: 490, w: 1, p: 4), (id: 492, w: 34, p: 19), (id: 497, w: 43, p: 7), (id: 498, w: 6, p: 20), (id: 499, w: 39, p: 12), (id: 500, w: 51, p: 12), (id: 501, w: 2, p: 19), (id: 502, w: 2, p: 13), (id: 504, w: 79, p: 12), (id: 505, w: 72, p: 10), (id: 506, w: 60, p: 14), (id: 507, w: 71, p: 17), (id: 508, w: 73, p: 14), (id: 509, w: 1, p: 3), (id: 510, w: 37, p: 16), (id: 512, w: 30, p: 8), (id: 513, w: 98, p: 16), (id: 515, w: 68, p: 19), (id: 516, w: 45, p: 5), (id: 518, w: 87, p: 16), (id: 520, w: 42, p: 14), (id: 521, w: 32, p: 18), (id: 522, w: 55, p: 19), (id: 523, w: 19, p: 7), (id: 524, w: 98, p: 16), (id: 525, w: 32, p: 10), (id: 527, w: 25, p: 11), (id: 528, w: 48, p: 16), (id: 529, w: 69, p: 20), (id: 531, w: 91, p: 11), (id: 532, w: 72, p: 15), (id: 533, w: 83, p: 20), (id: 534, w: 53, p: 9), (id: 535, w: 30, p: 13), (id: 536, w: 3, p: 16), (id: 537, w: 54, p: 15), (id: 540, w: 67, p: 12), (id: 541, w: 83, p: 13), (id: 545, w: 61, p: 20), (id: 546, w: 84, p: 16), (id: 547, w: 3, p: 14), (id: 549, w: 9, p: 19), (id: 551, w: 94, p: 20), (id: 552, w: 49, p: 19), (id: 554, w: 63, p: 17), (id: 556, w: 7, p: 15), (id: 557, w: 15, p: 12), (id: 558, w: 38, p: 13), (id: 560, w: 41, p: 19), (id: 563, w: 62, p: 19), (id: 565, w: 20, p: 9), (id: 566, w: 32, p: 6), (id: 568, w: 21, p: 11), (id: 569, w: 36, p: 5), (id: 570, w: 47, p: 12), (id: 571, w: 6, p: 20), (id: 572, w: 31, p: 16), (id: 574, w: 6, p: 11), (id: 575, w: 57, p: 10), (id: 576, w: 72, p: 19), (id: 577, w: 72, p: 9), (id: 578, w: 28, p: 6), (id: 579, w: 20, p: 20), (id: 580, w: 93, p: 15), (id: 582, w: 67, p: 10), (id: 583, w: 61, p: 16), (id: 584, w: 92, p: 15), (id: 585, w: 34, p: 10), (id: 589, w: 87, p: 11), (id: 590, w: 37, p: 14), (id: 591, w: 42, p: 19), (id: 592, w: 74, p: 15), (id: 593, w: 64, p: 18), (id: 595, w: 36, p: 10), (id: 596, w: 22, p: 19), (id: 597, w: 30, p: 19), (id: 598, w: 72, p: 11), (id: 599, w: 23, p: 3), (id: 600, w: 10, p: 14), (id: 601, w: 62, p: 14), (id: 602, w: 64, p: 8), (id: 607, w: 21, p: 3), (id: 608, w: 7, p: 20), (id: 609, w: 91, p: 11), (id: 610, w: 92, p: 16), (id: 611, w: 81, p: 10), (id: 613, w: 96, p: 17), (id: 614, w: 86, p: 19), (id: 615, w: 51, p: 11), (id: 616, w: 4, p: 3), (id: 617, w: 17, p: 19), (id: 618, w: 38, p: 10), (id: 619, w: 88, p: 19), (id: 623, w: 52, p: 17), (id: 626, w: 54, p: 7), (id: 628, w: 28, p: 20), (id: 629, w: 52, p: 12), (id: 631, w: 18, p: 8), (id: 634, w: 30, p: 13), (id: 635, w: 73, p: 18), (id: 636, w: 72, p: 10), (id: 637, w: 89, p: 19), (id: 640, w: 62, p: 14), (id: 641, w: 1, p: 1), (id: 642, w: 4, p: 10), (id: 643, w: 37, p: 18), (id: 644, w: 95, p: 11), (id: 645, w: 45, p: 9), (id: 646, w: 4, p: 6), (id: 647, w: 33, p: 13), (id: 648, w: 29, p: 4), (id: 649, w: 31, p: 16), (id: 650, w: 60, p: 12), (id: 652, w: 53, p: 11), (id: 653, w: 77, p: 20), (id: 654, w: 44, p: 11), (id: 655, w: 34, p: 17), (id: 656, w: 80, p: 13), (id: 660, w: 50, p: 12), (id: 662, w: 8, p: 8), (id: 663, w: 79, p: 15), (id: 668, w: 17, p: 13), (id: 669, w: 5, p: 3), (id: 672, w: 86, p: 10), (id: 673, w: 67, p: 17), (id: 674, w: 92, p: 14), (id: 675, w: 23, p: 5), (id: 676, w: 51, p: 11), (id: 678, w: 92, p: 16), (id: 681, w: 50, p: 12), (id: 682, w: 12, p: 15), (id: 683, w: 16, p: 13), (id: 685, w: 51, p: 14), (id: 687, w: 30, p: 14), (id: 688, w: 4, p: 19), (id: 689, w: 95, p: 18), (id: 691, w: 36, p: 17), (id: 692, w: 40, p: 6), (id: 693, w: 13, p: 10), (id: 694, w: 57, p: 10), (id: 695, w: 96, p: 14), (id: 696, w: 54, p: 6), (id: 697, w: 46, p: 10), (id: 698, w: 32, p: 15), (id: 699, w: 67, p: 18), (id: 700, w: 21, p: 17), (id: 701, w: 80, p: 9), (id: 702, w: 23, p: 19), (id: 703, w: 56, p: 11), (id: 704, w: 80, p: 14), (id: 705, w: 42, p: 12), (id: 706, w: 4, p: 12), (id: 707, w: 12, p: 18), (id: 709, w: 28, p: 11), (id: 710, w: 3, p: 6), (id: 711, w: 15, p: 9), (id: 713, w: 87, p: 10), (id: 714, w: 79, p: 19), (id: 715, w: 64, p: 7), (id: 717, w: 59, p: 9), (id: 718, w: 13, p: 6), (id: 719, w: 95, p: 17), (id: 720, w: 92, p: 13), (id: 721, w: 34, p: 20), (id: 722, w: 98, p: 14), (id: 726, w: 8, p: 14), (id: 728, w: 94, p: 13), (id: 729, w: 55, p: 7), (id: 730, w: 45, p: 19), (id: 733, w: 44, p: 13), (id: 734, w: 9, p: 20), (id: 735, w: 84, p: 12), (id: 738, w: 17, p: 2), (id: 740, w: 45, p: 16), (id: 742, w: 64, p: 17), (id: 743, w: 16, p: 11), (id: 745, w: 98, p: 15), (id: 746, w: 19, p: 10), (id: 747, w: 34, p: 9), (id: 748, w: 71, p: 11), (id: 750, w: 18, p: 5), (id: 753, w: 28, p: 10), (id: 754, w: 41, p: 6), (id: 756, w: 15, p: 16), (id: 757, w: 15, p: 20), (id: 758, w: 97, p: 14), (id: 761, w: 82, p: 16), (id: 762, w: 48, p: 19), (id: 763, w: 75, p: 11), (id: 765, w: 1, p: 4), (id: 766, w: 37, p: 8), (id: 767, w: 79, p: 17), (id: 769, w: 28, p: 11), (id: 770, w: 38, p: 20), (id: 771, w: 89, p: 11), (id: 772, w: 27, p: 11), (id: 773, w: 83, p: 15), (id: 774, w: 82, p: 17), (id: 775, w: 2, p: 18), (id: 776, w: 1, p: 8), (id: 777, w: 69, p: 11), (id: 778, w: 55, p: 12), (id: 779, w: 59, p: 19), (id: 781, w: 64, p: 18), (id: 782, w: 13, p: 2), (id: 783, w: 55, p: 19), (id: 784, w: 69, p: 20), (id: 785, w: 48, p: 7), (id: 786, w: 27, p: 11), (id: 787, w: 89, p: 12), (id: 788, w: 56, p: 17), (id: 789, w: 4, p: 19), (id: 790, w: 80, p: 16), (id: 791, w: 72, p: 17), (id: 793, w: 48, p: 12), (id: 795, w: 75, p: 13), (id: 796, w: 84, p: 11), (id: 798, w: 51, p: 17), (id: 799, w: 1, p: 5), (id: 801, w: 61, p: 7), (id: 802, w: 1, p: 6), (id: 803, w: 11, p: 12), (id: 805, w: 16, p: 9), (id: 806, w: 6, p: 20), (id: 808, w: 58, p: 19), (id: 809, w: 64, p: 9), (id: 811, w: 39, p: 12), (id: 812, w: 5, p: 3), (id: 813, w: 76, p: 14), (id: 814, w: 46, p: 17), (id: 815, w: 98, p: 19), (id: 816, w: 9, p: 15), (id: 817, w: 91, p: 13), (id: 818, w: 39, p: 8), (id: 819, w: 36, p: 10), (id: 820, w: 34, p: 6), (id: 821, w: 21, p: 3), (id: 822, w: 92, p: 11), (id: 823, w: 42, p: 13), (id: 824, w: 88, p: 17), (id: 825, w: 66, p: 18), (id: 826, w: 87, p: 13), (id: 827, w: 23, p: 3), (id: 828, w: 89, p: 17), (id: 829, w: 24, p: 18), (id: 830, w: 85, p: 13), (id: 832, w: 19, p: 18), (id: 834, w: 88, p: 20), (id: 835, w: 70, p: 9), (id: 836, w: 37, p: 6), (id: 838, w: 27, p: 4), (id: 839, w: 4, p: 11), (id: 840, w: 35, p: 9), (id: 842, w: 46, p: 12), (id: 843, w: 44, p: 15), (id: 844, w: 13, p: 11), (id: 845, w: 54, p: 15), (id: 847, w: 61, p: 20), (id: 848, w: 94, p: 11), (id: 849, w: 30, p: 6), (id: 850, w: 9, p: 3), (id: 851, w: 82, p: 20), (id: 852, w: 98, p: 19), (id: 853, w: 49, p: 6), (id: 854, w: 3, p: 12), (id: 856, w: 22, p: 16), (id: 857, w: 77, p: 16), (id: 858, w: 44, p: 9), (id: 860, w: 9, p: 8), (id: 861, w: 35, p: 12), (id: 862, w: 67, p: 18), (id: 863, w: 30, p: 8), (id: 864, w: 42, p: 16), (id: 865, w: 4, p: 10), (id: 867, w: 25, p: 14), (id: 868, w: 91, p: 13), (id: 870, w: 66, p: 16), (id: 871, w: 30, p: 13), (id: 872, w: 66, p: 19), (id: 873, w: 54, p: 8), (id: 875, w: 33, p: 19), (id: 877, w: 66, p: 16), (id: 878, w: 52, p: 20), (id: 879, w: 20, p: 16), (id: 880, w: 56, p: 15), (id: 881, w: 28, p: 5), (id: 882, w: 9, p: 12), (id: 883, w: 71, p: 8), (id: 885, w: 63, p: 12), (id: 886, w: 47, p: 10), (id: 889, w: 72, p: 16), (id: 890, w: 43, p: 11), (id: 891, w: 99, p: 18), (id: 892, w: 44, p: 13), (id: 893, w: 21, p: 14), (id: 895, w: 52, p: 12), (id: 896, w: 2, p: 10), (id: 898, w: 8, p: 18), (id: 900, w: 66, p: 17), (id: 902, w: 54, p: 18), (id: 903, w: 37, p: 6), (id: 904, w: 71, p: 18), (id: 905, w: 12, p: 16), (id: 906, w: 10, p: 13), (id: 907, w: 53, p: 15), (id: 910, w: 25, p: 15), (id: 911, w: 61, p: 20), (id: 912, w: 54, p: 6), (id: 914, w: 4, p: 20), (id: 916, w: 10, p: 13), (id: 917, w: 92, p: 12), (id: 919, w: 37, p: 19), (id: 921, w: 26, p: 5), (id: 922, w: 51, p: 20), (id: 923, w: 2, p: 3), (id: 924, w: 81, p: 16), (id: 925, w: 64, p: 15), (id: 926, w: 24, p: 13), (id: 927, w: 27, p: 8), (id: 928, w: 27, p: 4), (id: 929, w: 9, p: 2), (id: 930, w: 73, p: 10), (id: 931, w: 55, p: 8), (id: 933, w: 2, p: 16), (id: 934, w: 6, p: 2), (id: 935, w: 100, p: 20), (id: 936, w: 25, p: 11), (id: 938, w: 44, p: 14), (id: 940, w: 36, p: 7), (id: 941, w: 89, p: 12), (id: 946, w: 46, p: 7), (id: 947, w: 99, p: 11), (id: 948, w: 8, p: 14), (id: 949, w: 9, p: 19), (id: 950, w: 25, p: 13), (id: 951, w: 44, p: 9), (id: 952, w: 42, p: 9), (id: 954, w: 2, p: 6), (id: 956, w: 29, p: 17), (id: 957, w: 85, p: 12), (id: 958, w: 97, p: 14), (id: 961, w: 56, p: 18), (id: 962, w: 69, p: 11), (id: 963, w: 39, p: 15), (id: 964, w: 33, p: 17), (id: 966, w: 68, p: 16), (id: 968, w: 60, p: 14), (id: 969, w: 51, p: 14), (id: 970, w: 43, p: 20), (id: 971, w: 21, p: 6), (id: 973, w: 17, p: 2), (id: 975, w: 38, p: 18), (id: 977, w: 52, p: 12), (id: 978, w: 48, p: 9), (id: 979, w: 81, p: 20), (id: 980, w: 15, p: 11), (id: 982, w: 67, p: 8), (id: 984, w: 25, p: 18), (id: 986, w: 45, p: 19), (id: 987, w: 13, p: 10), (id: 988, w: 63, p: 13), (id: 990, w: 20, p: 9), (id: 991, w: 65, p: 19), (id: 992, w: 31, p: 18), (id: 993, w: 16, p: 14), (id: 995, w: 50, p: 15), (id: 997, w: 51, p: 13), (id: 999, w: 11, p: 11)]
