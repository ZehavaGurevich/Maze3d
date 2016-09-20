package algorithms.search;

public class State<T> 
{

	private T state;
	private double cost;
	private State<T> cameFrom;

	public State(T state)
	{
		this.state = state;
		this.cost = 0;
		this.cameFrom = null;
	}

	/**
	 * Instantiates a new state.
	 *
	 * @param state2 the state 2
	 */
	public State(State<T> state2)
	{
		state = state2.getState();
		cost = state2.getCost();
		cameFrom = state2.getCameFrom();
	}

	public T getState() 
	{
		return state;
	}

	public void setState(T state) 
	{
		this.state = state;
	}

	public double getCost()
	{
		return cost;
	}


	@Override
	public String toString()
	{
		return "node:"+this.state;
	}





	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((state == null) ? 0 : state.hashCode());
		return result;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) 
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		State<?> other = (State<?>) obj;
		if (state == null) {
			if (other.state != null)
				return false;
		} else if (!state.equals(other.state))
			return false;
		return true;
	}

	public void setCost(double cost)
	{
		this.cost = cost;
	}

	public State<T> getCameFrom()
	{
		return cameFrom;
	}

	public void setCameFrom(State<T> cameFrom)
	{
		this.cameFrom = cameFrom;
	}
}

