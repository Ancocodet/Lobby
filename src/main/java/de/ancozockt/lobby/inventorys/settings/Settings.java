package de.ancozockt.lobby.inventorys.settings;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.inventorys.settings.enums.Shield;
import de.ancozockt.lobby.inventorys.settings.enums.Vanish;
import de.ancozockt.lobby.utilitys.background.AEStringBuilder;
import de.ancozockt.lobby.utilitys.view.Items;
import de.ancozockt.lobby.utilitys.background.LobbyPlayer;
import de.ancozockt.utility.configurations.interfaces.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class Settings {

    public static void openSettings(Player p){
        Configuration language = Main.getInstance().getConfigurationManager().getLanguage();
        LobbyPlayer lobbyPlayer = Main.getInstance().getPlayerManager().getLobbyPlayer(p);
        if(p.hasPermission("lobby.youtuber")||p.hasPermission("lobby.team")){
            Inventory hider = Bukkit.createInventory(p, 9*4, AEStringBuilder.replaceDefaults(language.getString("Settings.InventoryName")));

            hider.setItem(10, Items.getInstance().getItem(Material.ENDER_PEARL, 1, 0, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Lore"))));

            if(lobbyPlayer.getVanish() == Vanish.ALL){
                hider.setItem(12, Items.getInstance().getItem(Material.STAINED_CLAY ,1, 5, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.All.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.All.Lore"))));
            }else{
                hider.setItem(12, Items.getInstance().getItem(Material.STAINED_GLASS ,1, 5, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.All.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.All.Lore"))));
            }
            if(lobbyPlayer.getVanish() == Vanish.NONE){
                hider.setItem(13, Items.getInstance().getItem(Material.STAINED_CLAY, 1, 4, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Premium.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Premium.Lore"))));
            }else{
                hider.setItem(13, Items.getInstance().getItem(Material.STAINED_GLASS, 1, 4, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Premium.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Premium.Lore"))));
            }
            if(lobbyPlayer.getVanish() == Vanish.INFLUENCER){
                hider.setItem(14, Items.getInstance().getItem(Material.STAINED_CLAY, 1, 11, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Youtuber.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Youtuber.Lore"))));
            }else{
                hider.setItem(14, Items.getInstance().getItem(Material.STAINED_GLASS, 1, 11, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Youtuber.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Youtuber.Lore"))));
            }
            if(lobbyPlayer.getVanish() == Vanish.NONE){
                hider.setItem(15, Items.getInstance().getItem(Material.STAINED_CLAY, 1, 14, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.None.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.None.Lore"))));
            }else{
                hider.setItem(15, Items.getInstance().getItem(Material.STAINED_GLASS, 1, 14, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.None.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.None.Lore"))));
            }

            hider.setItem(19, Items.getInstance().getItem(Material.GOLDEN_APPLE, 1, 0, AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Lore"))));

            if(lobbyPlayer.getShield() == Shield.NONE){
                hider.setItem(21, Items.getInstance().getItem(Material.STAINED_CLAY, 1, 5, AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Active.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Active.Lore"))));
            }else{
                hider.setItem(21, Items.getInstance().getItem(Material.STAINED_GLASS, 1, 5, AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Active.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Active.Lore"))));
            }
            if(lobbyPlayer.getShield() == Shield.PREMIUM){
                hider.setItem(22, Items.getInstance().getItem(Material.STAINED_CLAY, 1, 4, AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Premium.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Premium.Lore"))));
            }else{
                hider.setItem(22, Items.getInstance().getItem(Material.STAINED_GLASS, 1, 4, AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Premium.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Premium.Lore"))));
            }
            if(lobbyPlayer.getShield() == Shield.INFLUENCER){
                hider.setItem(23, Items.getInstance().getItem(Material.STAINED_CLAY, 1, 11, AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Youtuber.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Youtuber.Lore"))));
            }else{
                hider.setItem(23, Items.getInstance().getItem(Material.STAINED_GLASS, 1, 11, AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Youtuber.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Youtuber.Lore"))));
            }
            if(lobbyPlayer.getShield() == Shield.ALL){
                hider.setItem(24, Items.getInstance().getItem(Material.STAINED_CLAY, 1, 14, AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Inactive.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Inactive.Lore"))));
            }else{
                hider.setItem(24, Items.getInstance().getItem(Material.STAINED_GLASS, 1, 14, AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Inactive.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Shield.Inactive.Lore"))));
            }

            p.openInventory(hider);
        }else{
            Inventory hider = Bukkit.createInventory(p, 9*3, AEStringBuilder.replaceDefaults(language.getString("Settings.InventoryName")));

            hider.setItem(10, Items.getInstance().getItem(Material.ENDER_PEARL, 1, 0, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Lore"))));

            if(lobbyPlayer.getVanish() == Vanish.ALL){
                hider.setItem(12, Items.getInstance().getItem(Material.STAINED_CLAY ,1, 5, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.All.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.All.Lore"))));
            }else{
                hider.setItem(12, Items.getInstance().getItem(Material.STAINED_GLASS ,1, 5, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.All.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.All.Lore"))));
            }
            if(lobbyPlayer.getVanish() == Vanish.NONE){
                hider.setItem(13, Items.getInstance().getItem(Material.STAINED_CLAY, 1, 4, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Premium.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Premium.Lore"))));
            }else{
                hider.setItem(13, Items.getInstance().getItem(Material.STAINED_GLASS, 1, 4, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Premium.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Premium.Lore"))));
            }
            if(lobbyPlayer.getVanish() == Vanish.INFLUENCER){
                hider.setItem(14, Items.getInstance().getItem(Material.STAINED_CLAY, 1, 11, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Youtuber.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Youtuber.Lore"))));
            }else{
                hider.setItem(14, Items.getInstance().getItem(Material.STAINED_GLASS, 1, 11, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Youtuber.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.Youtuber.Lore"))));
            }
            if(lobbyPlayer.getVanish() == Vanish.NONE){
                hider.setItem(15, Items.getInstance().getItem(Material.STAINED_CLAY, 1, 14, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.None.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.None.Lore"))));
            }else{
                hider.setItem(15, Items.getInstance().getItem(Material.STAINED_GLASS, 1, 14, AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.None.Name")), AEStringBuilder.replaceDefaults(language.getString("Settings.Vanisher.None.Lore"))));
            }

            p.openInventory(hider);
        }
    }

    public static void vanishPlayers(Player p){
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("BungeeCord")){
            LobbyPlayer lobbyPlayer = Main.getInstance().getPlayerManager().getLobbyPlayer(p);
            if(lobbyPlayer.getVanish() == Vanish.ALL){
                for(Player a : Bukkit.getOnlinePlayers()){
                    if(Main.getInstance().getPlayerManager().getSilentLobby().contains(a))
                        p.hidePlayer(a);
                    p.showPlayer(a);
                }
            }else if(lobbyPlayer.getVanish() == Vanish.PREMIUM){
                for(Player a : Bukkit.getOnlinePlayers()){
                    if(!a.hasPermission("lobby.premium")){
                        p.hidePlayer(a);
                    }else{
                        if(Main.getInstance().getPlayerManager().getSilentLobby().contains(a))
                            p.hidePlayer(a);
                        p.showPlayer(a);
                    }
                }
            }else if(lobbyPlayer.getVanish() == Vanish.INFLUENCER){
                for(Player a : Bukkit.getOnlinePlayers()){
                    if(!a.hasPermission("lobby.youtuber")){
                        p.hidePlayer(a);
                    }else{
                        if(Main.getInstance().getPlayerManager().getSilentLobby().contains(a))
                            p.hidePlayer(a);
                        p.showPlayer(a);
                    }
                }
            }else if(lobbyPlayer.getVanish() == Vanish.NONE){
                for(Player a : Bukkit.getOnlinePlayers()){
                    p.hidePlayer(a);
                }
            }
        }else{
            LobbyPlayer lobbyPlayer = Main.getInstance().getPlayerManager().getLobbyPlayer(p);
            if(lobbyPlayer.getVanish() == Vanish.ALL){
                for(Player a : Main.getInstance().getPlayerManager().getInLobby()){
                    if(Main.getInstance().getPlayerManager().getSilentLobby().contains(a))
                        p.hidePlayer(a);
                    p.showPlayer(a);
                }
            }else if(lobbyPlayer.getVanish() == Vanish.PREMIUM){
                for(Player a : Main.getInstance().getPlayerManager().getInLobby()){
                    if(!a.hasPermission("lobby.premium")){
                        p.hidePlayer(a);
                    }else{
                        if(Main.getInstance().getPlayerManager().getSilentLobby().contains(a))
                            p.hidePlayer(a);
                        p.showPlayer(a);
                    }
                }
            }else if(lobbyPlayer.getVanish() == Vanish.INFLUENCER){
                for(Player a : Main.getInstance().getPlayerManager().getInLobby()){
                    if(!a.hasPermission("lobby.youtuber")){
                        p.hidePlayer(a);
                    }else{
                        if(Main.getInstance().getPlayerManager().getSilentLobby().contains(a))
                            p.hidePlayer(a);
                        p.showPlayer(a);
                    }
                }
            }else if(lobbyPlayer.getVanish() == Vanish.NONE){
                for(Player a : Main.getInstance().getPlayerManager().getInLobby()){
                    p.hidePlayer(a);
                }
            }
        }
    }

}
