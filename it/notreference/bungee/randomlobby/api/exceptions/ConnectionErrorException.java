package it.notreference.bungee.randomlobby.api.exceptions;

import it.notreference.bungee.randomlobby.utils.Messages;

public class ConnectionErrorException extends Throwable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5461557661030959639L;

	private String e;
	
	public ConnectionErrorException(String motivo) {
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
