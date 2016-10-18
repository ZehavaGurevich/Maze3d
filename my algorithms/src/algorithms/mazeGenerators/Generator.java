
package algorithms.mazeGenerators;

/**
 * The Class Generator.
 */

public abstract class Generator implements Maze3dGenerator
{

	/* (non-Javadoc)
	 * @see algorihems.mazeGenerators.Maze3dGenerator#generate(algorihems.mazeGenerators.Position)
	 */
	public abstract Maze3d generate(Position p1); 
	/* (non-Javadoc)
	 * @see algorihems.mazeGenerators.Maze3dGenerator#measureAlgorithmTime(algorihems.mazeGenerators.Position)
	 */
//This function measure algorithm time
	public String measureAlgorithmTime(Position p1)
	{
		long s;
		long t;
		s=System.currentTimeMillis();
		generate(p1);
		t=System.currentTimeMillis();
		long time= t-s;
		return String.valueOf(time);
	}

}





