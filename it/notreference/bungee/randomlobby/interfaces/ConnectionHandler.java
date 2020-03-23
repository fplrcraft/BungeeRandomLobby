package it.notreference.bungee.randomlobby.interfaces;

import net.md_5.bungee.api.config.ServerInfo;
import it.notreference.bungee.randomlobby.api.exceptions.ConnectionErrorException;
import it.notreference.bungee.randomlobby.api.exceptions.ServerNotFoundException;
import it.notreference.bungee.randomlobby.utils.Connection;

public interface ConnectionHandler {

	void handleConnection(Connection con) throws ConnectionErrorException, ServerNotFoundException;
	void destroyConnection(Connection con);
	boolean isPremium(Connection con);
	ServerInfo getInfo(String server) throws ServerNotFoundException;
}
