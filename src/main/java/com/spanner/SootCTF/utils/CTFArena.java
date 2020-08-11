package com.spanner.SootCTF.utils;

import java.util.HashMap;

public class CTFArena {

	public String name;
	
	public HashMap<String,CTFSpawn> spawns = new HashMap<String,CTFSpawn>();
	public HashMap<String,CTFTeam> teams = new HashMap<String,CTFTeam>();
	
	
	public CTFArena(String name) {
		this.name = name;
	}
}
