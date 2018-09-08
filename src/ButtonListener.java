import java.awt.Color; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Random;
import javax.swing.JOptionPane;
public class ButtonListener implements ActionListener{

	private GraphGUI gui;
	
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
			gui.canvas.repaint();
		}
	
		/**
		 * Colors in all the separate graphs
		 */
		if(buttonName.equals("Connected Components")) {
			HashSet<Vertex> visited = new HashSet<Vertex>();
			HashSet<Vertex> getAllVertices = gui.canvas.graph.getAllVertexes();
			for(Vertex v: getAllVertices) {
				if(visited.contains(v))continue;
				Random rand = new Random();
				Color rc = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
				DFS(v,visited,rc);
			}
			gui.canvas.repaint();
		}
		
		/*
		 * Displays a prompt on how to use the gui
		 */
		if(buttonName.equals("Help")) {
			//new HelpPrompt();
		}
	}
	
	
	public void DFS(Vertex v, HashSet<Vertex> visited, Color c) {
		visited.add(v);
		Iterator<Edge> it = gui.canvas.graph.getVertexEdges(v).iterator();
		while(it.hasNext()) {
			System.out.println(it);
			Edge e = it.next();
			e.setEdgeColor(c);
			if(!visited.contains(e.getEndpt2())) {
				DFS(e.getEndpt2(),visited,c);
			}
		}
	}

}