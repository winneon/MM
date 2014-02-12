package net.winneonsword.MM;

import java.lang.reflect.Method;
import java.util.logging.Level;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import static net.winneonsword.MM.Utils.*;

public class CommandMm implements CommandExecutor {
	
	private MM main;
	
	public CommandMm(MM main){
		
		this.main = main;
		
	}
	
	@Override
	public boolean onCommand(CommandSender s, Command cmd, String label, String[] args){
		
		if (label.equalsIgnoreCase("mm")){
			
			if (args.length == 0){
				
				s(s, "Try &6/mm ?&d.");
				
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
										
									} else {
										
										s(s, "&cYou don't have permission for that!");
										
									}
									
								}
								
							}
							
						}
						
					}
					
					if (!(triggered)){
						
						s(s, "&cUnknown argument! Try &6/mm ?&c.");
						
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
	
	@MMArg(refs = { "?", "help" })
	public void help(CommandSender s, String[] args){
		
		s(s, new String[] {
				
				MM + "Help Panel",
				"&5- &d/mm ? &5// &dThe main help menu.",
				"&5- &d/mm disable &5// &dDisable MM. &5STAFF"
				
		});
		
	}
	
	@MMArg(refs = { "disable" }, perm = "wa.staff")
	public void disable(CommandSender s, String[] args){
		
		main.pm().disablePlugin(main);
		s(s, "Disabled MM. To enable it, run &6/pm enable MM&d.");
		
	}
	
}
