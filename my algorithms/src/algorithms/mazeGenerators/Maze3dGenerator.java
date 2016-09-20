package algorithms.mazeGenerators;

/**
 * The Interface Maze3dGenerator.
 */
public interface Maze3dGenerator
{
	
	/**
	 * Generate.
	 *
	 * @param p the p
	 * @return the maze 3 d
	 */
	//Creates the maze
	public Maze3d generate(Position p);
	
	/**
	 * Measure algorithm time.
	 *
	 * @param p the p
	 * @return the string
	 */
	//Calculate Time
	public String measureAlgorithmTime(Position p);

}


	




