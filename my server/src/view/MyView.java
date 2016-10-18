package view;

import java.util.Observable;
import java.util.Scanner;


// TODO: Auto-generated Javadoc
/**
 * The Class MyView.
 */
public class MyView  extends Observable implements View 
{
	
	/* (non-Javadoc)
	 * @see view.View#start()
	 */
	@Override
	public void start()
	{	
		ViewMenu();
		String line="";
		Scanner scanner = new Scanner(System.in);
		do
		{
			line = scanner.nextLine();
			this.setChanged();
			this.notifyObservers(line);

		} while (!(line.equals("close")));
		scanner.close();
	}

	/* (non-Javadoc)
	 * @see view.View#ViewMenu()
	 */
	@Override
	public void ViewMenu()
	{
		System.out.println("----------------------------------------------------------------------");
		System.out.println("----- Server -----");
		System.out.println("The possible commands are:");
		System.out.println("open");
		System.out.println("close");
		System.out.println("----------------------------------------------------------------------\n");
	}

	/* (non-Javadoc)
	 * @see view.View#ViewPrintMsg(java.lang.String)
	 */
	public void ViewPrintMsg(String msg)
	{
		System.out.println(msg);
	}
}
