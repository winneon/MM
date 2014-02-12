package net.winneonsword.MM;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Utils {
	
	public static String MM;
	
	public Utils(){
		
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
	
}
