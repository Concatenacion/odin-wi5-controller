package gui;

import balancer.Balancer;
import utils.Network;

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
	
	private Network network;
	private Balancer bl;
	
	public gui(Network network, Balancer bl){
		this.network=network;
		this.bl=bl;
	}
	
	public void run(){
		
//	    setLayout(new FlowLayout());
//	    
//	    Label agents = new Label ("Numero de agentes:");
//	    TextField txtagents = new TextField(5);
//	    txtagents.setText(String.valueOf(this.numAgents));
//	    txtagents.setEditable(false);
//	    
//	    this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
//	    
//	    Label Clientes = new Label ("Numero de clientes: ");
//	    TextField txtclients= new TextField(5);
//	    txtclients.setText(String.valueOf(numClientes));
//	    txtclients.setEditable(false);
//	    
//	    add(agents);
//	    add(txtagents);	    
//	    add(Clientes);
//	    add(txtclients);
//	        
//	    
//	    Label [] agentsPoints = new Label[numAgents];
//	    for (int i = 0; i < agentsPoints.length; i++) {
//			agentsPoints[i] = new Label("Agente " +i+":"+(numClientes/3));
//			add(agentsPoints[i]);
//		}
	    
		int agents = network.numAgents();
		int clients = network.numClients();
		
		System.out.println("agente"+agents+"clients"+clients);
	    

	    Graph graph = new MultiGraph("Tutorial 1");
	    
	    for (int i = 0; i < agents; i++) {
			graph.addNode("Agente"+i);			
		}
	    
	    for (int i = 0; i<clients; i++){
	    	graph.addNode("cliente"+i);
	    }
		
		graph.display();

	    
//	    setVisible(true);
		
		try {
			System.out.println("");
			Thread.sleep(30000);
			bl.getNetwork();
			
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
