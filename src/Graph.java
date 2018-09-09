import java.awt.Point;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;


public class Graph {
	
	//Instance Variables for a Graph
	private static HashMap<Vertex,HashSet<Edge>> currentGraph;
	private static int numOfEdges,numOfVertexes;
	
	/*
	 * Default Constructor that creates an
	 * empty Graph
	 */
	public Graph() {
		currentGraph = new HashMap<Vertex,HashSet<Edge>>();
		numOfEdges = 0;
		numOfVertexes = 0;
	}
	
	/*
	 * Adds a vertex to the graph
	 * @param v a vertex
	 */
	public void addVertex(Vertex v) {
		if(!currentGraph.containsKey(v)) {
			currentGraph.put(v, new HashSet<Edge>());
			++numOfVertexes;
		}
	}
	
	/*
	 * Adds an edge to a graph that's incidence 
	 * with the given vertices
	 * @param v1 a vertex
	 * @param v2 a vertex
	 */
	public void addEdge(Vertex v1, Vertex v2) {
		if(edgeExists(v1,v2))return;
		currentGraph.get(v1).add(new Edge(v1,v2));
		currentGraph.get(v2).add(new Edge(v2,v1));
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
		for(Vertex v: currentGraph.keySet()) {
			
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
		for(Vertex v: currentGraph.keySet()) {
			if(v.getVertexID()==vertexNumber)return v;
		}
		return null;
	}
	
	/**
	 * Removes a vertex from the graph along with it's edges
	 * @param v the vertex that the user clicked on
	 */
	public void removeVertex(Vertex v) {
		Iterator<?> it = currentGraph.entrySet().iterator();
		while(it.hasNext()) {
			Map.Entry pair = (Map.Entry) it.next();
			if(pair.getKey().equals(v)) {
				it.remove();
				currentGraph.remove(v);
				break;
			}
		}
		
		removeEdges(v);
	}
	
	/**
	 * Helper method to move all the edges from a given
	 * vertex
	 * @param c a vertex
	 */
	private void removeEdges(Vertex c) {
		for(Vertex v : currentGraph.keySet()) {
			HashSet<Edge> ee = currentGraph.get(v);
			Iterator<?> it = ee.iterator();
			while(it.hasNext()) {
				Edge e = (Edge) it.next();
				if(e.getEndpt2().equals(c)) {
					it.remove();
					ee.remove(e);
					break;
				}
			}
			
		}
	}

	/*
	 * Checks to see if an edge exists 
	 * @param head the beginning vertex
	 * @param tail the ending vertex
	 * @return true if the edge exists
	 * @return false otherwise
	 */
	public boolean edgeExists(Vertex head, Vertex tail) {
		for(Edge e: currentGraph.get(head)) {
			if(e.getEndpt2().equals(tail)) return true;
		}
		for(Edge e: currentGraph.get(tail)) {
			if(e.getEndpt2().equals(head)) return true;
		}
		return false;
	}
	
	/**
	 * Finds a given edge from the graph
	 * @param userClick current click
	 * @return the edge that the user clicked on
	 */
	public Edge findEdge(Point userClick) {
		for(Vertex v: currentGraph.keySet()) {
			for(Edge e: currentGraph.get(v)) {
				if(e.getVisualEdge().getBounds().contains(userClick)) {
					return e;
				}
			}
		}
		return null;
	}
	
	/**
	 * Removes an edge from the graph while keeping the
	 * vertices
	 * @param e an edge
	 */
	public void removeEdge(Edge e) {
		for(Vertex v: currentGraph.keySet()) {
			HashSet<Edge> edges = currentGraph.get(v);
			if(edges.contains(e)) edges.remove(e);
		}
	}
	/*
	 * @return all the Vertices of the graph
	 */
	public HashSet<Vertex> getAllVertexes(){
		HashSet<Vertex> vertexes = new HashSet<Vertex>();
		for(Vertex v: currentGraph.keySet()) vertexes.add(v);
		return vertexes;
	}
	
	/*
	 * Gets all the edges of a given Vertex
	 * @param vertex a vertex
	 * @return all the edges that the vertex is connected to
	 */
	public HashSet<Edge> getVertexEdges(Vertex vertex){
		return currentGraph.get(vertex);
	}
}
	
