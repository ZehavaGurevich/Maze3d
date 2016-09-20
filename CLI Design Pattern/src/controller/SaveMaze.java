package controller;

// TODO: Auto-generated Javadoc
/**
 * The Class SaveMaze.
 */
public class SaveMaze extends AbstractCommand 
{
	
	/**
	 * Instantiates a new save maze.
	 *
	 * @param c the c
	 */
	public SaveMaze(Controller c)
	{
		super(c);
	}
	
	
	/* (non-Javadoc)
	 * @see controller.AbstractCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] params)
	{
		if(params.length!=2)
		{
			c.ControllerDisplayMessage("invalid input\n");
		}
		
		else {
			this.c.ControllerSaveMaze(params[0], params[1]);

	}

}
}


