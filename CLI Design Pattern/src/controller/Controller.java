package controller;

import java.io.IOException;

import model.Model;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Interface Controller.
 */
public interface Controller 
{

	/**
	 * Sets the model.
	 *
	 * @param m the new model
	 */
	public void setModel(Model m);
	
	/**
	 * Sets the view.
	 *
	 * @param v the new view
	 */
	public void setView(View v);
	

	/**
	 * Controller display message.
	 *
	 * @param msg the msg
	 */
	public void ControllerDisplayMessage(String msg);
	
	/**
	 * Controller display maze.
	 *
	 * @param mazeName the maze name
	 */
	public void ControllerDisplayMaze(String mazeName);
	
	/**
	 * Controller display string array.
	 *
	 * @param args the args
	 */
	public void ControllerDisplayStringArray(String [] args);
	
	/**
	 * Controller print dir.
	 *
	 * @param args the args
	 */
	public void ControllerPrintDir(String[] args);
	
	/**
	 * Controller generate 3 d maze.
	 *
	 * @param nameMaze the name maze
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public void ControllerGenerate3dMaze(String nameMaze, int x, int y, int z); 
	
	/**
	 * Controller display cross section by.
	 *
	 * @param XYZ the xyz
	 * @param index the index
	 * @param mazeName the maze name
	 */
	public void ControllerDisplayCrossSectionBy(String XYZ,int index,String mazeName);
	
	/**
	 * Controller save maze.
	 *
	 * @param mazeName the maze name
	 * @param fileName the file name
	 */
	public void ControllerSaveMaze(String mazeName,String fileName);
	
	/**
	 * Controller load maze.
	 *
	 * @param mazeName the maze name
	 * @param fileName the file name
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	public void ControllerLoadMaze(String mazeName,String fileName) throws IOException;
	
	/**
	 * Controller solve.
	 *
	 * @param mazeName the maze name
	 * @param algorithm the algorithm
	 */
	public void ControllerSolve(String mazeName,String algorithm);
	
	/**
	 * Controller display solution.
	 *
	 * @param mazeName the maze name
	 */
	public void ControllerDisplaySolution(String mazeName);
	
	/**
	 * Controller maze size.
	 *
	 * @param mazeName the maze name
	 */
	public void ControllerMazeSize(String mazeName);
	
	/**
	 * Controller file size.
	 *
	 * @param mazeName the maze name
	 */
	public void ControllerFileSize(String mazeName);
	
	/**
	 * Controller exit.
	 */
	public void ControllerExit();

	
}


