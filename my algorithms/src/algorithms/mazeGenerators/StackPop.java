package algorithms.mazeGenerators;

import java.util.ArrayList;

/**
 * The Class StackPop.
 */
public class StackPop extends AbstPop
{

/* (non-Javadoc)
 * @see algorithms.mazeGenerators.AbstPop#Pop(java.util.ArrayList)
 */
//Select the last cell added to the C
	@Override
	public Position Pop(ArrayList<Position> p)
	{
		this.arr = p;
		return arr.get(arr.size()-1);
	}

}