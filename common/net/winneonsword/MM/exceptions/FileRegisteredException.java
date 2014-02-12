package net.winneonsword.MM.exceptions;

public class FileRegisteredException extends Exception {

	private static final long serialVersionUID = 408953703840402063L;
	
	public FileRegisteredException(String file){
		
		super("The file '" + file + "' is already registered!");
		
	}

}
