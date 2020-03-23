package it.notreference.bungee.randomlobby.api.exceptions;

import it.notreference.bungee.randomlobby.utils.Messages;

public class ServerNotFoundException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -8187257046899006665L;

	
	private String e;
	
	public ServerNotFoundException(String motivo) {
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
