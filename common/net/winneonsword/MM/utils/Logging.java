package net.winneonsword.MM.utils;

import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.winneonsword.MM.MM;

public class Logging {
	
	private Logger logger;
	private HashMap<String, String> colours;
	
	public Logging(MM main){
		
		logger = main.getLogger();
		
		colours.put("0", "\u001B[0;30m");
		colours.put("1", "\u001B[0;34m");
		colours.put("2", "\u001B[0;32m");
		colours.put("3", "\u001B[0;36m");
		colours.put("4", "\u001B[0;31m");
		colours.put("5", "\u001B[0;35m");
		colours.put("6", "\u001B[0;33m");
		colours.put("7", "\u001B[0;37m");
		colours.put("8", "\u001B[1;30m");
		colours.put("9", "\u001B[1;34m");
		colours.put("a", "\u001B[1;32m");
		colours.put("b", "\u001B[1;36m");
		colours.put("c", "\u001B[1;31m");
		colours.put("d", "\u001B[1;35m");
		colours.put("e", "\u001B[1;33m");
		colours.put("f", "\u001B[1;37m");
		
	}
	
	public void log(Level level, String message){
		
		switch (level.getName().toLowerCase()){
		
		case "info":
			
			message = "&a" + message;
			break;
			
		case "severe":
			
			message = "&c" + message;
			break;
		
		}
		
		logger.log(level, CAS(message));
		
	}
	
	private String CAS(String message){
		
		for (String s : colours.keySet()){
			
			message = message.replace("&" + s, colours.get(s));
			
		}
		
		return message + colours.get("7");
		
	}
	
}
