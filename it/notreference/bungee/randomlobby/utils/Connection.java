package it.notreference.bungee.randomlobby.utils;

import java.util.UUID;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Connection {

	private ProxiedPlayer player;
	private ServerInfo dest;
	private ServerInfo fm;
	private UUID uniqueId;
	private String nme;
	private String ipHost;
	
	public Connection(ProxiedPlayer p, ServerInfo destination, ServerInfo from, UUID unique, String name, String ipHostStr) {
		player = p;
		dest = destination;
		fm = from;
		uniqueId = unique;
	 nme = name;
	 ipHost = ipHostStr;
	}
	
	public ProxiedPlayer getPlayer() {
		return player;
	}
	
	public ServerInfo getDestination() {
		return dest;
	}
	
	public ServerInfo getFrom() {
		return fm;
	}
	
	public UUID getUUID() {
		return uniqueId;
	}
	
	public String getName() {
		return nme;
	}
	
	public String getIP() {
		return ipHost;
	}
	
}
