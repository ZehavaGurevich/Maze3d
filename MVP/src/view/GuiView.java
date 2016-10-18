package view;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Observable;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.KeyListener;
import org.eclipse.swt.widgets.Event;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Listener;
import org.eclipse.swt.widgets.MessageBox;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.SearchSolution;
import algorithms.search.Solution;
import algorithms.search.State;
import boot.Run;
import presenter.Properties;

public class GuiView extends Observable implements View
{
	String message;
	HashMap<String,Listener> listenerCollection;
	MazeWindow mainWindow;
	GenericWindow genericWindow;
	boolean isSolve;
	private Thread thread;
	private Position characterPlace;
	private Maze3d maze;
	KeyListener canvasArrowKeyListener;

	private String path;
	private Boolean isSolvingNow;

	/**
	 * constructor 
	 */
	public GuiView() 
	{
		listenerCollection=new HashMap<String,Listener>();
		message=new String();
		initListeners();
		this.mainWindow=new MazeWindow("Minion Maze",600,400,listenerCollection,canvasArrowKeyListener);
		isSolve=false;
		this.isSolvingNow=false;

	}



	/**
	 * starting the main window
	 */
	@Override
	public void ViewStart() {
		mainWindow.run();

	}
	/**
	 * Initialize the listeners that will be sent to the MazeWindow
	 */
	public void initListeners()
	{
		listenerCollection.put("exit",new Listener() 
		{
			public void handleEvent(Event event) 
			{
				MessageBox messageBox = new MessageBox(mainWindow.getShell(),  SWT.ICON_QUESTION| SWT.YES | SWT.NO);
				messageBox.setMessage("Do you really want to exit?");
				messageBox.setText("Exiting Application");
				if(messageBox.open()==SWT.YES)
				{
					message="exit";
					setChanged();
					notifyObservers();
					event.doit=true;

				}
				else
				{
					event.doit=false;
				}


			}
		}); 

		listenerCollection.put("file dialog",new Listener() 
		{
			public void handleEvent(Event event) 
			{
				FileDialog fd=new FileDialog(mainWindow.getShell(),SWT.OPEN);
				fd.setText("xml loader");
				fd.setFilterPath("./");
				String[] filterExt = {  "*.xml" };
				fd.setFilterExtensions(filterExt);
				path = fd.open();
				message="load xml "+ path;
				setChanged();
				notifyObservers();

			}
		}); 
		listenerCollection.put("start", new Listener() {

			@Override
			public void handleEvent(Event arg0) {

				genericWindow=new GenericWindow(200,200,listenerCollection,new Properties());
				genericWindow.run();
				Properties mp=(Properties)genericWindow.getObj();
				if(mp==null)
				{
					return;
				}
				//String name=mp.getMazeName();
				int x=mp.getX();
				int y=mp.getY();
				int z=mp.getZ();

				String s="generateMaze3d-m "+x+" "+y+" "+z;
				setChanged();
				notifyObservers(s);
			}
		});
		listenerCollection.put("solve", new Listener() {

			@Override
			public void handleEvent(Event arg0) 
			{
				isSolvingNow=true;
				int x=characterPlace.getX();
				int y=characterPlace.getY();
				int z=characterPlace.getZ();
				message="solve from m "+x+" "+y+" "+z;

				isSolve=true;
				setChanged();
				notifyObservers();

			}
		});
		listenerCollection.put("hint", new Listener() {

			@Override
			public void handleEvent(Event arg0) 
			{

				int x=characterPlace.getX();
				int y=characterPlace.getY();
				int z=characterPlace.getZ();
				message="solve from m "+x+" "+y+" "+z;
				isSolve=false;
				setChanged();
				notifyObservers();
			}
		});
		listenerCollection.put("reset", new Listener() {

			@Override
			public void handleEvent(Event arg0) 
			{
				characterPlace.setX(maze.getStartPosition().getX());
				characterPlace.setY( maze.getStartPosition().getY());
				characterPlace.setZ(maze.getStartPosition().getZ());

				mainWindow.moveCharacterInCanvas(maze.getStartPosition().getX(), maze.getStartPosition().getY(), maze.getStartPosition().getZ(),canBeMovedUp(),canBeMovedDown(),false,isSolvingNow);

			}
		});

		canvasArrowKeyListener=new KeyListener() {

			@Override
			public void keyReleased(KeyEvent arg) 
			{
				if(isSolvingNow)
				{
					return;
				}
				int x=characterPlace.getX();
				int y=characterPlace.getY();
				int z=characterPlace.getZ();
				int[][][] mazeArr=maze.getMaze();
				
				
			
				if((arg.keyCode==SWT.ARROW_RIGHT)||(arg.keyCode==SWT.KEYPAD_6))
				{

					if((z<mazeArr[0][0].length-1)&&(mazeArr[x][y][z+1]==0))
					{
						characterPlace.setX(x);
						characterPlace.setY(y);
						characterPlace.setZ(z+1);
						mainWindow.moveCharacterInCanvas(x, y, z+1,canBeMovedUp(),canBeMovedDown(),false,isSolvingNow);
					}
				}
				else if((arg.keyCode==SWT.ARROW_LEFT)||(arg.keyCode==SWT.KEYPAD_4))
				{
					if((z>0)&&(mazeArr[x][y][z-1]==0))
					{


						characterPlace.setX(x);
						characterPlace.setY(y);
						characterPlace.setZ(z-1);
						mainWindow.moveCharacterInCanvas(x, y, z-1,canBeMovedUp(),canBeMovedDown(),false,isSolvingNow);
					}
				}
				else if((arg.keyCode==SWT.ARROW_UP)||(arg.keyCode==SWT.KEYPAD_8))
				{

					if((y>0)&&(mazeArr[x][y-1][z]==0))
					{
						characterPlace.setX(x);
						characterPlace.setY(y-1);
						characterPlace.setZ(z);
						mainWindow.moveCharacterInCanvas(x, y-1, z,canBeMovedUp(),canBeMovedDown(),false,isSolvingNow);
					}

				}
				else if((arg.keyCode==SWT.ARROW_DOWN)||(arg.keyCode==SWT.KEYPAD_2))
				{
					if((y<mazeArr[0].length-1)&&(mazeArr[x][y+1][z]==0))
					{
						characterPlace.setX(x);
						characterPlace.setY(y+1);
						characterPlace.setZ(z);
						mainWindow.moveCharacterInCanvas(x, y+1, z,canBeMovedUp(),canBeMovedDown(),false,isSolvingNow);
					}
				}

				else if(arg.keyCode==SWT.PAGE_DOWN)
				{
					System.out.println("down");
					if(x-2>=0 && (mazeArr[x-2][y][z]==1))
						//if((x>0)&&(mazeArr[x-1][y][z]==0))
					{
						maze.setCell(x-1,y,z,0);
						maze.setCell(x-2,y,z,0);



						System.out.println("down");


						characterPlace.setX(x-2);
						characterPlace.setY(y);
						characterPlace.setZ(z);

						mainWindow.moveCharacterInCanvas(x-2, y, z,canBeMovedUp(),canBeMovedDown(),false,isSolvingNow);
					}
				}
				else if(arg.keyCode==SWT.PAGE_UP)
				{
					if((x<mazeArr.length-1)&&(mazeArr[x+1][y][z]==0))
					{
						characterPlace.setX(x+2);
						characterPlace.setY(y);
						characterPlace.setZ(z);
						mainWindow.moveCharacterInCanvas(x+2, y, z,canBeMovedUp(),canBeMovedDown(),false,isSolvingNow);
					}
				}

			}

			@Override
			public void keyPressed(KeyEvent arg0) {}
		};
	}
	/**
	 * checking if in the current position of the character,it can move down in the floors
	 * @return true-if can move down,false-if can't
	 */
	public boolean canBeMovedDown()
	{
		int x=characterPlace.getX();
		int y=characterPlace.getY();
		int z=characterPlace.getZ();

		//check the exit!!!!!!
		if(maze!=null)
		{
			int[][][] mazeData=maze.getMaze();
			if((characterPlace.equals(maze.getStartPosition()))||(characterPlace.equals(maze.getGoalPosition())))
			{
				return false;
			}
			if((x>0)&&(mazeData[x-1][y][z]==0))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}

	}
	/**
	 * checking if in the current position of the character,it can move up in the floors
	 * @return true-if can move up,false-if can't
	 */
	public boolean canBeMovedUp()
	{
		int x=characterPlace.getX();
		int y=characterPlace.getY();
		int z=characterPlace.getZ();

		if(maze!=null)
		{
			int[][][] mazeData=maze.getMaze();
			if((characterPlace.equals(maze.getStartPosition()))||(characterPlace.equals(maze.getGoalPosition())))
			{
				return false;
			}
			if((x<mazeData.length-1)&&(mazeData[x+1][y][z]==0))
			{
				return true;
			}
			else
			{
				return false;
			}
		}
		else
		{
			return false;
		}

	}



