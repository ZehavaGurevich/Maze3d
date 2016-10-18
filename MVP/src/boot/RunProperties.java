package boot;

import java.beans.XMLEncoder;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

import presenter.Properties;

public class RunProperties
{
	public static void main(String[] args) 
	{
		Properties properties= new Properties(2,5,7,10,"BFS","My maze","gui");
		
	
		try {
			XMLEncoder xmlEncoder= new XMLEncoder(new FileOutputStream("mazeProperties.xml"));
			xmlEncoder.writeObject(properties);
			xmlEncoder.close();
		} catch (FileNotFoundException e) {
			System.out.println("the creat file is faild");
			e.printStackTrace();
		}
	
	
	}


}
