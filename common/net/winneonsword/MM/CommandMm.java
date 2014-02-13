package net.winneonsword.MM;

import java.lang.reflect.Method;
import java.util.logging.Level;

import net.winneonsword.MM.game.GameClass;

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
				"&5- &d/mm join <class> &5// &dJoin MM.",
				"&5- &d/mm leave &5// &dLeave MM.",
				"&5- &d/mm open &5// &dOpen MM for joining. &5STAFF",
				"&5- &d/mm disable &5// &dDisable MM. &5STAFF"
				
		});
		
	}
	
	@MMArg(refs = { "open" }, perm = "wa.staff")
	public void open(CommandSender s, String[] args){
		
		if (main.game().toggleOpen()){
			
			b("MM is open to join! Type &6/mm join <class> &dto join.");
			
		} else {
			
			b("MM joining has been closed. Better luck next time.");
			
		}
		
	}
	
	@MMArg(refs = { "join" })
	public void join(CommandSender s, String[] args){
		
		if (main.game().getOpenStatus()){
			
			if (args.length < 2){
				
				s(s, "&cYou did not type in a class! Type &6/mm class list &cfor a list of the classes.");
				
			} else {
				
				if (main.game().getPlayers().contains(s.getName())){
					
					s(s, "&cYou have already joined the game!");
					
				} else {
					
					GameClass clazz = main.game().translateClass(args[1]);
					
					if (clazz == null){
						
						s(s, "&cThe class &6" + args[1] + " &cis not a valid class!");
						
					} else {
						
						main.game().registerPlayer(s.getName(), clazz);
						s(s, "Joined MM under the class &6" + clazz.getName() + "&d!");
						
					}
					
				}
				
			}
			
		} else {
			
			s(s, "&cMM is not open for joining at this time!");
			
		}
		
	}
	
	@MMArg(refs = { "leave" })
	public void leave(CommandSender s, String[] args){
		
		if (main.game().removePlayer(s.getName())){
			
			s(s, "Successfully left MM.");
			
		} else {
			
			s(s, "&cYou have not yet joined MM!");
			
		}
		
	}
	
	@MMArg(refs = { "disable" }, perm = "wa.staff")
	public void disable(CommandSender s, String[] args){
		
		main.pm().disablePlugin(main);
		s(s, "Disabled MM. To enable it, run &6/pm enable MM&d.");
		
	}
	
}
