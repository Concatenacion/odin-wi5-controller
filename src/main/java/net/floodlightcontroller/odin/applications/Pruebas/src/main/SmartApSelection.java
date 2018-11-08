package net.floodlightcontroller.odin.applications.Pruebas.src.main;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import balancer.*;
import gui.gui;
import gui.PopUpAccept;

import net.floodlightcontroller.odin.master.OdinApplication;
import net.floodlightcontroller.odin.master.OdinClient;
import net.floodlightcontroller.util.MACAddress;
import utils.Network;


public class SmartApSelection extends OdinApplication {
	
	
	public static HashSet<OdinClient> clients = new HashSet<OdinClient>();
	
	private static Map<MACAddress, Set<InetAddress>> hearingMap = new HashMap<MACAddress, Set<InetAddress>> ();
	
	public static Map<InetAddress, Integer> newMapping = new HashMap<InetAddress, Integer> ();	
  
  private final static int INITIAL_INTERVAL = 30000; // in ms

	
	
	public void run() {
 
   System.out.println("DENTRO");
 
 		PopUpAccept.infoBox("Se ha terminado la configuracion de los clientes?", "INICIO");
   
		Network net = new Network(getClients(),getAgents());
    while(true){
      try {
        Thread.sleep(1000);
        net = new Network(getClients(), getAgents());
        
        System.out.println("CLIENTES - "+net.numClients());
        System.out.println("AGENTES - "+net.numClients());
      } catch (InterruptedException e) {
  	      e.printStackTrace();
      }
    }
  

		
		//String option = "";
		//Scanner sc = new Scanner (System.in);
		//option = sc.nextLine();
		
		//Balancer bl = new Balancer_clients_number(net);
				
		//if(option.equals("1")){
		//	System.out.println("Balanceo por servicio.");
			//bl = new Balancer_service(net);
		//}
		//else if(option.equals("2")){
	//		System.out.println("Balanceo por numero de clientes.");
		//	bl = new Balancer_clients_number(net);
			//bl = new Balancer_clients_number(network);

		//}
		//else if(option.equals("3")){
		//	System.out.println("Balanceo por ancho de banda usado.");
		//	bl = new Balancer_service(net);
		//	//bl = new Balancer_broadband(network);

		//}
		//else{
		//	System.out.println("Unknow option");
		//	System.exit(0);
		//}
		
		
		//Thread info = new Thread(new Info(bl));
		//info.start();
		
		//Thread gui = new Thread(new gui(net, bl));
		//gui.start();

	}
	
}
