package presenter;

public class DisplaySolution extends AbstractCommand
{ 
	public DisplaySolution(Presenter p)
	{
		super(p);
	}

	@Override
	public void doCommand(String params) 
	{ 
		if (params==null)
			this.p.getV().ViewPrintMsg("!!!invalid input!!!\n");
				

		else{
			this.p.getM().ModelDisplaySolution(params);
		}

		

	}

}
