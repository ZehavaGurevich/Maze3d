package algorithms.mazeGenerators;


import java.util.ArrayList;
import java.util.Random;

/**
 * The Class RandomPop.
 */
public class RandomPop extends AbstPop 
{

/* (non-Javadoc)
 * @see algorithms.mazeGenerators.AbstPop#Pop(java.util.ArrayList)
 */
//Random cell selection added to the C
	@Override
	public Position Pop(ArrayList<Position> p) 
	{

		Random rand=new Random();
		int randomNum=rand.nextInt(p.size());
		return p.get(randomNum);

	}

}