import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.HashSet;
import javax.swing.JPanel;

public class GraphCanvas extends JPanel implements MouseListener{

	//Instance Variables for Graph Canvas
    private static String radioButtonState;
    private static boolean isEnabled; 
    protected Graph graph;
    private static Vertex endpt1,endpt2;
    private static Edge edge;

    /*
     * Default Constructor
     */
    public GraphCanvas()
    {
        isEnabled = false;
        radioButtonState="";
        graph = new Graph();
        this.addMouseListener(this);
    }

    /*
     * @param e sets whether the user can edit on the 
     * canvas or not
     */
    public void setIsEnabled(boolean e) {
    	isEnabled = e;
    }
    
    /*
     * @param v sets the first vertex the user
     * clicked on
     */
    public void setEndpt1(Vertex v) {
    	endpt1=v;
    }
    
    /*
     * @param v sets the second vertex the user
     * clicked on
     */
    public void setEndpt2(Vertex v) {
    	endpt2=v;
    }
    
    /*
     * @return whether the user can edit on the canvas
     */
    public boolean getIsEnabled() {
    	return isEnabled;
    }

    /*
     * @param s sets which option (RadioButton) the 
     * user chose
     */
    public void setRadioButtonState(String s) {
    	radioButtonState=s;
    }
    
    /*
     * @return the option (RadioButton) the user chose
     */
    public String getRadioButtonState() {
    	return radioButtonState;
    }
    
    @Override
    public void mouseClicked(MouseEvent e){
    	
        int x = e.getX();
        int y = e.getY();
        
    	
        // Disables the canvas if the user hasn't chose an option
    	if(!isEnabled)return;
        
    	/*
    	 * (Add A Vertex)
    	 * Adds a vertex to the canvas (displays a red dot)
    	 */
        if(radioButtonState.equals("Add Vertex")) {
        	reset();
        	Vertex v = new Vertex(x,y);
        	graph.addVertex(v);
        	this.paintComponent(this.getGraphics());
        }
        
        /*
         * (Add An Edge)
         * Adds an edge to the two vertices that
         * the user has chosen (a blue line)
         */
        if(radioButtonState.equals("Add Edge")) {
           if(endpt1==null) {
        	   endpt1=graph.findVertex(e.getPoint());
        	   try{
        		   endpt1.setVertexState(Color.GREEN);
        	   }catch (NullPointerException ex) {
    			   this.repaint();
        		   return;
        	   }
        	   this.repaint();
           }else if(endpt1.getVertexState().equals(Color.GREEN)) {
        	   endpt2 = graph.findVertex(e.getPoint());
        	   try {
        		   //Disables user from creating loops
        		   if(endpt2 == null || endpt1==endpt2) throw new NullPointerException();
        		   graph.addEdge(endpt1,endpt2);
        	   }catch (NullPointerException ex) {
             	   endpt1.setVertexState(Color.RED);
             	   this.repaint();
              	   endpt1=null;
              	   endpt2=null;
              	   return;
        	   }
         	   endpt1.setVertexState(Color.RED);
         	   this.repaint();
         	   reset();
          	   endpt1=null;
          	   endpt2=null;
          	   
           }
        }
        
        /**
         * (Remove Vertex)
         * Removes the chosen vertex from the canvas
         * (Removes the edges connected to it as well)
         */
        
        if(radioButtonState.equals("Remove Vertex")) {
        	if(endpt1==null) {
           	   endpt1=graph.findVertex(e.getPoint());
           	   try{
           		   endpt1.setVertexState(Color.GREEN);
           		   graph.removeVertex(endpt1);
           		   endpt1=null;
           		   this.repaint();
           	   }catch (NullPointerException ex) {
           		   endpt1=null;
       			   this.repaint();
           		   return;
           	   }
           	   endpt1=null;
           	   this.repaint();
           	reset();
        	}
        }
        
        /*
         * (Remove Edge)
         * Removes the chosen edge from the canvas
         * (Doesn't remove vertices that were attached to the edge)
         */
        if(radioButtonState.equals("Remove Edge")) {
        	try {
              	edge = graph.findEdge(e.getPoint());
              	graph.removeEdge(edge);
              	edge = null;
              	this.repaint();
        	}catch(NullPointerException err) {
        		System.out.println("Didnt click on an edge");
        		edge = null;
        		return;
        	}
        	edge = null;
        	this.repaint();
        	reset();
        }
        
        /*
         * (Move a Vertex)
         * Moves a chosen vertex to another location of 
         * the canvas
         */
        if(radioButtonState.equals("Move Vertex")) {
            if(endpt1==null) {
            	try {
                    endpt1 = graph.findVertex(e.getPoint());
                    if(endpt1==null) throw new NullPointerException();
            	}catch (NullPointerException ex) {
            		return;
            	}
  			endpt1.setVertexState(Color.GREEN);
  			this.repaint();	
            }else if(endpt1.getVertexState().equals(Color.GREEN)) {
            	Point newPoint = e.getPoint();
            	endpt1.setX(newPoint.x);
            	endpt1.setY(newPoint.y);
            	endpt1.setVertexState(Color.RED);
            	this.repaint();
            	endpt1=null;
            	reset();
            }
            	
        }
        
        
        	
        }
        

    //Paints the canvas
	public void paintComponent(Graphics g){ 
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        
       HashSet<Vertex> allVertexes = graph.getAllVertexes();
      
      
       //Draws all the vertices that the user has created 
       for(Vertex x: allVertexes) {
           	Shape vertex = new Ellipse2D.Double(x.getX()-5, x.getY()-5, x.getSize(), x.getSize());
        	x.setVisualVertex(vertex);
        	g2.setColor(x.getVertexState());
            g2.fill(vertex);
       }

       //Draws all the edges that the user has created
       for(Vertex x: allVertexes) {
        	HashSet<Edge> temp = graph.getVertexEdges(x);
        	for(Edge e : temp) { 
        		Point from = new Point(x.getX(),x.getY());
        		Point to = new Point(e.getEndpt2().getX(),e.getEndpt2().getY());
        		Shape line = new Line2D.Double(from.x, from.y, to.x, to.y);
        		e.setVisualEdge(line);
          		g2.setColor(e.getEdgeColor());
             	g2.setStroke(new BasicStroke(5));
             	g2.draw(line);
        	}
        }
        
    }
	
	/**
	 * A function that resets everything back to normal
	 */
	private void reset() {
		for(Vertex v : graph.getAllVertexes()) {
			if(!v.getVertexState().equals(Color.RED)) {
				v.setVertexState(Color.RED);
			}
			v.setSize(12);
			HashSet<Edge> edges = graph.getVertexEdges(v);
			for(Edge ee: edges) {
				if(!ee.getEdgeColor().equals(Color.BLUE)) {
					ee.setEdgeColor(Color.BLUE);
				}
			}
		}
	}
	
	@Override
	public void mouseEntered(MouseEvent arg0) {}

	@Override
	public void mouseExited(MouseEvent arg0) {}

	@Override
	public void mousePressed(MouseEvent arg0) {}

	@Override
	public void mouseReleased(MouseEvent arg0) {}

}