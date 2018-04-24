package gui;

import balancer.Balancer;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;  // Using Frame class in package java.awt
import java.awt.Label;
import java.awt.Point;
import java.awt.TextField;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import org.graphstream.graph.Graph;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;

public class gui extends JFrame implements Runnable {
	
    private ImageIcon image = new ImageIcon("PRueba.png");
	
	private int numAgents = 3;
	private int numClientes = 4;
	
	private String [] network;
	private Balancer bl;
	
	public gui(String[] network, Balancer bl){
		this.network=network;
		this.bl=bl;
	}
	
	public void run(){
		
	    setLayout(new FlowLayout());
	    
	    Label agents = new Label ("Numero de agentes:");
	    TextField txtagents = new TextField(5);
	    txtagents.setText(String.valueOf(this.numAgents));
	    txtagents.setEditable(false);
	    
	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	    
	    Label Clientes = new Label ("Numero de clientes: ");
	    TextField txtclients= new TextField(5);
	    txtclients.setText(String.valueOf(numClientes));
	    txtclients.setEditable(false);
	    
	    add(agents);
	    add(txtagents);	    
	    add(Clientes);
	    add(txtclients);
	        
	    
	    Label [] agentsPoints = new Label[numAgents];
	    for (int i = 0; i < agentsPoints.length; i++) {
			agentsPoints[i] = new Label("Agente " +i+":"+(numClientes/3));
			add(agentsPoints[i]);
		}
	    
	    
	    Graph graph = new MultiGraph("Tutorial 1");

		graph.addNode("A");
		graph.addNode("B");
		graph.addNode("C");
		graph.addEdge("AB", "A", "B");
		graph.addEdge("BC", "B", "C");
		graph.addEdge("CA", "C", "A");
		
		JPanel panel = new JPanel();
		panel.setPreferredSize(new Dimension(2000,20000));
		Viewer viewer = new Viewer(graph, Viewer.ThreadingModel.GRAPH_IN_ANOTHER_THREAD);
	    View view = viewer.addDefaultView(false);		
		panel.add((Component) view, BorderLayout.CENTER);
		
	    setTitle("Network");  // "super" Frame sets its title
	    setSize(2500, 2500);
	    
	    add(panel, BorderLayout.CENTER);
	    
	    
	    setVisible(true);
		
		try {
			System.out.println("");
			Thread.sleep(30000);
			bl.getNetwork();
			
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
