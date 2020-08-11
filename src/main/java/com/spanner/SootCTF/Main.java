package com.spanner.SootCTF;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.spanner.SootCTF.utils.*;

public class Main extends JavaPlugin {
	
	Logger logger;
	
	public HashMap<String,CTFTeam> teams = new HashMap<String,CTFTeam>();
	public HashMap<String,CTFSpawn> spawns = new HashMap<String,CTFSpawn>();;
	public HashMap<String,CTFArena> arenas = new HashMap<String,CTFArena>();;
	
	@Override
	public void onEnable() {
		logger = getServer().getLogger();
		logger.info("SootCTF enabled");
		getCommand("sootctf").setExecutor(new Executor());
	}
	
}
