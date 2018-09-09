import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.JTextPane;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class HelpPrompt extends JFrame{
	

	private static final long serialVersionUID = 1L;

	/*
	 * Default Constructor for a HelpPrompt
	 */
	public HelpPrompt() {
		setSize(800,700);
		setTitle("Help");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		setInformation();
		setVisible(true);
	}

	/*
	 * A helper function that sets up the help prompt
	 */
	private void setInformation() {
		setLayout(new GridLayout(1,1));
		JTextPane helpInformation = new JTextPane();
		helpInformation.setEditable(false);
		helpInformation.setFont(new Font("Courier", Font.BOLD, 14));
		helpInformation.setBackground(Color.cyan);
		StyledDocument doc = helpInformation.getStyledDocument();
		SimpleAttributeSet center = new SimpleAttributeSet();
		StyleConstants.setAlignment(center, StyleConstants.ALIGN_CENTER);
		doc.setParagraphAttributes(0, doc.getLength(), center, false);
		helpInformation.setText("Welcome to the Graph GUI !\n"
				+ "This program will allow you to make your own graph.\n"
				+ "Here are some of the following features that this program has.\n\n"
				+ "Add Vertex: Adds a Vertex to the screen\n"
				+ "(Click anywhere on the canvas to add a vertex)\n\n"
				+ "Add Edge: Adds an Edge between two vertices\n"
				+ "(In order to add an edge, two vertices must be present. Select one vertex "
				+ "[this vertex will turn green]. Then "
				+ "select another vertex and it will create an edge)\n\n"
				+ "Remove Vertex: Removes the chosen vertex\n"
				+ "(Click on a vertex to remove the vertex from the screen. It will also remove"
				+ " the following edges that was connected to that vertex)\n\n"
				+ "Remove Edge: Removes the chosen edge\n"
				+ "(To remove an edge, double-click on the following edge. It will not remove the vertices"
				+ " attached to it)\n\n"
				+ "Move Vertex: Moves the selected vertex\n"
				+ "(Click on any vertex [this vertex will turn green] and then click anywhere on the screen"
				+ " to move the vertex)\n\n"
				+ "Add All Edges: Adds all possible edges with the present vertices (Creates a complete graph)\n\n"
				+ "Connected Components: Colors in all connected graphs that are present on the screen\n\n"
				+ "Show Cut Vertices: Shows all the vertices that could disconnect the graph");
		add(helpInformation);
	}
}