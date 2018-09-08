import java.awt.Color;
import java.awt.Shape;

public class Edge {
	
	//Instance Variables for Edges
	private Vertex endpt1,endpt2;
	private Color edgeColor;
	private Shape visualEdge;
	
	
	/*
	 * Default Constructor for an Edge
	 */
	public Edge() {
		endpt1=null;
		endpt2=null;
		edgeColor=Color.BLUE;
	}
	
	/*
	 * One Parameter Constructor for an edge
	 * @param endpt1 the initial vertex
	 */
	public Edge(Vertex endpt1) {
		super();
		this.endpt1=endpt1;
		edgeColor=Color.BLUE;
	
	}

	/*
	 * Two Parameter Constructor for an edge
	 * @param endpt1 the initial vertex
	 * @param endpt2 the ending vertex
	 */
	public Edge(Vertex endpt1,Vertex endpt2) {
		super();
		this.endpt1=endpt1;
		this.endpt2=endpt2;
		edgeColor=Color.BLUE;
	}

	/*
	 * @return the beginning vertex of an edge
	 */
	public Vertex getEndpt1() {
		return endpt1;
	}
	
	/*
	 * @return the ending vertex of an edge
	 */
	public Vertex getEndpt2() {
		return endpt2;
	}

	/*
	 * @return the edge color
	 */
	public Color getEdgeColor() {
		return edgeColor;
	}
	
	public Shape getVisualEdge() {
		return visualEdge;
	}

	/*
	 * Sets the color of the edge
	 * @param c Color
	 */
	public void setEdgeColor(Color c) {
		edgeColor=c;
	}
	
	/**
	 * Sets the edge shape
	 * @param s a line shape
	 */
	public void setVisualEdge(Shape s) {
		visualEdge = s;
	}
	

	public String toString() {
		return "Head: " + endpt1.getVertexID() + " Tail: " + endpt2.getVertexID();
	}


}
