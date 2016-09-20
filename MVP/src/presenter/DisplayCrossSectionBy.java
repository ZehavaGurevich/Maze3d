package presenter;

public class DisplayCrossSectionBy extends AbstractCommand 
{

	public DisplayCrossSectionBy(Presenter p)
	{
		super(p);
	}
	@Override
	public void doCommand(String params) 
	{
		String[] arrParmas= params.split(" ");
		if(arrParmas.length==3)
		{
			String XYZ= arrParmas[0];
			int index= Integer.parseInt(arrParmas[1]);
			String name= arrParmas[2];
	
			p.getM().ModelDisplayCrossSectionBy(XYZ, index, name);
			
		}
		else
		{
			System.out.println("Invalid input\n");
		}
   




	}

}
