package controller;

// TODO: Auto-generated Javadoc
/**
 * The Class Solve.
 */
public class Solve extends AbstractCommand {

	/**
	 * Instantiates a new solve.
	 *
	 * @param c the c
	 */
	public Solve(Controller c) 
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
		else
		{
			c.ControllerSolve(params[0], params[1]);
		}
		}

	}


