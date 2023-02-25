import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.FileInputStream;

class ReadTextFileAndParseTokensExample2
{
	public static void main(String[] args)
	{
		if( args.length != 1 )
		{
			System.out.println("format is: ReadTextFileAndParseTokensExample2 \"input file name\"");
			System.exit(0);
		}
		
		try( BufferedReader input = new BufferedReader(new InputStreamReader(new FileInputStream(args[0]))) )
		{
			String inn;
			while( (inn = input.readLine()) != null )
			{
				String[] tokens = inn.split(" +");
				//String[] tokens = inn.split("\\s+");	// not sure if we need the first "\"
				System.out.println(inn + " (" + tokens.length + ")");
				for( String s : tokens )
				{
					System.out.println("\t" + s + " (" + s.length() + ")");
				}
			}
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
	}
}

