package algorithms.search;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Stack;



/**
 * The Class CommonSearcher.
 *
 * @param <T> the generic type
 */
public abstract class CommonSearcher<T> implements Searcher<T> 
{


	/** The Stack node. */
	protected Stack<State<T>> StackNode;


	/** The open. */
	protected PriorityQueue<State<T>> open;


	/** The close. */
	protected ArrayList<State<T>> close;


	/** The Number of nods evaluated. */
	protected int NumberOfNodsEvaluated;



	/**
	 * Instantiates a new common searcher.
	 */
	public CommonSearcher()
	{
		open=new PriorityQueue<State<T>>(new Comparator<State<T>>()
		{

			@Override
			public int compare(State<T> s1,State <T> s2){

				return (int) (s1.getCost() -s2.getCost());


			}
		});

		close=new ArrayList<State<T>>();
		NumberOfNodsEvaluated=0;



	}





	/**
	 * Pop from priority queue.
	 *
	 * @return the state
	 */
	public State<T>PopFromPriorityQueue()
	{
		NumberOfNodsEvaluated++;
		return open.poll();
	}



	/**
	 * Adds the to priority queue.
	 *
	 * @param s the s
	 */
	public void AddToPriorityQueue(State<T> s)
	{
		open.add(s);
	}


	/**
	 * Removes the from priority queue.
	 *
	 * @param s the s
	 */
	public void removeFromPriorityQueue(State<T> s)
	{
		open.remove(s);
	}




	/**
	 * Back trace.
	 *
	 * @param start the start
	 * @param goal the goal
	 * @return the search solution
	 */
	protected SearchSolution<T> backTrace(State<T> start,State<T> goal)
	{
		ArrayList<T> backtrace=new ArrayList<T>();

		while(!start.equals(goal))
		{
			backtrace.add(goal.getState());
			goal=goal.getCameFrom();
		}
		backtrace.add(start.getState());

		Collections.reverse(backtrace);

		return new SearchSolution<T>(backtrace);
	}


	/**
	 * Calculate cost between states.
	 *
	 * @param s the s
	 * @param s1 the s 1
	 * @param s2 the s 2
	 * @return the double
	 */
	protected double calculateCostBetweenStates(Searcheble<T> s, State<T> s1, State<T> s2) 
	{

		return 1;
	}




	/**
	 * Search.
	 *
	 * @param s the s
	 * @return the search solution
	 */
	@Override
	public abstract SearchSolution<T> Search(Searcheble<T> s);



	/* (non-Javadoc)
	 * @see algorithms.search.Searcher#getNumberOfNodsEvaluated()
	 */
	@Override
	public int getNumberOfNodsEvaluated()
	{

		return NumberOfNodsEvaluated;
	}


	/**
	 * Sets the number of nods evaluated.
	 *
	 * @param numberOfNodsEvaluated the new number of nods evaluated
	 */
	public void setNumberOfNodsEvaluated(int numberOfNodsEvaluated)

	{
		NumberOfNodsEvaluated = numberOfNodsEvaluated;
	}

}
