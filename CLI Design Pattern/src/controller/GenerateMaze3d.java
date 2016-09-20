package controller;

// TODO: Auto-generated Javadoc
/**
 * The Class GenerateMaze3d.
 */
public class GenerateMaze3d extends AbstractCommand
{

	/**
	 * Instantiates a new generate maze 3 d.
	 *
	 * @param c the c
	 */
	public GenerateMaze3d(Controller c)
	{
		super(c);
	}
	
	
	/* (non-Javadoc)
	 * @see controller.AbstractCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] params)
	{
		int x,y,z;
		if (params.length==4){
		try{
			x = Integer.parseInt(params[1]);
			y = Integer.parseInt(params[2]);
			z = Integer.parseInt(params[3]);
			c.ControllerGenerate3dMaze(params[0],x,y,z); 
		}
		catch(NumberFormatException e)
		{
	       c.ControllerDisplayMessage("error");
		}
		}
		else{
			this.c.ControllerDisplayMessage("!!!number of parameters is wrong!!!\n");
		}
	
	
			

	}

}
