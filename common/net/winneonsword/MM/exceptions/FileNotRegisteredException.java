package net.winneonsword.MM.exceptions;

public class FileNotRegisteredException extends Exception {

	private static final long serialVersionUID = 408953703840402063L;
	
	public FileNotRegisteredException(String file){
		
		super("The file '" + file + "' is not registered!");
		
	}

}
