import java.awt.Color; 
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

public class RadioButtonListener implements ActionListener{

	//Instance Variables for RadioButtonListener
	private GraphGUI gui;
	
	/*
	 * One Parameter Constructor of a RadioButtonListener
	 * @param gui the current frame that has the canvas
	 */
	public RadioButtonListener(GraphGUI gui) {
		this.gui=gui;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		/*
		 * If any of the radio buttons are chosen, all
		 * radio buttons other are disabled until the user 
		 * exits out of the current option
		 */
		
		//Add a vertex
		if(gui.buttons[0].isSelected()) {
			reset();
			disableUnselectedButtons(0);
			gui.canvas.setRadioButtonState("Add Vertex");
			gui.canvas.setIsEnabled(true);
			
		
		//Add an Edge
		}else if(gui.buttons[1].isSelected()) {
			reset();
			disableUnselectedButtons(1);
			gui.canvas.setRadioButtonState("Add Edge");
			gui.canvas.setIsEnabled(true);
	
		//Remove Vertex
		}else if(gui.buttons[2].isSelected()) {
			reset();
			disableUnselectedButtons(2);
			gui.canvas.setRadioButtonState("Remove Vertex");
			gui.canvas.setIsEnabled(true);
	
		//Remove Edge
		}else if(gui.buttons[3].isSelected()) {
			reset();
			disableUnselectedButtons(3);
			gui.canvas.setRadioButtonState("Remove Edge");
			gui.canvas.setIsEnabled(true);
	
		//Move a vertex
		}else if(gui.buttons[4].isSelected()) {
			reset();
			disableUnselectedButtons(4);
			gui.canvas.setRadioButtonState("Move Vertex");
			gui.canvas.setIsEnabled(true);
			
		/*
		* Enables all radio buttons again and repaints
		* the canvas if any changes have been made
		*/
		}else {
			reset();
			gui.canvas.setRadioButtonState("");
			gui.canvas.setIsEnabled(false);
			for(int i=0;i<gui.buttons.length;++i) {
				gui.buttons[i].setEnabled(true);
			}
			for(Vertex v : gui.canvas.graph.getAllVertexes()) {
				if(v.getVertexState().equals(Color.GREEN)) {
					v.setVertexState(Color.RED);
				}
				HashSet<Edge> edges = gui.canvas.graph.getVertexEdges(v);
				for(Edge ee: edges) {
					if(ee.getEdgeColor().equals(Color.GREEN)) {
						ee.setEdgeColor(Color.BLUE);
					}
				}
			}
			gui.canvas.repaint();
		}
	}
	
	/*
	 * Disables all the radio buttons except for the
	 * one chosen
	 * @param index the radio button that has been clicked
	 */
	private void disableUnselectedButtons(int index) {
		gui.canvas.setIsEnabled(false);
		for(int i=0;i<gui.buttons.length;++i) {
			if(i==index)continue;
			gui.buttons[i].setEnabled(false);
		}
		gui.canvas.setEndpt1(null);
		gui.canvas.setEndpt2(null);
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
		gui.canvas.repaint();
	}
}
