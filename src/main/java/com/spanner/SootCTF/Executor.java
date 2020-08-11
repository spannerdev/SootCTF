package com.spanner.SootCTF;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;

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
			
			
			if (args[0].equalsIgnoreCase("edit")) {
				if (args.length < 2) return true; // todo: show help
				
				if (args[1].equalsIgnoreCase("arena")) {
					if (args.length < 3) return true; // todo: show help
					
					if (!sender.hasPermission("sootctf.edit.arena")) return true; // todo: show "no permission"
					
					CTFArena arena = p.arenas.get(args[2]);
					if (arena == null) return true; // todo: show "not found"
					
					if (args[3].equalsIgnoreCase("spawns")) {
						if (args.length < 5) return true; // todo: show help
						
						if (args[4].equalsIgnoreCase("add")) {
							CTFSpawn spawn = p.spawns.get(args[5]);
							if (spawn == null) return true; // todo: show "not found"
							arena.spawns.add(spawn);
							sender.sendMessage("success"); return true; // todo: improve message
						}
						if (args[4].equalsIgnoreCase("remove")) {
							CTFSpawn spawn = p.spawns.get(args[5]);
							if (spawn == null) return true; // todo: show "not found"
							if (!arena.spawns.contains(spawn)) return true; // todo: show "spawn not in arena"
							arena.spawns.remove(spawn);
							sender.sendMessage("success"); return true; // todo: improve message
						}
						return false;
					}
					if (args[3].equalsIgnoreCase("teams")) {
							if (args.length < 5) return true; // todo: show help
						
						if (args[4].equalsIgnoreCase("add")) {
							CTFTeam team = p.teams.get(args[5]);
							if (team == null) return true; // todo: show "not found"
							arena.teams.add(team);
							sender.sendMessage("success"); return true; // todo: improve message
						}
						if (args[4].equalsIgnoreCase("remove")) {
							CTFTeam team = p.teams.get(args[5]);
							if (team == null) return true; // todo: show "not found"
							if (!arena.teams.contains(team)) return true; // todo: show "team not in arena"
							arena.teams.remove(team);
							sender.sendMessage("success"); return true; // todo: improve message
						}
						return false;
					}
					return false;
				}
				
				if (args[1].equalsIgnoreCase("spawn")) {
					if (args.length < 3) return true; // todo: show help
					
					if (!sender.hasPermission("sootctf.edit.spawn")) return true; // todo: show "no permission"
					
					CTFSpawn spawn = p.spawns.get(args[2]);
					if (spawn == null) return true; // todo: show "not found"
					
					if (args[3].equalsIgnoreCase("team")) {
						if (args.length < 5) return true; // todo: show help or current value
						
						if (args[4].equalsIgnoreCase("set")) {
							CTFTeam team = p.teams.get(args[5]);
							if (team == null) return true; // todo: show "not found"
							spawn.team = team;
							sender.sendMessage("success"); return true; // todo: improve message
						}
						return false;
					}
					if (args[3].equalsIgnoreCase("radius")) {
							if (args.length < 5) return true; // todo: show help or current value
						
						if (args[4].equalsIgnoreCase("set")) {
							try {
								int radius = Integer.parseInt(args[5]);
								spawn.spawnRadius = radius;
								sender.sendMessage("success"); return true; // todo: improve message
							} catch (NumberFormatException e) {
								return true; // todo: show "must be number" or alike
							}
						}
						return false;
					}
					
					if (args[3].equalsIgnoreCase("pos") || args[3].equalsIgnoreCase("position")) {
						if (args.length < 5) return true; // todo: show help or current value
						
						if (args[4].equalsIgnoreCase("set")) {
							if (!(sender instanceof Entity)) return true; //todo: show error
							
							spawn.position = ((Entity)sender).getLocation();
							sender.sendMessage("success"); return true; // todo: improve message
						}
						return false;
					}
					return false;
				}
				
				if (args[1].equalsIgnoreCase("team")) {
					if (args.length < 3) return true; // todo: show help
					
					if (!sender.hasPermission("sootctf.edit.team")) return true; // todo: show "no permission"
					
					CTFTeam team = p.teams.get(args[2]);
					if (team == null) return true; // todo: show "not found"
					
					if (args[3].equalsIgnoreCase("color") || args[3].equalsIgnoreCase("colour")) {
						if (args.length < 5) return true; // todo: show help or current value
						
						if (args[4].equalsIgnoreCase("set")) {
							try {
								ChatColor color = ChatColor.valueOf(args[5].toUpperCase());
								if (color.isFormat()) return true; // todo: show "must be color"
								team.color = color;
								sender.sendMessage("success"); return true; // todo: improve message
							} catch (IllegalArgumentException e) {
								return true; // todo: show "not a valid color"
							}
						}
						return false;
					}
					if (args[3].equalsIgnoreCase("block")) {
						if (args.length < 5) return true; // todo: show help or current value
						
						if (args[4].equalsIgnoreCase("set")) {
							try {
								Material material = Material.valueOf(args[5].toUpperCase());
								if (!material.isBlock()) return true; // todo: show "must be block"
								team.block = material;
								sender.sendMessage("success"); return true; // todo: improve message
							} catch (IllegalArgumentException e) {
								return true; // todo: show "not a valid material name"
							}
						}
						return false;
					}
					return false;
				}
				return false;
			}
			return false;
			
		}
		sender.sendMessage(ChatColor.RED + "This shouldn't appear! Report at https://github.com/spannerdev/SootCTF/issues");
		return false;
	}

}
