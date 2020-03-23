package it.notreference.bungee.randomlobby.api.exceptions;

import it.notreference.bungee.randomlobby.utils.Messages;

public class ConfigurationException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -542816379009267657L;

	private String e;
	
	public ConfigurationException(String motivo) {
		super(motivo);
		e = motivo;
	}
	
	public String getReason() {
		return e;
	}
	
	public void print(String f) {
		Messages.logConsole(f);
	}
	
}
