package it.notreference.bungee.randomlobby.utils;

import it.notreference.bungee.randomlobby.RandomLobbyMain;
import it.notreference.bungee.randomlobby.api.exceptions.ConfigurationException;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;

public class Messages {

	
	private static ConfigUtils configUtils = new ConfigUtils();
	
	public static void send(ProxiedPlayer p, String m) {
		String nuovoMessaggio = m.replace("&", "§");
		p.sendMessage(new TextComponent(nuovoMessaggio));
	}
	
	public static String parse(String f) {
		String culo = f.replace("&", "§");
		return culo;
	}
	
	public static void sendParseColors(ProxiedPlayer p, String m) {
		String nuovoMessaggio = m.replace("&", "§");
		p.sendMessage(new TextComponent(nuovoMessaggio));
	}
	
	public static void logConsole(String d) {
		RandomLobbyMain.i().getLogger().info("LOG - " + d);
	}
	
	public static void logStaff(String s, PlaceholderConf conf) {
		try {
			if(!configUtils.getBool("log-staff")) {
				return;
			}
		} catch (ConfigurationException e) {
			e.printStackTrace();
			return;
		}
		for(ProxiedPlayer staff: RandomLobbyMain.i().getProxy().getPlayers()) {
			if(staff.hasPermission("randomlobby.staff")) {
				String placeholderModifica1 = s.replace("[utente]", conf.getName());
				String placeholderModifica2 = placeholderModifica1.replace("[server]", conf.getServer().getName());
				String placeholderFinale = placeholderModifica2.replace("[ip]", conf.ip());
				sendParseColors(staff, placeholderFinale);
			}
		}
	}
	
	

	

	
}
