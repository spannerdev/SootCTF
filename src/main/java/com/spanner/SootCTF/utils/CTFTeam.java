package com.spanner.SootCTF.utils;

import java.util.Set;

import org.bukkit.ChatColor;
import org.bukkit.Material;

public class CTFTeam {

	public String name;
	
	// todo: 1.16 rgb colour support
	
	public ChatColor color; // TODO: fix spelling :)
	public Material block;
	
	public Set<CTFPlayer> players; // not sure if i need this, we'll see how it plays out
	
	public CTFTeam(String name) {
		this.name = name;
	}
	
}
