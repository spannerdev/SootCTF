package com.spanner.SootCTF;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;
import com.spanner.SootCTF.utils.*;

public class Main extends JavaPlugin {
	
	public Logger logger;
	
	public HashMap<String,CTFTeam> teams = new HashMap<String,CTFTeam>();
	public HashMap<String,CTFSpawn> spawns = new HashMap<String,CTFSpawn>();;
	public HashMap<String,CTFArena> arenas = new HashMap<String,CTFArena>();;
	
	private String pre = "[SootCTF] ";
	
	@Override
	public void onEnable() {
		logger = getServer().getLogger();
		
		getCommand("sootctf").setExecutor(new Executor());
		
		logger.info(pre+"SootCTF enabled");
	}
	
	@Override
	public void onDisable() {
		saveAll();
	}
	
	public void saveAll() {
		// todo: async. i tried but not enough experience to make it worthwhile. need sync tasks for disabling.
		saveTeams();
		saveSpawns();
		saveArenas();
	}
	
	public void saveTeams() {
		File teamsymlFile = new File(getDataFolder() + "/teams.yml");
		YamlConfiguration teamsyml = new YamlConfiguration();
		for (CTFTeam team : teams.values()) {
			if (team.color != null) teamsyml.set(team.name+".color", team.color.name());
			if (team.block != null) teamsyml.set(team.name+".block", team.block.name());
		}
		try {
			teamsyml.save(teamsymlFile);
		} catch (IOException e) {
			e.printStackTrace();
			logger.severe("Could not save teams.yml file.");
		}
	}
	
	public void saveSpawns() {
		File spawnsymlFile = new File(getDataFolder() + "/spawns.yml");
		YamlConfiguration spawnsyml = new YamlConfiguration();
		for (CTFSpawn spawn : spawns.values()) {
			if (spawn.team != null) spawnsyml.set(spawn.name+".team", spawn.team.name);
			spawnsyml.set(spawn.name+".spawnradius", spawn.spawnRadius);
			if (spawn.position != null) {
				spawnsyml.set(spawn.name+".position.world", spawn.position.getWorld().getName());
				spawnsyml.set(spawn.name+".position.x", spawn.position.getX());
				spawnsyml.set(spawn.name+".position.y", spawn.position.getY());
				spawnsyml.set(spawn.name+".position.z", spawn.position.getZ());
			}
		}
		try {
			spawnsyml.save(spawnsymlFile);
		} catch (IOException e) {
			e.printStackTrace();
			logger.severe("Could not save spawns.yml file.");
		}
	}
	
	public void saveArenas() {
		File arenasymlFile = new File(getDataFolder() + "/arenas.yml");
		YamlConfiguration arenasyml = new YamlConfiguration();
		for (CTFArena arena : arenas.values()) {
			arenasyml.set(arena.name+".spawns", arena.spawns.values().toArray());
			arenasyml.set(arena.name+".teams", arena.teams.values().toArray());
		}
		try {
			arenasyml.save(arenasymlFile);
		} catch (IOException e) {
			e.printStackTrace();
			logger.severe("Could not save arenas.yml file.");
		}
	}
}
