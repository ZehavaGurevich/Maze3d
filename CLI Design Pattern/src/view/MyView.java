package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;
import controller.Controller;

public class MyView implements View 
{
	private CLI cli;
	private Controller c;
	protected BufferedReader in;
	protected PrintWriter out;
	protected HashMap<String,Command> commands;

	public MyView(BufferedReader in, PrintWriter out,HashMap<String,Command> commands) 
	{
		this.in=in;
		this.out=out;
		this.commands=commands;
	}

	@Override
	public void ViewStart() throws IOException
	{
		this.ViewMenu();
		cli.start();
	}


	//print menu
	public void ViewMenu()
	{
		out.println("----------------------------------------------------------------------\n");
		out.println("The possible commands are:");
		out.println("__________________________\n");

		out.println("dir <path>\n");
		out.println("generateMaze3d <maze name> <x> <y> <z> \n");
		out.println("display <maze name> \n");
		out.println("displayCrossSectionBy <{x/y/z}> <index> <maze zame> \n");
		out.println("saveMaze <name> <file name> \n ");
		out.println("loadMaze <file name> <name>\n");
		out.println("solve <maze name> <{BFS/DFS}>\n");
		out.println("displaySolution <maze name> \n");
		out.println("menu\n");
		out.println("exit\n");
		out.println("----------------------------------------------------------------------\n");
	}




	public void ViewPrintMsg(String msg)
	{
		out.write(msg);
		out.flush();
	}

	@Override
	public void ViewSendCommand(HashMap<String, Command> commands)
	{
		this.cli=new CLI(in, out, commands);
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

	@Override
	public void ViewGenerateMaze3d(String str) 
	{
		cli.getOut().println(str);
		cli.getOut().flush();

	}

	@Override
	public void ViewDisplayAllMaze(byte[] maze) {
	}


	@Override
	public void ViewDisplayCrossSectionBy(int[][] Maze2d) {
		// TODO Auto-generated method stub

	}

	@Override
	public void ViewSaveMaze(String str) 
	{
		cli.getOut().println(str);
        cli.getOut().flush();   

	}



	@Override
	public void ViewExit() {
		out.println("The end");
		out.flush();

	}

	
	//setters and getters
	public CLI getCli() 
	{
		return cli;
	}

	public void setCli(CLI cli)
	{
		this.cli = cli;
	}

	public Controller getC() 
	{
		return c;
	}

	public void setMyController(Controller c) 
	{
		this.c = c;
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

	public HashMap<String, Command> getCommands()
	{
		return commands;
	}

	public void setCommands(HashMap<String, Command> commands) 
	{
		this.commands = commands;
	}



}


