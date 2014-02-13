package net.winneonsword.MM.game;

public enum GameClass {
	
	MEDIC("medic"),
	SPIRIT("spirit"),
	WARRIOR("warrior"),
	INFERNO("inferno"),
	ROADRUNNER("roadrunner"),
	SNIPER("sniper");
	
	private String name;
	
	GameClass(String name){
		
		this.name = name;
		
	}
	
	public String getName(){
		
		return name;
		
	}
	
}
