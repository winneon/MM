package net.winneonsword.MM.game;

public class MMPlayer {
	
	private String name;
	private GameClass clazz;
	
	public MMPlayer(String name){
		
		this.name = name;
		
	}
	
	public String getName(){
		
		return name;
		
	}
	
	public GameClass getGameClass(){
		
		return clazz;
		
	}
	
	public void setGameClass(GameClass clazz){
		
		this.clazz = clazz;
		
	}
	
}
