package presenter;

import java.io.IOException;

public class LoadMaze extends AbstractCommand
{
	public LoadMaze(Presenter p) 
	{
		super(p);
	}

	@Override
	public void doCommand(String params) 
	{

		String[] arrParmas= params.split(" ");
		if(arrParmas.length==2)
		{
			String name= arrParmas[0];
			String file= arrParmas[1];
			p.getM().ModelSaveMaze(file,name);
		}
		else
		{
			System.out.println("Invalid input\n");
		}


	}
}
