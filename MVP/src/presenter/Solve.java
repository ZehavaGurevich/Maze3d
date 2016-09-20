package presenter;

public class Solve extends AbstractCommand 
{
	public Solve(Presenter p)
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
			String algo= arrParmas[1];
			p.getM().ModelSolve(name, algo);


		}
		else
		{
			System.out.println("Invalid input\n");
		}
	}
	

}
