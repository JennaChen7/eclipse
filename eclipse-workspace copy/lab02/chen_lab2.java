import java.io.*;
import java.nio.*;
import java.util.ArrayList;

class chen_lab2
{
	public static void main(String[] args)
	{
		// put some code here to check for three commandline arguments
		if(args.length != 3) {
			System.out.println("meh");
			System.exit(0);
		}
		
		// put some code here to check that the first commandline argument starts with "b" or "t"
		
		if( args[0].startsWith("b") )
		{
			convertBinaryToText(args[1], args[2]);
		}
		else if(args[0].startsWith("t"))
		{
			convertTextToBinary(args[1], args[2]);
		}else {
			System.out.println("nah");
			System.exit(0);
		}
	}
	
	private static void convertBinaryToText(String inputFilename, String outputFilename)
	{
		System.out.println("convertBinaryToText");
		try
		{
			// put your code to read a binary file and output it as a text file
			// create byte array
			//ArrayList<Byte> barray = new ArrayList<>(0);
			byte [] ba = new byte[1];
			
			//read binary file
			BufferedInputStream input = new BufferedInputStream(new FileInputStream(inputFilename));
			PrintWriter outp=new PrintWriter(new BufferedWriter(new FileWriter(outputFilename)));
			
			while(input.read(ba) > 0) {
				String inn=new String(ba);
				outp.print(inn);
			
			}
			
			input.close();
			outp.close();
	

			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
	}
	
	private static void convertTextToBinary(String inputFilename, String outputFilename)
	{
		System.out.println("convertTextToBinary");
		try
		{
			// put your code to read a text file and output it as a binary file
			//create string array
			ArrayList<String> sarray = new ArrayList<>(0);
			String inn;
			
			// read txt from file
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilename)));
			DataOutputStream dout = new DataOutputStream(new FileOutputStream(outputFilename));
			
			
			while( (inn = input.readLine()) != null )
			{
				sarray.add(inn);
				byte[] barray=inn.getBytes();
				dout.write(barray);
				dout.write(System.lineSeparator().getBytes());
			
			}
			input.close();
			dout.close();
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
	}
}

