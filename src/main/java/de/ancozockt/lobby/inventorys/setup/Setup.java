package de.ancozockt.lobby.inventorys.setup;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.utilitys.view.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Setup {
	
	public static void openSetup(Player p){
		Inventory inv = Bukkit.createInventory(null, 3*9, "§cSetUp");
		
		inv.setItem(10, Items.getInstance().getItem(Material.COMMAND, 1, 0, "§9Settings", ""));
		inv.setItem(12, Items.getInstance().getItem(Material.NETHER_STAR, 1, 0, "§bHotbar", ""));
		inv.setItem(14, Items.getInstance().getItem(Material.BARRIER, 1, 0, "§6Shop", "§cComing in version §e1.5"));
		inv.setItem(16, Items.getInstance().getItem(Material.COMPASS, 1, 0, "§aNavigation", ""));
		
		p.openInventory(inv);
	}
	
	public static void openSetupSettings(Player p){
		Inventory inv = Bukkit.createInventory(null, 4*9, "§cSetUp §8| §a✚");

		inv.setItem(12, Items.getInstance().getItem(Material.BARRIER, 1, 0, "§6Shop", "§cComing in version §e1.5"));
		inv.setItem(21, Items.getInstance().getItem(351, 8, "Coming in version §e1.5", 1, ""));
		/*
		if(Main.config.getBoolean("Shop")){
			inv.setItem(12, Items.getInstance().getItem(Material.GOLD_INGOT, 1, 0, "§6Shop", "§8[§aOn§8]"));
			inv.setItem(21, Items.getInstance().getItem(351, 10, "§aActive", 1, ""));
		}else{
			inv.setItem(12, Items.getInstance().getItem(Material.GOLD_INGOT, 1, 0, "§6Shop", "§8[§cOff§8]"));
			inv.setItem(21, Items.getInstance().getItem(351, 1, "§cInactive", 1, ""));
		}
		*/
		if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.Updatecheck")){
			inv.setItem(13, Items.getInstance().getItem(Material.REDSTONE_COMPARATOR, 1, 0, "§cUpdateCheck", "§8[§aEnabled§8]"));
			inv.setItem(22, Items.getInstance().getItem(351, 10, "§aEnabled", 1, "§7» §cDisable"));
		}else{
			inv.setItem(13, Items.getInstance().getItem(Material.REDSTONE_COMPARATOR, 1, 0, "§cUpdateCheck", "§8[§cDisabled§8]"));
			inv.setItem(22, Items.getInstance().getItem(351, 1, "§cDisabled", 1, "§7» §aEnable"));
		}
		if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("Extras.Tablist")){
			inv.setItem(14, Items.getInstance().getItem(Material.SIGN, 1, 0, "§aTablist", "§8[§aEnabled§8]"));
			inv.setItem(23, Items.getInstance().getItem(351, 10, "§aEnabled", 1, "§7» §cDisable"));
		}else{
			inv.setItem(14, Items.getInstance().getItem(Material.SIGN, 1, 0, "§aTablist", "§8[§cDisabled§8]"));
			inv.setItem(23, Items.getInstance().getItem(351, 1, "§cDisabled", 1, "§7» §aEnable"));
		}
		
		p.openInventory(inv);
	}

}
