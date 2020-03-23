package it.notreference.bungee.randomlobby;

import it.notreference.bungee.randomlobby.api.exceptions.ConfigurationException;
import it.notreference.bungee.randomlobby.utils.ConfigUtils;
import it.notreference.randomlobby.commands.CustomCmd;
import it.notreference.randomlobby.commands.HubCmd;
import it.notreference.randomlobby.commands.LobbyCmd;
import it.notreference.randomlobby.commands.RandomLobbyCmd;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

public class RandomLobbyMain extends Plugin{

	private static RandomLobbyMain x;
	private Configuration configuration;
	
	
	@Override
	public void onEnable() {
		setInstance(this);
		
		//Config
		 if (!getDataFolder().exists())
           getDataFolder().mkdir();

       File file = new File(getDataFolder(), "config.yml");

  
       if (!file.exists()) {
           try (InputStream in = getResourceAsStream("config.yml")) {
               Files.copy(in, file.toPath());
           } catch (IOException e) {
          	 getLogger().info("ERR - Cannot create the configuration.");
               e.printStackTrace();
           }
       }
		
		//Config.
		try {
		configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
		} catch(Exception exc) {
			exc.printStackTrace();
			getLogger().info(exc.getMessage());
			getLogger().info("ERR - Cannot load configuration.. Try to delete it.. Disabling..");
			return;
		}
		
		getProxy().getPluginManager().registerCommand(this, new HubCmd());
		getProxy().getPluginManager().registerCommand(this, new LobbyCmd());
		getProxy().getPluginManager().registerCommand(this, new RandomLobbyCmd());
		try {
			getProxy().getPluginManager().registerCommand(this, new CustomCmd(new ConfigUtils().getStr("custom-command")));
		} catch (ConfigurationException e) {
			e.printStackTrace();
			getLogger().info("ERR - Unable to enable CustomCommand.");
		}
		
		getLogger().info("HELLO - RandomLobby 1.0 by NotReference Successfully enabled.");
		
	}
	
	@Override
	public void onDisable() {
		getLogger().info("BYE - Disabled RandomLobby 1.0 by NotReference.. Byee.. <3");
	}
	
	public Configuration getConfig() {
		return configuration;
	}
	
	public void saveConf() {
		
		saveConfig();
		
	}
	
	public void reloadConf() {
		
		reloadConfig();
		
	}
	
	public void reloadConfig() {
		try {
			configuration = ConfigurationProvider.getProvider(YamlConfiguration.class).load(new File(getDataFolder(), "config.yml"));
			} catch(Exception exc) {
				exc.printStackTrace();
				getLogger().info("ERR - Cannot reload configuration..");
			}

	}
	
	public void saveConfig() {
		try {
			ConfigurationProvider.getProvider(YamlConfiguration.class).save(configuration, new File(getDataFolder(), "config.yml"));
		} catch (IOException e) {
			e.printStackTrace();
			getLogger().info("ERR - Cannot save configuration..");
		}
	}
	
	protected void setInstance(RandomLobbyMain y) {
		x = y;
	}
 	
	public static RandomLobbyMain i() {
		return x;
	}
	
}
