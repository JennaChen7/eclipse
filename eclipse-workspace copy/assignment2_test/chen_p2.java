import java.io.*;
import java.nio.*;
import java.util.*;
import java.util.ArrayList;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.nio.ByteBuffer;
import java.lang.Integer;

class chen_p2
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
			byte [] ba = new byte[2];
			byte [] ca = new byte[4];
			byte [] da = new byte[8];
			
			//read binary file
			DataInputStream input = new DataInputStream(new FileInputStream(inputFilename));
			PrintWriter outp=new PrintWriter(new BufferedWriter(new FileWriter(outputFilename)));
			
			//track arr
			int count=0;
			
				//get num of blocks

				input.read(ca);
			
				int num=ByteBuffer.wrap(ca).getInt();
				
				for(int i=0; i<num; i++) {
					//type of data
					input.read(ba);
					char type = ByteBuffer.wrap(ba).getChar();
					
					//bytes of value
					switch(type) {
						case 'i':
							input.read(ca);
							int integer= ByteBuffer.wrap(ca).getInt();
							outp.print("int	" + integer + "\n");
							break;
						case 'l':
							input.read(da);
							long lg= ByteBuffer.wrap(da).getLong();
							outp.print("long	" + lg + "\n");
							break;
						case 'h':
							input.read(ba);
							short st= ByteBuffer.wrap(ba).getShort();
							outp.print("short	" + st + "\n");
							break;
						case 'f':
							input.read(ca);
							float ft= ByteBuffer.wrap(ca).getFloat();
							outp.print("float	" + ft + "\n");
							break;
						case 'd':
							input.read(da);
							double de= ByteBuffer.wrap(da).getDouble();
							outp.print("double	" + de + "\n");
							break;
						case 'b':
							input.read(ca);
							int blength= ByteBuffer.wrap(ca).getInt();
							
							long [] larray=new long[blength];
							for(int b=0; b<blength; b++) {
								input.read(da);
								long le=ByteBuffer.wrap(da).getLong();
								larray[b]=le;
							}
							outp.print("long array	");
							for(int z=0;z<larray.length;z++) {
							outp.print(larray[z]);
							if(z!=(larray.length-1)) {
								outp.print(",");
							}
							}
							outp.print("\n");
							break;
						case 's':
							input.read(ca);
							int slength= ByteBuffer.wrap(ca).getInt();
							char [] sarray = new char[slength];
							for(int s=0;s<(slength);s++) {
								input.read(ba);
								char se = ByteBuffer.wrap(ba).getChar();
								sarray[s]=se;
							}
							outp.print("string	");
							for(int z=0;z<sarray.length;z++) {
								outp.print(sarray[z]);
							}
							outp.print("\n");
							break;
						case 'g':
							input.read(ca);
							int glength= ByteBuffer.wrap(ca).getInt();
							float [] garray= new float[glength];
							for(int g=0;g < glength;g++) {
								input.read(ca);
								float fe=ByteBuffer.wrap(ca).getFloat();
								garray[g]=fe;
							}
							outp.print("float array	");
							for(int z=0;z<garray.length;z++) {
								outp.print(garray[z]);
								if(z!=(garray.length-1)) {
									outp.print(",");
								}
							}
							outp.print("\n");
							break;
						default:
							break;
					}
				
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
	private static int cbb(int counter, ByteBuffer bb, FileOutputStream dout) {
		try {
		if((counter + 8) >= 100) {
			dout.write(bb.array(),0,counter);
			counter=0;
		}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
		return counter;
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
			
			
			while( (inn = input.readLine()) != null )
			{
				sarray.add(inn);
			}
			
			input.close();
			
			BufferedReader inpt = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilename)));
			//BufferedOutputStream dout = new BufferedOutputStream(new FileOutputStream(outputFilename));
			FileOutputStream dout = new FileOutputStream(outputFilename);
			
			int size=100;
			int counter=0;
			ByteBuffer bb = ByteBuffer.allocate(size);
			bb.position(0);
			
			
			//number of blocks
			int num=sarray.size();
			bb.putInt(num);
			counter += 4;
			System.out.println(bb.position());
			
			//
			while((inn = inpt.readLine()) != null) {
				String[] s =inn.split("	");
			switch(s[0]) {
				case "int":
					bb.putChar('i');
					counter += 2;
					counter = cbb(counter, bb, dout);
					
					if(counter==0)
					bb.position(0);
					
					System.out.println(bb.position());

					
					int i=Integer.parseInt(s[1]);  
					bb.putInt(i);
					counter += 4;
					counter = cbb(counter, bb, dout);
					if(counter==0)
						bb.position(0);
					
					System.out.println(bb.position());
					break;
					
				case "long":
					bb.putChar('l');
					counter += 2;
					counter = cbb(counter, bb, dout);
					if(counter==0)
						bb.position(0);
					System.out.println(bb.position());
					
					long l=Long.parseLong(s[1]);  
					bb.putLong(l);
					counter += 8;
					counter = cbb(counter, bb, dout);
					if(counter==0)
						bb.position(0);
					
					System.out.println(bb.position());
					break;
					
				case "short":
					bb.putChar('h');
					counter += 2;
					counter = cbb(counter, bb, dout);
					if(counter==0)
						bb.position(0);
					
					System.out.println(bb.position());
					
					short st=Short.parseShort(s[1]);  
					bb.putShort(st);
					counter += 2;
					counter = cbb(counter, bb, dout);
					if(counter==0)
						bb.position(0);
					
					System.out.println(bb.position());
					break;
					
				case "float":
					bb.putChar('f');
					counter += 2;
					counter = cbb(counter, bb, dout);
					if(counter==0)
						bb.position(0);
					System.out.println(bb.position());
					
					float ft=Float.parseFloat(s[1]);  
					bb.putFloat(ft);
					counter += 4;
					counter = cbb(counter, bb, dout);
					if(counter==0)
						bb.position(0);
					System.out.println(bb.position());
					break;
					
				case "double":
					bb.putChar('d');
					counter += 2;
					counter = cbb(counter, bb, dout);
					if(counter==0)
						bb.position(0);
					System.out.println(bb.position());
					
					double d=Double.parseDouble(s[1]);  
					bb.putDouble(d);
					counter += 8;
					counter = cbb(counter, bb, dout);
					if(counter==0)
						bb.position(0);
					System.out.println(bb.position());
					break;
					
				case "long array":
					bb.putChar('b');
					counter += 2;
					counter = cbb(counter, bb, dout);
					if(counter==0)
						bb.position(0);
					System.out.println(bb.position());
					
					String[] lstr = s[1].split(",");
					bb.putInt(lstr.length);
					counter +=4;
					counter = cbb(counter, bb, dout);
					if(counter==0)
						bb.position(0);
					System.out.println(bb.position());
					
					for(int k=0;k<lstr.length;k++) {
						long la=Long.parseLong(lstr[k]);  
						bb.putLong(la);
						counter += 8;
						counter = cbb(counter, bb, dout);
						if(counter==0)
							bb.position(0);
						
						System.out.println(bb.position());
					}
					break;
					
				case "string":
					bb.putChar('s');
					counter += 2;
					counter = cbb(counter, bb, dout);
					if(counter==0)
						bb.position(0);
					System.out.println(bb.position());
					
					bb.putInt(s[1].length());
					counter += 4;
					counter = cbb(counter, bb, dout);
					if(counter==0)
						bb.position(0);
					System.out.println(bb.position());

					for(int k=0;k<s[1].length();k++) {
	
						char[] ch = new char[s[1].length()];
				            ch[k] = s[1].charAt(k);
				            bb.putChar(ch[k]);
				            counter += 2;
				            counter = cbb(counter, bb, dout);
				            if(counter==0)
				            	bb.position(0);
				            System.out.println(bb.position());
						
					}
					 
					break;
					
				case "float array":
					bb.putChar('g');
					counter += 2;
					counter = cbb(counter, bb, dout);
					if(counter==0)
						bb.position(0);
					System.out.println(bb.position());
					
					String[] fstr = s[1].split(",");
					bb.putInt(fstr.length);
					counter += 4;
					if(counter==0)
						bb.position(0);
					System.out.println(bb.position());
					
					counter = cbb(counter, bb, dout);
					for(int k=0;k<fstr.length;k++) {
						float fa=Float.parseFloat(fstr[k]);  
						bb.putFloat(fa);
						counter += 4;
						counter = cbb(counter, bb, dout);
						if(counter==0)
							bb.position(0);
						System.out.println(bb.position());
					}
					break;
					
				default:
					break;
			}
			}
			dout.write(bb.array(),0,counter);
			
			inpt.close();
			dout.close();
			
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
	}
}

