package algorithms.search;

import java.util.ArrayList;


/**
 * The Class BFSsearcher.
 *
 * @param <T> the generic type
 */
public class BFSsearcher<T> extends CommonSearcher<T> 
{

	/**
	 * Instantiates a new BF ssearcher.
	 */
	public BFSsearcher() {
		super();
	}


	/* (non-Javadoc)
	 * @see algorithms.search.CommonSearcher#Search(algorithms.search.Searcheble)
	 */
	@Override
	public SearchSolution<T> Search(Searcheble<T> s)
	{
		if(s==null)
		{
			return null;
		}

		State<T> n; 
		AddToPriorityQueue(s.getStartState());
		while(open.size()>0)
		{
			n = PopFromPriorityQueue();
			close.add(n);
			if(n.equals(s.getGoalState()))
			{
				return backTrace(s.getStartState(),n);
			}
			ArrayList<State<T>>Successors=new ArrayList<State<T>>();
			Successors=s.getAllPossibleStates(n);
			for (State<T> state : Successors) 
			{
				if(!close.contains(state) &&(!open.contains(state)))
				{
					//add state to open.
					state.setCameFrom(n);
					state.setCost(n.getCost()+calculateCostBetweenStates(s, n, state));
					AddToPriorityQueue(state);
				}
				else{

					if(n.getCost()+calculateCostBetweenStates(s, n, state)<state.getCost())
					{
						if(!open.contains(state))
						{
							AddToPriorityQueue(state);
						}
						else{
							removeFromPriorityQueue(state);
							AddToPriorityQueue(state);
						}
					}

				}



			}

		}
		return null;
	}

}
