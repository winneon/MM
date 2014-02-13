package net.winneonsword.MM.game;

import java.util.HashMap;
import java.util.logging.Level;

import net.winneonsword.MM.MM;
import net.winneonsword.MM.exceptions.NoSuchPlayerException;
import net.winneonsword.MM.exceptions.PlayerAlreadyExistsException;

public class Gameplay {
	
	private static MM main;
	private static HashMap<String, MMPlayer> players;
	
	public Gameplay(MM main){
		
		this.main = main;
		players = new HashMap<String, MMPlayer>();
		
	}
	
	public static MMPlayer getPlayer(String name){
		
		if (players.containsKey(name)){
			
			return players.get(name);
			
		} else {
			
			try {
				
				throw new NoSuchPlayerException(name);
				
			} catch (NoSuchPlayerException e){
				
				main.logging().log(Level.SEVERE, "The player '" + name + "' does not exist!");
				e.printStackTrace();
				
				return null;
				
			}
			
		}
		
	}
	
	public static boolean registerPlayer(String name, GameClass clazz){
		
		if (players.containsKey(name)){
			
			try {
				
				throw new PlayerAlreadyExistsException(name);
				
			} catch (PlayerAlreadyExistsException e){
				
				main.logging().log(Level.SEVERE, "The player '" + name + "' is already registered!");
				e.printStackTrace();
				
				return false;
				
			}
			
		} else {
			
			MMPlayer mmp = new MMPlayer(name);
			mmp.setGameClass(clazz);
			
			players.put(name, mmp);
			
			return true;
			
		}
		
	}
	
}
