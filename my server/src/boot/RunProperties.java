package boot;

import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import presenter.Properties;


// TODO: Auto-generated Javadoc
/**
 * The Class RunProperties.
 */
public class RunProperties
{
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args)
	{
		Properties properties = new Properties(5555, 5);

		try
		{
			XMLEncoder xmlEncoder = new XMLEncoder(new FileOutputStream("properties.xml"));
			xmlEncoder.writeObject(properties);
			xmlEncoder.close();

		} 
		catch (FileNotFoundException e)
		{
			System.out.println("saving properties in file failed");
			e.printStackTrace();
		} 
	}
}
