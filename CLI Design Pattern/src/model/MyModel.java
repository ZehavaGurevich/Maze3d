package model;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import algorithms.demo.MazeDomain;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.StackPop;
import algorithms.search.BFSsearcher;
import algorithms.search.DFSsearcher;
import algorithms.search.SearchSolution;
import algorithms.search.Searcheble;
import algorithms.search.Searcher;
import controller.Controller;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;


// TODO: Auto-generated Javadoc
/**
 * The Class MyModel.
 */
public class MyModel implements Model
{
	
	/** The my controller. */
	private Controller myController;
	
	/** The my mazes. */
	private HashMap<String, Maze3d> myMazes;
	
	/** The solutions. */
	private HashMap<String, SearchSolution<Position>> solutions;
	
	/** The threads. */
	private ArrayList<Thread> threads;
	
	/** The files. */
	private ArrayList<File> files;
	
	/** The string builder. */
	private StringBuilder stringBuilder; 
	
	/** The thread pool. */
	ExecutorService threadPool;
	
	
	/**
	 * Instantiates a new my model.
	 */
	public MyModel()
	{
		this.myMazes=new HashMap<String,Maze3d>();
		this.solutions=new HashMap<String,SearchSolution<Position>>();
		this.threads=new ArrayList<Thread>();
		this.files=new ArrayList<File>();
		this.stringBuilder=new StringBuilder();
		
		threadPool = Executors.newFixedThreadPool(10);
	}
	
	

	
	

	/* (non-Javadoc)
	 * @see model.Model#ModelDir(java.lang.String)
	 */
	@Override
	public void ModelDir(String path)
	{
		File file=new File(path.toString());
		if(file.isFile())
			myController.ControllerDisplayMessage(file.toString());
			

		else{
				for (File f : file.listFiles())
				{
					myController.ControllerDisplayMessage(f.toString());
				}
		
			}
	}

	/* (non-Javadoc)
	 * @see model.Model#ModelGenerateMaze3d(java.lang.String, int, int, int)
	 */
	@Override
	public void ModelGenerateMaze3d(String mazeName, int x, int y, int z)
	{
		Thread thread=new Thread(new Runnable() {

			@Override
			public void run() 
			{
				if(myMazes.containsKey(mazeName))
				{
					myController.ControllerDisplayMessage("Name already exist\n");
					return ;
				}
				else
				{
				Position p=new Position(x,y,z);
				Maze3d maze=new Maze3d(p);

				maze=new GrowingTreeGenerator(new StackPop()).generate(p);

				myMazes.put(mazeName, maze);
				myController.ControllerDisplayMessage("maze "+ mazeName+ " is ready\n");
				}
			}
		});
		thread.start();
		threads.add(thread);
	  
	         
	           

	   
		
		

	}
	
	
	/* (non-Javadoc)
	 * @see model.Model#ModelDisplay(java.lang.String)
	 */
	@Override
	public void ModelDisplay(String mazeName)
	{
		if(!myMazes.containsKey(mazeName))
		{
			myController.ControllerDisplayMessage("There is no maze with this name\n");

		}	
		else{
			Maze3d maze= myMazes.get(mazeName);
			maze.printMaze3d();
		

		}
	}

	

	/* (non-Javadoc)
	 * @see model.Model#ModelDisplayCrossSectionBy(java.lang.String, int, java.lang.String)
	 */
	@Override
	public void ModelDisplayCrossSectionBy(String XYZ,int index, String mazeName) 
	{
		Maze3d maze3d = myMazes.get(mazeName);
		int[][] maze2d=null;
		if (maze3d==null){
			myController.ControllerDisplayMessage("Maze " + mazeName + " is not exist");
			return;
		}
		try{
			switch (XYZ){
			case "x":
			case "X": 
				maze2d = maze3d.getCrossSectionByX(index);
				break;
			case "y":
			case "Y":
				maze2d = maze3d.getCrossSectionByY(index);
				break;
			case "z":
			case "Z":
				maze2d = maze3d.getCrossSectionByZ(index);
				break;
			default: this.	myController.ControllerDisplayMessage("Invalid input!");
			}
		}catch (IndexOutOfBoundsException e)
		{
			myController.ControllerDisplayMessage("Invalid Index!");
			return;
		}
		
		String maze2dPrint = "";
		for (int i=0;i<maze2d.length;i++)
		{
			for(int j=0;j<maze2d[i].length;j++)
			{
				maze2dPrint = maze2dPrint + String.valueOf(maze2d[i][j]+ " ");
			}
			maze2dPrint = maze2dPrint + "\n";
		}
		myController.ControllerDisplayMessage(maze2dPrint);
	}

