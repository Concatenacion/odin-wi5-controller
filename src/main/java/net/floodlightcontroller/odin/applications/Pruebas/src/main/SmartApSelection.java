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
import utils.Network;
import utils.OdinClient;


public class SmartApSelection implements Runnable{
	
	
	public static HashSet<OdinClient> clients = new HashSet<OdinClient>();
	
	private static Map<MACAddress, Set<InetAddress>> hearingMap = new HashMap<MACAddress, Set<InetAddress>> ();
	
	public static Map<InetAddress, Integer> newMapping = new HashMap<InetAddress, Integer> ();	
	
	
	public static void main(String[] args) {
				
		
		Network net = new Network(clients);

		
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
			bl = new Balancer_service(net);
		}
		else if(option.equals("2")){
			System.out.println("Balanceo por numero de clientes.");
			bl = new Balancer_clients_number(net);
			//bl = new Balancer_clients_number(network);

		}
		else if(option.equals("3")){
			System.out.println("Balanceo por ancho de banda usado.");
			bl = new Balancer_service(net);
			//bl = new Balancer_broadband(network);

		}
		else{
			System.out.println("Unknow option");
			System.exit(0);
		}
		
		
		Thread info = new Thread(new Info(bl));
		info.start();
		
		Thread gui = new Thread(new gui(net, bl));
		gui.start();

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	
}
