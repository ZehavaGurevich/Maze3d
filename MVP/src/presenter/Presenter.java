package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;


import model.Model;
import view.View;

public class Presenter implements Observer
{ 
	private View v;
	private Model m;
	private HashMap<String, Command> commands;

	

	//c'tor
	public Presenter(View v, Model m)
	{
		this.v=v;
		this.m=m;
		this.commands= new HashMap<String,Command>(); 
		this.InitCommand();
	}

	public void InitCommand()
	{
		this.commands.put("menu", new Menu(this));
		this.commands.put("dir",new Dir(this));	
		this.commands.put("generateMaze3d", new GenerateMaze3d(this));
		this.commands.put("display", new Display(this));
		this.commands.put("displayCrossSectionBy", new DisplayCrossSectionBy(this));
		this.commands.put("saveMaze", new SaveMaze(this));
		this.commands.put("loadMaze", new LoadMaze(this));
		this.commands.put("solve", new Solve(this));
		this.commands.put("displaySolution", new DisplaySolution(this));
		this.commands.put("exit",new Exit(this));	
	}



	@Override
	public void update(Observable o, Object arg)
	{
		if(o instanceof View)
		{
			//arr[0] is the command
			String[] arr= ((String) arg).split("-"); 
			String params=null;

			if(commands.containsKey(arr[0]))
			{
				Command commandFromHash=commands.get(arr[0]);

				if(arr.length>1)
				{
					params=arr[1];
					
				}
				commandFromHash.doCommand(params);
			
			}

			else
			{
				System.out.println("Invalid command\n");	

			}



		}
		else if(o instanceof Model)
		{
			if(arg instanceof String)
			{
				this.getV().ViewPrintMsg((String) arg);
				
			}

		}

	}
	
	
	public View getV() {
		return v;
	}

	public void setV(View v) {
		this.v = v;
	}

	public Model getM() {
		return m;
	}

	public void setM(Model m) {
		this.m = m;
	}

}
