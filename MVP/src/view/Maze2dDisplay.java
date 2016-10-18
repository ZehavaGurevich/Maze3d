package view;



import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;


public class Maze2dDisplay extends Canvas 
{
	Maze3d maze;

	private Position characterPlace;
	private GamePlayer characterPic;

	private Image start_im;//the image of the starting point
	private Image end_im;//the image of the ending point
	private Image prize_im;//the image that you grt when you win

	/**
	 * constructor using fields
	 * @param parent the parent of the widget
	 * @param style style of the widget
	 * @param characterPic a class that contain the painting of the character
	 */
	public Maze2dDisplay(Composite parent, int style,GamePlayer characterPic) 
	{
		super(parent,style);
		setBackground(new Color(null, 255, 255, 255));
		this.characterPlace=null;
		this.characterPic=characterPic;
		end_im = new Image(getDisplay(), "./images/goal.png");
		start_im = new Image(getDisplay(), "./images/player.png");
		//prize_im=new Image(getDisplay(), "./resources/FotorCreated.jpg");
	}
	/**
	 * defines the listener that paint the maze
	 */
	public void paintMaze() 
	{

		addPaintListener(new PaintListener() {

			@Override
			public void paintControl(PaintEvent e) {
				if(getDisplay().isDisposed())
				{
					return;
				}
				if(maze!=null)
				{
					e.gc.setForeground(new Color(null, 0, 0, 0));
					e.gc.setBackground(new Color(null, 24,	116	,205));

					int[][][] mazeData = maze.getMaze();



					int width = getSize().x;// how many pixels there are in the canvas in the width(z in the maze)
					int depth = getSize().y;// how many pixels there is on the canvas in height(y in the maze)

					// the size of each cell in the canvas,according to the size of the changing window
					int cellX = width / mazeData[0][0].length;// the width of the cell
					int cellY = depth / mazeData[0].length; // the height of the cell




					// for calculating the size of the maze floor
					int lengthWidth = mazeData[0][0].length;// the length of z axis in the maze
					int lengthDepth = mazeData[0].length;// the length of y axis in the maze



					for (int i = 0; i < lengthDepth; i++) {
						for (int j = 0; j < lengthWidth; j++) {
							// this is the maze where i am starting to draw the
							// cell
							int pixelX = cellX * j;
							int pixelY = cellY * i;
							if (mazeData[characterPlace.getX()][i][j] != 0)
							{
								e.gc.fillRectangle(pixelX, pixelY, cellX, cellY);
								// drawing rectangle from the node cell size
							}

						}
					}

					if(characterPlace.getX()==maze.getGoalPosition().getX())
					{

						e.gc.setBackground(new Color(null,200,0,0));						
						e.gc.drawImage(end_im, 0, 0, end_im.getBounds().width,end_im.getBounds().height,maze.getGoalPosition().getZ()*cellX,maze.getGoalPosition().getY()*cellY ,cellX ,cellY);							
						e.gc.setBackground(new Color(null,255,0,0));
						e.gc.setBackground(new Color(null,0,0,0));
					}
					if(characterPlace.getX()==maze.getStartPosition().getX())
					{

						e.gc.setBackground(new Color(null,200,0,0));						
						e.gc.drawImage(start_im, 0, 0, start_im.getBounds().width,start_im.getBounds().height,maze.getStartPosition().getZ()*cellX,maze.getStartPosition().getY()*cellY ,cellX ,cellY);							
						e.gc.setBackground(new Color(null,255,0,0));
						e.gc.setBackground(new Color(null,0,0,0));
					}

					characterPic.paint(e,characterPlace.getZ()*cellX,characterPlace.getY()*cellY, cellX, cellY);

					if(characterPlace.equals(maze.getGoalPosition()))
					{

						e.gc.drawImage(prize_im,0,0,prize_im.getBounds().width,prize_im.getBounds().height,width/4,depth/4,width/2,depth/2);

					}



				}
			}
		});



	}


	/**
	 * setting the maze which we will draw
	 * @param maze object of maze(containing the 3d array,start position,goal position,and sizes)
	 */
	public void setMaze(Maze3d maze)
	{
		//only here I got maze
		this.maze = maze;


	}
//!!!!!!!!!!!!!!!!!!!!!!!11
	
	public void keyPressed(KeyEvent e) {
		String command = null;
	//	if(maze.equals(null)){
			switch (e.keyCode) {
			case SWT.ARROW_RIGHT:
				command="moveRight";
				break;
			
			case SWT.ARROW_LEFT:	
				command="moveLeft";
				break;
				
			case SWT.ARROW_UP:			
				command="moveBackWard";
				break;
				
			case SWT.ARROW_DOWN:			
				command="moveForward";
				break;
				
			case SWT.PAGE_UP:		
				command="moveUp";
				break;
			case SWT.PAGE_DOWN:		
				command="moveDown";
				break;
			default: 
				break;
			}
		view.executeCommand(command);
		redraw();
		}
	//!!!!!!!!!!!!!!!!!!!!!!!11
	

public void setCharacterInPlace(int x,int y,int z)
{
	//here i got the position,if the maze and the position is exists ,i draw on the canvas
	this.characterPlace=new Position(x,y,z);
	if(maze!=null)
	{
		getDisplay().syncExec(new Runnable() {

			@Override
			public void run() 
			{
				//now I will initialize the the paint listener
				paintMaze();
				//activate paint listener
				redraw();

			}
		});
	}

}


}