package controller;

import java.io.File;


/**
 * The Class Dir.
 */
public class Dir extends AbstractCommand 
{
	
	/**
	 * Instantiates a new dir.
	 *
	 * @param c the c
	 */
	public Dir(Controller c)
	{
		super(c);

	}


	/* (non-Javadoc)
	 * @see controller.AbstractCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] params)
	{
		if (params.length!=1)
			this.c.ControllerDisplayMessage("!!!number of parameters is wrong!!!\n");
		

		else{
			try {
				File file = new File(params[0]);
				if(file.list().length == 0)
					this.c.ControllerDisplayMessage("empty file\n");
				else
					this.c.ControllerPrintDir(file.list());
			}
			catch (NullPointerException e)
			{
				this.c.ControllerDisplayMessage("wrong path\n");
			}

		}
	}


}
