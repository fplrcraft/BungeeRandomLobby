package it.notreference.randomlobby.commands;

import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.chat.TextComponent;
import net.md_5.bungee.api.plugin.Command;

public class RandomLobbyCmd extends Command{

	public RandomLobbyCmd() {
		super("randomlobby");
	}

	@Override
	public void execute(CommandSender sender, String[] arg1) {
		
	 sender.sendMessage(new TextComponent("§7This server is using §bRandomLobby §7by §eNotReference§7. §6§l(1.0)"));	
	
	}

}
