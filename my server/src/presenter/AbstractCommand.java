package presenter;

// TODO: Auto-generated Javadoc
/**
 * The Class AbstractCommand.
 */
public abstract class AbstractCommand implements Command
{
	 
 	/** The p. */
 	protected Presenter p;
	 
	/**
	 * Instantiates a new abstract command.
	 *
	 * @param p the p
	 */
	public AbstractCommand(Presenter p)
	{
		this.p=p;
	}

}
