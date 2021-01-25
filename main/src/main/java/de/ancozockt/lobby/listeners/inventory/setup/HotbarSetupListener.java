package de.ancozockt.lobby.listeners.inventory.setup;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.inventorys.hotbar.LobbyItem;
import de.ancozockt.lobby.inventorys.hotbar.LobbyItemRole;
import de.ancozockt.lobby.inventorys.setup.Hotbar.HotbarItemSetup;
import de.ancozockt.lobby.inventorys.setup.Hotbar.HotbarPositionSetup;
import de.ancozockt.lobby.inventorys.setup.Hotbar.HotbarSetup;
import de.ancozockt.lobby.inventorys.setup.Hotbar.HotbarSetupUtilitys;
import de.ancozockt.lobby.inventorys.setup.Setup;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

public class HotbarSetupListener implements Listener {

    @EventHandler
    public void onHotbarSetup(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || player.getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
            if(event.getClickedInventory()!=null && event.getClickedInventory().getTitle().equalsIgnoreCase("§cSetUp §8| §5Hot")) {
                if(event.getCurrentItem()!=null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()) {
                    event.setCancelled(true);
                    if(player.hasPermission("lobby.setup")) {
                        boolean extended = false;
                        if(event.getClickedInventory().getItem(50).getItemMeta().hasEnchant(Enchantment.DURABILITY))
                            extended = true;

                        if(event.getSlot() == 48){
                            if(!event.getCurrentItem().getItemMeta().hasEnchant(Enchantment.DURABILITY))
                                HotbarSetup.openSetup(player, false);
                        }else if(event.getSlot() == 49){
                            Setup.openSetup(player);

                            for(int i = 0; i < 9; i++){
                                player.getInventory().setItem(i, new ItemStack(Material.AIR));
                            }

                            Main.getInstance().getLobbyItems().giveItems(player);
                        }else if(event.getSlot() == 50){
                            if(!event.getCurrentItem().getItemMeta().hasEnchant(Enchantment.DURABILITY))
                                HotbarSetup.openSetup(player, true);
                        }
                        if(event.getCurrentItem().getType() == Material.INK_SACK){
                            if(event.getCurrentItem().getDurability() == 10){
                                if(event.getSlot() == 3){
                                    disableLobbyItem(extended, LobbyItemRole.Settings);
                                    HotbarSetup.openSetup(player, extended);
                                }else if(event.getSlot() == 12){
                                    disableLobbyItem(extended, LobbyItemRole.Navigation);
                                    HotbarSetup.openSetup(player, extended);
                                }else if(event.getSlot() == 21){
                                    disableLobbyItem(extended, LobbyItemRole.Silentlobby);
                                    HotbarSetup.openSetup(player, extended);
                                }else if(event.getSlot() == 30){
                                    disableLobbyItem(extended, LobbyItemRole.Shop);
                                    HotbarSetup.openSetup(player, extended);
                                }
                            }else if(event.getCurrentItem().getDurability() == 1){
                                if(event.getSlot() == 3){
                                    enableLobbyItem(extended, LobbyItemRole.Settings);
                                    HotbarSetup.openSetup(player, extended);
                                }else if(event.getSlot() == 12){
                                    enableLobbyItem(extended, LobbyItemRole.Navigation);
                                    HotbarSetup.openSetup(player, extended);
                                }else if(event.getSlot() == 21){
                                    enableLobbyItem(extended, LobbyItemRole.Silentlobby);
                                    HotbarSetup.openSetup(player, extended);
                                }else if(event.getSlot() == 30){
                                    enableLobbyItem(extended, LobbyItemRole.Shop);
                                    HotbarSetup.openSetup(player, extended);
                                }
                            }
                        }else if(event.getCurrentItem().getType() == Material.BEACON){
                            if(event.getSlot() == 4){
                                HotbarPositionSetup.openSetup(player, LobbyItemRole.Settings, extended);
                            }else if(event.getSlot() == 13){
                                HotbarPositionSetup.openSetup(player, LobbyItemRole.Navigation, extended);
                            }else if(event.getSlot() == 22){
                                HotbarPositionSetup.openSetup(player, LobbyItemRole.Silentlobby, extended);
                            }else if(event.getSlot() == 31){
                                HotbarPositionSetup.openSetup(player, LobbyItemRole.Shop, extended);
                            }
                        }else if(event.getCurrentItem().getType() == Material.SIGN){
                            if(event.getSlot() == 5){
                                HotbarSetupUtilitys.openNameChange(player, LobbyItemRole.Settings, extended);
                            }else if(event.getSlot() == 14){
                                HotbarSetupUtilitys.openNameChange(player, LobbyItemRole.Navigation, extended);
                            }else if(event.getSlot() == 23){
                                HotbarSetupUtilitys.openNameChange(player, LobbyItemRole.Silentlobby, extended);
                            }else if(event.getSlot() == 32){
                                HotbarSetupUtilitys.openNameChange(player, LobbyItemRole.Shop, extended);
                            }
                        }else if(event.getCurrentItem().getType() == Material.WORKBENCH){
                            if(event.getSlot() == 6){
                                HotbarItemSetup.openSetup(player, LobbyItemRole.Settings, extended);
                            }else if(event.getSlot() == 15){
                                HotbarItemSetup.openSetup(player, LobbyItemRole.Navigation, extended);
                            }else if(event.getSlot() == 24){
                                HotbarItemSetup.openSetup(player, LobbyItemRole.Silentlobby, extended);
                            }else if(event.getSlot() == 33){
                                HotbarItemSetup.openSetup(player, LobbyItemRole.Shop, extended);
                            }
                        }
                    }
                }
            }else if(event.getClickedInventory() != null && (event.getClickedInventory().getTitle().startsWith("§cS §8| §d") || event.getClickedInventory().getTitle().startsWith("§cS §8| §e"))) {
                boolean extended = false;
                if(event.getClickedInventory().getTitle().startsWith("§cS §8| §e"))
                    extended = true;

                if(event.getCurrentItem()!=null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()) {
                    event.setCancelled(true);
                    if(player.hasPermission("lobby.setup")) {
                        if (event.getCurrentItem().getType() == Material.BARRIER) {
                            if (HotbarItemSetup.tempIDs.containsKey(player)) {
                                HotbarItemSetup.tempIDs.remove(player);
                            }
                            HotbarSetup.openSetup(player, false);
                        } else if (event.getCurrentItem().getType() == Material.EMERALD) {
                            if (HotbarItemSetup.tempIDs.containsKey(player)) {
                                Integer[] tempID = HotbarItemSetup.tempIDs.get(player);

                                String roleSTR = event.getClickedInventory().getTitle().replace("§cS §8| §d", "").replace("§cS §8| §e", "");
                                LobbyItemRole role = LobbyItemRole.valueOf(roleSTR);

                                LobbyItem item = Main.getInstance().getLobbyItems().getDisplayNamesDefault().get(role);
                                if(extended)
                                    item = Main.getInstance().getLobbyItems().getDisplayNamesExtended().get(role);

                                item.setItemID(tempID[0]);
                                item.setShortID(tempID[1]);
                                if(extended){
                                    Main.getInstance().getLobbyItems().getExtendedVersion().remove(item);

                                    Main.getInstance().getConfigurationManager().getHotbar().setValue("Extended.Items." + item.getName() + ".Item", tempID[0] + ":" + tempID[1]);

                                    Main.getInstance().getLobbyItems().getDisplayNamesExtended().put(role, item);
                                    Main.getInstance().getLobbyItems().getExtendedVersion().add(item);
                                }else{
                                    Main.getInstance().getLobbyItems().getDefaultVersion().remove(item);

                                    Main.getInstance().getConfigurationManager().getHotbar().setValue("Default.Items." + item.getName() + ".Item", tempID[0] + ":" + tempID[1]);

                                    Main.getInstance().getLobbyItems().getDisplayNamesDefault().put(role, item);
                                    Main.getInstance().getLobbyItems().getDefaultVersion().add(item);
                                }

                                HotbarItemSetup.tempIDs.remove(player);
                                HotbarSetup.openSetup(player, extended);
                            } else {
                                player.closeInventory();
                            }
                        } else if (event.getCurrentItem().getType() == Material.SKULL_ITEM) {
                            if (HotbarItemSetup.tempIDs.containsKey(player)) {
                                Integer[] tempID = HotbarItemSetup.tempIDs.get(player);
                                if (event.getCurrentItem().getItemMeta().getDisplayName().startsWith("§7+ §e")) {
                                    int value = Integer.valueOf(event.getCurrentItem().getItemMeta().getDisplayName().replace("§7+ §e", ""));
                                    if ((tempID[0] + value) < 1000) {
                                        tempID[0] += value;
                                    }
                                    HotbarItemSetup.tempIDs.put(player, tempID);
                                    HotbarItemSetup.updateItemEdit(player, event.getClickedInventory());
                                } else if (event.getCurrentItem().getItemMeta().getDisplayName().startsWith("§7- §e")) {
                                    int value = Integer.valueOf(event.getCurrentItem().getItemMeta().getDisplayName().replace("§7- §e", ""));
                                    if ((tempID[0] - value) > -1) {
                                        tempID[0] -= value;
                                    }
                                    HotbarItemSetup.tempIDs.put(player, tempID);
                                    HotbarItemSetup.updateItemEdit(player, event.getClickedInventory());
                                } else if (event.getCurrentItem().getItemMeta().getDisplayName().startsWith("§7+ §5")) {
                                    int value = Integer.valueOf(event.getCurrentItem().getItemMeta().getDisplayName().replace("§7+ §5", ""));
                                    if ((tempID[1] + value) < 100) {
                                        tempID[1] += value;
                                    }
                                    HotbarItemSetup.tempIDs.put(player, tempID);
                                    HotbarItemSetup.updateItemEdit(player, event.getClickedInventory());
                                } else if (event.getCurrentItem().getItemMeta().getDisplayName().startsWith("§7- §5")) {
                                    int value = Integer.valueOf(event.getCurrentItem().getItemMeta().getDisplayName().replace("§7- §5", ""));
                                    if ((tempID[1] - value) > -1) {
                                        tempID[1] -= value;
                                    }
                                    HotbarItemSetup.tempIDs.put(player, tempID);
                                    HotbarItemSetup.updateItemEdit(player, event.getClickedInventory());
                                }
                            }
                        }
                    }
                }
            }else if(event.getClickedInventory() != null && (event.getClickedInventory().getTitle().startsWith("§cS §8| §9") || event.getClickedInventory().getTitle().startsWith("§cS §8| §3"))) {
                boolean extended = false;
                if(event.getClickedInventory().getTitle().startsWith("§cS §8| §3"))
                    extended = true;
                if(event.getCurrentItem()!=null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()) {
                    if (player.hasPermission("lobby.setup")) {
                        if (event.getCurrentItem().getType() == Material.BARRIER) {
                            event.setCancelled(true);
                        }else if (event.isRightClick()) {
                            String roleSTR = event.getClickedInventory().getTitle().replace("§cS §8| §3", "").replace("§cS §8| §9", "");
                            LobbyItemRole role = LobbyItemRole.valueOf(roleSTR);

                            LobbyItem item = Main.getInstance().getLobbyItems().getDisplayNamesDefault().get(role);
                            if(extended)
                                item = Main.getInstance().getLobbyItems().getDisplayNamesExtended().get(role);

                            item.setPosition(event.getSlot());
                            if(extended){
                                Main.getInstance().getLobbyItems().getExtendedVersion().remove(item);

                                Main.getInstance().getConfigurationManager().getHotbar().setValue("Extended.Items." + item.getName() + ".Position", event.getSlot()+1);

                                Main.getInstance().getLobbyItems().getDisplayNamesExtended().put(role, item);
                                Main.getInstance().getLobbyItems().getExtendedVersion().add(item);
                            }else{
                                Main.getInstance().getLobbyItems().getDefaultVersion().remove(item);

                                Main.getInstance().getConfigurationManager().getHotbar().setValue("Default.Items." + item.getName() + ".Position", event.getSlot()+1);

                                Main.getInstance().getLobbyItems().getDisplayNamesDefault().put(role, item);
                                Main.getInstance().getLobbyItems().getDefaultVersion().add(item);
                            }

                            player.closeInventory();
                            Main.getInstance().getNmsHandler().getChatapi().sendActionbar(player, "§7Position §asaved");
                        }
                    }else {
                        event.setCancelled(true);
                    }
                }
            }
        }
    }

    public void disableLobbyItem(boolean extended, LobbyItemRole role){
        if(extended){
            LobbyItem item = Main.getInstance().getLobbyItems().getDisplayNamesExtended().get(role);
            Main.getInstance().getLobbyItems().getExtendedVersion().remove(item);

            item.setEnabled(false);
            Main.getInstance().getConfigurationManager().getHotbar().setValue("Extended.Items." + item.getName() + ".Enabled", false);

            Main.getInstance().getLobbyItems().getExtendedVersion().add(item);
            Main.getInstance().getLobbyItems().getDisplayNamesExtended().put(role, item);
        }else{
            LobbyItem item = Main.getInstance().getLobbyItems().getDisplayNamesDefault().get(role);
            Main.getInstance().getLobbyItems().getDefaultVersion().remove(item);

            item.setEnabled(false);
            Main.getInstance().getConfigurationManager().getHotbar().setValue("Default.Items." + item.getName() + ".Enabled", false);

            Main.getInstance().getLobbyItems().getDefaultVersion().add(item);
            Main.getInstance().getLobbyItems().getDisplayNamesDefault().put(role, item);
        }
    }

    public void enableLobbyItem(boolean extended, LobbyItemRole role){
        if(extended){
            LobbyItem item = Main.getInstance().getLobbyItems().getDisplayNamesExtended().get(role);
            Main.getInstance().getLobbyItems().getExtendedVersion().remove(item);

            item.setEnabled(true);
            Main.getInstance().getConfigurationManager().getHotbar().setValue("Extended.Items." + item.getName() + ".Enabled", true);

            Main.getInstance().getLobbyItems().getExtendedVersion().add(item);
            Main.getInstance().getLobbyItems().getDisplayNamesExtended().put(role, item);
        }else{
            LobbyItem item = Main.getInstance().getLobbyItems().getDisplayNamesDefault().get(role);
            Main.getInstance().getLobbyItems().getDefaultVersion().remove(item);

            item.setEnabled(true);
            Main.getInstance().getConfigurationManager().getHotbar().setValue("Default.Items." + item.getName() + ".Enabled", true);

            Main.getInstance().getLobbyItems().getDefaultVersion().add(item);
            Main.getInstance().getLobbyItems().getDisplayNamesDefault().put(role, item);
        }
    }

}
