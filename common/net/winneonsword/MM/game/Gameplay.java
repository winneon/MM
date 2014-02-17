package net.winneonsword.MM.game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import net.winneonsword.MM.MM;
import net.winneonsword.MM.exceptions.NoSuchPlayerException;
import net.winneonsword.MM.exceptions.PlayerAlreadyExistsException;

public class Gameplay {
	
	private MM main;
	
	private boolean open;
	private HashMap<String, MMPlayer> players;
	
	private Location arena;
	
	public Gameplay(MM main){
		
		this.main = main;
		players = new HashMap<String, MMPlayer>();
		
	}
	
	public MMPlayer getPlayer(String name){
		
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
	
	public boolean registerPlayer(Player p, GameClass clazz){
		
		if (players.containsKey(p.getName())){
			
			try {
				
				throw new PlayerAlreadyExistsException(p.getName());
				
			} catch (PlayerAlreadyExistsException e){
				
				main.logging().log(Level.SEVERE, "The player '" + p.getName() + "' is already registered!");
				
				return false;
				
			}
			
		} else {
			
			MMPlayer mmp = new MMPlayer(p.getName());
			
			mmp.setGameClass(clazz);
			mmp.setLastLocation(p.getLocation());
			
			players.put(p.getName(), mmp);
			
			return true;
			
		}
		
	}
	
	public boolean removePlayer(String name){
		
		if (players.containsKey(name)){
			
			players.remove(name);
			
			return true;
			
		} else {
			
			try {
				
				throw new NoSuchPlayerException(name);
				
			} catch (NoSuchPlayerException e){
				
				main.logging().log(Level.SEVERE, "The player '" + name + "' does not exist!");
				
				return false;
				
			}
			
		}
		
	}
	
	public boolean getOpenStatus(){
		
		return open;
		
	}
	
	public List<String> getPlayers(){
		
		List<String> list = new ArrayList<String>();
		
		for (String name : players.keySet()){
			
			list.add(name);
			
		}
		
		return list;
		
	}
	
	public boolean toggleOpen(){
		
		return open = !(open);
		
	}
	
	public GameClass translateClass(String clazz){
		
		clazz = clazz.toUpperCase();
		
		try {
			
			return GameClass.valueOf(clazz);
			
		} catch (Exception e){
			
			return null;
			
		}
		
	}
	
	public Location getArena(){
		
		return arena;
		
	}
	
	public void setArena(Location arena){
		
		this.arena = arena;
		
	}
	
}
