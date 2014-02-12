package net.winneonsword.MM.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.logging.Level;

import org.bukkit.configuration.file.YamlConfiguration;

import net.winneonsword.MM.MM;
import net.winneonsword.MM.exceptions.FileNotRegisteredException;
import net.winneonsword.MM.exceptions.FileRegisteredException;

public class Config {
	
	private static MM main;
	private static HashMap<File, YamlConfiguration> configs;
	
	public Config(MM main){
		
		this.main = main;
		configs = new HashMap<File, YamlConfiguration>();
		
	}
	
	public static File getFile(String name){
		
		for (File file : configs.keySet()){
			
			if (file.getName().equals(name + ".yml")){
				
				return file;
				
			}
			
		}
		
		main.logging().log(Level.SEVERE, "The config '" + name + "' is not registered!");
		
		try {
			
			throw new FileNotRegisteredException(name);
			
		} catch (FileNotRegisteredException e){
			
			e.printStackTrace();
			
			return null;
			
		}
		
	}
	
	public static YamlConfiguration getConfig(String name){
		
		File file = getFile(name);
		
		if (configs.containsKey(file)){
			
			return configs.get(file);
			
		} else {
			
			main.logging().log(Level.SEVERE, "The config '" + name + "' is not registered!");
			
			try {
				
				throw new FileNotRegisteredException(name);
				
			} catch (FileNotRegisteredException e){
				
				e.printStackTrace();
				
				return null;
				
			}
			
		}
		
	}
	
	public static boolean saveConfig(String name){
		
		try {
			
			YamlConfiguration yaml = getConfig(name);
			File file = getFile(name);
			
			yaml.save(file);
			
			return true;
			
		} catch (Exception e){
			
			main.logging().log(Level.SEVERE, "Failed to save the config '" + name + "'!");
			e.printStackTrace();
			
			return false;
			
		}
		
	}
	
	public static YamlConfiguration registerConfig(String name){
		
		if (!(main.getDataFolder().exists())){
			
			main.getDataFolder().mkdirs();
			
		}
		
		if (configs.containsKey(name)){
			
			main.logging().log(Level.SEVERE, "The config '" + name + "' is already registered!");
			
			try {
				
				throw new FileRegisteredException(name);
				
			} catch (FileRegisteredException e){
				
				e.printStackTrace();
				
				return null;
				
			}
			
		} else {
			
			String ext = name + ".yml";
			File file = new File(main.getDataFolder(), ext);
			
			if (!(file.exists())){
				
				copy(main.getResource(ext), file);
				
			}
			
			YamlConfiguration yaml = YamlConfiguration.loadConfiguration(file);
			configs.put(file, yaml);
			
			return yaml;
			
		}
		
	}
	
	private static void copy(InputStream in, File file){
		
		try {
			
			if (in == null){
				
				file.createNewFile();
				
			} else {
				
				OutputStream out = new FileOutputStream(file);
				byte[] buf = new byte[1024];
				int len;
				
				while ((len = in.read(buf)) > 0){
					
					out.write(buf, 0, len);
					
				}
				
				out.close();
				in.close();
				
			}
			
		} catch (Exception e){
			
			main.logging().log(Level.SEVERE, "Failed to copy the file '" + file.getName() + "'!");
			e.printStackTrace();
			
		}
		
	}
	
}
