package controller;


/**
 * The Class Display.
 */
public class Display extends AbstractCommand 
{
	
	/**
	 * Instantiates a new display.
	 *
	 * @param c the c
	 */
	public Display(Controller c)
	{
		super(c);
	}
	
	/* (non-Javadoc)
	 * @see controller.AbstractCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] params)
	{
		if(params.length != 1)
		{
			this.c.ControllerDisplayMessage("!!!number of parameters is wrong!!!\n");
		}else{
			this.c.ControllerDisplayMaze(params[0]);
			
		}

	}

}
