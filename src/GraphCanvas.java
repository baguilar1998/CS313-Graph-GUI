import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.HashMap;
import java.util.HashSet;
import java.util.PriorityQueue;

import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class GraphCanvas extends JPanel implements MouseListener{

	//Instance Variables of a GraphCanvas
    private GraphGUI frame;
    private static String radioButtonState;
    private static boolean isEnabled; 
    protected static Graph graph;
    private static Vertex endpt1,endpt2;
    private static Edge edge;

    /*
     * One Parameter Constructor of a GraphCanvas
     * @param frame the current JFrame the canvas
     * is on
     */
    public GraphCanvas(GraphGUI frame)
    {
        this.frame=frame;
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
     * @return the first vertex the user clicked on
     */
    public Vertex getEndpt1() {
    	return endpt1;
    }
    
    /*
     * @return the second vertex the user clicked on
     */
    public Vertex getEndpt2() {
    	return endpt2;
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
        
       //DEBUG CODE
      if(radioButtonState.equals("")) {
    	  this.paintComponent(this.getGraphics());
        }
    	
    	if(!isEnabled)return;
        
    	/*
    	 * (Add A Vertex)
    	 * Adds a vertex to the canvas
    	 */
        if(radioButtonState.equals("Add Vertex")) {
        	Vertex v = new Vertex(x,y);
        	graph.addVertex(v);
        	this.paintComponent(this.getGraphics());
        }
        
        /*
         * (Add An Edge)
         * Adds an edge to the two vertices that
         * the user has chosen
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
          	   endpt1=null;
          	   endpt2=null;
          	   
           }
        }
        
        /**
         * (Remove Vertex)
         * Removes the chosen vertex from the canvas
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
        	}
        }
        
        /*
         * (Remove Edge)
         * Removes the chosen edge from the canvas
         */
        if(radioButtonState.equals("Remove Edge")) {
        	try {
              	edge = graph.findEdge(e.getPoint());
              	graph.removeEdge(edge);
              	edge = null;
              	this.repaint();
        	}catch(NullPointerException err) {
        		System.out.println("Didnt click on an edge");
        	}
        	edge = null;
        	this.repaint();
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
            }
            	
        }
        
        
        	
        }
        

	public void paintComponent(Graphics g){ 
        super.paintComponent(g);
        
        Graphics2D g2 = (Graphics2D) g;
        
        
       HashSet<Vertex> allVertexes = graph.getAllVertexes();
      
      /*
       * Draws all the Vertices that the user has
       * placed on the canvas
       */
       for(Vertex x: allVertexes) {
           	Shape vertex = new Ellipse2D.Double(x.getX()-5, x.getY()-5, 12, 12);
        	x.setVisualVertex(vertex);
        	g2.setColor(x.getVertexState());
            g2.fill(vertex);
       }

       /*
        * Draws all the edges that the user has made
        * with their given weights if one exists
        */
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
             	System.out.println(e.getVisualEdge().getBounds2D());
             	

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