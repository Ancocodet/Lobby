package de.ancozockt.lobby.inventorys.setup.Navigation;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.inventorys.navigation.items.NavGameItem;
import de.ancozockt.lobby.inventorys.navigation.items.NavItem;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class NavSetup {

    public static void openSetUp(Player p){
        Inventory inv = Bukkit.createInventory(null, Main.getInstance().getInventoryManager().getNavConfig().getRows() * 9, "§cSetUp §8| §bNav");

        for(NavItem item : Main.getInstance().getInventoryManager().getNavigator().getItems().values()){
            inv.setItem(item.getPosition(), item.getItem());
        }

        for(NavGameItem item : Main.getInstance().getInventoryManager().getNavigator().getGames().values()){
            inv.setItem(item.getPosition(), item.getItem());
        }

        p.openInventory(inv);
    }



}
