package net.winneonsword.MM.game;

public enum GameClass {
	
	MEDIC("medic", "A support class that is focused on healing.", "Give yourself Regeneration III for 1 minute.", "Give everyone including yourself Regeneration III for 1 minute."),
	SPIRIT("spirit", "A defensive class that is focused on flying and strategy.", "Give yourself the ability to fly for 1 minute.", "Give everyone including yourself the ability to fly for 1 minute."),
	WARRIOR("warrior", "An offensive class that is focused on battle.", "The ability to 1 - Hit KO any mob for 10 seconds. (Not including bosses.)", "Recieve a battle axe that allows for a super-knockback for every mob around you. (3 time use.)"),
	INFERNO("inferno", "An offensive class that is focused on fire and strategy.", "The ability to shoot small fireballs at mobs for 30 seconds.", "Forge a fire-ring that sets all mobs around you on fire for an infinite amount of time. (1 time use.)"),
	ROADRUNNER("roadrunner", "A defensive class that is focused on speed and manuveuring.", "Give yourself Speed IV for 1 minute.", "Give everyone including yourself Speed IV for 1 minute."),
	SNIPER("sniper", "An offensive class that is focused on stealth and fast actions.", "Headshot (1 - Hit KO) all mobs you shoot for 20 seconds.", "Send a barrage of arrows all around you at all mobs. (1 time use.)");
	
	private String name;
	private String desc;
	
	private String alpha;
	private String omega;
	
	GameClass(String name, String desc, String alpha, String omega){
		
		this.name = name;
		this.desc = desc;
		
		this.alpha = alpha;
		this.omega = omega;
		
	}
	
	public String getName(){
		
		return name;
		
	}
	
	public String getDesc(){
		
		return desc;
		
	}
	
	public String getAlpha(){
		
		return alpha;
		
	}
	
	public String getOmega(){
		
		return omega;
		
	}
	
	@Override
	public String toString(){
		
		return name;
		
	}
	
}
