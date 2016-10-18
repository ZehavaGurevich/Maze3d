package presenter;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

import model.Model;
import view.View;


// TODO: Auto-generated Javadoc
/**
 * The Class Presenter.
 */
public class Presenter implements Observer
{
	
	/** The v. */
	private View v;
	
	/** The m. */
	private Model m;
	
	/** The commands. */
	private HashMap<String, Command> commands;


	/**
	 * Instantiates a new presenter.
	 *
	 * @param v the v
	 * @param m the m
	 */
	//c'tor
	public Presenter(View v, Model m)
	{
		this.v=v;
		this.m=m;
		this.commands= new HashMap<String,Command>(); 
		this.InitCommand();
	}


	/**
	 * Inits the command.
	 */
	public void InitCommand()
	{
		commands.put("open", new Open(this));
		commands.put("close",new Close(this));
	}



	/* (non-Javadoc)
	 * @see java.util.Observer#update(java.util.Observable, java.lang.Object)
	 */
	@Override
	public void update(Observable o, Object arg) 
	{
		if (o instanceof View)
		{	
			if (commands.containsKey((String) arg))
			{			
				Command command = commands.get((String) arg);
				command.doCommand();				
			}
			else
				v.ViewPrintMsg("Invalid command");

		}

		else if(o instanceof Model)
		{
			if (arg instanceof String) 
			{
				v.ViewPrintMsg((String) arg);
			}
		}
	}


	/**
	 * Gets the m.
	 *
	 * @return the m
	 */
	public Model getM() {
		return m;
	}


	/**
	 * Sets the m.
	 *
	 * @param m the new m
	 */
	public void setM(Model m) {
		this.m = m;
	}


	/**
	 * Gets the v.
	 *
	 * @return the v
	 */
	public View getV() {
		return v;
	}


	/**
	 * Sets the v.
	 *
	 * @param v the new v
	 */
	public void setV(View v) {
		this.v = v;
	}	
	
	
}
