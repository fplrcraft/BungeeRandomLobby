package it.notreference.bungee.randomlobby.api;

import net.md_5.bungee.api.plugin.Event;
import it.notreference.bungee.randomlobby.RandomLobbyMain;

public class RandomLobbyEventAPI {

	public static void fire(Event e) {
		RandomLobbyMain.i().getProxy().getPluginManager().callEvent(e);
	}
	
}
