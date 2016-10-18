package boot;

import model.MyModel;
import presenter.Presenter;
import view.MyView;


// TODO: Auto-generated Javadoc
/**
 * The Class Run.
 */
public class Run
{
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) 
	{
		MyModel m = new MyModel();
		MyView v = new MyView();
		Presenter p = new Presenter(v, m);

		m.addObserver(p);
		v.addObserver(p);

		v.start();
	}
}
