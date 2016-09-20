package view;

import java.io.IOException;
import java.util.HashMap;

import controller.Command;

// TODO: Auto-generated Javadoc
/**
 * The Interface View.
 */
public interface View 
{
	
	/**
	 * View start.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//start CLI
	public void ViewStart() throws IOException;
	
	
	/**
	 * View send command.
	 *
	 * @param commands the commands
	 */
	//initialize the commands
	public void ViewSendCommand(HashMap<String, Command> commands);
	
	/**
	 * View print msg.
	 *
	 * @param msg the msg
	 */
	//print error
	public void ViewPrintMsg(String msg);
	
	/**
	 * View print dir.
	 *
	 * @param args the args
	 */
	//print all path in path
	public void ViewPrintDir(String[] args);
			
	/**
	 * View generate maze 3 d.
	 *
	 * @param str the str
	 */
	//print "maze is ready" after generate it
	public void ViewGenerateMaze3d(String str);
	
	/**
	 * View display all maze.
	 *
	 * @param maze the maze
	 */
	//print all mazes that the client ask
	public void ViewDisplayAllMaze(byte[] maze);
	
	/**
	 * View display cross section by.
	 *
	 * @param Maze2d the maze 2 d
	 */
	//print the maze's cross section by X/Y/Z in this index
	public void ViewDisplayCrossSectionBy(int[][] Maze2d);
	
	/**
	 * View save maze.
	 *
	 * @param str the str
	 */
	//print "maze has been saved" after save it 
	public void ViewSaveMaze(String str);
	

	/**
	 * View menu.
	 */
	//menu of commands
	public void ViewMenu();
	
	
	/**
	 * View exit.
	 */
	//print "bye bye"
	public void ViewExit();
	
	
	

	
}






