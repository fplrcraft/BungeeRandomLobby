package it.notreference.randomlobby.commands;

import it.notreference.bungee.randomlobby.api.exceptions.ConfigurationException;
import it.notreference.bungee.randomlobby.api.exceptions.ConnectionErrorException;
import it.notreference.bungee.randomlobby.api.exceptions.ServerNotFoundException;
import it.notreference.bungee.randomlobby.utils.ConfigUtils;
import it.notreference.bungee.randomlobby.utils.Connection;
import it.notreference.bungee.randomlobby.utils.ConnectionBuilder;
import it.notreference.bungee.randomlobby.utils.ConnectionUtils;
import it.notreference.bungee.randomlobby.utils.Messages;
import it.notreference.bungee.randomlobby.utils.RandomSelectorUtils;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.connection.ProxiedPlayer;
import net.md_5.bungee.api.plugin.Command;

public class CustomCmd extends Command{

	public CustomCmd(String custom) {
		super(custom);
		
	}

	private ConfigUtils conf = new ConfigUtils();
	private ConnectionUtils connectionManager = new ConnectionUtils();
	
	@Override
	public void execute(CommandSender sender, String[] args) {
	
		if(!(sender instanceof ProxiedPlayer)) {
			sender.sendMessage(new TextComponent("ERROR - You must be a player."));
			return;
		}
		
		ProxiedPlayer p = (ProxiedPlayer) sender;
		try {
			if(conf.getStr("blocked-server") == p.getServer().getInfo().getName()) {
			Messages.send(p, conf.getStr("blocked-here"));
			return;
			}
		} catch (ConfigurationException e) {
			e.printStackTrace();
			Messages.logConsole("Unable to find blocked server into configuration.");
		}
				
		Connection con = new ConnectionBuilder()
		                   .setDestination(RandomSelectorUtils.getRandomLobbyServer())
		                   .setIPHost(p.getAddress().getHostString())
		                   .setName(p.getName())
		                   .setFrom(p.getServer().getInfo())
		                   .setPlayer(p)
		                   .setUUID(p.getUniqueId())
		                   .build();
		
		try {
			connectionManager.handleConnection(con);
		} catch (ConnectionErrorException e) {
			e.printStackTrace();
			
		} catch (ServerNotFoundException e) {
			e.printStackTrace();
			
		}
		
	}

}
