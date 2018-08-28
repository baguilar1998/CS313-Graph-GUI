import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;

public class Graph {
	
	//Instance Variables for a Graph
	private static HashMap<Vertex,HashSet<Vertex>> adjacenyList;
	private static HashSet<Edge> edges;
	private static int numOfEdges,numOfVertexes;
	
	/*
	 * Default Constructor that creates an
	 * empty Graph
	 */
	public Graph() {
		adjacenyList = new HashMap<Vertex,HashSet<Vertex>>();
		edges = new HashSet<Edge>();
		numOfEdges = 0;
		numOfVertexes = 0;
	}
	
	/*
	 * Adds a vertex to the graph
	 * @param v a vertex
	 */
	public void addVertex(Vertex v) {
		if(!adjacenyList.containsKey(v)) {
			adjacenyList.put(v, new HashSet<Vertex>());
			++numOfVertexes;
		}
	}
	
	public void removeVertex(Vertex v) {
		for(Edge e: edges) {
			if(e.getEndpt1().equals(v) || e.getEndpt2().equals(v)) {
				edges.remove(e);
				--numOfEdges;
			}
		}
		adjacenyList.remove(v);
		--numOfVertexes;
	}
	
	/*
	 * Adds an edge to a graph that's incidence 
	 * with the given vertices
	 * @param v1 a vertex
	 * @param v2 a vertex
	 */
	public void addEdge(Vertex v1, Vertex v2) {
		if(edgeExists(v1,v2))return;
		adjacenyList.get(v1).add(v2);
		adjacenyList.get(v2).add(v1);
		edges.add(new Edge(v1,v2));
		numOfEdges += 2;
	}
	
	/*
	 * @return the number of vertices in the Graph
	 */
	public static int getNumOfVertexes() {
		return numOfVertexes;
	}
	
	/*
	 * @return the number of edges in the Graph
	 */
	public static int getNumOfEdges() {
		return numOfEdges/2;
	}
	
	/*
	 * Finds the vertex that the user clicked on
	 * @param userClick the location of the mouse click
	 * @return v if the user clicked on a vertex from the graph
	 * @return null if the user did not click on the vertex from the graph
	 */
	public Vertex findVertex(Point userClick) {
		for(Vertex v: adjacenyList.keySet()) {
			
			if(v.getVisualVertex().contains(userClick)) {
				return v;
			}
		}
		return null;
		
	}
	
	/*
	 * Gets a vertex that is in the graph
	 * @param vertexNumber the vertex that the user wants
	 */
	public Vertex getVertex(int vertexNumber) {
		for(Vertex v: adjacenyList.keySet()) {
			if(v.getVertexID()==vertexNumber)return v;
		}
		return null;
	}
	
	public void findEdge(Point userClick) {
		for(Edge e: edges) {
			if(e.getVisualEdge().contains(userClick)) {
				System.out.println("You clicked an edge");
			}
		}
	}
	/*
	 * Gets an edge from the graph
	 * @param head the beginning vertex
	 * @param tail the ending vertex
	 * @return e (an edge) if that edge exists
	 * @return null otherwise
	 */
	public Edge getEdge(Vertex head, Vertex tail) {
		for(Edge e: edges) {
			if(e.getEndpt1().equals(head) && e.getEndpt2().equals(tail))return e;
			if(e.getEndpt1().equals(tail) && e.getEndpt2().equals(head))return e;
		}
		return null;
	}
	
	/*
	 * Checks to see if an edge exists 
	 * @param head the beginning vertex
	 * @param tail the ending vertex
	 * @return true if the edge exists
	 * @return false otherwise
	 */
	public boolean edgeExists(Vertex head, Vertex tail) {
		Edge exist = getEdge(head,tail);
		if(exist !=null)return true;
		return false;
	}
	
	/*
	 * @return all the Vertices of the graph
	 */
	public HashSet<Vertex> getAllVertexes(){
		HashSet<Vertex> vertexes = new HashSet<Vertex>();
		for(Vertex v: adjacenyList.keySet()) vertexes.add(v);
		return vertexes;
	}
	
	/*
	 * Gets all the edges of a given Vertex
	 * @param vertex a vertex
	 * @return all the edges that the vertex is connected to
	 */
	public HashSet<Edge> getVertexEdges(Vertex vertex){
		HashSet<Edge> temp = new HashSet<Edge>();
		for(Edge e: edges) {
			if(e.getEndpt1().equals(vertex) || e.getEndpt2().equals(vertex))temp.add(e);
		}
		return temp;
	}
	

}