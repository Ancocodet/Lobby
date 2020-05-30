package de.ancozockt.lobby.warps;

import org.bukkit.Location;

public class Warp {
	
	private String name;
	private Location loc;
	
	public Warp(String name, Location loc) {
		this.name = name;
		this.loc = loc;
	}

	public String getName() {
		return name;
	}

	public Location getLoc() {
		return loc;
	}

}
