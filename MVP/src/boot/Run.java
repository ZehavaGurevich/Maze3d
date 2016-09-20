package boot;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

import model.MyModel;
import presenter.Presenter;
import view.CliView;

public class Run {

	public static void main(String[] args) throws IOException
	{
		MyModel m = new MyModel();
		CliView v = new CliView(new BufferedReader(new InputStreamReader(System.in)), new PrintWriter(System.out));
		Presenter p = new Presenter(v, m);

		m.addObserver(p);
		v.addObserver(p);
		v.ViewStart();
	}

}
