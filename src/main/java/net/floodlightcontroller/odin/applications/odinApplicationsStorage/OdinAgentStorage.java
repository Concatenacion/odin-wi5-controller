package net.floodlightcontroller.odin.applications.odinApplicationsStorage;

import java.net.InetAddress;

public class OdinAgentStorage {
	
	private InetAddress ipAddress;
	private long lastHeard;
	private int channel;
	private int lastScan;
	private int txpower;
	
	public OdinAgentStorage(InetAddress ipAddress, long lastHeard, int channel,
			int lastScan, int txpower) {
		this.ipAddress = ipAddress;
		this.lastHeard = lastHeard;
		this.channel = channel;
		this.lastScan = lastScan;
		this.txpower = txpower;
	}

	public InetAddress getIpAddress() {
		return ipAddress;
	}

	public void setIpAddress(InetAddress ipAddress) {
		this.ipAddress = ipAddress;
	}

	public long getLastHeard() {
		return lastHeard;
	}

	public void setLastHeard(long lastHeard) {
		this.lastHeard = lastHeard;
	}

	public int getChannel() {
		return channel;
	}

	public void setChannel(int channel) {
		this.channel = channel;
	}

	public int getLastScan() {
		return lastScan;
	}

	public void setLastScan(int lastScan) {
		this.lastScan = lastScan;
	}

	public int getTxpower() {
		return txpower;
	}

	public void setTxpower(int txpower) {
		this.txpower = txpower;
	}
	
	
}