	/* (non-Javadoc)
	 * @see model.Model#ModelSaveMaze(java.lang.String, java.lang.String)
	 */
	@Override
	public void ModelSaveMaze(String mazeName, String fileName)
	{
		if(!myMazes.containsKey(mazeName)){
			myController.ControllerDisplayMessage("There is no such maze in this name\n");

		}else{
			try{
				Maze3d maze=myMazes.get(mazeName);
				OutputStream out= new MyCompressorOutputStream(new FileOutputStream(fileName));
				out.write(maze.toByteArray());
				out.close();
				myController.ControllerDisplayMessage("The maze " + mazeName+ " is saved successfully in the file " + fileName+"\n");
			}
			catch (FileNotFoundException e) {
				this.myController.ControllerDisplayMessage("The " + fileName + " isn't exist\n");
			}
			catch (IOException e) {

				this.myController.ControllerDisplayMessage("This maze isn't save\n");
			}
		}

	}

	

	/* (non-Javadoc)
	 * @see model.Model#ModeLoadMaze(java.lang.String, java.lang.String)
	 */
	@Override
	public void ModeLoadMaze(String mazeName, String fileName) throws IOException
	{
		try {
			InputStream in = new MyDecompressorInputStream(new FileInputStream(fileName));
			byte[] bArr = new byte[50000];
			//Reads some number of bytes from the input stream and stores them into the buffer array bArr
			int numByte = in.read(bArr);
			
			in.close();
			byte[] newbArr = new byte[numByte];
			for (int i = 0; i < newbArr.length; i++)
			{
				newbArr[i] = bArr[i];
			}
			Maze3d maze3d = new Maze3d(bArr);
			myMazes.put(mazeName, maze3d);
			myController.ControllerDisplayMessage("Maze " + mazeName + " is loaded from " + fileName + " file\n");
			in.close();
		} 
		catch (FileNotFoundException e)
		{
			myController.ControllerDisplayMessage("The file " + fileName + " is not found\n");
		} catch (IOException e)
		{
			myController.ControllerDisplayMessage("Error with the new maze\n");
		}
	}

	/* (non-Javadoc)
	 * @see model.Model#ModelMzeSize(java.lang.String)
	 */
	@Override
	public void ModelMzeSize(String mazeName) 
	{
		if(!myMazes.containsKey(mazeName))
		{
			myController.ControllerDisplayMessage("There such no maze like that");

		}

		else{
			Maze3d maze=myMazes.get(mazeName);
			int sizeX=maze.getP().getX()*4;
			int sizeY=maze.getP().getY()*4;
			int sizeZ=maze.getP().getZ()*4;
			int size=sizeX+sizeY+sizeZ+36;
			myController.ControllerDisplayMessage("The maze " + mazeName+ " the size is "+ size+"\n");

		}

	}

	

	/* (non-Javadoc)
	 * @see model.Model#ModelFileSize(java.lang.String)
	 */
	@Override
	public void ModelFileSize(String fileName)
	{
		Maze3d maze=myMazes.get(fileName);
		if(maze==null)
			myController.ControllerDisplayMessage("There in no maze with these name");

		else
		{

			ByteArrayOutputStream byteArrayOut = new ByteArrayOutputStream();
			MyCompressorOutputStream compressorOut = new MyCompressorOutputStream(byteArrayOut);
			try {
				compressorOut.write(maze.toByteArray());
				compressorOut.close();
				myController.ControllerDisplayMessage("The size of " + fileName + " maze in the file is: " + byteArrayOut.size()+"\n");
			} catch (IOException e) 
			{
				myController.ControllerDisplayMessage("Could not write the " + fileName + " maze to a file");
			}
		}


	}
	
	

