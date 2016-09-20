package algorithms.mazeGenerators;

public class SimpleMaze3dGenerator extends Generator
{

	Maze3d maze;
	Position move;

	@Override

	public Maze3d generate(Position p)
	{
		Position newp=new Position(p.getX()*2+1, p.getY()*2+1, p.getZ()*2+1);
		Maze3d maze=new Maze3d(newp);
		maze.initMaze(); //all cells in the maze is 1
		maze.GetRandomStartPosition();
		Position start=maze.getStartPosition();
		maze.setCell(start.getX(), start.getY(), start.getZ(), 0);


		maze.GetRandomGoalPosition();
		Position goal=maze.getGoalPosition();
		maze.setCell(goal.getX(), goal.getY(), goal.getZ(), 0);
		Position move= new Position(start.getX(), start.getY(), start.getZ());
		String[] PlayerMoves;

		while(!(move.equals(goal)))
		{
			PlayerMoves= maze.isLegal(move); //all possibale moves in the maze
			maze.RandomMove(PlayerMoves,move);
			maze.setCell(move.getX(), move.getY(), move.getZ(), 0);
			maze.BreakWall(move);

		}
		return maze;


	}

}
