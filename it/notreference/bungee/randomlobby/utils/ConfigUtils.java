package it.notreference.bungee.randomlobby.utils;

import java.util.List;

import net.md_5.bungee.config.Configuration;
import it.notreference.bungee.randomlobby.RandomLobbyMain;
import it.notreference.bungee.randomlobby.api.exceptions.ConfigurationException;
import it.notreference.bungee.randomlobby.interfaces.ConfigurationManager;

public class ConfigUtils implements ConfigurationManager {

	
	@Override
	public void reloadConfig() throws ConfigurationException {
		
		RandomLobbyMain.i().reloadConfig();
		
	}

	@Override
	public void saveConfig() throws ConfigurationException {
		
		RandomLobbyMain.i().saveConfig();
		
	}

	@Override
	public void setStr(String pt, String val) throws ConfigurationException {
		
		config().set(pt, val);
		
	}

	@Override
	public void setBool(String pt, boolean val) throws ConfigurationException {
		
		config().set(pt, val);
		
	}

	public Configuration config() {
		return RandomLobbyMain.i().getConfig();
	}
	
	@Override
	public String getStr(String pt) throws ConfigurationException {
       try {
    	   return config().getString(pt);
       } catch(Exception e) {
    	   throw new ConfigurationException("Unable to get " + pt + "'s value (string)");
       }
	}
	
	public List<String> getStrList(String pt) throws ConfigurationException {
       try {
    	   return config().getStringList(pt);
       } catch(Exception e) {
    	   throw new ConfigurationException("Unable to get " + pt + "'s list value (string)");
       }
	}

	@Override
	public boolean getBool(String pt) throws ConfigurationException {
		try {
	    	   return config().getBoolean(pt);
	       } catch(Exception e) {
	    	   throw new ConfigurationException("Unable to get " + pt + "'s value (boolean)");
	       }

	}

}
