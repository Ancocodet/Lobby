package de.ancozockt.lobby.inventorys.navigation;

import java.io.File;
import java.io.IOException;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.inventorys.navigation.items.NavGameItem;
import de.ancozockt.lobby.inventorys.navigation.items.NavItem;
import de.ancozockt.lobby.inventorys.navigation.items.NavItemType;
import de.ancozockt.utility.database.FileManager;
import de.ancozockt.utility.database.FileType;
import org.bukkit.configuration.file.FileConfiguration;

import org.bukkit.configuration.file.YamlConfiguration;

public class NavConfig {

	private static String Title = "§bNavigator";
	private static int Rows = 5;

	private static File navigator;
	private static FileConfiguration cfgnavi;

	public NavConfig(){
		FileManager fileManager = new FileManager("plugins/Lobby");

		navigator = fileManager.getFile("navigator", FileType.YAML);
		cfgnavi = YamlConfiguration.loadConfiguration(navigator);
	}

	public void changePosition(String item, int position){
		FileConfiguration cfg = cfgnavi;

		cfg.set("Items."+item+".Position", position);

		try {
			cfg.save(navigator);
		} catch (IOException ignored) { }
	}

	public void changeItemID(String item, String itemID){
		FileConfiguration cfg = cfgnavi;

		cfg.set("Items."+item+".Item", itemID);

		try {
			cfg.save(navigator);
		} catch (IOException ignored) { }
	}

	public NavGameItem changeName(String item, int position, String display){
		FileConfiguration cfg = cfgnavi;

		cfg.set("Items."+item+".DisplayName", display.replace("&", "§"));

		try {
			Navigator nav = Main.getInstance().getInventoryManager().getNavigator();

			cfg.save(navigator);
			NavGameItem gameItem = nav.getGames().get(position);

			gameItem.setDisplayname(display.replace("&", "§"));
			gameItem.createItem();

			nav.getGames().put(position, gameItem);

			nav.createNavigator();

			return nav.getGames().get(position);
		} catch (IOException e) {
			return null;
		}
	}

	public NavGameItem changeLore(String item, int position, String lore){
		FileConfiguration cfg = cfgnavi;

		cfg.set("Items."+item+".Lore", lore.replace("&", "§"));

		try {
			Navigator nav = Main.getInstance().getInventoryManager().getNavigator();

			cfg.save(navigator);
			NavGameItem gameItem = nav.getGames().get(position);

			gameItem.setLore(lore.replace("&", "§"));
			gameItem.createItem();

			nav.getGames().put(position, gameItem);
			nav.createNavigator();

			return nav.getGames().get(position);
		} catch (IOException e) {
			return null;
		}
	}

	public NavGameItem changeWarp(String item, int position, String warp){
		FileConfiguration cfg = cfgnavi;

		cfg.set("Items."+item+".Warp", warp);

		try {
			Navigator nav = Main.getInstance().getInventoryManager().getNavigator();

			cfg.save(navigator);
			NavGameItem gameItem = nav.getGames().get(position);

			gameItem.setWarp(warp);

			return nav.getGames().get(position);
		} catch (IOException e) {
			return null;
		}
	}

	public void setDefaults(){
		FileConfiguration cfg = cfgnavi;

		cfg.options().copyDefaults(true);

		cfg.addDefault("Title", "&bNavigator");
		cfg.addDefault("Rows", 5);

		if(!navigator.exists()){
			cfg.set("Items.SuperJump.Position", 23);
			cfg.set("Items.SuperJump.Item", "301");
			cfg.set("Items.SuperJump.DisplayName", "&aSuperJump");
			cfg.set("Items.SuperJump.Lore", "");
			cfg.set("Items.SuperJump.Warp", "Superjump");
			cfg.set("Items.SuperJump.Type", "Game");

			cfg.set("Items.Glasplatte1.Position", 22);
			cfg.set("Items.Glasplatte1.Item", "160:5");
			cfg.set("Items.Glasplatte1.Type", "Item");

			cfg.set("Items.Glasplatte2.Position", 24);
			cfg.set("Items.Glasplatte2.Item", "160:5");
			cfg.set("Items.Glasplatte2.Type", "Item");
		}

		try {
			cfg.save(navigator);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void read(){
		Navigator nav = Main.getInstance().getInventoryManager().getNavigator();
		FileConfiguration cfg = cfgnavi;

		Title = cfgnavi.getString("Title").replace("&", "§");
		Rows = cfgnavi.getInt("Rows");

		for(String name : cfgnavi.getConfigurationSection("Items").getKeys(false)){
			NavItemType type = NavItemType.valueOf(cfg.getString("Items." + name + ".Type"));

			if(type == NavItemType.Game){

				int position = cfg.getInt("Items." + name + ".Position");
				position = position-1;

				String itemraw = cfg.getString("Items." + name + ".Item");
				int itemid;
				int shortid;
				if(itemraw.contains(":")){
					String[] item = itemraw.split(":");

					try{
						itemid = Integer.valueOf(item[0]);
					}catch(NumberFormatException e){
						itemid = 1;
					}
					try{
						shortid = Integer.valueOf(item[1]);
					}catch(NumberFormatException e){
						shortid = 0;
					}
				}else{
					try{
						itemid = Integer.valueOf(itemraw);
					}catch(NumberFormatException e){
						itemid = 1;
					}
					shortid = 0;
				}

				String displayname = cfg.getString("Items." + name + ".DisplayName").replace("&", "§");
				String lore = cfg.getString("Items." + name + ".Lore").replace("&", "§");;

				String warp = cfg.getString("Items." + name + ".Warp");

				NavGameItem gameitem = new NavGameItem(name, position, displayname, lore, warp, itemid, shortid);
				nav.getGames().put(position, gameitem);
			}else if(type == NavItemType.Item){

				int position = cfg.getInt("Items." + name + ".Position");
				position = position-1;

				String itemraw = cfg.getString("Items." + name + ".Item");
				int itemid;
				int shortid;
				if(itemraw.contains(":")){
					String[] item = itemraw.split(":");

					try{
						itemid = Integer.valueOf(item[0]);
					}catch(NumberFormatException e){
						itemid = 1;
					}
					try{
						shortid = Integer.valueOf(item[1]);
					}catch(NumberFormatException e){
						shortid = 0;
					}
				}else{
					try{
						itemid = Integer.valueOf(itemraw);
					}catch(NumberFormatException e){
						itemid = 1;
					}
					shortid = 0;
				}

				NavItem normalitem = new NavItem(name, position, itemid, shortid);
				nav.getItems().put(position, normalitem);
			}
		}
	}

	public String getTitle() {
		return Title;
	}

	public int getRows() {
		return Rows;
	}
	
}
