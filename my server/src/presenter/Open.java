package presenter;

// TODO: Auto-generated Javadoc
/**
 * The Class Open.
 */
public class Open extends AbstractCommand {

	/**
	 * Instantiates a new open.
	 *
	 * @param p the p
	 */
	public Open(Presenter p){
		super(p);
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		this.p.getM().openServer();
	}

}
