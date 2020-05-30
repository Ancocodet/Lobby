package de.ancozockt.lobby.inventorys.setup.Navigation;

import de.ancozockt.lobby.inventorys.navigation.items.NavGameItem;
import de.ancozockt.lobby.inventorys.navigation.items.NavItem;
import de.ancozockt.lobby.utilitys.enums.HeadNumber;
import de.ancozockt.lobby.utilitys.view.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import java.util.HashMap;

public class NavItemSetup {

    private static void addDefaults(Inventory inventory){
        inventory.setItem(1, Items.getInstance().getSkull("§7+ §e100", "", "http://textures.minecraft.net/texture/3040fe836a6c2fbd2c7a9c8ec6be5174fddf1ac20f55e366156fa5f712e10", false));
        inventory.setItem(2, Items.getInstance().getSkull("§7+ §e10", "", "http://textures.minecraft.net/texture/3040fe836a6c2fbd2c7a9c8ec6be5174fddf1ac20f55e366156fa5f712e10", false));
        inventory.setItem(3, Items.getInstance().getSkull("§7+ §e1", "", "http://textures.minecraft.net/texture/3040fe836a6c2fbd2c7a9c8ec6be5174fddf1ac20f55e366156fa5f712e10", false));
        inventory.setItem(4, Items.getInstance().getSkull(" ", "", "http://textures.minecraft.net/texture/5db532b5cced46b4b535ece16eced7bbc5cac55594d61e8b8f8eac4299c9fc", false));
        inventory.setItem(5, Items.getInstance().getSkull("§7+ §510", "", "http://textures.minecraft.net/texture/3040fe836a6c2fbd2c7a9c8ec6be5174fddf1ac20f55e366156fa5f712e10", false));
        inventory.setItem(6, Items.getInstance().getSkull("§7+ §51", "", "http://textures.minecraft.net/texture/3040fe836a6c2fbd2c7a9c8ec6be5174fddf1ac20f55e366156fa5f712e10", false));

        inventory.setItem(13, Items.getInstance().getSkull("", "", "http://textures.minecraft.net/texture/ccbee28e2c79db138f3977ba472dfae6b11a9bb82d5b3d7f25479338fff1fe92", false));

        inventory.setItem(19, Items.getInstance().getSkull("§7- §e100", "", "http://textures.minecraft.net/texture/7437346d8bda78d525d19f540a95e4e79daeda795cbc5a13256236312cf", false));
        inventory.setItem(20, Items.getInstance().getSkull("§7- §e10", "", "http://textures.minecraft.net/texture/7437346d8bda78d525d19f540a95e4e79daeda795cbc5a13256236312cf", false));
        inventory.setItem(21, Items.getInstance().getSkull("§7- §e1", "", "http://textures.minecraft.net/texture/7437346d8bda78d525d19f540a95e4e79daeda795cbc5a13256236312cf", false));
        inventory.setItem(22, Items.getInstance().getSkull(" ", "", "http://textures.minecraft.net/texture/5db532b5cced46b4b535ece16eced7bbc5cac55594d61e8b8f8eac4299c9fc", false));
        inventory.setItem(23, Items.getInstance().getSkull("§7- §510", "", "http://textures.minecraft.net/texture/7437346d8bda78d525d19f540a95e4e79daeda795cbc5a13256236312cf", false));
        inventory.setItem(24, Items.getInstance().getSkull("§7- §51", "", "http://textures.minecraft.net/texture/7437346d8bda78d525d19f540a95e4e79daeda795cbc5a13256236312cf", false));

        inventory.setItem(8, Items.getInstance().getItem(Material.BARRIER, 1, 0, "§cAbbrechen", ""));
        inventory.setItem(17, Items.getInstance().getItem(Material.EMERALD, 1, 0, "§aSpeichern", ""));

    }

    public static void openSetup(Player player, NavGameItem navGameItem, int position){
        Inventory inv = Bukkit.createInventory(null, 3 * 9, "§cSetUp §8| §b#" + position);

        inv.setItem(10, Items.getInstance().getItem(Material.BARRIER, 1, 0, "§cClose", ""));

        inv.setItem(12, Items.getInstance().getItem(Material.BEACON, 1, 0, "§7Change §cPosition", "§7Current: §e" + navGameItem.getPosition()));
        inv.setItem(13, Items.getInstance().getItem(Material.NAME_TAG, 1, 0, "§7Change §5Name", "§7Current: §e" + navGameItem.getDisplayname()));
        inv.setItem(14, Items.getInstance().getItem(Material.NAME_TAG, 1, 0, "§7Change §5Lore", "§7Current: §e" + navGameItem.getLore()));
        inv.setItem(15, Items.getInstance().getItem(Material.COMPASS, 1, 0, "§7Change §6Warp", "§7Current: §e" + navGameItem.getWarp()));
        inv.setItem(16, Items.getInstance().getItem(navGameItem.getItemid(), navGameItem.getShortid(), "§7Edit §eItem", 1, "§7Current: §e" + navGameItem.getItemid() + "§8:§e" + navGameItem.getShortid()));

        player.openInventory(inv);
    }

