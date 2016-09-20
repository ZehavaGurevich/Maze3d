package model;

import java.io.IOException;

public interface Model
{

	public void ModelDir(String path);
		
	public void ModelGenerateMaze3d(String mazeName,int x,int y,int z);
		
	public void ModelDisplay(String mazeName);
		
	public void ModelDisplayCrossSectionBy(String XYZ,int index,String mazeName);
		
	public void ModelSaveMaze(String mazeName,String fileName);
	
	public void ModeLoadMaze(String mazeName,String fileName) throws IOException;
	
	public void ModelMzeSize(String mazeName);
	
	public void ModelFileSize(String fileName);

	public void ModelSolve(String mazeName,String algorithm);
		
	public void ModelDisplaySolution(String mazeName);
		
	public void ModelExit();

}
