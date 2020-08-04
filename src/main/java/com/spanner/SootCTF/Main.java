package com.spanner.SootCTF;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

import com.spanner.SootCTF.utils.*;

public class Main extends JavaPlugin {
	
	Logger logger;
	
	public HashMap<String,CTFTeam> teams;
	public HashMap<String,CTFSpawn> spawns;
	public HashMap<String,CTFArena> arenas;
	
	@Override
	public void onEnable() {
		logger = getServer().getLogger();
		logger.info("SootCTF enabled");
	}
	
}
