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

	private String NO_PERMISSION = ChatColor.RED + "You don't have permission to use this command";
	private String NOT_FOUND = ChatColor.RED + "Not found. Are you sure you typed the name correctly? It's case sensitive!";
	
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		// todo: de-yanderedev if statements
		// todo: all the todos
		
		if (command.getName().equals("sootctf")) {
			if (args.length == 0) {
				sender.sendMessage(ChatColor.GOLD + "Usage: /sootctf <create|edit|help>");
				return true;
			}
			
			
			if (args[0].equalsIgnoreCase("create")) {
				if (args.length < 2) {
					sender.sendMessage(ChatColor.GOLD + "Usage: /sootctf create <arena|spawn|team> <name>");
					return true;
				}
				
				if (args[1].equalsIgnoreCase("arena")) {
					if (args.length != 3) {
						sender.sendMessage(ChatColor.GOLD + "Usage: /sootctf create arena <name>");
						return true;
					}
					
					if (!sender.hasPermission("sootctf.edit.arena")) {
						sender.sendMessage(NO_PERMISSION);
						return true;
					}
					
					p.arenas.put(args[2],new CTFArena(args[2]));
					sender.sendMessage("success"); return true; // todo: improve message
				}
				
				if (args[1].equalsIgnoreCase("spawn")) {
					if (args.length != 3) {
						sender.sendMessage(ChatColor.GOLD + "Usage: /sootctf create spawn <name>");
						return true;
					}
					
					if (!sender.hasPermission("sootctf.edit.spawn")) {
						sender.sendMessage(NO_PERMISSION);
						return true;
					}
					
					p.spawns.put(args[2], new CTFSpawn(args[2]));
					sender.sendMessage("success"); return true; // todo: improve message
				}
				
				if (args[1].equalsIgnoreCase("team")) {
					if (args.length != 3) {
						sender.sendMessage(ChatColor.GOLD + "Usage: /sootctf create team <name>");
						return true;
					}
					
					if (!sender.hasPermission("sootctf.edit.team")) {
						sender.sendMessage(NO_PERMISSION);
						return true;
					}
					
					p.teams.put(args[2],new CTFTeam(args[2]));
					sender.sendMessage("success"); return true; // todo: improve message
				}
				return false;
			}
			
			
			if (args[0].equalsIgnoreCase("edit")) {
				if (args.length < 2) {
					sender.sendMessage(ChatColor.GOLD + "Usage: /sootctf edit <arena|spawn|team> <name>");
					return true;
				}
				
				if (args[1].equalsIgnoreCase("arena")) {
					if (args.length < 3) {
						sender.sendMessage(ChatColor.GOLD + "Usage: /sootctf edit arena <name> <spawns|teams>");
						return true;
					}
					
					if (!sender.hasPermission("sootctf.edit.arena")) {
						sender.sendMessage(NO_PERMISSION);
						return true;
					}
					
					CTFArena arena = p.arenas.get(args[2]);
					if (arena == null) {
						sender.sendMessage(NOT_FOUND);
						return true;
					}
					
					if (args[3].equalsIgnoreCase("spawns")) {
						if (args.length < 5) {
							sender.sendMessage(ChatColor.GOLD + "Usage: /sootctf edit arena <name> spawns <add|remove> <spawn name>");
							return true;
						}
						
						if (args[4].equalsIgnoreCase("add")) {
							CTFSpawn spawn = p.spawns.get(args[5]);
							if (spawn == null) {
								sender.sendMessage(NOT_FOUND);
								return true;
							}
							arena.spawns.put(args[5],spawn);
							sender.sendMessage("success"); return true; // todo: improve message
						}
						if (args[4].equalsIgnoreCase("remove")) {
							CTFSpawn spawn = p.spawns.get(args[5]);
							if (spawn == null) {
								sender.sendMessage(NOT_FOUND);
								return true;
							}
							if (!arena.spawns.containsValue(spawn)) return true; // todo: show "spawn not in arena"
							arena.spawns.remove(args[5]);
							sender.sendMessage("success"); return true; // todo: improve message
						}
						return false;
					}
					if (args[3].equalsIgnoreCase("teams")) {
						if (args.length < 5) {
							sender.sendMessage(ChatColor.GOLD + "Usage: /sootctf edit arena <name> teams <add|remove>");
							return true;
						}
						
						if (args[4].equalsIgnoreCase("add")) {
							CTFTeam team = p.teams.get(args[5]);
							p.getServer().broadcastMessage(""+team);
							if (team == null) {
								sender.sendMessage(NOT_FOUND);
								return true;
							}
							sender.sendMessage("success"); return true; // todo: improve message
						}
						if (args[4].equalsIgnoreCase("remove")) {
							CTFTeam team = p.teams.get(args[5]);
							if (team == null) {
								sender.sendMessage(NOT_FOUND);
								return true;
							}
							if (!arena.teams.containsValue(team)) return true; // todo: show "team not in arena"
							arena.teams.remove(args[5]);
							sender.sendMessage("success"); return true; // todo: improve message
						}
						return false;
					}
					return false;
				}
				
				if (args[1].equalsIgnoreCase("spawn")) {
					if (args.length < 3) {
						sender.sendMessage(ChatColor.GOLD + "Usage: /sootctf edit spawn <name> <team|radius|position>");
						return true;
					}
					
					if (!sender.hasPermission("sootctf.edit.spawn")) {
						sender.sendMessage(NO_PERMISSION);
						return true;
					}
					
					CTFSpawn spawn = p.spawns.get(args[2]);
					if (spawn == null) {
						sender.sendMessage(NOT_FOUND);
						return true;
					}
					
					if (args[3].equalsIgnoreCase("team")) {
						if (args.length < 5) {
							sender.sendMessage(ChatColor.GREEN + "Team: "+spawn.team.color+spawn.team.name);
							return true;
						}
						
						if (args[4].equalsIgnoreCase("set")) {
							CTFTeam team = p.teams.get(args[5]);
							if (team == null) {
								sender.sendMessage(NOT_FOUND);
								return true;
							}
							spawn.team = team;
							sender.sendMessage("success"); return true; // todo: improve message
						}
						return false;
					}
					if (args[3].equalsIgnoreCase("radius")) {
							if (args.length < 5) {
								sender.sendMessage(ChatColor.GREEN + "Radius: "+spawn.spawnRadius);
								return true;
							}
						
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
						if (args.length < 5) {
							sender.sendMessage(ChatColor.GREEN + "Position: "+spawn.position.getBlockX()+" "+spawn.position.getBlockY()+" "+spawn.position.getBlockZ());
							return true;
						}
						
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
					if (args.length < 3) {
						sender.sendMessage(ChatColor.GOLD + "Usage: /sootctf edit team <name> <color|block>");
						return true;
					}
					
					if (!sender.hasPermission("sootctf.edit.team")) {
						sender.sendMessage(NO_PERMISSION);
						return true;
					}
					
					CTFTeam team = p.teams.get(args[2]);
					if (team == null) {
						sender.sendMessage(NOT_FOUND);
						return true;
					}
					
					if (args[3].equalsIgnoreCase("color") || args[3].equalsIgnoreCase("colour")) {
						if (args.length < 5) {
							sender.sendMessage(ChatColor.GREEN + "Color: "+team.color+team.color.toString());
							return true;
						}
						
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
						if (args.length < 5) {
							sender.sendMessage(ChatColor.GREEN + "Block: "+team.block.toString());
							return true;
						}
						
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
			
			if (args[0].equalsIgnoreCase("help")) {
				sender.sendMessage("Help command not yet implemented. Just read the source code 4head.\nhttps://github.com/spannerdev/SootCTF");
				return true;
			}
			return false;
			
		}
		sender.sendMessage(ChatColor.RED + "This shouldn't appear! Report at https://github.com/spannerdev/SootCTF/issues");
		return false;
	}

}