	/* (non-Javadoc)
	 * @see model.Model#ModelSolve(java.lang.String, java.lang.String)
	 */
	@Override
	public void ModelSolve(String mazeName, String algorithm) 
	{
		Thread thread=new Thread(new Runnable()
		{

			@Override
			public void run() 
			{
				Maze3d maze=myMazes.get(mazeName);
				Searcheble<Position> algoSearch= new MazeDomain(maze);
			
				if(maze==null)
				{
					myController.ControllerDisplayMessage("There is no maze with this name");
				}

				else{
					
					Searcher<Position> myAlgo;
					

					switch(algorithm)
					{
					case "BFS":
						myAlgo = new BFSsearcher<Position>();
						break;

					case "DFS":
						myAlgo=new DFSsearcher<Position>();
						break;

					default:
						myController.ControllerDisplayMessage("There is no such algorithms");
						return;

					}

					myController.ControllerDisplayMessage("The solutin for maze "+ mazeName+ " is ready\n");
					solutions.put(mazeName, myAlgo.Search(algoSearch));

				}

			}
		});thread.start();
		threads.add(thread);

	}


	/* (non-Javadoc)
	 * @see model.Model#ModelDisplaySolution(java.lang.String)
	 */
	@Override
	public void ModelDisplaySolution(String mazeName) 
	{
		// get solution from hashMap by key (name).
		SearchSolution<Position> mySolution = solutions.get(mazeName);
		if (mySolution == null)
			myController.ControllerDisplayMessage("Solution for " + mazeName + " is not exist");
		else {
			// temp arrayList to get the solution.
			ArrayList<Position> arraySolution = mySolution.getSolution();
			// concat all solution steps.
			for (int i = 0; i < arraySolution.size(); i++)
				this.stringBuilder.append(arraySolution.get(i));
			// print solution
			myController.ControllerDisplayMessage("The solution for maze " + mazeName + " is:\n" + stringBuilder.toString()+"\n");
		}
	}


	/* (non-Javadoc)
	 * @see model.Model#ModelExit()
	 */
	@Override
	public void ModelExit()
	{
		for(int i=0;i<this.threads.size();i++){
			if(this.threads.get(i)!=null){
				//ask about remove.
				this.threads.remove(i);
			}

		}

		for(int j=0;j<this.files.size();j++){
			if(this.files.get(j)!=null){
				this.files.remove(j);

			}
		}

	}
	


	/**
	 * Gets the my controller.
	 *
	 * @return the my controller
	 */
	public Controller getMyController()
	{
		return myController;
	}


	/**
	 * Sets the my controller.
	 *
	 * @param myController the new my controller
	 */
	public void setMyController(Controller myController)
	{
		this.myController = myController;
	}


	/**
	 * Gets the my mazes.
	 *
	 * @return the my mazes
	 */
	public HashMap<String, Maze3d> getMyMazes()
	{
		return myMazes;
	}


	/**
	 * Sets the my mazes.
	 *
	 * @param myMazes the my mazes
	 */
	public void setMyMazes(HashMap<String, Maze3d> myMazes) 
	{
		this.myMazes = myMazes;
	}


	/**
	 * Gets the solutions.
	 *
	 * @return the solutions
	 */
	public HashMap<String, SearchSolution<Position>> getSolutions()
	{
		return solutions;
	}


	/**
	 * Sets the solutions.
	 *
	 * @param solutions the solutions
	 */
	public void setSolutions(HashMap<String, SearchSolution<Position>> solutions)
	{
		this.solutions = solutions;
	}


	/**
	 * Gets the threads.
	 *
	 * @return the threads
	 */
	public ArrayList<Thread> getThreads()
	{
		return threads;
	}


	/**
	 * Sets the threads.
	 *
	 * @param threads the new threads
	 */
	public void setThreads(ArrayList<Thread> threads) 
	{
		this.threads = threads;
	}


	/**
	 * Gets the files.
	 *
	 * @return the files
	 */
	public ArrayList<File> getFiles() 
	{
		return files;
	}


	/**
	 * Sets the files.
	 *
	 * @param files the new files
	 */
	public void setFiles(ArrayList<File> files)
	{
		this.files = files;
	}


	

}
