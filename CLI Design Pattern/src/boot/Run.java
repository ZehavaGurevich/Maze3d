package boot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;

import controller.Command;
import controller.MyController;
import model.MyModel;
import view.MyView;

public class Run {

	public static void main(String[] args) throws IOException
	{
			
		MyModel myModel= new MyModel();
		MyView myView= new MyView(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out), new HashMap<String, Command>());
		MyController myController= new MyController(myModel,myView);
		myController.setView(myView);
		
		myModel.setMyController(myController);
		myView.setMyController(myController);
		//CLI cli=new CLI(myView.getIn(), myView.getOut(), myView.getCommands());
		//myView.setCli(cli);
		myView.ViewStart();
	}

}
