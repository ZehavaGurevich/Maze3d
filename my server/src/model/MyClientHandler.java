package model;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;
import java.util.zip.GZIPOutputStream;

import algorithms.demo.MazeDomain;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.BFSSearcher;
import algorithms.search.DFSsearcher;
import algorithms.search.SearchSolution;
import algorithms.search.Searcher;


// TODO: Auto-generated Javadoc
/**
 * The Class MyClientHandler.
 */
public class MyClientHandler implements ClientHandler
{
	
	/** The solutions. */
	private HashMap<Maze3d, SearchSolution<Position>> solutions;

	/**
	 * Instantiates a new my client handler.
	 */
	public MyClientHandler()
	{
		this.solutions=new HashMap<Maze3d,SearchSolution<Position>>();
		this.loadSolutionZip();
	}


	/* (non-Javadoc)
	 * @see model.ClientHandler#handleClient(java.io.InputStream, java.io.OutputStream)
	 */
	@SuppressWarnings({ "unchecked", "unused" })
	@Override
	public void handleClient(InputStream inFromClient, OutputStream outToClient) 
	{
		try
		{
			BufferedReader in = new BufferedReader(new InputStreamReader(inFromClient));
			PrintWriter out = new PrintWriter(outToClient);
			String line;

			if((line=in.readLine()).equals("hi"))
			{
				out.println("ok");
				out.flush();

				ObjectInputStream packetFromClient = new ObjectInputStream(inFromClient);
				ArrayList<Object> request = (ArrayList<Object>)packetFromClient.readObject();
				String alg = (String)request.get(0);
				Maze3d maze = new Maze3d((byte[])request.get(1));


				ObjectOutputStream solutionToClient = new ObjectOutputStream(outToClient);

				MazeDomain ms=new MazeDomain(maze);
				SearchSolution<Position> sol;

				if(solutions.containsKey(maze))
				{
					sol=solutions.get(maze);
				}

				if(alg.equals("BFS"))
				{
					Searcher<Position> bfs=new BFSSearcher<Position>();
					sol=bfs.Search(ms);
				}

				else
				{
					Searcher<Position> dfs=new DFSsearcher<Position>();
					sol=dfs.Search(ms);
				}

				solutions.put(maze, sol);


				solutionToClient.writeObject(sol);
				solutionToClient.flush();

				packetFromClient.close();
				solutionToClient.close();
			}				

			in.close();
			out.close();
		}

		catch(IOException e)
		{
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		} 
	}



	/* (non-Javadoc)
	 * @see model.ClientHandler#close()
	 */
	public void close()
	{
		saveSolutionZip();
	}


	/**
	 * Save solution zip.
	 */
	public void saveSolutionZip()
	{
		if(solutions.isEmpty())
		{
			System.out.println("No solution for saving\n");
			return;
		}
		try {
			ObjectOutputStream out=new ObjectOutputStream(new GZIPOutputStream(new FileOutputStream("solutoinsZ.zip")));
			out.writeObject(solutions);
			out.close();

		} catch (FileNotFoundException e) 
		{
			System.out.println("file not found\n");

		} catch (IOException e)
		{
			System.out.println("save solution to zip is faild");
		}

	}

	/**
	 * Load solution zip.
	 */
	@SuppressWarnings("unchecked")
	public void loadSolutionZip()
	{
		try {
			ObjectInputStream in= new ObjectInputStream(new GZIPInputStream(new FileInputStream("solutoinsZ.zip")));
			solutions=((HashMap<Maze3d, SearchSolution<Position>>) in.readObject());
			in.close();
		} catch (FileNotFoundException e) 
		{
			System.out.println("file not found\n");

		} catch (IOException |ClassNotFoundException e) 
		{
			System.out.println(e.getMessage());
		}	

	}
}
