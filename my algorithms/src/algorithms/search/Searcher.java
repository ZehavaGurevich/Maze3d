package algorithms.search;


/**
 * The Interface Searcher.
 *
 * @param <T> the generic type
 */
public interface Searcher<T> 
{

	/**
	 * Search.
	 *
	 * @param s the s
	 * @return the search solution
	 */
	public SearchSolution<T> Search(Searcheble<T> s);

	/**
	 * Gets the number of nods evaluated.
	 *
	 * @return the number of nods evaluated
	 */
	public int getNumberOfNodsEvaluated();





}