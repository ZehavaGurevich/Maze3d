package presenter;

// TODO: Auto-generated Javadoc
/**
 * The Class Close.
 */
public class Close extends AbstractCommand {

	/**
	 * Instantiates a new close.
	 *
	 * @param p the p
	 */
	public Close(Presenter p){
		super(p);
	}

	/* (non-Javadoc)
	 * @see presenter.Command#doCommand()
	 */
	@Override
	public void doCommand() {
		this.p.getM().closeServer();
	}

}
