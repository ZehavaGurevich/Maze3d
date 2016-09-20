package io;

import java.io.IOException;
import java.io.InputStream;

public class MyDecompressorInputStream extends InputStream 
{
	private InputStream in;
	private int count;
	private int check;
	
	//c'tor
	public MyDecompressorInputStream(InputStream in)
	{
		this.in=in;
		this.count=0;
		this.check=0;
	}

	@Override
	public int read() throws IOException 
	{
		return 0;
	}

	public int read(int b) throws IOException 
	{
		in.read();
		return -1;
	}
	
	@Override
	public int read(byte[] arrB) throws IOException 
	{
			int index=0;
			check=in.read();
			count=in.read();
			while(check!=-1 && count!=-1)
			{
				for(int i = 0; i<count; i++)
				{
					arrB[index] = (byte)check;
					index++;
					
				}
				check=in.read();
				count=in.read();
			}
		    in.close();
		    return 0;
		}
	
	
	

}