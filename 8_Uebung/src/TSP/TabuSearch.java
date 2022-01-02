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
			System.out.println(i);

			refreshTabuList();

			var solutionsForThisIteration = new Solution[maxReversals];

			for(int j = 0; j < maxReversals; j++)
			{
				var reverseSteps = (j / 2) + 1;

				var reversedSolution = getReversedSolution(bestSolutions[i-1].getRoute(), reverseSteps, instance2D);
				solutionsForThisIteration[j] = reversedSolution;
			}

			var bestSolutionForThisIteration = findBestSolutionForIteration(solutionsForThisIteration);
			bestSolutions[i] = bestSolutionForThisIteration;

			this.tabuList.add(new TabuSolution(bestSolutionForThisIteration.getStartNeighbour(), this.tau));
			this.tabuList.add(new TabuSolution(bestSolutionForThisIteration.getEndNeighbour(), this.tau));
		}

		var bestSolution = getBestSolution(bestSolutions);
		solution = bestSolution.getRoute();
	}
	
	/*
	 * Constructor and the method toString (like in the class NearestNeighbourHeuristic).
	 */
	public TabuSearch(int tau, int maxIteration, int maxReversals)
	{
		this.tau = tau;
		this.maxIteration = maxIteration;
		this.maxReversals = maxReversals;
		this.random = new Random();
	}

	private Solution getReversedSolution(int[] bestTour, int reversedSteps, Instance2D instance2D)
	{
		var newTour = bestTour.clone();

		var startRandoIndex = reversedSteps;
		var endRandoIndex = newTour.length - (reversedSteps + 2); // index 0
		var randIndex = random.nextInt((endRandoIndex - startRandoIndex) + 1) + startRandoIndex;

		var backUps = new int[reversedSteps];

		for(int i = 0; i < reversedSteps; i++)
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

		var newSolution = new Solution(newTour, length, randIndex, randIndex + reversedSteps);
		return newSolution;
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
		TabuSearch tabuSearch = new TabuSearch(3, 100, 6);
		tabuSearch.solve(instance2D);
		instance2D.draw(800, 800, tabuSearch.getSolution());
		System.out.println(tabuSearch);
	}
}
