package model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.util.Observable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import presenter.Properties;


// TODO: Auto-generated Javadoc
/**
 * The Class MyModel.
 */
public class MyModel extends Observable implements Model 
{
	
	/** The port. */
	private int port;
	
	/** The num of clients. */
	private int numOfClients;
	
	/** The clients counter. */
	private int clientsCounter;
	
	/** The clinet handler. */
	private ClientHandler clinetHandler;
	
	/** The thread pool. */
	private ExecutorService threadPool;

	/** The stop. */
	private volatile boolean stop;

	/** The server. */
	private ServerSocket server;
	
	/** The main server thread. */
	private Thread mainServerThread;

	/** The prop. */
	private Properties prop;



	/**
	 * Instantiates a new my model.
	 */
	public MyModel() 
	{
		this.prop = new Properties();
		this.prop = prop.readPropertiesFromFile("properties.xml");

		try
		{
			this.numOfClients = prop.getNumOfClients();
			this.threadPool = Executors.newFixedThreadPool(this.numOfClients);
			this.port = prop.getPort();
			this.server=new ServerSocket(this.port); //ask OP to control this port
		} 
		catch (IOException e)
		{
			e.printStackTrace();
		}


		this.clinetHandler=new MyClientHandler();
		this.clientsCounter=0;
		this.stop=false;
	}



	/* (non-Javadoc)
	 * @see model.Model#openServer()
	 */
	public void openServer()
	{
		try 
		{
			server.setSoTimeout(5000);
		}
		catch (SocketException e) 
		{
			setChanged();
			notifyObservers(e.getMessage());
		}

		mainServerThread=new Thread(new Runnable()
		{			
			@Override
			public void run()
			{
				while(!stop)
				{
					try
					{
						final Socket someClient = server.accept();

						if(someClient!=null)
						{
							threadPool.execute(new Runnable()
							{									
								@Override
								public void run()
								{
									try
									{	
										clientsCounter++;
										setChanged();
										notifyObservers("client "+ clientsCounter+ " now connected");

										clinetHandler.handleClient(someClient.getInputStream(), someClient.getOutputStream());
										someClient.close();
										setChanged();
										notifyObservers("done handling client "+clientsCounter);
									}

									catch(IOException e)
									{
										setChanged();
										notifyObservers(e.getMessage());
									}									
								}
							});								
						}
					}

					catch (SocketTimeoutException e)
					{
						setChanged();
						notifyObservers("no clinet connected...");
					} 
					catch (IOException e)
					{
						setChanged();
						notifyObservers(e.getMessage());
					}
				}

				setChanged();
				notifyObservers("done accepting new clients.");
			}
		});

		mainServerThread.start();
		setChanged();
		notifyObservers("server is open on port "+this.port);
	}


	/* (non-Javadoc)
	 * @see model.Model#closeServer()
	 */
	@SuppressWarnings("unused")
	public void closeServer()
	{
		stop=true;
		clinetHandler.close();

		try 
		{
			setChanged();
			notifyObservers("shutting down...");
			threadPool.shutdown();

			//wait 10 seconds over and over again until all running jobs have finished
			boolean allTasksCompleted=false;
			while(!(allTasksCompleted=threadPool.awaitTermination(10, TimeUnit.SECONDS)));
			setChanged();
			notifyObservers("all the tasks have finished");

			//wait for the main server thread to die
			if(mainServerThread!=null)
			{
				mainServerThread.join();		
				setChanged();
				notifyObservers("main server thread is done");
			}

			server.close();
			setChanged();
			notifyObservers("server is safely closed");
		} 
		catch (InterruptedException e) 
		{
			setChanged();
			notifyObservers(e.getMessage());
		}
		catch (IOException e) 
		{
			setChanged();
			notifyObservers(e.getMessage());
		}

		setChanged();
		notifyObservers("server is close\nbye bye");
	}
}
