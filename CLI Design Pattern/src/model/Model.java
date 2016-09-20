package model;
import java.io.IOException;;

// TODO: Auto-generated Javadoc
/**
 * The Interface Model.
 */
public interface Model 
{

	/**
	 * Model dir.
	 *
	 * @param path the path
	 */
	public void ModelDir(String path);
	
	/**
	 * Model generate maze 3 d.
	 *
	 * @param mazeName the maze name
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public void ModelGenerateMaze3d(String mazeName,int x,int y,int z);
	
	/**
	 * Model display.
	 *
	 * @param mazeName the maze name
	 */
	public void ModelDisplay(String mazeName);
	
	/**
	 * Model display cross section by.
	 *
	 * @param XYZ the xyz
	 * @param index the index
	 * @param mazeName the maze name
	 */
	public void ModelDisplayCrossSectionBy(String XYZ,int index,String mazeName);
	
	/**
	 * Model save maze.
	 *
	 * @param mazeName the maze name
	 * @param fileName the file name
	 */
	public void ModelSaveMaze(String mazeName,String fileName);
	
	/**
	 * Mode load maze.
	 *
	 * @param mazeName the maze name
	 * @param fileName the file name
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void ModeLoadMaze(String mazeName,String fileName) throws IOException;
	
	/**
	 * Model mze size.
	 *
	 * @param mazeName the maze name
	 */
	public void ModelMzeSize(String mazeName);
	
	/**
	 * Model file size.
	 *
	 * @param fileName the file name
	 */
	public void ModelFileSize(String fileName);
	
	/**
	 * Model solve.
	 *
	 * @param mazeName the maze name
	 * @param algorithm the algorithm
	 */
	public void ModelSolve(String mazeName,String algorithm);
	
	/**
	 * Model display solution.
	 *
	 * @param mazeName the maze name
	 */
	public void ModelDisplaySolution(String mazeName);
	
	/**
	 * Model exit.
	 */
	public void ModelExit();
	
	
}


