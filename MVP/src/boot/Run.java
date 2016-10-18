package boot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import presenter.Presenter;
import presenter.Properties;
import view.CliView;

public class Run 
{

	public static void main(String[] args) throws IOException
	{
		MyModel m = new MyModel();

		Properties properties=new Properties();
		properties=properties.readPropertiesFromFile("mazeProperties.xml");
		
		
		
//		if(properties.getViewType().equals("cli"))
//		{
			CliView v = new CliView(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out));
			Presenter p = new Presenter(v, m);
			m.addObserver(p);
			v.addObserver(p);
			v.ViewStart();
	//	}
		
//		else if(properties.getViewType().equals("gui"))
//		{
//			GuiView v=new GuiView();
//			Presenter p = new Presenter(v, m);
//			m.addObserver(p);
//			v.addObserver(p);
//			v.ViewStart();
//			System.out.println("gui");
//		}
//			
	
	}
	
}
