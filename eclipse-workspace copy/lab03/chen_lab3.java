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

class chen_lab3
{
	public static void main(String[] args)
	{
		// put some code here to check for three commandline arguments
		if((args.length < 2) || (args.length > 3)) {
			System.out.println("meh");
			System.exit(0);
		}
		boolean unique = false;
		if(args.length == 3) {
		if((args[2].startsWith("True"))||(args[2].startsWith("true")) || (args[2].startsWith("Yes"))|| (args[2].startsWith("yes")) || (args[2].startsWith("T")) || (args[2].startsWith("t")) || (args[2].startsWith("Y")) || (args[2].startsWith("y")))
		{
			unique = true;
		}
		}
		
		ReverseText(args[0], args[1], unique);
		
	}
	
	// whether output list of unique words
	
	private static void ReverseText(String inputFilename, String outputFilename, boolean unique)
	{
		//System.out.println("ReverseText");
		
		// whether output list of unique words
	
		try
		{
			//read file
			BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(inputFilename)));
			PrintWriter outp=new PrintWriter(new BufferedWriter(new FileWriter(outputFilename)));
			
			java.text.DecimalFormat df = new java.text.DecimalFormat("###,###,###,###,###");
			
			String inn;
			Set<String> ts = new TreeSet<>();
			int numLine=0;
			int numWord=0;
			int numChar=0;
			
			while( (inn = input.readLine()) != null )
			{
				numLine++;
				
				String[] sword =inn.split(" ");
				ArrayDeque<String> wstack = new ArrayDeque<>();
				ArrayDeque<Character> cstack = new ArrayDeque<>();
				//System.out.println("two arrays");
				
				//store word order
				//System.out.println(sword.length);
				
				for(int i=0; i < sword.length; i++) {
					wstack.addLast(sword[i]);
					ts.add(sword[i]);
					numWord++;
				}
				
				int wsize=wstack.size();
				
				//store char order
				for(int i=0; i < wsize; i++) {
					String s = wstack.removeFirst();
					//System.out.println(s);
					
					char [] wchar = s.toCharArray();
					
					for(int j=0; j < wchar.length; j++) {
					cstack.addLast(wchar[j]);
					numChar++;
					}
					
					if(i != (wsize-1)) {
					cstack.addLast(' ');
					}
					if(i == (wsize-1)){
					cstack.addLast('\n');
					}
				}
				//System.out.println("cstack" + cstack);
				
				int csize=cstack.size();
				//reverse char
				
				for(int k=0; k<csize; k++) {
					outp.print(cstack.removeLast());
					//System.out.println(cstack.removeLast());
				}
				
				
			}
			
			System.out.println("lines = " + df.format(numLine));
			System.out.println("words = " + df.format(numWord));
			System.out.println("character in words = " + df.format(numChar));
			if(unique == true) {
				System.out.println("unique words = " + df.format(ts.size()));
				System.out.println(ts);
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
	
}

