package algorithms.search;

import java.util.ArrayList;
import java.util.Stack;

/**
 * The Class DFSsearcher.
 *
 * @param <T> the generic type
 */
public class DFSsearcher<T> extends CommonSearcher<T> 
{

	/* (non-Javadoc)
	 * @see algorithms.search.CommonSearcher#Search(algorithms.search.Searchable)
	 */
	public SearchSolution<T> Search(Searcheble<T> s)
	{
		Stack<State<T>> stackNode=new Stack<State<T>>();
		ArrayList<State<T>> Visit = new ArrayList<State<T>>();
		stackNode.add(s.getStartState());
		Visit.add(s.getStartState());
		while(stackNode.size()>0)
		{

			State<T> element=stackNode.pop();

			NumberOfNodsEvaluated++;
			if(element.equals(s.getGoalState()))
			{
				return backTrace(s.getStartState(),element);
			}
			ArrayList<State<T>> neighbours= new ArrayList<State<T>>(s.getAllPossibleStates(element));

			for (State<T> state : neighbours) {

				if((!Visit.contains(state)) && (!state.getState().equals(null))){
					state.setCameFrom(element);
					stackNode.add(state);
					Visit.add(state);
				}

			}



		}


		return null;
	}
}