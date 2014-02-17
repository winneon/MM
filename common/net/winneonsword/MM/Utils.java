package net.winneonsword.MM;

import java.lang.reflect.Method;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utils {
	
	public static String MM;
	private static MM main;
	
	public Utils(MM main){
		
		this.main = main;
		MM = "&dMM &5// &d";
		
	}
	
	public static void s(CommandSender s, String message){
		
		s.sendMessage(AS(MM + message));
		
	}
	
	public static void s(CommandSender s, String[] message){
		
		s.sendMessage(AS(message));
		
	}
	
	public static void s(Player p, String message){
		
		s((CommandSender) p, message);
		
	}
	
	public static void s(Player p, String[] message){
		
		s((CommandSender) p, message);
		
	}
	
	public static void b(String message){
		
		Bukkit.broadcastMessage(AS(MM + message));
		
	}
	
	public static void b(String[] message){
		
		for (String m : message){
			
			Bukkit.broadcastMessage(AS(m));
			
		}
		
	}
	
	public static String AS(String message){
		
		return ChatColor.translateAlternateColorCodes('&', message);
		
	}
	
	public static String[] AS(String[] message){
		
		for (int i = 0; i < message.length; i++){
			
			message[i] = AS(message[i]);
			
		}
		
		return message;
		
	}
	
	public static void args(Object clazz, CommandSender s, String cmd, String[] args){
		
		for (int i = 0; i < args.length; i++){
			
			args[i] = args[i].toLowerCase();
			
		}
		
		try {
			
			boolean triggered = false;
			
			for (Method m : clazz.getClass().getMethods()){
				
				MMArg anno = m.getAnnotation(MMArg.class);
				
				if (anno != null){
					
					for (String arg : anno.refs()){
						
						if (arg.equalsIgnoreCase(args[0])){
							
							if (s.hasPermission(anno.perm())){
								
								triggered = true;
								
								if (anno.player()){
									
									if (s instanceof Player){
										
										m.invoke(clazz, (Player) s, args);
										
									} else {
										
										s(s, "&cConsole players cannot run this command!");
										
									}
									
								} else {
									
									m.invoke(clazz, s, args);
									
								}
								
							} else {
								
								s(s, "&cYou don't have permission for that!");
								
							}
							
						}
						
					}
					
				}
				
			}
			
			if (!(triggered)){
				
				s(s, "&cUnknown argument! Try &6/mm ?&c.");
				
			}
			
		} catch (Exception e){
			
			main.logging().log(Level.SEVERE, "An internal error has occured involving the command '" + cmd + "'!");
			e.printStackTrace();
			
		}
		
	}
	
}
