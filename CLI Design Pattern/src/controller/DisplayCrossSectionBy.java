package controller;


/**
 * The Class DisplayCrossSectionBy.
 */
public class DisplayCrossSectionBy extends AbstractCommand 
{
	
	/**
	 * Instantiates a new display cross section by.
	 *
	 * @param c the c
	 */
	public DisplayCrossSectionBy(Controller c)
	{
		super(c);
	}

	/* (non-Javadoc)
	 * @see controller.AbstractCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] params)
	{
		if(params.length!=3)
		{
			c.ControllerDisplayMessage("invalid input\n");
		}
		
		
		if(!(params[0].equals("x")) && !(params[0].equals("y")) && !(params[0].equals("z")))
		{
			c.ControllerDisplayMessage("ivalid parameters\n");
			return ;
		}
		else{
			c.ControllerDisplayCrossSectionBy(params[0], Integer.parseInt(params[1]), params[2]);		
		}
	}

}
