package main;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import balancer.*;
import gui.gui;
import utils.MACAddress;
import utils.OdinClient;


public class SmartApSelection implements Runnable{
	
	
	public static HashSet<OdinClient> clients = new HashSet<OdinClient>();
	
	private static Map<MACAddress, Set<InetAddress>> hearingMap = new HashMap<MACAddress, Set<InetAddress>> ();
	
	public static Map<InetAddress, Integer> newMapping = new HashMap<InetAddress, Integer> ();
	
	
	
	public static void main(String[] args) {
		
		inicializacionVariables();
		
		String [] network = new String[10];
		
		System.out.println("Balance options:");
		System.out.println("1.- By service");
		System.out.println("2.- By number of clients");
		System.out.println("3.- By broadband");
		System.out.println("Balancer Option: ");
		
		
		String option = "";
		Scanner sc = new Scanner (System.in);
		option = sc.nextLine();
		
		Balancer bl = null;
				
		if(option.equals("1")){
			System.out.println("Balanceo por servicio.");
			bl = new Balancer_service(new HashSet<OdinClient>());
		}
		else if(option.equals("2")){
			System.out.println("Balanceo por numero de clientes.");
			bl = new Balancer_clients_number(clients, hearingMap, newMapping);
			//bl = new Balancer_clients_number(network);

		}
		else if(option.equals("3")){
			System.out.println("Balanceo por ancho de banda usado.");
			bl = new Balancer_service(new HashSet<OdinClient>());
			//bl = new Balancer_broadband(network);

		}
		else{
			System.out.println("Unknow option");
			System.exit(0);
		}
		
		
		Thread info = new Thread(new Info(bl));
		info.start();
		Thread gi = new Thread( new gui(network, bl));		
		gi.start();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	//TODO: para pruebas y tal
	// 4 clientes en la red 192.168.1.1 --> 192.168.1.9
	// 3 ap's, en la red 192.168.0.1
	private static void inicializacionVariables(){
		
		try {
			//Agentes - en la red 0
			OdinClient agent1 = new OdinClient(new MACAddress(new String("00:00:00:00:00:01").getBytes())
					, InetAddress.getByName("192.168.0.1"), "Cosa"); 
			OdinClient agent2 = new OdinClient(new MACAddress(new String("00:00:00:00:00:02").getBytes())
					, InetAddress.getByName("192.168.0.2"), "Cosa"); 
			OdinClient agent3 = new OdinClient(new MACAddress(new String("00:00:00:00:00:03").getBytes())
					, InetAddress.getByName("192.168.0.3"), "Cosa"); 
			
			//Clientes
			OdinClient cliente1 = new OdinClient(new MACAddress(new String("00:00:00:00:00:10").getBytes())
					, InetAddress.getByName("192.168.1.1"), "Cosa"); clients.add(cliente1);
			OdinClient cliente2 = new OdinClient(new MACAddress(new String("00:00:00:00:00:12").getBytes())
					, InetAddress.getByName("192.168.1.2"), "Cosa"); clients.add(cliente2);
			OdinClient cliente3 = new OdinClient(new MACAddress(new String("00:00:00:00:00:13").getBytes())
					, InetAddress.getByName("192.168.1.3"), "Cosa"); clients.add(cliente3);
			OdinClient cliente4 = new OdinClient(new MACAddress(new String("00:00:00:00:00:14").getBytes())
					, InetAddress.getByName("192.168.1.4"), "Cosa"); clients.add(cliente4);
					
					
					
			hearingMap.put(cliente1.getMacAddress(), new HashSet<InetAddress> ());
			hearingMap.put(cliente2.getMacAddress(), new HashSet<InetAddress> ());
			hearingMap.put(cliente3.getMacAddress(), new HashSet<InetAddress> ());
			hearingMap.put(cliente4.getMacAddress(), new HashSet<InetAddress> ());
			
			//agente 1 escucha todo
			hearingMap.get(cliente1.getMacAddress()).add(agent1.getIpAddress());
			hearingMap.get(cliente2.getMacAddress()).add(agent1.getIpAddress());
			hearingMap.get(cliente3.getMacAddress()).add(agent1.getIpAddress());
			hearingMap.get(cliente4.getMacAddress()).add(agent1.getIpAddress());
			
			//agente 2 solo escucha algunos
			hearingMap.get(cliente4.getMacAddress()).add(agent2.getIpAddress());
//			hearingMap.get(cliente3.getMacAddress()).add(agent2.getIpAddress());
//			hearingMap.get(cliente1.getMacAddress()).add(agent2.getIpAddress());
//			
//			//agente 3 solo escucha 1
//			hearingMap.get(cliente1.getMacAddress()).add(agent3.getIpAddress());
			
			System.out.println("creados");
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block111
			e.printStackTrace();
		}
		
	}

}
