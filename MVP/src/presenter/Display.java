package presenter;

import java.io.File;

public class Display extends AbstractCommand 
{
	public Display(Presenter p) 
	{
		super(p);
	}
	@Override
	public void doCommand(String params) 
	{
		if (params==null)
			this.p.getV().ViewPrintMsg("!!!number of parameters is wrong!!!\n");
				

		else{
			this.p.getM().ModelDisplay(params);
		}

	}
}
