package com.spanner.SootCTF;

import java.util.logging.Logger;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
	
	Logger logger;
	
	@Override
	public void onEnable() {
		logger = getServer().getLogger();
		logger.info("SootCTF enabled");
	}
	
}
