package presenter;

public class Exit extends AbstractCommand 
{
	public Exit(Presenter p) 
	{
		super(p);
	}

		@Override
		public void doCommand(String params) 
		{
			this.p.getM().ModelExit();
			
		}

	}
