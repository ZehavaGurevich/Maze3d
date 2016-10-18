package model;

import java.io.IOException;
import java.util.ArrayList;

import algorithms.mazeGenerators.Position;
import presenter.Properties;

public interface Model
{

	public void ModelDir(String path);
		
	public void ModelGenerateMaze3d(String mazeName, int x, int y, int z);
		
	public void ModelDisplay(String mazeName);
		
	public void ModelDisplayCrossSectionBy(String XYZ,int index,String mazeName);
		
	public void ModelSaveMaze(String mazeName,String fileName);
	
	public void ModeLoadMaze(String mazeName,String fileName) throws IOException;

	public void ModelSolve(String mazeName,String algorithm);
		
	public void ModelDisplaySolution(String mazeName);
		
	public void ModelExit();
	
	public ArrayList<Position> getMazeSolution(String mazeName);
	
	Object getUserCommand(String command);
	
	void saveToZip();
	
	void loadFromZip();
	
	Position getPositionFromHash(String mazeName);
	
	void setProperties(Properties properties);
	
	Properties getProperties();

}
