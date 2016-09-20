package controller;

import java.io.IOException;
import java.util.HashMap;

import model.Model;
import view.View;

// TODO: Auto-generated Javadoc
/**
 * The Class MyController.
 */
public class MyController implements Controller 
{

	/** The m. */
	private Model m;
	
	/** The v. */
	private View v;
	
	/** The commands. */
	private HashMap<String, Command> commands;


	/**
	 * Instantiates a new my controller.
	 *
	 * @param m the m
	 * @param v the v
	 */
	public MyController(Model m, View v) 
	{
		this.m = m;
		this.v = v;
	}

	/**
	 * The Class Menu.
	 */
	public class Menu implements Command
	{

		/* (non-Javadoc)
		 * @see controller.Command#doCommand(java.lang.String[])
		 */
		@Override
		public void doCommand(String[] params)
		{
			v.ViewMenu();

		}
	}



	/* (non-Javadoc)
	 * @see controller.Controller#setModel(model.Model)
	 */
	@Override
	public void setModel(Model m)
	{
		this.m=m;
	}

	/* (non-Javadoc)
	 * @see controller.Controller#setView(view.View)
	 */
	@Override
	public void setView(View v) 
	{
		this.v=v;
		this.commands=new HashMap<String,Command>();

		this.commands.put("menu", new Menu());
		this.commands.put("dir",new Dir(this));	
		this.commands.put("generateMaze3d", new GenerateMaze3d(this));
		this.commands.put("display", new Display(this));
		this.commands.put("displayCrossSectionBy", new DisplayCrossSectionBy(this));
		this.commands.put("saveMaze", new SaveMaze(this));
		this.commands.put("loadMaze", new LoadMaze(this));
		this.commands.put("solve",new Solve(this));
		this.commands.put("displaySolution", new DisplaySolution(this));
		this.commands.put("exit", new Exit(this));
		v.ViewSendCommand(commands);



	}

	/* (non-Javadoc)
	 * @see controller.Controller#ControllerDisplayMessage(java.lang.String)
	 */
	@Override
	public void ControllerDisplayMessage(String msg)
	{
		v.ViewPrintMsg(msg);

	}

	/* (non-Javadoc)
	 * @see controller.Controller#ControllerDisplayMaze(java.lang.String)
	 */
	@Override
	public void ControllerDisplayMaze(String mazeName)
	{
		m.ModelDisplay(mazeName);

	}

	/* (non-Javadoc)
	 * @see controller.Controller#ControllerDisplayStringArray(java.lang.String[])
	 */
	@Override
	public void ControllerDisplayStringArray(String[] args)
	{

	}

	/* (non-Javadoc)
	 * @see controller.Controller#ControllerPrintDir(java.lang.String[])
	 */
	@Override
	public void ControllerPrintDir(String[] args) {
		v.ViewPrintDir(args);

	}

	/* (non-Javadoc)
	 * @see controller.Controller#ControllerGenerate3dMaze(java.lang.String, int, int, int)
	 */
	@Override
	public void ControllerGenerate3dMaze(String nameMaze, int x, int y, int z)
	{
		m.ModelGenerateMaze3d(nameMaze, x, y, z);
	}

		/* (non-Javadoc)
		 * @see controller.Controller#ControllerDisplayCrossSectionBy(java.lang.String, int, java.lang.String)
		 */
		@Override
		public void ControllerDisplayCrossSectionBy(String XYZ, int index,String mazeName)
		{
			m.ModelDisplayCrossSectionBy(XYZ,index,mazeName);

		}

		/* (non-Javadoc)
		 * @see controller.Controller#ControllerSaveMaze(java.lang.String, java.lang.String)
		 */
		@Override
		public void ControllerSaveMaze(String mazeName, String fileName) {
			m.ModelSaveMaze(mazeName, fileName);
		}

		/* (non-Javadoc)
		 * @see controller.Controller#ControllerLoadMaze(java.lang.String, java.lang.String)
		 */
		@Override
		public void ControllerLoadMaze(String mazeName, String fileName) throws IOException 
		{
			m.ModeLoadMaze(mazeName, fileName);

		}

		/* (non-Javadoc)
		 * @see controller.Controller#ControllerSolve(java.lang.String, java.lang.String)
		 */
		@Override
		public void ControllerSolve(String mazeName, String algorithm) {
			m.ModelSolve(mazeName, algorithm);

		}

		/* (non-Javadoc)
		 * @see controller.Controller#ControllerDisplaySolution(java.lang.String)
		 */
		@Override
		public void ControllerDisplaySolution(String mazeName) {
			m.ModelDisplaySolution(mazeName);

		}

		/* (non-Javadoc)
		 * @see controller.Controller#ControllerMazeSize(java.lang.String)
		 */
		@Override
		public void ControllerMazeSize(String mazeName) {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see controller.Controller#ControllerFileSize(java.lang.String)
		 */
		@Override
		public void ControllerFileSize(String mazeName) {
			// TODO Auto-generated method stub

		}

		/* (non-Javadoc)
		 * @see controller.Controller#ControllerExit()
		 */
		@Override
		public void ControllerExit()
		{
			m.ModelExit();

		}

	}
