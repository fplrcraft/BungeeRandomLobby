package it.notreference.bungee.randomlobby.interfaces;

import it.notreference.bungee.randomlobby.api.exceptions.ConfigurationException;

public interface ConfigurationManager {

	void reloadConfig() throws ConfigurationException;
	void saveConfig() throws ConfigurationException;
	void setStr(String pt, String val) throws ConfigurationException;
	void setBool(String pt, boolean val) throws ConfigurationException;
	String getStr(String pt) throws ConfigurationException; 
	boolean getBool(String pt) throws ConfigurationException;
	
}
