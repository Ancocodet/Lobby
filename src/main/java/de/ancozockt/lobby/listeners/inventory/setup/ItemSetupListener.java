package de.ancozockt.lobby.listeners.inventory.setup;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.inventorys.navigation.NavConfig;
import de.ancozockt.lobby.inventorys.navigation.Navigator;
import de.ancozockt.lobby.inventorys.navigation.items.NavGameItem;
import de.ancozockt.lobby.inventorys.navigation.items.NavItem;
import de.ancozockt.lobby.inventorys.setup.Navigation.NavItemSetup;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ItemSetupListener implements Listener {

    @EventHandler
    public void onSetupItemEdit(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("BungeeCord")
                || player.getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("World"))){
            if(event.getClickedInventory()!=null && event.getClickedInventory().getTitle().startsWith("§cSetUp §8| §a#")) {
                if(event.getCurrentItem()!=null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()) {
                    event.setCancelled(true);
                    if(player.hasPermission("lobby.setup")) {
                        if (event.getCurrentItem().getType() == Material.BARRIER) {
                            if (NavItemSetup.tempIDs.containsKey(player)) {
                                NavItemSetup.tempIDs.remove(player);
                            }
                            int id = Integer.valueOf(event.getClickedInventory().getTitle().replace("§cSetUp §8| §a#", ""));
                            NavConfig navigator = Main.getInstance().getInventoryManager().getNavConfig();
                            if (navigator.getGames().containsKey(id)) {
                                NavGameItem gameItem = navigator.getGames().get(id);
                                NavItemSetup.openSetup(player, gameItem, gameItem.getPosition());
                            } else if (navigator.getItems().containsKey(id)) {
                                NavItem navItem = navigator.getItems().get(id);
                                NavItemSetup.openSetup(player, navItem, navItem.getPosition());
                            }else{
                                player.closeInventory();
                            }
                        } else if (event.getCurrentItem().getType() == Material.EMERALD) {
                            if (NavItemSetup.tempIDs.containsKey(player)) {
                                Integer[] tempID = NavItemSetup.tempIDs.get(player);
                                int id = Integer.valueOf(event.getClickedInventory().getTitle().replace("§cSetUp §8| §a#", ""));
                                NavConfig navigator = Main.getInstance().getInventoryManager().getNavConfig();
                                if (navigator.getGames().containsKey(id)) {
                                    NavGameItem gameItem = navigator.getGames().get(id);
                                    gameItem.setItemid(tempID[0]);
                                    gameItem.setShortid(tempID[1]);
                                    gameItem.createItem();

                                    String itemID = tempID[0].toString();
                                    if(tempID[1] > 0){
                                        itemID = itemID + ":" + tempID[1];
                                    }
                                    Main.getInstance().getInventoryManager().getNavConfig().changeItemID(gameItem.getName(), itemID);

                                    navigator.getGames().put(id, gameItem);
                                    Main.getInstance().getInventoryManager().getNavigator().createNavigator();

                                    NavItemSetup.tempIDs.remove(player);
                                    NavItemSetup.openSetup(player, gameItem, gameItem.getPosition());
                                } else if (navigator.getItems().containsKey(id)) {
                                    NavItem navItem = navigator.getItems().get(id);
                                    navItem.setItemid(tempID[0]);
                                    navItem.setShortid(tempID[1]);
                                    navItem.createItem();

                                    String itemID = tempID[0].toString();
                                    if(tempID[1] > 0){
                                        itemID = itemID + ":" + tempID[1];
                                    }
                                    Main.getInstance().getInventoryManager().getNavConfig().changeItemID(navItem.getName(), itemID);

                                    navigator.getItems().put(id, navItem);
                                    Main.getInstance().getInventoryManager().getNavigator().createNavigator();

                                    NavItemSetup.tempIDs.remove(player);
                                    NavItemSetup.openSetup(player, navItem, navItem.getPosition());
                                }else{
                                    player.closeInventory();
                                }
                            } else {
                                player.closeInventory();
                            }
                        } else if (event.getCurrentItem().getType() == Material.SKULL_ITEM) {
                            if (NavItemSetup.tempIDs.containsKey(player)) {
                                Integer[] tempID = NavItemSetup.tempIDs.get(player);
                                if (event.getCurrentItem().getItemMeta().getDisplayName().startsWith("§7+ §e")) {
                                    int value = Integer.valueOf(event.getCurrentItem().getItemMeta().getDisplayName().replace("§7+ §e", ""));
                                    if ((tempID[0] + value) < 1000) {
                                        tempID[0] += value;
                                    }
                                    NavItemSetup.tempIDs.put(player, tempID);
                                    NavItemSetup.updateItemEdit(player, event.getClickedInventory());
                                } else if (event.getCurrentItem().getItemMeta().getDisplayName().startsWith("§7- §e")) {
                                    int value = Integer.valueOf(event.getCurrentItem().getItemMeta().getDisplayName().replace("§7- §e", ""));
                                    if ((tempID[0] - value) > -1) {
                                        tempID[0] -= value;
                                    }
                                    NavItemSetup.tempIDs.put(player, tempID);
                                    NavItemSetup.updateItemEdit(player, event.getClickedInventory());
                                } else if (event.getCurrentItem().getItemMeta().getDisplayName().startsWith("§7+ §5")) {
                                    int value = Integer.valueOf(event.getCurrentItem().getItemMeta().getDisplayName().replace("§7+ §5", ""));
                                    if ((tempID[1] + value) < 100) {
                                        tempID[1] += value;
                                    }
                                    NavItemSetup.tempIDs.put(player, tempID);
                                    NavItemSetup.updateItemEdit(player, event.getClickedInventory());
                                } else if (event.getCurrentItem().getItemMeta().getDisplayName().startsWith("§7- §5")) {
                                    int value = Integer.valueOf(event.getCurrentItem().getItemMeta().getDisplayName().replace("§7- §5", ""));
                                    if ((tempID[1] - value) > -1) {
                                        tempID[1] -= value;
                                    }
                                    NavItemSetup.tempIDs.put(player, tempID);
                                    NavItemSetup.updateItemEdit(player, event.getClickedInventory());
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onSetupItemPosition(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("BungeeCord")
                || player.getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("World"))){
            if(event.getClickedInventory()!=null && event.getClickedInventory().getTitle().startsWith("§cSetUp §8| §c#")) {
                if(event.getCurrentItem()!=null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()) {
                    if(player.hasPermission("lobby.setup")) {
                        if (event.getCurrentItem().getType() == Material.BARRIER) {
                            event.setCancelled(true);
                        } else if (event.isRightClick()) {
                            int id = Integer.valueOf(event.getClickedInventory().getTitle().replace("§cSetUp §8| §c#", ""));
                            NavConfig navigator = Main.getInstance().getInventoryManager().getNavConfig();
                            if (navigator.getGames().containsKey(id)) {
                                NavGameItem gameItem = navigator.getGames().get(id);
                                gameItem.setPosition(event.getSlot());

                                Main.getInstance().getInventoryManager().getNavConfig().changePosition(gameItem.getName(), gameItem.getPosition()+1);

                                navigator.getGames().remove(id);
                                navigator.getGames().put(event.getSlot(), gameItem);
                                Main.getInstance().getInventoryManager().getNavigator().createNavigator();

                                player.closeInventory();
                                Main.getInstance().getNmsHandler().getChatapi().sendActionbar(player, "§7Position §asaved");
                            }else if (navigator.getItems().containsKey(id)) {
                                NavItem navItem = navigator.getItems().get(id);
                                navItem.setPosition(event.getSlot());

                                Main.getInstance().getInventoryManager().getNavConfig().changePosition(navItem.getName(), navItem.getPosition()+1);

                                navigator.getItems().remove(id);
                                navigator.getItems().put(event.getSlot(), navItem);
                                Main.getInstance().getInventoryManager().getNavigator().createNavigator();

                                player.closeInventory();
                                Main.getInstance().getNmsHandler().getChatapi().sendActionbar(player, "§7Position §asaved");
                            }
                        }
                    }else {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

}
