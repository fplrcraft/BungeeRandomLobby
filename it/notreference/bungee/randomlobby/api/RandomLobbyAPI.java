package it.notreference.bungee.randomlobby.api;

import java.util.List;

import it.notreference.bungee.randomlobby.api.exceptions.ConfigurationException;
import it.notreference.bungee.randomlobby.api.exceptions.ConnectionErrorException;
import it.notreference.bungee.randomlobby.api.exceptions.ServerNotFoundException;
import it.notreference.bungee.randomlobby.utils.ConfigUtils;
import it.notreference.bungee.randomlobby.utils.Connection;
import it.notreference.bungee.randomlobby.utils.ConnectionBuilder;
import it.notreference.bungee.randomlobby.utils.ConnectionUtils;
import it.notreference.bungee.randomlobby.utils.RandomSelectorUtils;
import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class RandomLobbyAPI {

 public ConnectionResponse connectRandomLobby(ProxiedPlayer p) {
	 
	 if(!RandomSelectorUtils.lobbyServersAvaliable()) {
		 return ConnectionResponse.ALLOFFLINE;
	 }
	 
	 if(!p.isConnected()) {
		 return ConnectionResponse.UNABLE;
	 }
	 
	Connection con = new ConnectionBuilder()
	                      .setFrom(p.getServer().getInfo())
	                      .setIPHost(p.getAddress().getHostString())
	                      .setPlayer(p)
	                      .setUUID(p.getUniqueId())
	                      .setDestination(RandomSelectorUtils.getRandomLobbyServer())
	                      .setName(p.getName())
	                      .build();
	
	try {
		new ConnectionUtils().handleConnection(con);
		return ConnectionResponse.SUCCESS;
	} catch(Exception exc) {
		exc.printStackTrace();
		return ConnectionResponse.ERROR;
	} catch (ConnectionErrorException e) {
		e.printStackTrace();
		return ConnectionResponse.UNABLE;
	} catch (ServerNotFoundException e) {
		e.printStackTrace();
		return ConnectionResponse.ERROR;
		
	}
	                      
 }
 
 public boolean isALobbyAvaliable() {
	 return RandomSelectorUtils.lobbyServersAvaliable();
 }
 
 public ServerInfo getRandomLobby() {
	 return RandomSelectorUtils.getRandomLobbyServer();
 }
 
 public List<String> getConfigLobbyServers() throws ConfigurationException {
	 return new ConfigUtils().getStrList("lobby-servers");
 }
	
}
