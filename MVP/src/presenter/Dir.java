package presenter;

import java.io.File;

public class Dir extends AbstractCommand 
{
	public Dir(Presenter p)
	{
		super(p);
	}

	@Override
	public void doCommand(String params) 
	{
		if (params==null)
			this.p.getV().ViewPrintMsg("!!!number of parameters is wrong!!!\n");
				

		else{
			try {
				File file = new File(params);
				if(file.list().length == 0)
					this.p.getV().ViewPrintMsg("empty folder\n");
				else
					this.p.getV().ViewPrintDir(file.list());
					
			}
			catch (NullPointerException e)
			{
				this.p.getV().ViewPrintMsg("wrong path\n");
				
			}

		}

	}

}
