package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.demo.MazeDomain;
import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.SimpleMaze3dGenerator;
import algorithms.mazeGenerators.StackPop;
import algorithms.search.BFSsearcher;
import algorithms.search.DFSsearcher;
import algorithms.search.SearchSolution;
import algorithms.search.Searcheble;
import algorithms.search.Searcher;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import presenter.Properties;

public class MyModel extends Observable implements Model 
{


	private HashMap<String, Maze3d> myMazes;

	private HashMap<Maze3d, SearchSolution<Position>> solutions;



	//private ArrayList<Thread> threads;

	private ArrayList<File> files;

	private StringBuilder stringBuilder; 

	ExecutorService threadPool;



	public MyModel()
	{
		this.myMazes=new HashMap<String,Maze3d>();
		this.solutions=new HashMap<Maze3d,SearchSolution<Position>>();
		this.loadSolutionZip();
		//this.threads=new ArrayList<Thread>();
		this.files=new ArrayList<File>();
		this.stringBuilder=new StringBuilder();
		//threadPool=Executors.newFixedThreadPool(Properties.getNumOfThreads());
		threadPool = Executors.newFixedThreadPool(10);
	}

	public void saveSolutionZip()
	{
		if(solutions.isEmpty())
		{
			System.out.println("No solution for saving\n");
			return;
		}
		try {
			ObjectOutputStream out=new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("solutoins.zip")));
			out.writeObject(solutions);
			out.close();

		} catch (FileNotFoundException e) 
		{
			System.out.println("file not found\n");

		} catch (IOException e)
		{
			System.out.println(e.getMessage());
			System.out.println("save solution to zip is faild");

		}


	}
	
	@SuppressWarnings("unchecked")
	public void loadSolutionZip()
	{
		try {
			ObjectInputStream in= new ObjectInputStream(new GZIPInputStream(new FileInputStream("solutoins.zip")));
			solutions=((HashMap<Maze3d, SearchSolution<Position>>) in.readObject());
			in.close();
		} catch (FileNotFoundException e) 
		{
			System.out.println("file not found\n");
		
		} catch (IOException |ClassNotFoundException e) 
		{
		System.out.println(e.getMessage());
			//System.out.println("load solution from zip is faild");
		}	
		
	}
	@Override
	public void ModelDir(String path) {}


	@Override
	public void ModelGenerateMaze3d(String mazeName, int x, int y, int z)
	{
		if(myMazes.containsKey(mazeName))
		{
			setChanged();
			notifyObservers("Name already exist\n");
			return;
		}

		Future<Maze3d> fMaze = threadPool.submit(new Callable<Maze3d>()
		{
			@Override
			public Maze3d call() throws Exception {

				Position p=new Position(x,y,z);
				Maze3d maze=new Maze3d(p);

				//maze=new GrowingTreeGenerator(new StackPop()).generate(p);
				maze=new SimpleMaze3dGenerator().generate(p);

				return maze;
			}
		});


		try
		{
			myMazes.put(mazeName, fMaze.get());
		} 
		catch (InterruptedException | ExecutionException e)
		{
			setChanged();
			notifyObservers(e.getMessage());				
		}
		setChanged();
		notifyObservers("maze is ready");
	}



	@Override
	public void ModelDisplay(String mazeName)
	{
		if(!myMazes.containsKey(mazeName))
		{
			setChanged();
			notifyObservers("There is no maze with this name\n");

		}	
		else{
			Maze3d maze= myMazes.get(mazeName);
			setChanged();
			notifyObservers(maze.toByteArray());
		}

	}


	@Override
	public void ModelDisplayCrossSectionBy(String XYZ, int index, String mazeName)
	{
		Maze3d maze3d = myMazes.get(mazeName);
		int[][] maze2d=null;
		if (maze3d==null)
		{
			setChanged();
			notifyObservers("Maze " + mazeName + " is not exist");
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
			default:
				setChanged();
				notifyObservers("Invalid input!");
			}
		}catch (IndexOutOfBoundsException e)
		{
			setChanged();
			notifyObservers("Invalid Index!");
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
		setChanged();
		notifyObservers(maze2dPrint);
	}



	@Override
	public void ModelSaveMaze(String mazeName, String fileName)
	{
		if(!myMazes.containsKey(mazeName))
		{
			setChanged();
			notifyObservers("There is no such maze in this name\n");

		}else{
			try{
				Maze3d maze=myMazes.get(mazeName);
				OutputStream out= new MyCompressorOutputStream(new FileOutputStream(fileName));
				out.write(maze.toByteArray());
				out.close();
				setChanged();
				notifyObservers("The maze " + mazeName+ " is saved successfully in the file " + fileName+"\n");
			}
			catch (FileNotFoundException e) {
				setChanged();
				notifyObservers("The " + fileName + " isn't exist\n");
			}
			catch (IOException e) {

				setChanged();
				notifyObservers("This maze isn't save\n");
			}
		}


	}


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
			setChanged();
			notifyObservers("Maze " + mazeName + " is loaded from " + fileName + " file\n");
			in.close();
		} 
		catch (FileNotFoundException e)
		{
			setChanged();
			notifyObservers("The file " + fileName + " is not found\n");
		} catch (IOException e)
		{
			setChanged();
			notifyObservers("Error with the new maze\n");
		}
	}



	@Override
	public void ModelSolve(String mazeName, String algorithm) 
	{
		if(!myMazes.containsKey(mazeName))
		{
			setChanged();
			notifyObservers("There is no maze with this name");
			return;
		}

		//check if solution is already exist
		if(solutions.containsKey(myMazes.get(mazeName)))
		{
			setChanged();
			notifyObservers("The solutin for maze "+ mazeName+ " is ready!!!!\n");
			return ;
		}

		Future<SearchSolution<Position>> fSolution = threadPool.submit(new Callable<SearchSolution<Position>>()
		{
			@Override
			public SearchSolution<Position> call() throws Exception
			{
				Maze3d maze=myMazes.get(mazeName);
				Searcheble<Position> algoSearch= new MazeDomain(maze);

				Searcher<Position> myAlgo;
				SearchSolution<Position> sol;


				switch(algorithm)
				{
				case "BFS":
					myAlgo = new BFSsearcher<Position>();
					break;

				case "DFS":
					myAlgo=new DFSsearcher<Position>();
					break;

				default:
					setChanged();
					notifyObservers("There is no such algorithms");
					return null;

				}
				sol=myAlgo.Search(algoSearch);
				return sol;
			}
		});

		try
		{
			solutions.put(myMazes.get(mazeName), fSolution.get());
		} 
		catch (InterruptedException | ExecutionException e)
		{
			setChanged();
			notifyObservers(e.getMessage());
		}

		setChanged();
		notifyObservers("The solutin for maze "+ mazeName+ " is ready\n");
	}




	@Override
	public void ModelDisplaySolution(String mazeName)
	{
		// get solution from hashMap by key (name).
		if (!solutions.containsKey(myMazes.get(mazeName)))
		{
			setChanged();
			notifyObservers("Solution for " + mazeName + " is not exist");
		}
		else{
			SearchSolution<Position> mySolution = solutions.get(myMazes.get(mazeName));
			// temp arrayList to get the solution.
			ArrayList<Position> arraySolution = mySolution.getSolution();
			// concat all solution steps.
			for (int i = 0; i < arraySolution.size(); i++)
				this.stringBuilder.append(arraySolution.get(i));
			// print solution
			setChanged();
			notifyObservers("The solution for maze " + mazeName + " is:\n" + stringBuilder.toString()+"\n");
		}

	}


	@Override
	public void ModelExit() 
	{

		threadPool.shutdown();
		saveSolutionZip();
		try
		{
		while(!(threadPool.awaitTermination(10, TimeUnit.SECONDS)));
		}

		catch (InterruptedException e)
		{
		System.out.println("exit failed");
		}



	}
	
	//!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
	//This function is checking if we can move up.
		@Override
		public void m_moveUp() {
			Maze3d maze=this.m_currentMaze.getCurrentMaze();
			Position p=this.m_currentMaze.getCurrentPosition();
			String [] moves=maze.getPossibleMoves(p);
			for(String move:moves){
				if(move.equals("Up")){
					Position temp=new Position(p.getX()+2,p.getY(),p.getZ());
					this.m_currentMaze.setCurrentPosition(temp);
					setChanged();
					notifyObservers("move");
				}
			}
			
		}
		//!!!!!!!!!!!!!!!!!!!!!!1



	@Override
	public ArrayList<Position> getMazeSolution(String mazeName) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public Object getUserCommand(String command) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void saveToZip() {
		// TODO Auto-generated method stub

	}



	@Override
	public void loadFromZip() {
		// TODO Auto-generated method stub

	}



	@Override
	public Position getPositionFromHash(String mazeName) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void setProperties(Properties properties) {
		// TODO Auto-generated method stub

	}



	@Override
	public Properties getProperties() {

		return null;
	}

}

