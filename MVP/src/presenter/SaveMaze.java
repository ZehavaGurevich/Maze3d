package presenter;

public class SaveMaze extends AbstractCommand 
{
	public SaveMaze(Presenter p)
	{
		super(p);
	}

	@Override
	public void doCommand(String params)
	{
		String[] arrParmas= params.split(" ");
		if(arrParmas.length==2)
		{
			String name= arrParmas[0];
			String file= arrParmas[1];
			p.getM().ModelSaveMaze(name, file);


		}
		else
		{
			System.out.println("Invalid input\n");
		}





	}
}

