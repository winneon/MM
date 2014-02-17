package net.winneonsword.MM.game;

import org.bukkit.Location;

public class MMPlayer {
	
	private String name;
	
	private GameClass clazz;
	private Location lastLoc;
	
	public MMPlayer(String name){
		
		this.name = name;
		
	}
	
	public String getName(){
		
		return name;
		
	}
	
	public GameClass getGameClass(){
		
		return clazz;
		
	}
	
	public Location getLastLocation(){
		
		return lastLoc;
		
	}
	
	public void setGameClass(GameClass clazz){
		
		this.clazz = clazz;
		
	}
	
	public void setLastLocation(Location loc){
		
		lastLoc = loc;
		
	}
	
}
