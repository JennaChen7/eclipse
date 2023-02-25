class ReadAndWriteBinaryFilesMultipleBytesAtATime
{
	// sample program using FileInputStream & FileOutputStream to read & write binary files multiple bytes at a time
	public static void main(String[] args)
	{
		if( args.length < 2 )
		{
			System.out.println("format: ReadAndWriteBinaryFilesMultipleBytesAtATime \"input file\" \"size of byte array\" \"<output file>\"");
			System.exit(0);
		}
		
		try
		{
			java.io.FileInputStream input = new java.io.FileInputStream(args[0]);
			java.io.FileOutputStream output = null;
			int bytesToRead = Integer.parseInt(args[1]);
			boolean copyFile = false;
			byte[] buffer = new byte[bytesToRead];
			if( args.length > 2 )
			{
				output = new java.io.FileOutputStream(args[2]);
				copyFile = true;
			}
			
			long t0 = System.currentTimeMillis();
			int currentByteCount;
			int byteCount = 0;
			while( (currentByteCount = input.read(buffer)) >= 0 )
			{
				byteCount = byteCount+currentByteCount;
				
				// using the if versus not doing a test did not have much of an 
				// effect the time to copy the file 
				if( copyFile )
				{
					output.write(buffer, 0, currentByteCount);
				}
			}
			System.out.println("byteCount = " + byteCount);
			
			input.close();
			if( args.length > 2 )
			{
				output.close();
			}
			
			long t1 = System.currentTimeMillis();
			System.out.println("total time " + ((t1-t0)/1000.0) + " seconds");
			
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
	}
}
