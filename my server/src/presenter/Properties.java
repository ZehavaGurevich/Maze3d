package presenter;

import java.beans.XMLDecoder;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;


// TODO: Auto-generated Javadoc
/**
 * The Class Properties.
 */
@SuppressWarnings("serial")
public class Properties implements Serializable 
{
	
	/** The num of clients. */
	protected int numOfClients;
	
	/** The port. */
	protected int port;
	

	/**
	 * Instantiates a new properties.
	 */
	public Properties()
	{
	}
	

	/**
	 * Instantiates a new properties.
	 *
	 * @param port the port
	 * @param numOfClients the num of clients
	 */
	public Properties(int port, int numOfClients)
	{
		this.port=port;
		this.numOfClients=numOfClients;
	}
	

	/**
	 * Read properties from file.
	 *
	 * @param fileName the file name
	 * @return the properties
	 */
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


	/**
	 * Gets the num of clients.
	 *
	 * @return the num of clients
	 */
	public int getNumOfClients(){
		return numOfClients;
	}

	/**
	 * Sets the num of clients.
	 *
	 * @param numOfClients the new num of clients
	 */
	public void setNumOfClients(int numOfClients){
		this.numOfClients = numOfClients;
	}

	/**
	 * Gets the port.
	 *
	 * @return the port
	 */
	public int getPort(){
		return port;
	}

	/**
	 * Sets the port.
	 *
	 * @param port the new port
	 */
	public void setPort(int port){
		this.port = port;
	}
}
