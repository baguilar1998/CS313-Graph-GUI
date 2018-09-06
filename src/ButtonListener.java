import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
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
	
		if(buttonName.equals("Connected Components")) {
			HashSet<Vertex> vertices = gui.canvas.graph.getAllVertexes();
			//HashSet<Vertex> allVisited = new HashSet<>();
			for(Vertex v: vertices) {
				//if(allVisited.equals(vertices))break;
				HashMap<Vertex, Boolean> visited = new HashMap<>();
				
				visited.put(v,false);
				for(Edge ee : gui.canvas.graph.getVertexEdges(v)) {visited.put(ee.getEndpt2(),false);}
				System.out.println(visited);
				
				//Generates a random color
				Random rand = new Random();
				Color rc = new Color(rand.nextFloat(),rand.nextFloat(),rand.nextFloat());
				
				for(Vertex vv : vertices) {
					boolean allValid = true;
					
					for(Edge ee: gui.canvas.graph.getVertexEdges(vv)) {
						if(!ee.getEdgeColor().equals(Color.BLUE))continue;
						else {
							ee.setEdgeColor(rc);
							if(visited.containsKey(ee.getEndpt2())) {
								visited.replace(ee.getEndpt2(),true);
								//allVisited.add(ee.getEndpt2());
							}
						}
					}
					
					for(Vertex x : visited.keySet()) {
						if(!visited.get(x)) {
							allValid = false;
							break;
						}
					}
					if(allValid)break;
					
					
				}

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

}