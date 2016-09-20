package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;
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
import algorithms.search.Solution;
import io.MyCompressorOutputStream;
import io.MyDecompressorInputStream;
import presenter.Command;

public class MyModel extends Observable implements Model 
{

	Maze3d maze;

	private HashMap<String, Maze3d> myMazes;

	private HashMap<String, SearchSolution<Position>> solutions;

	private ArrayList<Thread> threads;

	private ArrayList<File> files;

	private StringBuilder stringBuilder; 

	ExecutorService threadPool;


	public MyModel()
	{
		this.myMazes=new HashMap<String,Maze3d>();
		this.solutions=new HashMap<String,SearchSolution<Position>>();
		this.threads=new ArrayList<Thread>();
		this.files=new ArrayList<File>();
		this.stringBuilder=new StringBuilder();

		threadPool = Executors.newFixedThreadPool(10);
	}



	@Override
	public void ModelDir(String path) {
		// TODO Auto-generated method stub

	}


	@Override
	public void ModelGenerateMaze3d(String mazeName, int x, int y, int z)
	{
		Thread thread=new Thread(new Runnable() {

			@Override
			public void run() 
			{
				if(myMazes.containsKey(mazeName))
				{
					setChanged();
					notifyObservers("Name already exist\n");
					return ;
				}
				else
				{
					Position p=new Position(x,y,z);
					Maze3d maze=new Maze3d(p);

					maze=new GrowingTreeGenerator(new StackPop()).generate(p);

					myMazes.put(mazeName, maze);
					setChanged();
					notifyObservers("maze "+ mazeName+ " is ready\n");
				}
			}
		});
		thread.start();
		threads.add(thread);


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
			maze.printMaze3d();


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
	public void ModelMzeSize(String mazeName) {
		// TODO Auto-generated method stub

	}


	@Override
	public void ModelFileSize(String fileName) {
		// TODO Auto-generated method stub

	}


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
					setChanged();
					notifyObservers("There is no maze with this name");
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
						setChanged();
						notifyObservers("There is no such algorithms");
						return;

					}

					setChanged();
					notifyObservers("The solutin for maze "+ mazeName+ " is ready\n");
					solutions.put(mazeName, myAlgo.Search(algoSearch));

				}

			}
		});thread.start();

		threads.add(thread);

	}




	@Override
	public void ModelDisplaySolution(String mazeName)
	{
		// get solution from hashMap by key (name).
		SearchSolution<Position> mySolution = solutions.get(mazeName);
		if (mySolution == null)
		{
			setChanged();
		notifyObservers("Solution for " + mazeName + " is not exist");
		}
		else {
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

}