    public static void openSetup(Player player, NavItem navItem, int position){
        Inventory inv = Bukkit.createInventory(null, 3 * 9, "§cSetUp §8| §b#" + position);

        inv.setItem(10, Items.getInstance().getItem(Material.BARRIER, 1, 0, "§cClose", ""));

        inv.setItem(13, Items.getInstance().getItem(Material.BEACON, 1, 0, "§7Change §cPosition", "§7Current: §e" + navItem.getPosition()));
        inv.setItem(14, Items.getInstance().getItem(navItem.getItemid(), navItem.getShortid(), "§7Edit §eItem", 1, "§7Current: §e" + navItem.getItemid() + "§8:§e" + navItem.getShortid()));

        player.openInventory(inv);
    }

    public static HashMap<Player, Integer[]> tempIDs = new HashMap<>();

    public static void openItemEdit(Player player, NavItem navItem, int position){
        Inventory inv = Bukkit.createInventory(null, 3 * 9, "§cSetUp §8| §a#" + position);

        addDefaults(inv);

        Integer[] tempID = {navItem.getItemid(), navItem.getShortid()};
        Integer[] itemID = calculateValues(tempID[0]);
        Integer[] shortID = calculateValues(tempID[1]);

        inv.setItem(10, Items.getInstance().getSkull("§7" + itemID[0], "", HeadNumber.getByValue(itemID[0]).getSkinURL(), false));
        inv.setItem(11, Items.getInstance().getSkull("§7" + itemID[1], "", HeadNumber.getByValue(itemID[1]).getSkinURL(), false));
        inv.setItem(12, Items.getInstance().getSkull("§7" + itemID[2], "", HeadNumber.getByValue(itemID[2]).getSkinURL(), false));
        inv.setItem(14, Items.getInstance().getSkull("§7" + shortID[1], "", HeadNumber.getByValue(shortID[1]).getSkinURL(), false));
        inv.setItem(15, Items.getInstance().getSkull("§7" + shortID[2], "", HeadNumber.getByValue(shortID[2]).getSkinURL(), false));


        tempIDs.put(player, tempID);
        player.openInventory(inv);
    }

    public static void openItemEdit(Player player, NavGameItem navGameItem, int position){
        Inventory inv = Bukkit.createInventory(null, 3 * 9, "§cSetUp §8| §a#" + position);

        addDefaults(inv);

        Integer[] tempID = {navGameItem.getItemid(), navGameItem.getShortid()};
        Integer[] itemID = calculateValues(tempID[0]);
        Integer[] shortID = calculateValues(tempID[1]);

        inv.setItem(10, Items.getInstance().getSkull("§7" + itemID[0], "", HeadNumber.getByValue(itemID[0]).getSkinURL(), false));
        inv.setItem(11, Items.getInstance().getSkull("§7" + itemID[1], "", HeadNumber.getByValue(itemID[1]).getSkinURL(), false));
        inv.setItem(12, Items.getInstance().getSkull("§7" + itemID[2], "", HeadNumber.getByValue(itemID[2]).getSkinURL(), false));
        inv.setItem(14, Items.getInstance().getSkull("§7" + shortID[1], "", HeadNumber.getByValue(shortID[1]).getSkinURL(), false));
        inv.setItem(15, Items.getInstance().getSkull("§7" + shortID[2], "", HeadNumber.getByValue(shortID[2]).getSkinURL(), false));

        tempIDs.put(player, tempID);
        player.openInventory(inv);
    }

    public static void updateItemEdit(Player player, Inventory inventory){
        if(tempIDs.containsKey(player) && inventory.getName().startsWith("§cSetUp §8| §a#")) {
            Integer[] tempID = tempIDs.get(player);
            Integer[] itemID = calculateValues(tempID[0]);
            Integer[] shortID = calculateValues(tempID[1]);

            inventory.setItem(10, Items.getInstance().getSkull("§7" + itemID[0], "", HeadNumber.getByValue(itemID[0]).getSkinURL(), false));
            inventory.setItem(11, Items.getInstance().getSkull("§7" + itemID[1], "", HeadNumber.getByValue(itemID[1]).getSkinURL(), false));
            inventory.setItem(12, Items.getInstance().getSkull("§7" + itemID[2], "", HeadNumber.getByValue(itemID[2]).getSkinURL(), false));
            inventory.setItem(14, Items.getInstance().getSkull("§7" + shortID[1], "", HeadNumber.getByValue(shortID[1]).getSkinURL(), false));
            inventory.setItem(15, Items.getInstance().getSkull("§7" + shortID[2], "", HeadNumber.getByValue(shortID[2]).getSkinURL(), false));
        }
    }

    private static int calculateValue(Integer[] values){
        int response = 0;

        response += values[0]*100;
        response += values[1]*10;
        response += values[2];

        return response;
    }

    private static Integer[] calculateValues(int value){
        Integer[] response = {0, 0, 0};

        while (value >= 100){
            value -= 100;
            response[0] = response[0] +1;
        }

        while (value >= 10){
            value -= 10;
            response[1] = response[1] +1;
        }

        response[2] = response[2] + value;

        return response;
    }
}
