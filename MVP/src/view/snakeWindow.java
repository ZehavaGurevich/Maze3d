package view;

import java.util.HashMap;

import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.Shell;

public class snakeWindow extends BasicWindow
{
	

	public snakeWindow(String title, int width, int height, HashMap<String, Listener> listenerCollection) {
		super(title, width, height, listenerCollection);
		// TODO Auto-generated constructor stub
	}

	@Override
	public Shell getShell() {
		
		return null;
	}

	@Override
	void initWidgets() 
	{
	
		
	}

	@Override
	public void close() {
		// TODO Auto-generated method stub
		
	}
	
}
