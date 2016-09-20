package controller;

// TODO: Auto-generated Javadoc
/**
 * The Class DisplaySolution.
 */
public class DisplaySolution extends AbstractCommand {

	/**
	 * Instantiates a new display solution.
	 *
	 * @param c the c
	 */
	public DisplaySolution(Controller c)
	{
		super(c);
	}


	/* (non-Javadoc)
	 * @see controller.AbstractCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] params) 
	{
		if(params.length!=1)
		{
			c.ControllerDisplayMessage("invalid input\n");
		}else{
			c.ControllerDisplaySolution(params[0]);
		}
	}
}


