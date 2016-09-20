package presenter;

public class GenerateMaze3d extends AbstractCommand 
{
	public GenerateMaze3d(Presenter p) 
	{
		super(p);
	}
	
	@Override
	public void doCommand(String params)
	{
		String[] arrParmas= params.split(" ");
		if(arrParmas.length==4)
		{
			String name= arrParmas[0];
			int x= Integer.parseInt(arrParmas[1]);
			int y= Integer.parseInt(arrParmas[2]);
			int z= Integer.parseInt(arrParmas[3]);
			p.getM().ModelGenerateMaze3d(name, x, y, z);
			
		}
		else
		{
			System.out.println("Invalid input\n");
		}
   

	}

}
