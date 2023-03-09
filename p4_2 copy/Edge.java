record Edge(Integer fromVertex, Integer toVertex)
{
	public String toString()
	{
		return "(" + fromVertex + "," + toVertex + ")";
	}
}

/*
class Edge
{
	private final Integer fromVertex;
	private final Integer toVertex;
	
	Edge(Integer fromVertex, Integer toVertex)
	{
		this.fromVertex = fromVertex;
		this.toVertex = toVertex;
	}
	
	public Integer fromVertex()
	{
		return fromVertex;
	}
	
	public Integer toVertex()
	{
		return toVertex;
	}
	
	public String toString()
	{
		return "(" + fromVertex + "," + toVertex + ")";
	}
}
*/
