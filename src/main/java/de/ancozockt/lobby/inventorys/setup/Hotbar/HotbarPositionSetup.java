package de.ancozockt.lobby.inventorys.setup.Hotbar;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.inventorys.hotbar.LobbyItem;
import de.ancozockt.lobby.inventorys.hotbar.LobbyItemRole;
import de.ancozockt.lobby.utilitys.view.Items;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Arrays;

public class HotbarPositionSetup {

    public static void openSetup(Player player, LobbyItemRole role, boolean extended){
        Inventory inv = Bukkit.createInventory(null, 9, "§cS §8| §9" + role.name());
        if(extended)
            inv = Bukkit.createInventory(null, 9, "§cS §8| §3" + role.name());

        ArrayList<LobbyItem> items = Main.getInstance().getLobbyItems().getDefaultVersion();
        if(extended)
            items = Main.getInstance().getLobbyItems().getExtendedVersion();

        LobbyItem target = Main.getInstance().getLobbyItems().getDisplayNamesDefault().get(role);
        if(extended)
            target = Main.getInstance().getLobbyItems().getDisplayNamesExtended().get(role);

        for(LobbyItem item : items){
            if(!item.getName().equalsIgnoreCase(role.name()) && item.isEnabled()){
                inv.setItem(item.getPosition(), Items.getInstance().getItem(Material.BARRIER, 1, 0, "§7*-*", ""));
            }
        }

        ItemStack itemStack = target.getItemStack().clone();
        ItemMeta itemMeta = itemStack.getItemMeta();
        itemMeta.setLore(Arrays.asList(new String[]{"§cRightclick §7» §eSave position", "§cClose inventory §7» §e Cancel"}));
        itemStack.setItemMeta(itemMeta);
        inv.setItem(target.getPosition(), itemStack);

        player.openInventory(inv);
    }
}
