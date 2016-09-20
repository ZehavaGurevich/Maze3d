package io;

import java.io.IOException;
import java.io.OutputStream;



public class MyCompressorOutputStream extends OutputStream
{
	private OutputStream out;
	private  int count;
	private int check; 





	//c'tor
	public MyCompressorOutputStream(OutputStream out) 
	{
		this.out=out;
		this.count=0;
		this.check=0;

	}

	@Override
	public void write(int b) throws IOException 
	{
		if(count==0)
		{
			check=b;
			count++;
		}
		else if(check==b)
		{
			count++;
		}
		else{
			while(count>255){
				out.write(check);
				out.write(255);
				count-=255;
			}
			out.write(check);
			out.write(count);
			check=b;
			count=1;
		}

	}
	
	public void write(byte[]arrByte)throws IOException
	{
		for(int i=0;i<arrByte.length;i++)
		{
			write(arrByte[i]);
		}
		
		out.write(check);
		out.write(count);
		out.close();
	}
	
	
	//getters and setters
	public OutputStream getOut()
	{
		return out;
	}
	public void setOut(OutputStream out)
	{
		this.out = out;
	}
	public int getCount()
	{
		return count;
	}
	public void setCount(int count) 
	{
		this.count = count;
	}
	public int getCheck()
	{
		return check;
	}
	public void setCheckNum(int check)
	{
		this.check = check;
	}
}
