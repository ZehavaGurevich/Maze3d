package algorithms.search;

import java.io.Serializable;
import java.util.ArrayList;

// TODO: 
/**
 * The Class SearchSolution.
 *
 * @param <T> the generic type
 */
@SuppressWarnings("serial")
public class SearchSolution<T> extends Solution implements Serializable 
{

	/** The solution. */
	private ArrayList<T> solution;

	/**
	 * Instantiates a new search solution.
	 *
	 * @param solution the solution
	 */
	public SearchSolution(ArrayList<T> solution)
	{
		super();
		this.solution = solution;
	}

	/**
	 * Gets the solution.
	 *
	 * @return the solution
	 */
	public ArrayList<T> getSolution()
	{
		return solution;
	}

	/**
	 * Sets the solution.
	 *
	 * @param solution the new solution
	 */
	public void setSolution(ArrayList<T> solution)
	{
		this.solution = solution;
	}


}