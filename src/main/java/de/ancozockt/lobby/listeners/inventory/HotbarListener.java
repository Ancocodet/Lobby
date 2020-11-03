package de.ancozockt.lobby.listeners.inventory;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.inventorys.hotbar.LobbyItem;
import de.ancozockt.lobby.inventorys.hotbar.LobbyItemRole;
import de.ancozockt.lobby.inventorys.settings.Settings;
import de.ancozockt.lobby.utilitys.background.AEStringBuilder;
import de.ancozockt.utility.configurations.interfaces.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import java.util.HashMap;

public class HotbarListener implements Listener {

    @EventHandler
    public void onHotbar(PlayerInteractEvent event){
        Player player = event.getPlayer();
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("BungeeCord")
                || player.getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("World"))){
            if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
                if(event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock() != null){
                    if(event.getClickedBlock().getType() == Material.WORKBENCH
                            || event.getClickedBlock().getType() == Material.COMMAND
                                || event.getClickedBlock().getType() == Material.TRAP_DOOR
                                    || event.getClickedBlock().getType() == Material.CHEST
                                        || event.getClickedBlock().getType() == Material.ANVIL
                                            || event.getClickedBlock().getType() == Material.NOTE_BLOCK
                                                || event.getClickedBlock().getType() == Material.FENCE_GATE){
                        if(!player.hasPermission("lobby.interact")){
                            event.setCancelled(true);
                        }
                    }
                }
                if(event.getItem()!=null && event.getItem().hasItemMeta() && event.getItem().getItemMeta().hasDisplayName()){
                    HashMap<LobbyItemRole, LobbyItem> displayNames = Main.getInstance().getLobbyItems().getDisplayNamesDefault();
                    Configuration language = Main.getInstance().getConfigurationManager().getLanguage();
                    if(Main.getInstance().getLobbyItems().isExtended(player)){
                        displayNames = Main.getInstance().getLobbyItems().getDisplayNamesExtended();
                    }
                    if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(displayNames.get(LobbyItemRole.Navigation).getDisplayname())){
                        event.setCancelled(true);
                        Main.getInstance().getInventoryManager().getNavigator().openNavigator(player);
                    }else if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(displayNames.get(LobbyItemRole.Settings).getDisplayname())){
                        event.setCancelled(true);
                        Settings.openSettings(player);
                    }else if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(displayNames.get(LobbyItemRole.Shop).getDisplayname())){
                        event.setCancelled(true);
                        Main.getInstance().getInventoryManager().getGadgets().openGadgetsMenu(player);
                    }else if(event.getItem().getItemMeta().getDisplayName().equalsIgnoreCase(displayNames.get(LobbyItemRole.Silentlobby).getDisplayname())){
                        event.setCancelled(true);
                        if(!player.hasPermission("lobby.youtuber") && !player.hasPermission("lobby.admin") && !player.hasPermission("lobby.silentlobby") ){
                            if(language.getString("Messages.Silentlobby.Error") != null){
                                player.sendMessage(AEStringBuilder.buildMessage(language.getString("Messages.Silentlobby.Error")).replace("%n", Main.getInstance().getStringManager().getNormal()));
                            }
                        }else{
                            if(Main.getInstance().getPlayerManager().getSilentLobby().contains(player)){
                                Main.getInstance().getPlayerManager().leaveSilentLobby(player);
                                if(language.getString("Messages.Silentlobby.Leave") != null){
                                    player.sendMessage(AEStringBuilder.buildMessage(language.getString("Messages.Silentlobby.Leave")).replace("%n", Main.getInstance().getStringManager().getNormal()));
                                }
                                Settings.vanishPlayers(player);
                                for(Player all : Bukkit.getOnlinePlayers()){
                                    Settings.vanishPlayers(all);
                                }
                            }else{
                                Main.getInstance().getPlayerManager().joinSilentLobby(player);
                                if(language.getString("Messages.Silentlobby.Join") != null){
                                    player.sendMessage(AEStringBuilder.buildMessage(language.getString("Messages.Silentlobby.Join")).replace("%n", Main.getInstance().getStringManager().getNormal()));
                                }
                                for(Player all : Bukkit.getOnlinePlayers()){
                                    player.hidePlayer(all);
                                    all.hidePlayer(player);
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
