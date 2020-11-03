package de.ancozockt.lobby.listeners.inventory;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.inventorys.settings.Settings;
import de.ancozockt.lobby.inventorys.settings.enums.Shield;
import de.ancozockt.lobby.inventorys.settings.enums.Vanish;
import de.ancozockt.lobby.utilitys.background.AEStringBuilder;
import de.ancozockt.lobby.utilitys.background.LobbyPlayer;
import de.ancozockt.utility.configurations.interfaces.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class InventoryListener implements Listener {

    @EventHandler
    public void onSettings(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("BungeeCord")
                || player.getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("World"))){
            Configuration language = Main.getInstance().getConfigurationManager().getLanguage();
            if(event.getClickedInventory()!=null && event.getClickedInventory().getTitle().equalsIgnoreCase(AEStringBuilder.replaceDefaults(language.getString("Settings.InventoryName")))) {
                if(event.getCurrentItem()!=null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()){
                    event.setCancelled(true);
                    if(event.getSlot() > 11 && event.getSlot() < 16) {
                        LobbyPlayer lobbyPlayer = Main.getInstance().getPlayerManager().getLobbyPlayer(player);
                        if (event.getSlot() == 12) {
                            lobbyPlayer.setVanish(Vanish.ALL);
                        } else if (event.getSlot() == 13) {
                            lobbyPlayer.setVanish(Vanish.PREMIUM);
                        } else if (event.getSlot() == 14) {
                            lobbyPlayer.setVanish(Vanish.INFLUENCER);
                        } else if (event.getSlot() == 15) {
                            lobbyPlayer.setVanish(Vanish.NONE);
                        }
                        Settings.vanishPlayers(player);
                        Settings.openSettings(player);
                    }
                    if(event.getSlot() > 20 && event.getSlot() < 25) {
                        LobbyPlayer lobbyPlayer = Main.getInstance().getPlayerManager().getLobbyPlayer(player);
                        if (event.getSlot() == 21) {
                            lobbyPlayer.setShield(Shield.NONE);
                            lobbyPlayer.getShieldHandler().startRunnable();
                        } else if (event.getSlot() == 22) {
                            lobbyPlayer.setShield(Shield.PREMIUM);
                            lobbyPlayer.getShieldHandler().startRunnable();
                        } else if (event.getSlot() == 23) {
                            lobbyPlayer.setShield(Shield.INFLUENCER);
                            lobbyPlayer.getShieldHandler().startRunnable();
                        } else if (event.getSlot() == 24) {
                            lobbyPlayer.setShield(Shield.ALL);
                            lobbyPlayer.getShieldHandler().stopRunnable();
                        }
                        Settings.openSettings(player);
                    }
                }
            }
        }
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("BungeeCord")
                || player.getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("World"))){
            if(event.getClickedInventory() == player.getInventory()){
                if(player.getGameMode() == GameMode.CREATIVE && player.isOp())
                    return;
                event.setCancelled(true);
            }
        }
    }

}
