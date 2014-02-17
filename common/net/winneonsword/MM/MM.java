package net.winneonsword.MM;

import java.util.logging.Level;

import net.winneonsword.MM.game.Gameplay;
import net.winneonsword.MM.utils.Config;
import net.winneonsword.MM.utils.Logging;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;

import com.github.lyokofirelyte.WCAPI.WCAPI;
import com.github.lyokofirelyte.WCAPI.WCManager;
import com.github.lyokofirelyte.WCAPI.WCNode;

public class MM extends WCNode {
	
	private WCAPI api;
	private WCManager wcm;
	
	private Logging logging;
	private PluginManager pm;
	private Gameplay game;
	
	private String[] configs;
	
	@Override
	public void onEnable(){
		
		logging = new Logging(this);
		pm = getServer().getPluginManager();
		game = new Gameplay(this);
		
		api = (WCAPI) pm.getPlugin("WCAPI");
		wcm = new WCManager(api);
		
		new Config(this);
		new Utils(this);
		
		configs = new String[] {
				
				"config", "datacore"
				
		};
		
		for (String config : configs){
			
			Config.registerConfig(config);
			logging().log(Level.INFO, "&eRegistered the config '" + config + "'.");
			
		}
		
		YamlConfiguration data = Config.getConfig("datacore");
		
		try {
			
			double x = data.getDouble("arena.x");
			double y = data.getDouble("arena.y");
			double z = data.getDouble("arena.z");
			World w = getServer().getWorld(data.getString("arena.w"));
			
			game().setArena(new Location(w, x, y, z));
			
		} catch (Exception e){
			
			logging().log(Level.SEVERE, "Could not load the arena! Is the datacore intact?");
			
		}
		
		getCommand("mm").setExecutor(new CommandMm(this));
		logging().log(Level.INFO, "MM has been fully enabled. Enjoy!");
		
	}
	
	@Override
	public void onDisable(){
		
		YamlConfiguration data = Config.getConfig("datacore");
		Location arena = game().getArena();
		
		data.set("arena.x", arena.getX());
		data.set("arena.y", arena.getY());
		data.set("arena.z", arena.getZ());
		data.set("arena.w", arena.getWorld().getName());
		
		for (String c : configs){
			
			Config.saveConfig(c);
			logging().log(Level.INFO, "&eSaved the config '" + c + "'.");
			
		}
		
		logging().log(Level.INFO, "&cMM has been fully disabled. Bye-bye.");
		
	}
	
	public WCAPI api(){
		
		return api;
		
	}
	
	public WCManager wcm(){
		
		return wcm;
		
	}
	
	public Logging logging(){
		
		return logging;
		
	}
	
	public PluginManager pm(){
		
		return pm;
		
	}
	
	public Gameplay game(){
		
		return game;
		
	}
	
}
