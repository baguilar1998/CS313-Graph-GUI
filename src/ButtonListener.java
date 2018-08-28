import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.HashSet;
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
	
		
		/*
		 * Displays a prompt on how to use the gui
		 */
		if(buttonName.equals("Help")) {
			//new HelpPrompt();
		}
	}

}