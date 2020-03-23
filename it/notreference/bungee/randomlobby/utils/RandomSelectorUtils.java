package it.notreference.bungee.randomlobby.utils;

import java.util.List;
import java.util.Random;

import it.notreference.bungee.randomlobby.api.exceptions.ConfigurationException;
import it.notreference.bungee.randomlobby.api.exceptions.ServerNotFoundException;
import net.md_5.bungee.api.config.ServerInfo;

public class RandomSelectorUtils {

	public static ServerInfo getRandomLobbyServer() {
		try {
			List<String> lobby = new ConfigUtils().getStrList("lobby-servers");
			Random random = new Random();
			int rando = 0;
			for(int i =0; i<3; i++){
			    int r = random.nextInt(lobby.size());
			    rando = r;
			}
			try {
				return new ConnectionUtils().getInfo(lobby.get(rando));
			} catch (ServerNotFoundException e) {
				
				e.printStackTrace();
				return null;
				
			}
		} catch(Exception | ConfigurationException exc) {
			return null;
		}
	}
	
	
	public static boolean lobbyServersAvaliable() {
		try {
			List<String> lobby = new ConfigUtils().getStrList("lobby-servers");
			for(String server: lobby) {
				try {
					if(new ConnectionUtils().getInfo(server) != null) {
						return true;
					} else {
						return false; 
					}
				} catch (ServerNotFoundException e) {
					e.printStackTrace();
					return false;
				}
			}
		} catch (ConfigurationException e) {
			
			e.printStackTrace();
			return false;
			
		}
		return false;
	}
	
}
