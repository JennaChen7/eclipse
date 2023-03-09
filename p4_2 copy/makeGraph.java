class makeGraph
{
	public static void main(String[] args)
	{
		if( args.length < 1 )
		{
			System.out.println("format: makeGraph \"input filename\" \"<directed>\" \"<check if connected>\"");
			System.exit(0);
		}
		long t0 = System.currentTimeMillis();
		
		String inputFilename = args[0];
		
		boolean directed = false;
		if( args.length > 1 )
		{
			if( args[1].toLowerCase().startsWith("t") || args[1].toLowerCase().startsWith("y") )
			{
				directed = true;
			}
		}
		
		ConnectedGraphFunctions g;
		if( directed )
		{
			g = new chen_Graph(directed);
		}
		else
		{
			g = new chen_Graph();
		}
		
		boolean checkIfConnected = false;
		if( args.length > 2 )
		{
			if( args[2].toLowerCase().startsWith("t") || args[2].toLowerCase().startsWith("y") )
			{
				checkIfConnected = true;
			}
		}
		
		java.util.ArrayList<Integer> duplicateVertices = new java.util.ArrayList<>();
		java.util.ArrayList<String> duplicateOrInvalidEdges = new java.util.ArrayList<>();
		
		int startingVertex = -1;
		
		try
		{
			java.io.BufferedReader input = new java.io.BufferedReader(new java.io.InputStreamReader(new java.io.FileInputStream(inputFilename)));
			String inn;
			
			// get rid of the first, line G = (V,E)
			inn = input.readLine();
			
			// get the list of vertices
			inn = input.readLine();
			inn = inn.substring(4);
			java.util.StringTokenizer st = new java.util.StringTokenizer(inn, "{},");
			while( st.hasMoreTokens() )
			{
				int newVertex = Integer.parseInt(st.nextToken());
				startingVertex = newVertex;
				
				try
				{
					g.addVertex(newVertex);
				}
				catch(GraphException e)
				{
					// keep track of duplicate vertices
					System.out.println(e.toString());
					duplicateVertices.add(Integer.valueOf(newVertex));
				}
			}
			
			// get the list of edges
			inn = input.readLine();
			inn = inn.substring(4);
			st = new java.util.StringTokenizer(inn, "{}");
			inn = st.nextToken();
			st = new java.util.StringTokenizer(inn, "(),");
			while( st.hasMoreTokens() )
			{
				int from = Integer.parseInt(st.nextToken());
				int to = Integer.parseInt(st.nextToken());
				
				try
				{
					g.addEdge(from, to);
				}
				catch(GraphException e)
				{
					// keep track of duplicate edge
					System.out.println(e.toString());
					duplicateOrInvalidEdges.add(new Edge(from, to).toString());
				}
			}
			input.close();
		}
		catch(Exception e)
		{
			System.out.println(e.toString());
			System.exit(0);
		}
		
		System.out.println(g.toString());
						
		if( checkIfConnected )
		{
			long t0a = System.currentTimeMillis();
			boolean connected = g.isConnected(startingVertex);
			long t1a = System.currentTimeMillis();
			System.out.println("g.isConnected(" + startingVertex + ") = " + connected);
			System.out.println("time to check if connected " + ((t1a-t0a)/1000.0) + " seconds");
			
			t0a = System.currentTimeMillis();
			java.util.HashSet<Integer> connectedSet = g.getConnectedSet(startingVertex);
			t1a = System.currentTimeMillis();
			System.out.print("g.getConnectedSet(" + startingVertex + ") = {");
			boolean firstValue = true;
			for( Integer v : connectedSet )
			{
				if( firstValue )
				{
					System.out.print(v);
					firstValue = false;
				}
				else
				{
					System.out.print("," + v);
				}
			}
			System.out.println("}");
			System.out.println("time to get connected set " + ((t1a-t0a)/1000.0) + " seconds");
		}
		
		long t1 = System.currentTimeMillis();
		System.out.println("time " + ((t1-t0)/1000.0) + " seconds");
		
		System.out.println("duplicate vertices");
		for( int i = 0; i < duplicateVertices.size(); i++ )
		{
			System.out.print(duplicateVertices.get(i));
			if( i < (duplicateVertices.size()-1) )
			{
				System.out.print(",");
			}
		}
		System.out.println();
		
		System.out.println("duplicate or invalid edges");
		for( int i = 0; i < duplicateOrInvalidEdges.size(); i++ )
		{
			System.out.print(duplicateOrInvalidEdges.get(i));
			if( i < (duplicateOrInvalidEdges.size()-1) )
			{
				System.out.print(",");
			}
		}
		System.out.println();
	}
}
