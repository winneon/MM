package net.winneonsword.MM.exceptions;

public class NoSuchPlayerException extends Exception {

	private static final long serialVersionUID = -2901562035900543997L;
	
	public NoSuchPlayerException(String name){
		
		super("No such player '" + name + "'!");
		
	}

}
