package it.notreference.bungee.randomlobby.utils;

import java.util.UUID;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class ConnectionBuilder {

	private ProxiedPlayer player;
	private ServerInfo dest;
	private ServerInfo fm;
	private UUID uniqueId;
	private String nme;
	private String ipHost;
	
	public ConnectionBuilder setPlayer(ProxiedPlayer p) {
		player = p;
		return this;
	}
	
	public ConnectionBuilder setDestination(ServerInfo destination) {
		dest = destination;
		return this;
	}
	
	public ConnectionBuilder setFrom(ServerInfo from) {
		fm = from;
		return this;
	}
	
	public ConnectionBuilder setUUID(UUID uuid) {
		uniqueId = uuid;
		return this;
	}
	 
	public ConnectionBuilder setName(String name) {
		nme = name;
		return this;
	}
	
	public ConnectionBuilder setIPHost(String ip) {
		ipHost = ip;
		return this;
	}
	
	public Connection build() {
		return new Connection(player, dest, fm, uniqueId, nme, ipHost);
	}
	
	
	
}
