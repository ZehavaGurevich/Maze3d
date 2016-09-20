package algorithms.mazeGenerators;

import java.util.ArrayList;


/**
 * The Class AbstPop.
 */
//abstract class the StackPop and RandomPop extend form her
public abstract class AbstPop implements Pop 
{

	/** The arr. */
	ArrayList<Position>	arr;
	
	/* (non-Javadoc)
	 * @see algorithms.mazeGenerators.Pop#Pop(java.util.ArrayList)
	 */
	public abstract Position Pop(ArrayList<Position> p);


}


