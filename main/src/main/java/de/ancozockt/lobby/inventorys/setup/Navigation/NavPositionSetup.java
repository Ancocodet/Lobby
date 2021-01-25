package de.ancozockt.lobby.inventorys.setup.Navigation;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.inventorys.navigation.items.NavGameItem;
import de.ancozockt.lobby.inventorys.navigation.items.NavItem;
import de.ancozockt.lobby.utilitys.view.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;

public class NavPositionSetup {

    public static void openPositionSetup(Player player, NavGameItem gameItem, int position){
        Inventory inv = Bukkit.createInventory(null, Main.getInstance().getInventoryManager().getNavConfig().getRows() * 9, "§cSetUp §8| §c#" + position);

        for(int items : Main.getInstance().getInventoryManager().getNavConfig().getItems().keySet()){
            if(items != position){
                inv.setItem(items, Items.getInstance().getItem(Material.BARRIER, 1,0, "§7*-*", ""));
            }
        }

        for(int games : Main.getInstance().getInventoryManager().getNavConfig().getGames().keySet()){
            if(games != position){
                inv.setItem(games, Items.getInstance().getItem(Material.BARRIER, 1,0, "§7*-*", ""));
            }
        }

        ItemStack itemStack = gameItem.getItem().clone();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(Arrays.asList("§cRightclick §7» §eSave position", "§cClose inventory §7» §e Cancel"));
        itemStack.setItemMeta(itemMeta);

        inv.setItem(position, itemStack);

        player.openInventory(inv);
    }

    public static void openPositionSetup(Player player, NavItem navItem, int position){
        Inventory inv = Bukkit.createInventory(null, Main.getInstance().getInventoryManager().getNavConfig().getRows() * 9, "§cSetUp §8| §c#" + position);

        for(int items : Main.getInstance().getInventoryManager().getNavConfig().getItems().keySet()){
            if(items != position){
                inv.setItem(items, Items.getInstance().getItem(Material.BARRIER, 1,0, "§7*-*", ""));
            }
        }

        for(int games : Main.getInstance().getInventoryManager().getNavConfig().getGames().keySet()){
            if(games != position){
                inv.setItem(games, Items.getInstance().getItem(Material.BARRIER, 1,0, "§7*-*", ""));
            }
        }

        ItemStack itemStack = navItem.getItem().clone();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(Arrays.asList("§cRightclick §7» §eSave position", "§cClose inventory §7» §e Cancel"));
        itemStack.setItemMeta(itemMeta);

        inv.setItem(position, itemStack);

        player.openInventory(inv);
    }

}
