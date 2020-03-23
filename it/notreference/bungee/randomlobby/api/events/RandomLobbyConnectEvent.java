package it.notreference.bungee.randomlobby.api.events;

import it.notreference.bungee.randomlobby.utils.Messages;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Event;

public class RandomLobbyConnectEvent extends Event{

	
	private ProxiedPlayer pl;
	private ServerInfo f;
	private ServerInfo t;
	
	public RandomLobbyConnectEvent(ProxiedPlayer p, ServerInfo from, ServerInfo destination)  {
		pl = p;
		f = from;
		t = destination;
	}
	
	public void kick(String reason) {
		pl.disconnect(new TextComponent(Messages.parse(reason)));
	}
	
	public ProxiedPlayer getPlayer() {
		return pl;
	}
	
	public ServerInfo getFrom() {
		return f;
	}
	
	public ServerInfo getDestination() {
		return t;
	}
	
}