	//methods inherited from view
	/**
	 * {@inheritDoc}
	 */

	public String getUserCommand() 
	{
		return message;
	}
	/**
	 * {@inheritDoc}
	 */

	@Override
	public void ViewPrintMsg(String error) 
	{
		mainWindow.showMessageBox(error);
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ViewGenerateMaze3d(String message) 
	{
		this.message="display-m";

		setChanged();
		notifyObservers();
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ViewDisplayAllMaze(byte[] byteArr) {
		maze=new Maze3d(byteArr);
		characterPlace=new Position(maze.getStartPosition().getX(),maze.getStartPosition().getY(),maze.getStartPosition().getZ());
		mainWindow.setMazeInCanvas(maze);
		mainWindow.moveCharacterInCanvas(maze.getStartPosition().getX(),maze.getStartPosition().getY(),maze.getStartPosition().getZ(),canBeMovedUp(),canBeMovedDown(),true,isSolvingNow);
	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ViewDisplayCrossSectionBy(int[][] crossSection) {}


	/**
	 * {@inheritDoc}
	 */
	//@Override
	public void showDisplaySolution(SearchSolution<Position> solution) {}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ViewExit() 
	{

		if((thread!=null)&&(!thread.isInterrupted()))
		{
			thread.interrupt();
		}

		mainWindow.close();
	}

	public void showSolveFrom(String message)
	{
		this.message="display half solution m";
		setChanged();
		notifyObservers();
	}
	/**
	 * {@inheritDoc}
	 */
	public void showDisplayHalfSolution(SearchSolution<Position> solution)
	{
		//		if(isSolve)
		//		{
		//			//mainWindow.solveInCanvas(solution);
		//
		//			ArrayList<State<Position>> al=solution.getSolution();
		//
		//
		//			thread=new Thread(new Runnable() {
		//
		//				@Override
		//				public void run() 
		//				{
		//					for(State<Position> s:al)
		//					{
		//						if(mainWindow.IsDisposed())
		//						{
		//							return;
		//						}
		//
		//						Position p=s.getState();
		//						if(p.equals(maze.getGoalPosition()))
		//						{
		//							isSolvingNow=false;
		//						}
		//						int x=p.getX();
		//						int y=p.getY();
		//						int z=p.getZ();
		//
		//
		//						characterPlace.setXYZ(x, y, z);
		//
		//						mainWindow.moveCharacterInCanvas(x,y,z,canBeMovedUp(),canBeMovedDown(),false,isSolvingNow);
		//
		//						try {
		//							Thread.sleep(500);
		//						} catch (InterruptedException e) {
		//
		//							//e.printStackTrace();
		//						}
		//					}
		//
		//				}
		//			});
		//			thread.start();
		//
		//		}
		//		else//if hint
		//		{
		//			ArrayList<State<Position>> al=solution.getAL();
		//			if(al.isEmpty())
		//			{
		//				return;
		//			}
		//			if(al.size()<2)
		//			{
		//				mainWindow.showMessageBox("You are already in the goal Position,can't show hint");
		//				return;
		//			}
		//			Position p=al.get(1).getState();
		//
		//			if(p!=null)
		//			{
		//				int x=p.getX();
		//				int y=p.getY();
		//				int z=p.getZ();
		//				characterPlace.setXYZ(x, y, z);
		//				mainWindow.moveCharacterInCanvas(x, y, z,canBeMovedUp(),canBeMovedDown(),false,isSolvingNow);
		//
		//			}
		//		}

	}
	/**
	 * {@inheritDoc}
	 */
	@Override
	public void ResetProp(Properties p) 
	{
		//		if(p.getTypeOfUserInterfece().intern()=="cli")
		//		{
		//
		//			showExit();
		//			String[] s=new String[1];
		//			s[0]=path;
		//			Run.main(s);
		//		}

	}


	@Override
	public void ViewPrintDir(String[] args) {
		// TODO Auto-generated method stub

	}


	@Override
	public void ViewMenu() {
		// TODO Auto-generated method stub

	}




}