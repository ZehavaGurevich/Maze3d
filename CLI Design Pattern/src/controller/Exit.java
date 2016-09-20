package controller;

// TODO: Auto-generated Javadoc
/**
 * The Class Exit.
 */
public class Exit extends AbstractCommand
{
	
	/**
	 * Instantiates a new exit.
	 *
	 * @param c the c
	 */
	public Exit(Controller c) 
	{
		super(c);
	}

	/* (non-Javadoc)
	 * @see controller.AbstractCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] params)
	{
		c.ControllerExit();

	}

}
