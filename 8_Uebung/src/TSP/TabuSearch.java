package TSP;
import java.io.IOException;
import java.util.*;

/**
 * <b>Class implementing the tabu search.</b>
 * 
 * @author Your name.
 */
public class TabuSearch extends Algorithm
{
	/*
	 * Your parameters as private variables.
	 */
	private final int tau;
	private final int maxIteration;
	private final int maxReversals;
	private final Random random;
	private NewRouteAlgorithmType typeEnum;

	private List<TabuSolution> tabuList;


	/**
	 * <b>Method implementing the tabu search.</b>
	 *
	 * @param instance2D Test instance.
	 */
	protected void algorithm(final Instance2D instance2D)
	{
		Algorithm startHeuristic = new NearestNeighbourHeuristic(0, true);
		startHeuristic.solve(instance2D);
		var startSolution = startHeuristic.getSolution();

		this.tabuList = new ArrayList<>();


		var bestSolutions = new Solution[maxIteration + 1];
		bestSolutions[0] = new Solution(startSolution, instance2D.oV(startSolution), -1, -1);

		for(int i = 1; i < maxIteration + 1; i++)
		{
			var perc = ((double) i / maxIteration) * 100 ;
			if(perc % 1 == 0)
				System.out.println(perc + "%");

			refreshTabuList();

			var solutionsForThisIteration = new Solution[maxReversals];

			switch (this.typeEnum)
			{
				case Reversed -> bestSolutions[i] = getReversedSolutions(bestSolutions[i-1], instance2D);
				case NearestNeighbour -> bestSolutions[i] =  getRandomNearestNeighbour(bestSolutions[i-1].getRoute(), instance2D);
			}
		}

		//Arrays.stream(bestSolutions).toList().forEach(System.out::println);
		//tabuList.forEach(System.out::println);
		var bestSolution = getBestSolution(bestSolutions);
		solution = bestSolution.getRoute();
	}
	
	/*
	 * Constructor and the method toString (like in the class NearestNeighbourHeuristic).
	 */
	public TabuSearch(int tau, int maxIteration, int maxReversals, NewRouteAlgorithmType typeEnum)
	{
		this.tau = tau;
		this.maxIteration = maxIteration;
		this.maxReversals = maxReversals;
		this.random = new Random();
		this.typeEnum = typeEnum;
	}

	private Solution getReversedSolutions(Solution bestSolution, Instance2D instance2D)
	{
		var solutionsForThisIteration = new Solution[maxReversals];

		for(int j = 0; j < maxReversals; j++)
		{
			var reverseLength = (j / 2) + 1;
			//var reverseLength = maxReversals / 2;

			var reversedSolution = getReversedSolution(bestSolution.getRoute(), reverseLength, instance2D);
			solutionsForThisIteration[j] = reversedSolution;
		}

		var bestSolutionForThisIteration = findBestSolutionForIteration(solutionsForThisIteration);

		this.tabuList.add(new TabuSolution(bestSolutionForThisIteration.getStartNeighbour(), this.tau));
		this.tabuList.add(new TabuSolution(bestSolutionForThisIteration.getEndNeighbour(), this.tau));

		return bestSolutionForThisIteration;
	}

	private Solution getReversedSolution(int[] bestTour, int reversedLength, Instance2D instance2D)
	{
		var newTour = bestTour.clone();

		var startRandoIndex = reversedLength;
		var endRandoIndex = newTour.length - (reversedLength + 2); // index 0

		var randIndex = random.nextInt((endRandoIndex - startRandoIndex) + 1) + startRandoIndex;

		var backUps = new int[reversedLength];

		for(int i = 0; i < reversedLength; i++)
		{
			backUps[i] = newTour[randIndex + i];
		}

		var newTourIndex = 0;
		for(int i = backUps.length - 1; i >= 0; i--)
		{
			newTour[randIndex + newTourIndex] = backUps[i];
			newTourIndex++;
		}

		var length = instance2D.oV(newTour);

		var newSolution = new Solution(newTour, length, randIndex, randIndex + reversedLength);
		return newSolution;
	}

	private Solution getRandomNearestNeighbour(int[] bestTour, Instance2D instance2D)
	{
		var randIndex = random.nextInt(bestTour.length);

		Algorithm startHeuristic = new NearestNeighbourHeuristic(randIndex, true);
		startHeuristic.solve(instance2D);
		var newRoute = startHeuristic.getSolution();

		return new Solution(newRoute,2,3,4);
	}

	private Solution getBestSolution(Solution[] solutions)
	{
		var bestLength = Integer.MAX_VALUE;
		Solution bestSolution = null;

		for (var solution : solutions)
		{
			if(solution.getLength() < bestLength)
			{
				bestLength = solution.getLength();
				bestSolution = solution;
			}
		}

		return bestSolution;
	}

	private boolean isInTabuList(int[] route)
	{
		for (var sol : tabuList)
		{
			if(sol.getRemainingTabu() == 0)
				continue;

			if(sol.getRoute()[0] == route[0] &
				sol.getRoute()[1] == route[1])
				return true;

		}
		return false;
	}

	private void refreshTabuList()
	{
		for (var sol : tabuList)
		{
			sol.reduceRemainingTabu();
		}
	}

	private Solution findBestSolutionForIteration(Solution[] solutions)
	{
		var nonTabuSolutions = new ArrayList<Solution>();
		for (var sol : solutions)
		{
			if(!isInTabuList(sol.getStartNeighbour()) && !isInTabuList(sol.getEndNeighbour()))
				nonTabuSolutions.add(sol);
		}

		var array = new Solution[nonTabuSolutions.size()];
		var bestSolution = getBestSolution(nonTabuSolutions.toArray(array));

		return bestSolution;
	}

	/**
	 * <b>The main function of the class TabuSearch.</b>
	 * 
	 * @param args Filename of a file containing one instance.
	 * @throws IOException if the number of arguments is not 1.
	 */
	public static void main(String[] args) throws IOException
	{
		if (args.length != 1)
		{
			throw new IOException();
		}
		
		Instance2D instance2D = new Instance2DDistanceMatrix(args[0]);
		TabuSearch tabuSearch = new TabuSearch(3, 100, 5, NewRouteAlgorithmType.Reversed);
		tabuSearch.solve(instance2D);
		instance2D.draw(800, 800, tabuSearch.getSolution());
		System.out.println(tabuSearch);
	}

	/**
	 *
	 *Best Solution: 7544
	 *
	 *
	 * Good Results berlin52:
	 *
	 * Tau: 3, MaxIteration: 100, maxReversals: 5, type: Reversed
	 * TabuSearch: ov: "8630", time: "0.025", solution: [28, 1, 6, 41, 16, 20, 29, 22, 19, 49, 15, 43, 45, 24, 3, 5, 14, 4, 23, 47, 37, 36, 39, 38, 33, 34, 35, 48, 31, 0, 21, 30, 17, 2, 44, 18, 40, 7, 8, 9, 42, 32, 50, 11, 27, 26, 25, 46, 12, 13, 51, 10]
	 *
	 *
	 * Useless :D
	 * Tau: 3, MaxIteration: 100000, maxReversals: 30
	 * TabuSearch: ov: "8630", time: "3977.621", solution: [28, 1, 6, 41, 16, 20, 29, 22, 19, 49, 15, 43, 45, 24, 3, 5, 14, 4, 23, 47, 37, 36, 39, 38, 33, 34, 35, 48, 31, 0, 21, 30, 17, 2, 44, 18, 40, 7, 8, 9, 42, 32, 50, 11, 27, 26, 25, 46, 12, 13, 51, 10]
	 *
	 **/
}
