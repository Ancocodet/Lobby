package de.ancozockt.lobby.warps;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.utilitys.background.AEStringBuilder;
import de.ancozockt.utility.configurations.interfaces.Configuration;
import de.ancozockt.utility.database.FileManager;
import de.ancozockt.utility.database.FileType;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

public class WarpManager {
	
	public File warps;
	public FileConfiguration cfgwarps;

	public HashMap<String, Warp> warplist = new HashMap<>();

	public WarpManager() {
		FileManager fileManager = new FileManager("plugins/Lobby");
		warps = fileManager.getFile("warps", FileType.YAML);
		cfgwarps = YamlConfiguration.loadConfiguration(warps);

		for(String name : cfgwarps.getConfigurationSection("").getKeys(false)){
			World world = Bukkit.getWorld(cfgwarps.getString(name + ".loc.world"));
			double x = cfgwarps.getDouble(name + ".loc.x");
			double y = cfgwarps.getDouble(name + ".loc.y");
			double z = cfgwarps.getDouble(name + ".loc.z");
			double yaw = cfgwarps.getDouble(name + ".loc.yaw");
			double pitch = cfgwarps.getDouble(name + ".loc.pitch");
			Location loc = new Location(world, x, y, z, (float) yaw, (float) pitch);
			
			Warp warp = new Warp(name, loc);
			warplist.put(name, warp);
		}
	}
	
	public Location getWarp(String name){
		if(warplist.containsKey(name)){
			return warplist.get(name).getLoc();
		}else{
			return null;
		}
	}
	
	public void teleportWarp(Player p, String name){
		Configuration language = Main.getInstance().getConfigurationManager().getLanguage();

		if(warplist.containsKey(name)){
			Location warp = warplist.get(name).getLoc();
			p.teleport(warp);
		}else{
			if(language.getString("Messages.Warp.NotExists") !=null){
				String msg = language.getString("Messages.Warp.NotExists").replace("%name", name);
				p.sendMessage(AEStringBuilder.buildMessage(msg));
			}
		}
	}
	
	public void createWarp(Player p, String name){
		Configuration language = Main.getInstance().getConfigurationManager().getLanguage();

		if(!warplist.containsKey(name)){
			Location loc = p.getLocation();
			double x = loc.getX(); cfgwarps.set(name + ".loc.x", x);
			double y = loc.getY(); cfgwarps.set(name + ".loc.y", y);
			double z = loc.getZ(); cfgwarps.set(name + ".loc.z", z);
			double yaw = loc.getYaw(); cfgwarps.set(name + ".loc.yaw", yaw);
			double pitch = loc.getPitch(); cfgwarps.set(name + ".loc.pitch", pitch);
			String world = loc.getWorld().getName(); cfgwarps.set(name + ".loc.world", world);
			
			try {
				cfgwarps.save(warps);
				
				Warp warp = new Warp(name, loc);
				warplist.put(name, warp);
				
				if(language.getString("Messages.Warp.Create") != null){
					String msg = language.getString("Messages.Warp.Create").replace("%name", name);
					p.sendMessage(AEStringBuilder.buildMessage(msg));
				}
			} catch (IOException e) {
				if(language.getString("Messages.Warp.Error") != null) {
					p.sendMessage(AEStringBuilder.buildMessage(language.getString("Messages.Warp.Error")));
				}
			}
		}else{
			if(language.getString("Messages.Warp.Exists") != null) {
				String msg = language.getString("Messages.Warp.Exists").replace("%name", name);
				p.sendMessage(AEStringBuilder.buildMessage(msg));
			}
		}
	}
	
	public void deleteWarp(Player p, String name){
		Configuration language = Main.getInstance().getConfigurationManager().getLanguage();

		if(warplist.containsKey(name)){
			
			cfgwarps.set(name, null);
			
			try {
				cfgwarps.save(warps);
				
				if(warplist.containsKey(name)){
					warplist.remove(name);
				}

				if(language.getString("Messages.Warp.Delete") != null){
					String msg = language.getString("Messages.Warp.Delete").replace("%name", name);
					p.sendMessage(AEStringBuilder.buildMessage(msg));
				}
			} catch (IOException e) {
				if(language.getString("Messages.Warp.Error") != null) {
					p.sendMessage(AEStringBuilder.buildMessage(language.getString("Messages.Warp.Error")));
				}
			}
		}else{
			if(language.getString("Messages.Warp.NotExists") != null) {
				String msg = language.getString("Messages.Warp.NotExists").replace("%name", name);
				p.sendMessage(AEStringBuilder.buildMessage(msg));
			}
		}
	}
}
