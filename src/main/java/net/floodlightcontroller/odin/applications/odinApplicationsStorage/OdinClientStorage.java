package net.floodlightcontroller.odin.applications.odinApplicationsStorage;

import java.net.InetAddress;

import net.floodlightcontroller.odin.master.Lvap;
import net.floodlightcontroller.util.MACAddress;

public class OdinClientStorage {
	
	private final MACAddress hwAddress;
	private InetAddress ipAddress;
	private Lvap lvap;
	
	
	public OdinClientStorage(MACAddress hwAddress, InetAddress ipAddress,
			Lvap lvap) {
		super();
		this.hwAddress = hwAddress;
		this.ipAddress = ipAddress;
		this.lvap = lvap;
	}
	
	
}
