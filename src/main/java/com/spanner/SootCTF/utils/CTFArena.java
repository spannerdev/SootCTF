package com.spanner.SootCTF.utils;

import java.util.List;

public class CTFArena {

	public String name;
	
	public List<CTFSpawn> spawns;
	public List<CTFTeam> teams;
	
	
	public CTFArena(String name) {
		this.name = name;
	}
}
