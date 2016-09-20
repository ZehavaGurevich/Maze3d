package algorithms.demo;

import java.util.ArrayList;

import  algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.CommonSearcheble;
import algorithms.search.State;


public class MazeDomain extends CommonSearcheble<Position> {

	Maze3d maze;

	public MazeDomain(Maze3d maze) {
		super();
		this.maze=maze;
	}


	/* (non-Javadoc)
	 * @see algorithms.search.CommonSearchable#getStartState()
	 */
	@Override
	public State<Position> getStartState() {
		State<Position> start=new State<Position>(maze.getStartPosition());
		start.setCost(0);
		return start;
	}

	@Override
	public State<Position> getGoalState() {
		State<Position> goal=new State<Position>(maze.getGoalPosition());
		goal.setCost(1);
		return goal;
	}


	@Override
	public ArrayList<State<Position>> getAllPossibleStates(State<Position> s) {

		ArrayList<State<Position>> sol=new ArrayList<State<Position>>();
		Position p=new Position(s.getState().getX(),s.getState().getY(),s.getState().getZ());
		String[] arr=maze.getPossibleMoves(p);
		for(int i=0;i<arr.length;i++){
			switch(arr[i]){
			case "Up":
				Position up=new Position(p.getX()+2,p.getY(),p.getZ());
				State<Position> sup=new State<Position>(up);
				sol.add(sup);
				break;
			case "Down":
				Position down=new Position(p.getX()-2,p.getY(),p.getZ());
				State<Position> sdown=new State<Position>(down);
				sol.add(sdown);
			break;
			case "Forward":
				Position forw=new Position(p.getX(),p.getY()+2,p.getZ());
				State<Position> sfor=new State<Position>(forw);
				sol.add(sfor);
			break;
			case "Backward":
				Position back=new Position(p.getX(),p.getY()-2,p.getZ());
				State<Position> sback=new State<Position>(back);
				sol.add(sback);
			break;
			case "Right":
				Position right=new Position(p.getX(),p.getY(),p.getZ()+2);
				State<Position> sright=new State<Position>(right);
				sol.add(sright);
			break;
			case "Left":
				Position left=new Position(p.getX(),p.getY(),p.getZ()-2);
				State<Position> sleft=new State<Position>(left);
				sol.add(sleft);
			break;

		}

	}
	return sol;
}


	}









	


	


