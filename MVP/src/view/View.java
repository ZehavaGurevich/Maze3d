package view;

import java.io.IOException;

import presenter.Properties;



public interface View
{

	//start CLI
	public void ViewStart() throws IOException;
	
	
//	//initialize the commands
//	public void ViewSendCommand(HashMap<String, Command> commands);
	
	
	//print error
	public void ViewPrintMsg(String msg);
	

	//print all path in path
	public void ViewPrintDir(String[] args);
			
	
	//print "maze is ready" after generate it
	public void ViewGenerateMaze3d(String str);
	
	//print all mazes that the client ask
	public void ViewDisplayAllMaze(byte[] maze);
	
	
	//print the maze's cross section by X/Y/Z in this index
	public void ViewDisplayCrossSectionBy(int[][] Maze2d);


	//menu of commands
	public void ViewMenu();
	
	
	//print "bye bye"
	public void ViewExit();
	
	public void ResetProp(Properties p);
	 
}
