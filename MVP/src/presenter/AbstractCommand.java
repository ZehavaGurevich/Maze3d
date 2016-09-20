package presenter;

public abstract class AbstractCommand implements Command
{
	 protected Presenter p;
	 
	public AbstractCommand(Presenter p)
	{
		this.p=p;
	}

}
