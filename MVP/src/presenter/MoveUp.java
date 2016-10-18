package presenter;

import model.Model;
import view.View;

public class MoveUp extends AbstractCommand
{


private Model m;
private View v;

public MoveUp(Model m,View v)
{
	this.m=m;
	this.v=v;
}


public Model getM() {
	return m;
}


public void setM(Model m) {
	this.m = m;
}


public View getV() {
	return v;
}

/**
 * Sets the v.
 *
 * @param v the new v
 */
public void setV(View v) {
	this.v = v;
}

@Override
public void doCommand(String command) throws IOException {
	String [] temp=command.split(" ");
	if(temp.length==1&&command.equals("moveUp")){
		m.m_moveUp();
	}else{
		v.display_message("Bad paramaters\n");
	}
	
}
}
}
