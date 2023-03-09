import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;

public class chen_Graph implements ConnectedGraphFunctions{
	private final ArrayList<Integer> vertices = new ArrayList<>();
	private final ArrayList<Edge> edges = new ArrayList<>();
	private final boolean isDirected;
	
	public chen_Graph(){
		this.isDirected = false;
	}
	public chen_Graph(boolean id){
		this.isDirected = id;
	}
	
	//@Override
	public boolean isConnected(int startingVertex) {
		HashSet<Integer> returnedList = new HashSet<>();
		returnedList = getConnectedSet(startingVertex);
		if(returnedList.size()==vertices.size()) {
			return true;
		}
		return false;
	}
	
	//@Override
	public String toString() {
		StringBuilder str = new StringBuilder();
		str.append("G = (V,E)" + "\n" + "V = {");
		for(int i=0;i<vertices.size();i++) {
			str.append(vertices.get(i));
			if(i != (vertices.size()-1)) {
				str.append(",");
			}
		}
		str.append("}" + "\n" + "E = {");
		for(int j=0;j<edges.size();j++) {
			str.append("(" + edges.get(j).fromVertex() + "," + edges.get(j).toVertex() + ")");
			if(j != (edges.size()-1)) {
				str.append(",");
			}
		}
		str.append("}");
		
		return str.toString();
	}
	
	//@Override
	public int getNumberOfVertices() {
		int i = vertices.size();
		return i;
	}

	//@Override
	public int getNumberOfEdges() {
		int i = edges.size();
		return i;
	}

	//@Override
	public boolean isDirected() {
		return isDirected;
	}

	//@Override
	public void addVertex(int v) throws GraphException {
		if(vertices.contains(v)) {
			throw new GraphException("duplicate vertex exception");
		}else {
		vertices.add(v);
		}
	}

	//@Override
	public void addEdge(int from, int to) throws GraphException {
		if(isDirected) {
		if(edges.contains(new Edge(from,to))) {
			throw new GraphException("duplicate edge exception");
		}else{
			if((vertices.contains(from)) && (vertices.contains(to))) {
		edges.add(new Edge(from,to));
			}else {
				throw new GraphException("vertex does not exist");
			}
		}
		}
		if(isDirected == false) {
			if((edges.contains(new Edge(from,to))) || (edges.contains(new Edge(to,from)))) {
				throw new GraphException("duplicate edge exception");
			}else {
				if((vertices.contains(from)) && (vertices.contains(to))) {
			edges.add(new Edge(from,to));
				}else {
					throw new GraphException("vertex does not exist");
				}
			}
		}
	}

	
	//@Override
	public HashSet<Integer> getConnectedSet(int startingVertex){//call getConnectedSet(int, edges)
		if(isDirected) {
			HashSet<Integer> path1 = new HashSet<>();
			HashSet<Integer> path2 = new HashSet<>();
			HashSet<Integer> intersection = new HashSet<>();
			
		try {
			path1 = getConnectedSet(startingVertex, edges);
		} catch (GraphException e) {
			e.toString();
		}
		
		//reverse edges
		for(int j=0;j<edges.size();j++) {
			int a;
			int b;
			a=edges.get(j).fromVertex();
			b=edges.get(j).toVertex();
			edges.set(j,new Edge(b,a));
		}
		try {
			path2 = getConnectedSet(startingVertex, edges);
		} catch (GraphException e) {
			e.toString();
		}
		
		//intersection
		ArrayList<Integer> ipath1 = new ArrayList<>(path1);
		ArrayList<Integer> ipath2 = new ArrayList<>(path2);
		
		for(int i=0; i<ipath1.size();i++) {
			if(ipath2.contains(ipath1.get(i))) {
				intersection.add(ipath1.get(i));
			}
		}
		return intersection;
		}
		
		HashSet<Integer> path3 = new HashSet<>();
		if(isDirected == false) {
			try {
				path3 = getConnectedSet(startingVertex, edges);
			} catch (GraphException e) {
				e.toString();
			}
		}
		return path3;
	}
	
	private HashSet<Integer> getConnectedSet(int startingVertex, ArrayList<Edge> edges) throws GraphException {
		HashSet<Integer> connectedSubset = new HashSet<>();
		ArrayDeque<Integer> newlyAddedVertices = new ArrayDeque<>();
		//.contains() to avoid duplicate
		newlyAddedVertices.add(startingVertex);
		connectedSubset.add(startingVertex);

//debug
int counter=0;
int edgesize=0;
int fromvertex=0;
int tovertex=0;
		while(newlyAddedVertices.isEmpty() == false) {
counter++;
edgesize=edges.size();
			 int currentVertex = newlyAddedVertices.pollFirst();//get & remove first element
			 
			for(int i=0;i<edges.size();i++) {
				if(isDirected == false) {//undirected graph
					if(edges.get(i).toVertex()==currentVertex) {//if currentVertex in current Edge_to
						if(connectedSubset.contains(edges.get(i).fromVertex()) == false) {
							connectedSubset.add(edges.get(i).fromVertex());
							newlyAddedVertices.add(edges.get(i).fromVertex());
						}
					}
					if(edges.get(i).fromVertex()==currentVertex) {//if currentVertex in current Edge_from
						if(connectedSubset.contains(edges.get(i).toVertex()) == false) {
							connectedSubset.add(edges.get(i).toVertex());
							newlyAddedVertices.add(edges.get(i).toVertex());
						}
					}
				}
				
				if(isDirected) {//directed graph
					//debug
					fromvertex = edges.get(i).fromVertex();
					tovertex = edges.get(i).toVertex();
					if(edges.get(i).fromVertex()==currentVertex) {//if currentVertex in current Edge_from
						//
						if((connectedSubset.contains(edges.get(i).toVertex())) == false) {
							connectedSubset.add(edges.get(i).toVertex());
							newlyAddedVertices.add(edges.get(i).toVertex());
						}
					}
				}
			}
			 
		}
		return connectedSubset;
	}
	
}