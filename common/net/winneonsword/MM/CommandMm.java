package net.winneonsword.MM;

import java.text.DecimalFormat;

import net.minecraft.util.org.apache.commons.lang3.text.WordUtils;
import net.winneonsword.MM.game.GameClass;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import com.github.lyokofirelyte.WCAPI.Loops.WCDelay;

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
				
				args(this, s, label, args);
				
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
				"&5- &d/mm list &5// &dShow the MM player list.",
				"&5- &d/mm join <class> &5// &dJoin MM.",
				"&5- &d/mm leave &5// &dLeave MM.",
				"&5- &d/mm class &5// &dShow the class help menu.",
				"&5- &d/mm toggle &5// &dToggle joining status for MM. &5STAFF",
				"&5- &d/mm disable &5// &dDisable MM. &5STAFF"
				
		});
		
	}
	
	@MMArg(refs = { "toggle" }, perm = "wa.staff")
	public void toggle(CommandSender s, String[] args){
		
		if (main.game().toggleOpen()){
			
			b("MM is open to join! Type &6/mm join <class> &dto join.");
			
		} else {
			
			b("MM joining has been closed. Better luck next time.");
			
		}
		
	}
	
	@MMArg(refs = { "join" }, player = true)
	public void join(Player p, String[] args){
		
		if (main.game().getOpenStatus()){
			
			if (args.length < 2){
				
				s(p, "&cYou did not type in a class! Type &6/mm class list &cfor a list of the classes.");
				
			} else {
				
				if (main.game().getPlayers().contains(p.getName())){
					
					s(p, "&cYou have already joined the game!");
					
				} else {
					
					GameClass clazz = main.game().translateClass(args[1]);
					
					if (clazz == null){
						
						s(p, "&cThe class &6" + args[1] + " &cis not a valid class!");
						
					} else {
						
						main.game().registerPlayer(p, clazz);
						s(p, "Joined MM under the class &6" + clazz + "&d! Teleporting to the arena in 5 seconds. ");
						main.api().ls.callDelay(main, this, "teleportDelay", p, main.game().getArena());
						
					}
					
				}
				
			}
			
		} else {
			
			s(p, "&cMM is not open for joining at this time!");
			
		}
		
	}
	
	@WCDelay(time = 100)
	public void teleportDelay(Player p, Location loc){
		
		p.teleport(loc);
		s(p, "Teleported!");
		
	}
	
	@MMArg(refs = { "leave" }, player = true)
	public void leave(Player p, String[] args){
		
		Location loc = main.game().getPlayer(p.getName()).getLastLocation();
		
		if (main.game().removePlayer(p.getName())){
			
			s(p, "Successfully left MM. Teleporting to your last location in 5 seconds.");
			main.api().ls.callDelay(main, this, "teleportDelay", p, loc);
			
		} else {
			
			s(p, "&cYou have not yet joined MM!");
			
		}
		
	}
	
	@MMArg(refs = { "list" })
	public void list(CommandSender s, String[] args){
		
		if (main.game().getPlayers().isEmpty()){
			
			s(s, "&cNoone has joined MM so far!");
			
		} else {
			
			s(s, "MM Player List");
			
			for (String name : main.game().getPlayers()){
				
				s(s, new String[] {
						
						"&5- &6" + name
						
				});
				
			}
			
		}
		
	}
	
	@MMArg(refs = { "class" })
	public void clazz(CommandSender s, String[] args){
		
		if (args.length < 2){
			
			s(s, new String[] {
					
					MM + "Class Help Menu",
					"&5- &d/mm class <name> &5// &dShow the info of a certain class.",
					"&5- &d/mm class list &5// &dShow the list of all classes."
					
			});
			
		} else {
			
			switch (args[1]){
			
			default:
				
				GameClass clazz = main.game().translateClass(args[1]);
				
				if (clazz == null){
					
					s(s, "&cThat is not a valid class! Type &6/mm class list &cfor a list of classes!");
					
				} else {
					
					s(s, new String[] {
							
							MM + WordUtils.capitalize(clazz.getName()) + " Info",
							"&5- &dDescription: &6" + clazz.getDesc(),
							"&5- &dAlpha Ability: &6" + clazz.getAlpha(),
							"&5- &dOmega Ability: &6" + clazz.getOmega()
							
					});
					
				}
				
				break;
			
			case "list":
				
				s(s, "Class List");
				
				for (GameClass c : GameClass.values()){
					
					s(s, new String[] {
							
							"&5- &6" + WordUtils.capitalize(c.getName())
							
					});
					
				}
				
				break;
				
			}
			
		}
		
	}
	
	@MMArg(refs = { "set" }, perm = "wa.staff", player = true)
	public void set(Player p, String[] args){
		
		if (args.length < 2){
			
			s(p, new String[] {
					
					MM + "Settings Help Menu &5STAFF",
					"&5- &d/mm set arena &5// &dSet the arena to your location."
					
			});
			
		} else {
			
			switch (args[1]){
			
			case "arena":
				
				Location loc = p.getLocation();
				DecimalFormat form = new DecimalFormat("0.0");
				
				String x = form.format(loc.getX());
				String y = form.format(loc.getY());
				String z = form.format(loc.getZ());
				String w = loc.getWorld().getName();
				
				main.game().setArena(loc);
				s(p, "Set the arena to &6" + x + "&5, &6" + y + "&5, &6" + z + " &din the world &6" + w + "&d.");
				break;
				
			}
			
		}
		
	}
	
	@MMArg(refs = { "disable" }, perm = "wa.staff")
	public void disable(CommandSender s, String[] args){
		
		main.pm().disablePlugin(main);
		s(s, "Disabled MM. To enable it, run &6/pm enable MM&d.");
		
	}
	
}
