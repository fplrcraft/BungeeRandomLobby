package it.notreference.bungee.randomlobby.utils;

import net.md_5.bungee.api.config.ServerInfo;

/**
 * PremiumLogin 1.1 by NotReference
 *
 * @description Autologin premium players easily and safely.
 * @dependency AuthMe 5.5.0
 */

public class PlaceholderConf {

	private String name;
	private ServerInfo ud;
	private String ipp; //inetsocketaddress
	
	public PlaceholderConf(String nick, ServerInfo i, String ip) {
		name = nick;
		ud = i;
		ipp = ip;
	}
	
	public String getName() {
		return name;
	}
	
	public ServerInfo getServer() {
		return ud;
	}
	
	public String ip() {
		return ipp;
	}
	
	
}
