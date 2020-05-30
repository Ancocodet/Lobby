package de.ancozockt.lobby.inventorys.setup.Hotbar;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.inventorys.hotbar.LobbyItem;
import de.ancozockt.lobby.inventorys.hotbar.LobbyItemRole;
import de.ancozockt.lobby.utilitys.view.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.HashMap;

public class HotbarSetup {

    public static void openSetup(Player player, boolean extended){
        Inventory inv = Bukkit.createInventory(null, 6*9, "§cSetUp §8| §5Hot");

        HashMap<LobbyItemRole, LobbyItem> items = Main.getInstance().getLobbyItems().getDisplayNamesDefault();
        if(extended)
            items = Main.getInstance().getLobbyItems().getDisplayNamesExtended();

        /*
        Settings Item
         */
        LobbyItem seli = items.get(LobbyItemRole.Settings);

        ItemStack settings = seli.getItemStack().clone();
        ItemMeta setMeta = settings.getItemMeta();
        setMeta.setDisplayName("§aSettings §7Item");
        settings.setItemMeta(setMeta);
        inv.setItem(1, settings);

        inv.setItem(3, enabledItem(seli.isEnabled(), (seli.getPosition() >= 9)));
        if((seli.getPosition() < 9)) {
            inv.setItem(4, Items.getInstance().getItem(Material.BEACON, 1, 0, "§7Change §ePosition", "§7Current: §e" + (seli.getPosition()+1)));
        }else{
            inv.setItem(4, Items.getInstance().getItem(Material.BARRIER, 1, 0, "§7Change §ePosition", "§7Current: §e" + (seli.getPosition()+1) + ", §cChange in configurations!"));
        }
        inv.setItem(5, Items.getInstance().getItem(Material.SIGN, 1, 0, "§7Change §3Name", "§7Current: §e" + seli.getDisplayname()));
        inv.setItem(6, Items.getInstance().getItem(Material.WORKBENCH, 1, 0, "§7Change §dItem", "§7Current: §e" + seli.getItemID() + "§7:" + seli.getShortID()));

        /*
        Navigation Item
         */
        LobbyItem nli = items.get(LobbyItemRole.Navigation);

        ItemStack navigation = nli.getItemStack().clone();
        ItemMeta navMeta = navigation.getItemMeta();
        navMeta.setDisplayName("§bNavigation §7Item");
        navigation.setItemMeta(navMeta);
        inv.setItem(10, navigation);

        inv.setItem(12, enabledItem(nli.isEnabled(), (nli.getPosition() >= 9) ));
        if((nli.getPosition() < 9)) {
            inv.setItem(13, Items.getInstance().getItem(Material.BEACON, 1, 0, "§7Change §ePosition", "§7Current: §e" + (nli.getPosition()+1)));
        }else{
            inv.setItem(13, Items.getInstance().getItem(Material.BARRIER, 1, 0, "§7Change §ePosition", "§7Current: §e" + (nli.getPosition()+1) + ", §cChange in configurations!"));
        }
        inv.setItem(14, Items.getInstance().getItem(Material.SIGN, 1, 0, "§7Change §3Name", "§7Current: §e" + nli.getDisplayname()));
        inv.setItem(15, Items.getInstance().getItem(Material.WORKBENCH, 1, 0, "§7Change §dItem", "§7Current: §e" + nli.getItemID() + "§7:§e" + nli.getShortID()));

        /*
        Silentlobby Item
         */
        LobbyItem slli = items.get(LobbyItemRole.Silentlobby);

        ItemStack silentlobby = slli.getItemStack().clone();
        ItemMeta slMeta = silentlobby.getItemMeta();
        slMeta.setDisplayName("§cSilentlobby §7Item");
        silentlobby.setItemMeta(slMeta);
        inv.setItem(19, silentlobby);

        inv.setItem(21, enabledItem(slli.isEnabled(), (slli.getPosition() >= 9)));
        if((slli.getPosition() < 9)) {
            inv.setItem(22, Items.getInstance().getItem(Material.BEACON, 1, 0, "§7Change §ePosition", "§7Current: §e" + (slli.getPosition()+1)));
        }else{
            inv.setItem(22, Items.getInstance().getItem(Material.BARRIER, 1, 0, "§7Change §ePosition", "§7Current: §e" + (slli.getPosition()+1) + ", §cChange in configurations!"));
        }
        inv.setItem(23, Items.getInstance().getItem(Material.SIGN, 1, 0, "§7Change §3Name", "§7Current: §e" + slli.getDisplayname()));
        inv.setItem(24, Items.getInstance().getItem(Material.WORKBENCH, 1, 0, "§7Change §dItem", "§7Current: §e" + slli.getItemID() + "§7:§e" + slli.getShortID()));

        /*
        Shop Item
         */
        LobbyItem shli = items.get(LobbyItemRole.Shop);

        ItemStack shop = shli.getItemStack().clone();
        ItemMeta shMeta = shop.getItemMeta();
        shMeta.setDisplayName("§6Shop §7Item");
        shop.setItemMeta(shMeta);
        inv.setItem(28, shop);

        inv.setItem(30, enabledItem(shli.isEnabled(), (shli.getPosition() >= 9)));
        if((shli.getPosition() < 9)) {
            inv.setItem(31, Items.getInstance().getItem(Material.BEACON, 1, 0, "§7Change §ePosition", "§7Current: §e" + (shli.getPosition()+1)));
        }else{
            inv.setItem(31, Items.getInstance().getItem(Material.BARRIER, 1, 0, "§7Change §ePosition", "§7Current: §e" + (shli.getPosition()+1) + ", §cChange in configurations!"));
        }
        inv.setItem(32, Items.getInstance().getItem(Material.SIGN, 1, 0, "§7Change §3Name", "§7Current: §e" + shli.getDisplayname()));
        inv.setItem(33, Items.getInstance().getItem(Material.WORKBENCH, 1, 0, "§7Change §dItem", "§7Current: §e" + shli.getItemID() + "§7:§e" + shli.getShortID()));

        /*
        Switch Items
         */
        inv.setItem(48, Items.getInstance().getItem("§aDefault §7Version", "", Material.IRON_INGOT, 1, 0, Enchantment.DURABILITY, 1, !extended));
        inv.setItem(49, Items.getInstance().getItem(Material.IRON_DOOR, 1, 0, "§cBack", "§7Will give you §ecurrent §7Hotbar"));
        inv.setItem(50, Items.getInstance().getItem("§3Extended §7Version", "", Material.DIAMOND, 1, 0, Enchantment.DURABILITY, 1, extended));
        player.openInventory(inv);
    }

    private static ItemStack enabledItem(boolean enabled, boolean ableToEnable){
        if(!ableToEnable) {
            if (enabled) {
                return Items.getInstance().getItem(351, 10, "§aEnabled", 1, "§7» §cDisable §7Item");
            } else {
                return Items.getInstance().getItem(351, 1, "§cDisabled", 1, "§7» §aEnable §7Item");
            }
        }else{
            return Items.getInstance().getItem(351, 8, "§cCan´t be §aEnabled", 1, "§7» §7Change §ePosition");
        }
    }

}
