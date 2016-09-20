package controller;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractCommand.
 */
public abstract class AbstractCommand implements Command 
{
	
	/** The c. */
	protected Controller c;
	
	 /**
 	 * Instantiates a new abstract command.
 	 *
 	 * @param c the c
 	 */
 	public AbstractCommand(Controller c) 
	 {
		 this.c=c;
	
	}
	
	/* (non-Javadoc)
	 * @see controller.Command#doCommand(java.lang.String[])
	 */
	@Override
	public abstract void doCommand(String[] params);

}
