package com.spanner.SootCTF.utils;

import org.bukkit.Location;

public class CTFSpawn {

	public String name;
	
	public CTFTeam team;
	
	public Location position;
	public int spawnRadius;
	
	public CTFSpawn(String name) {
		this.name = name;
	}
	
}
