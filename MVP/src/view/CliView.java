package view;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Observable;
import java.util.Scanner;

import algorithms.mazeGenerators.Maze3d;
import presenter.Command;
import presenter.Properties;


public class CliView extends Observable implements View 
{

	protected BufferedReader in;
	protected PrintWriter out;
	protected HashMap<String,Command> commands;


	public CliView(BufferedReader in, PrintWriter out) 
	{
		this.in=in;
		this.out=out;
		
	}

	//start CLI
	@Override
	public void ViewStart() throws IOException
	{
		ViewMenu();
		String line="";
		Scanner scanner = new Scanner(System.in);
		do
		{
			line = scanner.nextLine();
			this.setChanged();
			this.notifyObservers(line);

		} while (!(line.equals("exit")));
		scanner.close();
	}



	//print menu
	public void ViewMenu()
	{
		System.out.println("----------------------------------------------------------------------\n");
	
		System.out.println("The possible commands are:");
		System.out.println("__________________________\n");

		System.out.println("dir <path>\n");
		System.out.println("generateMaze3d <maze name> <x> <y> <z> \n");
		System.out.println("display <maze name> \n");
		System.out.println("displayCrossSectionBy <{x/y/z}> <index> <maze zame> \n");
		System.out.println("saveMaze <name> <file name> \n ");
		System.out.println("loadMaze <file name> <name>\n");
		System.out.println("solve <maze name> <{BFS/DFS}>\n");
		System.out.println("displaySolution <maze name> \n");
		System.out.println("menu\n");
		System.out.println("exit\n");
		System.out.println("----------------------------------------------------------------------\n");
	}


//!!!!!!!!!!!!!!!!!!!!!!
	public void executeCommand(String string){

		setChanged();
		notifyObservers(string);

	}
	//!!!!!!!!!!!!!!!!!!!!!!!!

	public void ViewPrintMsg(String msg)
	{
		out.write(msg);
		out.flush();
	}





	@Override
	public void ViewPrintDir(String[] args) 
	{
		if(args!=null){
			out.println("The path is:  ");
			out.flush();
			for(int i=0;i<args.length;i++)
				out.write(args[i]+ "\n");
			out.flush();

		}
		else{
			out.println("This Array is empty");
			out.flush();
		}

	}



	public BufferedReader getIn() 
	{
		return in;
	}

	public void setIn(BufferedReader in)
	{
		this.in = in;
	}

	public PrintWriter getOut()
	{
		return out;
	}

	public void setOut(PrintWriter out)
	{
		this.out = out;
	}
	
	@Override
	public void ViewGenerateMaze3d(String str) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ViewDisplayAllMaze(byte[] maze)
	{
		Maze3d myMaze=new Maze3d(maze);
		for(int i=0;i<myMaze.getP().getX();i++){
			for(int j=0;j<myMaze.getP().getY();j++){
				for(int k=0;k<myMaze.getP().getZ();k++)
				{
					System.out.print(myMaze.getCell(i,j,k)+" ");
				}
				System.out.println();
			}
			System.out.println();
		}
		System.out.println("\nstart: "+myMaze.getStartPosition().toString()+"\ngoal: "+myMaze.getGoalPosition().toString());
		

	}
	

	@Override
	public void ViewDisplayCrossSectionBy(int[][] Maze2d) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void ViewExit() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void ResetProp(Properties p) {
		// TODO Auto-generated method stub
		
	}





}


