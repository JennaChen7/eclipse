interface ConnectedGraphFunctions
{
	public int getNumberOfVertices();
	public int getNumberOfEdges();
	public boolean isDirected();
	public void addVertex(int v) throws GraphException;
	public void addEdge(int from, int to) throws GraphException;
	public String toString();
	public boolean isConnected(int startingVertex);
	public java.util.HashSet<Integer> getConnectedSet(int startingVertex);
}
