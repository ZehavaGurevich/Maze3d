package presenter;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

public class Properties implements Serializable
{
	private static final long serialVersionUID = 1L;
	private int x,y,z;
	private int numOfThreads;
	private String algo;

	private String mazeName;
	private String viewType;




	public Properties()
	{


	}


	public Properties( int x,int y,int z,int numOfThreads, String algo,String mazeName,String viewType)
	{
		this.x=x;
		this.y=y;
		this.z=z;
		this.numOfThreads=numOfThreads;
		this.algo=algo;
		this.mazeName=mazeName;
		this.viewType=viewType;

	}

	public Properties readPropertiesFromFile(String fileName)
	{
		try
		{
			XMLDecoder xmlDecoder = new XMLDecoder(new FileInputStream(fileName));
			Properties pro = (Properties)xmlDecoder.readObject();
			xmlDecoder.close();
			return pro;
		}
		catch (FileNotFoundException e)
		{
			System.out.println("reading properties from file failed");
			e.printStackTrace();
			return null;
		}
	}


	public int getX() {
		return x;
	}


	public void setX(int x) {
		this.x = x;
	}


	public int getY() {
		return y;
	}


	public void setY(int y) {
		this.y = y;
	}


	public int getZ() {
		return z;
	}


	public void setZ(int z) {
		this.z = z;
	}


	public int getNumOfThreads() {
		return numOfThreads;
	}


	public void setNumOfThreads(int numOfThreads) {
		this.numOfThreads = numOfThreads;
	}


	public String getAlgo() {
		return algo;
	}


	public void setAlgo(String algo) {
		this.algo = algo;
	}


	public String getMazeName() {
		return mazeName;
	}


	public void setMazeName(String mazeName) {
		this.mazeName = mazeName;
	}


	public String getViewType() {
		return viewType;
	}


	public void setViewType(String viewType) {
		this.viewType = viewType;
	}


	public static long getSerialversionuid() {
		return serialVersionUID;
	}




}