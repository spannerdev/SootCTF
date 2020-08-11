package com.spanner.SootCTF;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import com.spanner.SootCTF.utils.*;

public class Executor implements CommandExecutor {
	
	private Main p = Main.getPlugin(Main.class);

	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// todo: de-yanderedev if statements
		// todo: all the todos
		
		if (command.getName().equals("sootctf")) {
			if (args.length == 0) return true; // todo: show help
			
			if (args[0].equalsIgnoreCase("create")) {
				if (args.length < 2) return true; // todo: show help
				
				if (args[1].equalsIgnoreCase("arena")) {
					if (args.length != 3) return true; // todo: show help
					
					if (!sender.hasPermission("sootctf.edit.arena")) return true; // todo: show "no permission"
					
					p.arenas.put(args[2],new CTFArena(args[2]));
					sender.sendMessage("success"); return true; // todo: improve message
				}
				
				if (args[1].equalsIgnoreCase("spawn")) {
					if (args.length != 3) return true; // todo: show help
					
					if (!sender.hasPermission("sootctf.edit.spawn")) return true; // todo: show "no permission"
					
					p.spawns.put(args[2], new CTFSpawn(args[2]));
					sender.sendMessage("success"); return true; // todo: improve message
				}
				
				if (args[1].equalsIgnoreCase("team")) {
					if (args.length != 3) return true; // todo: show help
					
					if (!sender.hasPermission("sootctf.edit.team")) return true; // todo: show "no permission"
					
					p.teams.put(args[2],new CTFTeam(args[2]));
					sender.sendMessage("success"); return true; // todo: improve message
				}
				return false;
			}
			return false;
		}
		sender.sendMessage(ChatColor.RED + "This shouldn't appear! Report at https://github.com/spannerdev/SootCTF/issues");
		return false;
	}

}
