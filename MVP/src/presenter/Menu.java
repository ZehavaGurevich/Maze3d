package presenter;

public class Menu extends AbstractCommand
{
	public Menu(Presenter p) 
	{
		super(p);

	}
	@Override
	public void doCommand(String params)
	{
		this.p.getV().ViewMenu();

	}

}
