package net.winneonsword.MM.exceptions;

public class PlayerAlreadyExistsException extends Exception {

	private static final long serialVersionUID = -2901562035900543997L;
	
	public PlayerAlreadyExistsException(String name){
		
		super("The player '" + name + "' already exists!");
		
	}

}
