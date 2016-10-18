package algorithms.mazeGenerators;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Position implements Serializable
{
	private int x;
	private int y;
	private int z;
//c'tor
	public Position(Position p)
	{
		this.setX(p.getX());
		this.setY(p.getY());
		this.setZ(p.getZ());
	}

//c'tor
	public Position(int x,int y,int z) 
	{
		this.setX(x);
		this.setY(y);
		this.setZ(z);
	}


	// expects to get string in format "{x,y,z}" and returns position
	public Position(String stringPos) throws NullPointerException {

		String[] stringPosArray = stringPos.substring(1, stringPos.length() - 1).split(",");

		this.x = Integer.parseInt(stringPosArray[0]);
		this.y = Integer.parseInt(stringPosArray[1]);
		this.z = Integer.parseInt(stringPosArray[2]);
	}
	
	
	
	@Override
	public String toString()
	{
		return ("{" + this.getX() + "," + this.getY() + "," + this.getZ() + "}");
	}


//getters and setters
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

	@Override
	public int hashCode() 
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + x;
		result = prime * result + y;
		result = prime * result + z;
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Position other = (Position) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		if (z != other.z)
			return false;
		return true;
	}

}


