package main;

import java.net.InetAddress;
import java.util.HashSet;

import balancer.Balancer;
import utils.OdinClient;

public class Info /* TODO Extends OdinApplication */ implements Runnable {
	
	
	// this interval is for allowing the agents to connect to the controller
	private final int INITIAL_INTERVAL = 30000; // in ms

	// this interval this interval is the period for showing the statistics
	private final int REPORTING_PERIOD = 15000; // in ms
	
	private Balancer bl;
	
	public Info(Balancer bl){
		this.bl = bl;
	}
	
	@Override
	public void run() {
		
		try {
			Thread.sleep(INITIAL_INTERVAL);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}

		
		while (true){
			try {
				
				
				Thread.sleep(REPORTING_PERIOD);
				System.out.println("Pregunto a gente cosas.");   
		        
				
				
				bl.balance(obtenerInfo());
				
				System.out.println();
				
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}		
	}
		
	private  HashSet<OdinClient> obtenerInfo(){
		return SmartApSelection.clients;
	}

	

}
