package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;

// TODO: Auto-generated Javadoc
/**
 * The Class CLI.
 */
public class CLI 
{
	
	/** The in. */
	private BufferedReader in;
	
	/** The out. */
	private PrintWriter out;
	
	/** The commands. */
	private HashMap<String, Command> commands; //maps between command string and command object 


	/**
	 * Instantiates a new cli.
	 *
	 * @param in the in
	 * @param out the out
	 * @param commands the commands
	 */
	//c'tor
	public CLI(BufferedReader in,PrintWriter out,HashMap<String, Command>commands) 
	{
		this.in = in;
		this.out = out;
		this.commands=commands;

	}



	/**
	 * Start.
	 *
	 * @throws IOException Signals that an I/O exception has occurred.
	 */
	//start CLI	
	public void start() throws IOException
	{
		//create a new thread 
		Thread thread= new Thread(new Runnable(){
	
			
			@Override
			public void run()
			{

				String [] arrayUserCommands = null;
				String currentCommand=null;
		
				do{
					//ask the user to enter command
					out.println("Please enter your command:");
					out.flush();
					try{
						// get the command
						String UserCommand = in.readLine();

						arrayUserCommands = UserCommand.split(" ");
						currentCommand = arrayUserCommands[0];

						if(currentCommand.equals("exit")){
							break;
						}
						else{
							String [] paramsArray=new String[arrayUserCommands.length-1];
							// if the value of the key "userCommand" in hash map is not empty

							if(commands.containsKey(currentCommand))
							{
								Command commandFromHash=commands.get(currentCommand);
								
								for (int i=0; i< arrayUserCommands.length-1;i++){

									paramsArray[i] = arrayUserCommands[i+1];
								}
								// check substring, the second part of the command from user.
								commandFromHash.doCommand(paramsArray);
							}
							else
							{
								out.println("Invalid command\n");
								out.flush();
							}
							
							
							 Thread.sleep(1000); // wait until another thread will finish 
						}

					}catch(IOException e){
						out.println("Could not read line IOExeption");
						e.printStackTrace();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}while(!(currentCommand.equals("exit")));
			}
		});
		thread.start();
	}



	/**
	 * Gets the in.
	 *
	 * @return the in
	 */
	public BufferedReader getIn() {
		return in;
	}



	/**
	 * Sets the in.
	 *
	 * @param in the new in
	 */
	public void setIn(BufferedReader in) {
		this.in = in;
	}



	/**
	 * Gets the out.
	 *
	 * @return the out
	 */
	public PrintWriter getOut() {
		return out;
	}



	/**
	 * Sets the out.
	 *
	 * @param out the new out
	 */
	public void setOut(PrintWriter out) {
		this.out = out;
	}



	/**
	 * Gets the commands.
	 *
	 * @return the commands
	 */
	public HashMap<String, Command> getCommands() {
		return commands;
	}



	/**
	 * Sets the commands.
	 *
	 * @param commands the commands
	 */
	public void setCommands(HashMap<String, Command> commands) {
		this.commands = commands;
	}
		







}
