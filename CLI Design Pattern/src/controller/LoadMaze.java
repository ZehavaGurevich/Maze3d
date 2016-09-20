package controller;

import java.io.IOException;

// TODO: Auto-generated Javadoc
/**
 * The Class LoadMaze.
 */
public class LoadMaze extends AbstractCommand {

	/**
	 * Instantiates a new load maze.
	 *
	 * @param c the c
	 */
	public LoadMaze(Controller c)
	{
		super(c);
	}


	/* (non-Javadoc)
	 * @see controller.AbstractCommand#doCommand(java.lang.String[])
	 */
	@Override
	public void doCommand(String[] params) 
	{
		if(params.length!=2)
		{
			c.ControllerDisplayMessage("invalid input\n");
		}else{
			try {
				c.ControllerLoadMaze(params[0], params[1]);
			} catch (IOException e) {
				e.printStackTrace();
			}



		}
	}


}

