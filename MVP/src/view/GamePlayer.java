package view;

import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;

public class GamePlayer 
{
	public void paint(PaintEvent e,int x,int y, int width, int height)
	{

	e.gc.setBackground(new Color(null,255,165,0));
	e.gc.fillOval(x, y, width, height);

	e.gc.setBackground(new Color(null,255,165,0));
	e.gc.setBackground(new Color(null,0,0,0));
	}

}
