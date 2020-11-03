package de.ancozockt.lobby.inventorys.navigation;

import java.util.HashMap;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.inventorys.navigation.items.NavGameItem;
import de.ancozockt.lobby.inventorys.navigation.items.NavItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;


public class Navigator implements Listener{

	private Inventory navigator;

	private NavConfig config;

	public Navigator(NavConfig config){
		this.config = config;
	}

	public void createNavigator(){
		Inventory inv = Bukkit.createInventory(null, config.getRows() * 9, config.getTitle());
		
		for(NavItem item : config.getItems().values()){
			inv.setItem(item.getPosition(), item.getItem());
		}
		
		for(NavGameItem item : config.getGames().values()){
			inv.setItem(item.getPosition(), item.getItem());
		}
		
		navigator = inv;
	}
	
	public void openNavigator(Player p){
		if(navigator != null){
			p.openInventory(navigator);
		}else{
			createNavigator();
			p.openInventory(navigator);
		}
	}

	@EventHandler
	public void onNav(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		if(e.getClickedInventory()!=null && e.getClickedInventory().getTitle().equalsIgnoreCase(config.getTitle())) {
			if(e.getCurrentItem() != null && e.getCurrentItem().hasItemMeta() && e.getCurrentItem().getItemMeta().hasDisplayName()){
				e.setCancelled(true);
				int position = e.getSlot();
				if(config.getGames().containsKey(position)){
					String warp = config.getGames().get(position).getWarp();
					p.closeInventory();
					Main.getInstance().getWarpManager().teleportWarp(p, warp);
				}
			}
		}
	}
	
}
