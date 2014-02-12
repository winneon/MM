package net.winneonsword.MM;

import java.lang.reflect.Method;
import java.util.logging.Level;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.winneonsword.MM.Utils.*;

public class CommandMm implements CommandExecutor {
	
	private MM main;
	private String[] help;
	
	public CommandMm(MM main){
		
		this.main = main;
		help = new String[] {
				
				MM + "Help Panel"
				
		};
		
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("mm")){
			
			if (args.length == 0){
				
				s(s, "Try &6/mm help&d.");
				
			} else {
				
				try {
					
					boolean triggered = false;
					
					for (Method m : getClass().getMethods()){
						
						MMArg anno = m.getAnnotation(MMArg.class);
						
						if (anno != null){
							
							for (String arg : anno.refs()){
								
								if (arg.equalsIgnoreCase(args[0])){
									
									if (s.hasPermission(anno.perm())){
										
										triggered = true;
										m.invoke(this, s, args);
										
									}
									
								}
								
							}
							
						}
						
					}
					
					if (!(triggered)){
						
						s(s, help);
						
					}
					
				} catch (Exception e){
					
					main.logging().log(Level.SEVERE, "An internal error has occured involving the command '" + label + "'!");
					e.printStackTrace();
					
				}
				
			}
			
			return true;
			
		} else {
			
			return false;
			
		}
		
	}
	
	@MMArg(refs = { "disable" }, perm = "wa.staff")
	public void disable(CommandSender s, String[] args){
		
		System.out.println("test");
		
	}
	
}
