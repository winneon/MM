package net.winneonsword.MM;

import java.util.logging.Level;

import net.winneonsword.MM.utils.Config;
import net.winneonsword.MM.utils.Logging;

import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

public class MM extends JavaPlugin {
	
	private Logging logging;
	private PluginManager pm;
	
	private String[] configs;
	
	@Override
	public void onEnable(){
		
		logging = new Logging(this);
		pm = getServer().getPluginManager();
		
		new Config(this);
		
		configs = new String[] {
				
				"config", "datacore"
				
		};
		
		for (String config : configs){
			
			Config.registerConfig(config);
			logging().log(Level.INFO, "Registered the config '" + config + "'.");
			
		}
		
		logging().log(Level.INFO, "MM has been fully enabled. Enjoy!");
		
	}
	
	@Override
	public void onDisable(){
		
		
		
	}
	
	public Logging logging(){
		
		return logging;
		
	}
	
	public PluginManager pm(){
		
		return pm;
		
	}
	
}
