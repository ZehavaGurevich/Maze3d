package algorithms.demo;

import java.util.ArrayList;

import algorithms.mazeGenerators.GrowingTreeGenerator;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.mazeGenerators.StackPop;
import algorithms.search.BFSsearcher;
import algorithms.search.CommonSearcher;
import algorithms.search.DFSsearcher;

public class Demo {

	static Maze3d maze;
	
	/**
	 * Run.
	 */
	public void Run(){
	        Position p= new Position(5,5,5);
			 maze=new GrowingTreeGenerator(new StackPop()).generate(p);
	       // maze=new SimpleMaze3dGenerator().generate(p);
			System.out.println(maze);
			System.out.println(maze.getStartPosition());
			System.out.println(maze.getGoalPosition());
		    MazeDomain mazedomian=new MazeDomain(maze);
			CommonSearcher<Position> searcher;
			ArrayList<Position> solution;
			System.out.println("------------BFS TEST:----------------");
			searcher = new BFSsearcher<Position>();
			solution = searcher.Search(mazedomian).getSolution();
			System.out.println("Solution path:" + solution);
			System.out.println("number of nodes evaluated:" + searcher.getNumberOfNodsEvaluated());
			System.out.println("-------------------------------------- \n");
			System.out.println("------------DFS TEST:----------------");
			searcher = new DFSsearcher<Position>();
            solution=searcher.Search(mazedomian).getSolution();
			System.out.println("Solution path:" + solution);
			System.out.println("number of nodes evaluated:" + searcher.getNumberOfNodsEvaluated());
			System.out.println("-------------------------------------- \n");
		
		
	}
	
	public static void main(String[] args) {
		Demo demo = new Demo();
		demo.Run();
	}
}
