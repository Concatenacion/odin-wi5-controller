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
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;


import org.graphstream.graph.Graph;
import org.graphstream.graph.Node;
import org.graphstream.graph.implementations.MultiGraph;
import org.graphstream.graph.implementations.SingleGraph;
import org.graphstream.ui.graphicGraph.GraphicGraph;
import org.graphstream.ui.view.View;
import org.graphstream.ui.view.Viewer;
import org.graphstream.ui.view.util.MouseManager;

public class gui extends JFrame implements Runnable {
	
    private ImageIcon image = new ImageIcon("PRueba.png");
	
	private int numAgents = 3;
	private int numClientes = 4;
	
	private Network network;
	
	public gui(Network network, Balancer bl){
		this.network=network;
	}
	
	public void run(){
		
		System.setProperty("org.graphstream.ui.renderer", "org.graphstream.ui.j2dviewer.J2DGraphRenderer");
		
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
	    

	    Graph graph = new MultiGraph("Network");
	    
//	    try {
//	    	    	
//	    	String text = new Scanner(new File("/stylesheet.txt"), "UTF-8").useDelimiter("\\A").next();
//	    	System.out.println(text);
//			
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
		String styleSheet = 
				"node {"
						+ "size: 50px;"
						+ "z-index: 1;"
				+ "}"
				+ "edge {"
					+ "size: 3px;"
					+ "z-index: 0;"
				+ "}"
				+ "node.agent {"
				 	+ "fill-image: url('./wireless_wifi_symbol.png'); "
				 	+ "fill-mode: image-scaled; "
				 	+ "shape: box;"
				 	+ "stroke-mode: plain;"
				 	+ "stroke-color: white;"
				 + "}"
				 + "node.client {fill-image: url('./mobile_icon.png'); "
				 + "fill-mode: image-scaled;}"
				 + "edge.bad { fill-color: red;}"
				 + "edge.stable { fill-color: yellow;}" 
				 + "edge.good { fill-color: green;}";
				 
		
	    
	    graph.addAttribute("ui.stylesheet", styleSheet);
	    
	    graph.setStrict(true);
	    

	    graph.addNode("A1");
	    graph.addNode("A2");
	    graph.addNode("A3");
	    
	    
	    graph.addNode("192.168.1.1");
	    graph.addEdge("cliente1-agente1", "192.168.1.1","A1");
	    graph.getEdge("cliente1-agente1").addAttribute("ui.class", "bad");
	    
	    graph.addNode("192.168.1.2");
	    graph.addEdge("cliente2-agente2", "192.168.1.2","A2");
	    graph.getEdge("cliente2-agente2").addAttribute("ui.class", "good");

	    graph.addNode("192.168.1.3");
	    graph.addEdge("cliente3-agente2", "192.168.1.3","A2");
	    graph.getEdge("cliente3-agente2").addAttribute("ui.class", "stable");

	    graph.addNode("192.168.1.4");
	    graph.addEdge("cliente4-agente3", "192.168.1.4","A3"); 
	    graph.getEdge("cliente4-agente3").addAttribute("ui.class", "good");

		
		Viewer vie = graph.display(false);
		//View view = vie.addDefaultView(false);  
	
		
		graph.addAttribute("ui.quality");
		graph.addAttribute("ui.antialias");


		
		vie.disableAutoLayout();
		
		Node agente1 = graph.getNode("A1");
		agente1.setAttribute("xyz", 1, 1, 0);
		agente1.addAttribute("ui.class", "agent");
		
		Node cliente1 = graph.getNode("192.168.1.1");
		cliente1.setAttribute("xyz", 1, 0, 0);
		cliente1.addAttribute("ui.class", "client");
		
		
		Node agente2 = graph.getNode("A2");		
		agente2.setAttribute("xyz", 6, 1, 0);
		agente2.addAttribute("ui.class", "agent");
		
		Node cliente2 = graph.getNode("192.168.1.2");
		cliente2.setAttribute("xyz", 5, 0, 0);
		cliente2.addAttribute("ui.class", "client");

		
		Node cliente3 = graph.getNode("192.168.1.3");
		cliente3.setAttribute("xyz", 7, 0, 0);
		cliente3.addAttribute("ui.class", "client");

		
		Node agente3 = graph.getNode("A3");
		agente3.setAttribute("xyz", 11, 1, 0);
		agente3.addAttribute("ui.class", "agent");
		
		Node cliente4 = graph.getNode("192.168.1.4");
		cliente4.setAttribute("xyz", 11, 0, 0);
		cliente4.addAttribute("ui.class", "client");

			
		
		//vie.enableAutoLayout();
	    
//	    setVisible(true);
		
		try {
			System.out.println("");
			Thread.sleep(3000);
			System.out.println("Cambiando colores.");
			graph.getEdge("cliente4-agente3").setAttribute("ui.class", "bad");
			
		    graph.getEdge("cliente3-agente2").setAttribute("ui.class", "good");
		    

			
			
			
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
