package it.notreference.bungee.randomlobby.utils;

import java.util.concurrent.TimeUnit;

import net.md_5.bungee.api.config.ServerInfo;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.scheduler.ScheduledTask;
import it.notreference.bungee.randomlobby.RandomLobbyMain;
import it.notreference.bungee.randomlobby.api.RandomLobbyEventAPI;
import it.notreference.bungee.randomlobby.api.events.RandomLobbyConnectEvent;
import it.notreference.bungee.randomlobby.api.exceptions.ConfigurationException;
import it.notreference.bungee.randomlobby.api.exceptions.ConnectionErrorException;
import it.notreference.bungee.randomlobby.api.exceptions.ServerNotFoundException;
import it.notreference.bungee.randomlobby.interfaces.ConnectionHandler;

public class ConnectionUtils implements ConnectionHandler{

	@Override
	public void handleConnection(Connection con)
			throws ConnectionErrorException, ServerNotFoundException {
		ProxiedPlayer p = con.getPlayer();
		ServerInfo destination = con.getDestination();
		ServerInfo from = con.getFrom();
		if(!p.isConnected()) {
			throw new ConnectionErrorException("Unable to find player " + p.getName());
		}
		if(p.getServer().getInfo() == destination) {
			try {
				Messages.send(p, new ConfigUtils().getStr("arleady-con"));
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}
			throw new ConnectionErrorException("This player is arleady connected.");
		}
		try {
			Messages.logStaff(new ConfigUtils().getStr("staff-log-sending"), new PlaceholderConf(p.getName(), con.getDestination(), p.getAddress().getHostString()));
			Messages.send(p, new ConfigUtils().getStr("sending").replace("[server]", destination.getName()));
			Messages.send(p, new ConfigUtils().getStr("connecting").replace("[server]", destination.getName()));
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}    
		try {
			
			p.connect(destination);
			
		} catch(Exception exc) {
			try {
				
				Messages.send(p , new ConfigUtils().getStr("error-con").replace("[server]", destination.getName()));
				Messages.send(p, new ConfigUtils().getStr("error-back"));
				
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}
			p.connect(from);
			try {
				Messages.logStaff(new ConfigUtils().getStr("staff-log-fail"), new PlaceholderConf(p.getName(), con.getDestination(), p.getAddress().getHostString()));
			} catch (ConfigurationException e) {
				e.printStackTrace();
			}
			exc.printStackTrace();
		    throw new ConnectionErrorException("Unable to connect " + p.getName() + " to " + destination.getName());
		}
		ScheduledTask task = RandomLobbyMain.i().getProxy().getScheduler().schedule(RandomLobbyMain.i(), new Runnable() {

			@Override
			public void run() {
			
				if(p.getServer().getInfo() == destination) {
					try {
						Messages.logStaff(new ConfigUtils().getStr("staff-log-sending"), new PlaceholderConf(p.getName(), con.getDestination(), p.getAddress().getHostString()));
						Messages.send(p , new ConfigUtils().getStr("success").replace("[server]", destination.getName()));
						Messages.logStaff(new ConfigUtils().getStr("staff-log-success"), new PlaceholderConf(p.getName(), con.getDestination(), p.getAddress().getHostString()));
					    RandomLobbyEventAPI.fire(new RandomLobbyConnectEvent(p, from, destination));
						destroyConnection(con);
						
					} catch (ConfigurationException e) {
						e.printStackTrace();
					}
				} else {
					try {
						
						
						Messages.send(p , new ConfigUtils().getStr("error-con").replace("[server]", destination.getName()));
						Messages.send(p, new ConfigUtils().getStr("error-back"));
						Messages.logStaff(new ConfigUtils().getStr("staff-log-fail"), new PlaceholderConf(p.getName(), con.getDestination(), p.getAddress().getHostString()));
                        p.connect(from);
						
					} catch (ConfigurationException e) {
						e.printStackTrace();
					}
				}
				
			}
			
		}, 20L, TimeUnit.SECONDS);
		task.cancel();
	}

	@Override
	public void destroyConnection(Connection con) {
		con = null;
	}

	@Override
	public boolean isPremium(Connection con) {
		return con.getPlayer().getPendingConnection().isOnlineMode();
	}

	@Override
	public ServerInfo getInfo(String server) throws ServerNotFoundException {
		try {
			ServerInfo d = RandomLobbyMain.i().getProxy().getServerInfo(server);
			return d;
		} catch(Exception exc) {
			throw new ServerNotFoundException("Unable to find server " + server);
		}
	}
	
	

}
