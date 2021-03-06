package model;

import java.io.InputStream;
import java.io.OutputStream;


// TODO: Auto-generated Javadoc
/**
 * The Interface ClientHandler.
 */
public interface ClientHandler 
{
	
	/**
	 * Handle client.
	 *
	 * @param inFromClient the in from client
	 * @param outToClient the out to client
	 */
	void handleClient(InputStream inFromClient, OutputStream outToClient);
	
	/**
	 * Close.
	 */
	public void close();
}
