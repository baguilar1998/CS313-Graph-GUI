import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

public class GraphGUI extends JFrame{
	

	//Instance Variables 
	private JPanel sideMenu;
	protected GraphCanvas canvas;
	protected JTextField weightInput;
	protected JRadioButton[] buttons; 
	private JButton addAllEdges,connectedComponents,cutVertices,help;
	private RadioButtonListener rbl;
	private ButtonListener bl;
	
	// A default constructor for the GraphGUI
	public GraphGUI() {
		setTitle("Graph GUI");
		setSize(1000,700);
		setResizable(true);
		setLocationRelativeTo(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		rbl = new RadioButtonListener(this);
		bl = new ButtonListener(this);
		setComponents();
		setVisible(true);
	}
	
	// A helper function that sets up the whole GUI
	private void setComponents() {
		setLayout(new BoxLayout(this.getContentPane(), BoxLayout.X_AXIS));
	
		sideMenu = new JPanel();
		sideMenu.setAlignmentX(LEFT_ALIGNMENT);
		sideMenu.setMaximumSize(new Dimension(200,700));
		setSideMenu();
		add(sideMenu);

		canvas = new GraphCanvas();
		canvas.setAlignmentX(RIGHT_ALIGNMENT);
		add(canvas);

		
	}

	/*
	 * A helper function that sets up the side menu
	 * for the GUI
	 */
	private void setSideMenu() {
		sideMenu.setLayout(new GridLayout(9,1));
		
		buttons = new JRadioButton[5];
		
		//Add a vertex
		buttons[0] = new JRadioButton("Add a vertex");
		buttons[0].addActionListener(rbl);
		sideMenu.add(buttons[0]);
		
		//Add an edge
		buttons[1]= new JRadioButton("Add an edge");
		buttons[1].addActionListener(rbl);
		sideMenu.add(buttons[1]);
		
		//Remove vertex
		buttons[2] = new JRadioButton("Remove vertex");
		buttons[2].addActionListener(rbl);
		sideMenu.add(buttons[2]);
		
		//Remove edge
		buttons[3] = new JRadioButton("Remove Edge");
		buttons[3].addActionListener(rbl);
		sideMenu.add(buttons[3]);
		
		//Move vertex
		buttons[4] = new JRadioButton("Move Vertex");
		buttons[4].addActionListener(rbl);
		sideMenu.add(buttons[4]);

		//Add all edges
		addAllEdges = new JButton("Add All Edges");
		addAllEdges.addActionListener(bl);
		sideMenu.add(addAllEdges);
		
		//Connected components
		connectedComponents = new JButton("Connected Components");
		connectedComponents.addActionListener(bl);
		sideMenu.add(connectedComponents);
		
		//Show cut vertices
		cutVertices = new JButton("Show Cut Vertices");
		cutVertices.addActionListener(bl);
		sideMenu.add(cutVertices);
		
		//Help
		help = new JButton("Help");
		help.addActionListener(bl);
		sideMenu.add(help);
	}
	

}
