import java.awt.Color; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
public class ButtonListener implements ActionListener{

	private GraphGUI gui;
	//For CutDFS
	private int time=0;
	
	public ButtonListener(GraphGUI gui) {
		this.gui=gui;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String buttonName = e.getActionCommand();
		
		/*
		 * Adds all possibles edges in a graph 
		 * based on the number of vertices that are presented
		 * (Creates a Complete Graph)
		 */
		if(buttonName.equals("Add All Edges")) {
			HashSet<Vertex> vertexes = gui.canvas.graph.getAllVertexes();
			for(Vertex v: vertexes) {
				for(Vertex vv: vertexes) {
					if(v.equals(vv))continue;
					gui.canvas.graph.addEdge(v,vv);
				}
			}
			reset();
			gui.canvas.repaint();
		}
	
		/**
		 * Colors in all the separate graphs
		 */
		if(buttonName.equals("Connected Components")) {
			reset();
			HashSet<Vertex> visited = new HashSet<Vertex>();
			HashSet<Vertex> getAllVertices = gui.canvas.graph.getAllVertexes();
			for(Vertex v: getAllVertices) {
				if(visited.contains(v))continue;
				Random rand = new Random();
				Color rc = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
				ConnectedDFS(v,visited,rc);
			}
			gui.canvas.repaint();
		}
		
		/**
		 * Shows all vertices that can
		 * disconnect the graph (single)
		 */
		if(buttonName.equals("Show Cut Vertices")) {
			reset();
			HashSet<Vertex> visited = new HashSet<Vertex>();
			HashMap<Vertex,Vertex> parent = new HashMap<>();
			HashMap<Vertex,Integer> dis = new HashMap<>();
			HashMap<Vertex,Integer> low = new HashMap<>();
			HashSet<Vertex> cutVertices = new HashSet<>();
			HashSet<Vertex> getAllVertices = gui.canvas.graph.getAllVertexes();

			for(Vertex v: getAllVertices) {
				if(!visited.contains(v)) CutDFS(v,visited,dis,low,parent,cutVertices);
			}
			
			for(Vertex v: cutVertices) {
				v.setSize(22);
				v.setVertexState(Color.GREEN);
			}
			time = 0;
			gui.canvas.repaint();
		}
		
		/*
		 * Displays a prompt on how to use the gui
		 */
		if(buttonName.equals("Help")) {
			new HelpPrompt();
		}
	}
	
	/**
	 * Depth First Search Algorithm to find connected graphs
	 * @param v vertex to be next visited
	 * @param visited a list of visited vertices
	 * @param c color
	 */
	private void ConnectedDFS(Vertex v, HashSet<Vertex> visited, Color c) {
		visited.add(v);
		v.setVertexState(c);
		Iterator<Edge> it = gui.canvas.graph.getVertexEdges(v).iterator();
		while(it.hasNext()) {
			Edge e = it.next();
			e.setEdgeColor(c);
			if(!visited.contains(e.getEndpt2())) {
				ConnectedDFS(e.getEndpt2(),visited,c);
			}
		}
	}
	
	/**
	 * Depth First Search Algorithm to find any cut vertices
	 * @param v vertex to be visited next
	 * @param visited a list of all visited vertices
	 * @param dis discovery time of vertices
	 * @param low lowest discovery time
	 * @param parent parent vertices in DFS tree
	 * @param ap a list of all cut vertices
	 */
	private void CutDFS(Vertex v, HashSet<Vertex> visited, HashMap<Vertex,Integer> dis, 
			HashMap<Vertex,Integer> low, HashMap<Vertex,Vertex> parent, HashSet<Vertex> ap) {
		
		int children = 0;
		visited.add(v);
		++time;
		dis.put(v,time);
		low.put(v, time);
		
		Iterator<Edge> it  = gui.canvas.graph.getVertexEdges(v).iterator();
		while(it.hasNext()) {
			Edge e = it.next();
			if(!visited.contains(e.getEndpt2())) {
				children++;
				parent.put(e.getEndpt2(),v);
				CutDFS(e.getEndpt2(), visited, dis, low, parent, ap);
				
				low.put(v, Math.min(low.get(v), low.get(e.getEndpt2())));
				
				if(parent.get(v) == null && children > 1) ap.add(v);
				
				if(parent.get(v) != null && low.get(e.getEndpt2()) >= dis.get(v)) ap.add(v);
			}
			
			else if (!e.getEndpt2().equals(parent.get(v))) {
				Integer newLow = low.get(v);
				low.put(v,Math.min(newLow, dis.get(e.getEndpt2())));
			}
		}
		
	}
	
	/**
	 * A function that resets everything back to normal
	 */
	private void reset() {
		for(Vertex v : gui.canvas.graph.getAllVertexes()) {
			if(!v.getVertexState().equals(Color.RED)) {
				v.setVertexState(Color.RED);
			}
			v.setSize(12);
			HashSet<Edge> edges = gui.canvas.graph.getVertexEdges(v);
			for(Edge ee: edges) {
				if(!ee.getEdgeColor().equals(Color.BLUE)) {
					ee.setEdgeColor(Color.BLUE);
				}
			}
		}
	}

}