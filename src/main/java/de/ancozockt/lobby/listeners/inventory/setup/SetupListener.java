package de.ancozockt.lobby.listeners.inventory.setup;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.inventorys.navigation.NavConfig;
import de.ancozockt.lobby.inventorys.navigation.Navigator;
import de.ancozockt.lobby.inventorys.navigation.items.NavGameItem;
import de.ancozockt.lobby.inventorys.navigation.items.NavItem;
import de.ancozockt.lobby.inventorys.setup.Hotbar.HotbarSetup;
import de.ancozockt.lobby.inventorys.setup.Navigation.NavItemSetup;
import de.ancozockt.lobby.inventorys.setup.Navigation.NavPositionSetup;
import de.ancozockt.lobby.inventorys.setup.Navigation.NavSetup;
import de.ancozockt.lobby.inventorys.setup.Navigation.NavSetupUtilitys;
import de.ancozockt.lobby.inventorys.setup.Setup;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class SetupListener implements Listener {

    @EventHandler
    public void onSetup(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("BungeeCord")
                || player.getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("World"))){
            if(event.getClickedInventory()!=null && event.getClickedInventory().getTitle().equalsIgnoreCase("§cSetUp")) {
                if(event.getCurrentItem()!=null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()){
                    event.setCancelled(true);
                    if(player.hasPermission("lobby.setup")){
                        if(event.getCurrentItem().getType() == Material.COMMAND){
                            Setup.openSetupSettings(player);
                        }else if(event.getCurrentItem().getType() == Material.COMPASS){
                            NavSetup.openSetUp(player);
                        }else if(event.getCurrentItem().getType() == Material.NETHER_STAR){
                            HotbarSetup.openSetup(player, false);
                        }
                    }else{
                        player.closeInventory();
                    }
                }
            }else if(event.getClickedInventory()!=null && event.getClickedInventory().getTitle().equalsIgnoreCase("§cSetUp §8| §a✚")) {
                if(event.getCurrentItem()!=null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()){
                    event.setCancelled(true);
                    if(player.hasPermission("lobby.setup")){
                        if(event.getCurrentItem().getType() == Material.INK_SACK){
							/*if(e.getSlot() == 21){
								if(e.getCurrentItem().getDurability() == 10) {
									Main.config.changeSetting("Ranks", false);
								} else{
									Main.config.changeSetting("Ranks", true);
								}
								Setup.openSetupSettings(p);
							}else */
                            if(event.getSlot() == 22){
                                if(event.getCurrentItem().getDurability() == 10){
                                    Main.getInstance().getConfigurationManager().getConfiguration().setValue("Updatecheck", false);
                                }else{
                                    Main.getInstance().getConfigurationManager().getConfiguration().setValue("Updatecheck", true);
                                }
                                Setup.openSetupSettings(player);
                            }else if(event.getSlot() == 23){
                                if(event.getCurrentItem().getDurability() == 10){
                                    Main.getInstance().getConfigurationManager().getConfiguration().setValue("Tablist", false);
                                }else{
                                    Main.getInstance().getConfigurationManager().getConfiguration().setValue("Tablist", true);
                                }
                                Setup.openSetupSettings(player);
                            }
                        }
                    }else{
                        player.closeInventory();
                    }
                }
            }else if(event.getClickedInventory()!=null && event.getClickedInventory().getTitle().equalsIgnoreCase("§cSetUp §8| §bNav")) {
                if(event.getCurrentItem()!=null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()){
                    event.setCancelled(true);
                    NavConfig navigator = Main.getInstance().getInventoryManager().getNavConfig();
                    if(navigator.getGames().containsKey(event.getSlot())){
                        NavItemSetup.openSetup(player, navigator.getGames().get(event.getSlot()), event.getSlot());
                    }else if(navigator.getItems().containsKey(event.getSlot())){
                        NavItemSetup.openSetup(player, navigator.getItems().get(event.getSlot()), event.getSlot());
                    }else{
                        player.closeInventory();
                    }
                }
            }else if(event.getClickedInventory()!=null && event.getClickedInventory().getTitle().startsWith("§cSetUp §8| §b#")) {
                if(event.getCurrentItem()!=null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()) {
                    event.setCancelled(true);
                    if(event.getCurrentItem().getType() == Material.BARRIER){
                        NavSetup.openSetUp(player);
                    }else if(event.getCurrentItem().getType() == Material.BEACON){
                        int id = Integer.valueOf(event.getClickedInventory().getTitle().replace("§cSetUp §8| §b#", ""));
                        NavConfig navigator = Main.getInstance().getInventoryManager().getNavConfig();
                        if(navigator.getGames().containsKey(id)) {
                            NavGameItem gameItem = navigator.getGames().get(id);
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Change §cPosition")) {
                                NavPositionSetup.openPositionSetup(player, gameItem, gameItem.getPosition());
                            }
                        }else if(navigator.getItems().containsKey(id)) {
                            NavItem navItem = navigator.getItems().get(id);
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Change §cPosition")) {
                                NavPositionSetup.openPositionSetup(player, navItem, navItem.getPosition());
                            }
                        }else{
                            player.closeInventory();
                        }
                    }else if(event.getCurrentItem().getType() == Material.NAME_TAG){
                        int id = Integer.valueOf(event.getClickedInventory().getTitle().replace("§cSetUp §8| §b#", ""));
                        NavConfig navigator = Main.getInstance().getInventoryManager().getNavConfig();
                        if(navigator.getGames().containsKey(id)) {
                            NavGameItem gameItem = navigator.getGames().get(id);
                            if (event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase("§7Change §5Name")) {
                                NavSetupUtilitys.openNameChange(player, id, gameItem.getName(), gameItem.getDisplayname().replace("§", "&"));
                            } else {
                                NavSetupUtilitys.openLoreChange(player, id, gameItem.getName(), gameItem.getLore().replace("§", "&"));
                            }
                        }else{
                            player.closeInventory();
                        }
                    }else if(event.getCurrentItem().getType() == Material.COMPASS){
                        int id = Integer.valueOf(event.getClickedInventory().getTitle().replace("§cSetUp §8| §b#", ""));
                        NavConfig navigator = Main.getInstance().getInventoryManager().getNavConfig();
                        if(navigator.getGames().containsKey(id)) {
                            NavGameItem gameItem = navigator.getGames().get(id);
                            NavSetupUtilitys.openWarpChange(player, id, gameItem.getName(), gameItem.getWarp());
                        }else{
                            player.closeInventory();
                        }
                    }else if (event.getSlot() == 16){
                        int id = Integer.valueOf(event.getClickedInventory().getTitle().replace("§cSetUp §8| §b#", ""));
                        NavConfig navigator = Main.getInstance().getInventoryManager().getNavConfig();
                        if(navigator.getGames().containsKey(id)) {
                            NavGameItem gameItem = navigator.getGames().get(id);
                            NavItemSetup.openItemEdit(player, gameItem, gameItem.getPosition());
                        }else{
                            player.closeInventory();
                        }
                    }else if (event.getSlot() == 14){
                        int id = Integer.valueOf(event.getClickedInventory().getTitle().replace("§cSetUp §8| §b#", ""));
                        NavConfig navigator = Main.getInstance().getInventoryManager().getNavConfig();
                        if(navigator.getItems().containsKey(id)) {
                            NavItem navItem = navigator.getItems().get(id);
                            NavItemSetup.openItemEdit(player, navItem, navItem.getPosition());
                        }else{
                            player.closeInventory();
                        }
                    }
                }
            }
        }
    }

}
