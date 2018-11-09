package net.floodlightcontroller.odin.applications.odinApplicationsStorage;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.floodlightcontroller.storage.IPredicate;
import net.floodlightcontroller.storage.IResultSet;
import net.floodlightcontroller.storage.IStorageSourceService;
import net.floodlightcontroller.storage.OperatorPredicate;
import net.floodlightcontroller.storage.StorageException;


public class SmartApSelectionStorage {
	
	private IStorageSourceService storageSourceService;
		
	public String SMARTAP_PARAMS = "SMARTAP_PARAMS";
	
	public String SMARTAP_AGENTS = "SMARTAP_AGENTS";
	
	public String SMARTAP_CLIENTS = "SMARTAP_CLIENTS";
	
	public SmartApSelectionStorage(IStorageSourceService storageSourceService) {
		this.storageSourceService = storageSourceService;
	}	
	
	public void initStorage(){
		//Parametros de la aplicacion		

		Set<String> columns = new HashSet<String>();
		columns.add("SMARTAP_PARAMS");


		this.storageSourceService.createTable("PARAMETERS", columns);		
		
		//Agentes
		columns = new HashSet<String>();
		columns.add("agent");		
		
		this.storageSourceService.createTable(SMARTAP_AGENTS, columns);		
		
		try {
			insertAgent(new OdinAgentStorage(InetAddress.getByName("173.39.161.140"), 10, 14, 30, 80));
			getAgents();
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		}
		
		
		//Clientes
		columns = new HashSet<String>();
		columns.add("CLIENT");
				
		this.storageSourceService.createTable(SMARTAP_CLIENTS, columns);		

	}	
	
	//OPERACIONES SOBRE TABLAS
	//TODO SI SE VA DE MADRE METER EN CLASES SEPARADAS
	
	//CLIENTES
	public List<OdinClientStorage> getClients(){
		return new ArrayList<OdinClientStorage>();
	}
	
	public void insertClient(OdinClientStorage client){
		
	}
	
	//AGENTES
	public List<OdinAgentStorage> getAgents(){
			
		List<OdinAgentStorage> agents = new ArrayList<OdinAgentStorage>();
		
		IResultSet resultSet = this.storageSourceService.executeQuery("SMARTAP_AGENTS",null,null, null);
		
		try{
			Map<String, Object> res = resultSet.getRow();
			System.out.println(res.toString());		
		}
		catch(StorageException ex){
			System.out.println("PROBLEMA ENCONTRADO AQUI");
		}
		
//		agents.add((OdinAgentStorage)resultSet.getRow());
		
		return agents;

	}
	
	public void insertAgent(OdinAgentStorage agent){
		Map<String, Object> rowToInsert = new HashMap<String, Object>();		
		rowToInsert.put("agent", agent);
		
		storageSourceService.insertRow("SMARTAP_AGENTS", rowToInsert);
	}
	
	
	

}
