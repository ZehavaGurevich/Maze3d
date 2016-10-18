package view;




import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Color;

import algorithms.mazeGenerators.Position;

public class Player 
{
	int x,y,z;
	public Player(int x,int y, int z) 
	{
		this.x=x;
		this.y=y;
		this.z=z;
		
	}
	public Player(Position p) 
	{
		this.x=p.getX();
		this.y=p.getY();
		this.z=p.getZ();
	}
	
	public void paint(PaintEvent e, int w, int h)
	{
	e.gc.setForeground(new Color(null,255,0,0));
	e.gc.setBackground(new Color(null,255,0,0));
	

	
	e.gc.fillOval(x*w,y*h, w, h);
	e.gc.drawOval(x*w,y*h, w, h);
	}
	

	
	//getter and setters
	public int getX() 
	{
		return x;
	}
	public void setX(int x)
	{
		this.x = x;
	}
	public int getY() 
	{
		return y;
	}
	public void setY(int y) 
	{
		this.y = y;
	}
	public int getZ()
	{
		return z;
	}
	public void setZ(int z)
	{
		this.z = z;
	}
	
	public Position getPosition()
	{
		return new Position(this.x,this.y,this.z);
	}
	
	public void setPosition(int x, int y, int z)
	{
		this.x=x;
		this.y=y;
		this.z=z;
	}

	
}
