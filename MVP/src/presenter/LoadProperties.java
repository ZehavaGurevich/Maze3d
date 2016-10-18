package presenter;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class LoadProperties
{
	private static LoadProperties instance;
	private Properties properties;
	

	private LoadProperties()
	{
		try
		{
			XMLDecoder decoder = new XMLDecoder (new FileInputStream ("p	roperties.xml"));
			properties = (Properties)decoder.readObject();
			decoder.close();
		}
		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			System.out.println("reading properties from file failed");
		}
	}

	public static LoadProperties getInstance()
	{
		if (instance == null)
			instance = new LoadProperties();
		
		return instance;
	}

	public Properties getProperties()
	{
		return properties;
	}
	 
}
