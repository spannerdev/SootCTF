package com.spanner.SootCTF.utils;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class CTFTeam {

	public String name;
	
	public ChatColor color; // TODO: fix spelling :)
	public Material block;
	
	public Set<CTFPlayer> players;
	
	public CTFTeam(String name) {
		this.name = name;
	}
	
}
