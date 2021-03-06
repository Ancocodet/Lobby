package de.ancozockt.lobby.listeners.player;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.configuration.StringManager;
import de.ancozockt.lobby.database.LoadDatabase;
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
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")){
            event.setJoinMessage(null);

            Player player = event.getPlayer();
            Configuration configuration = Main.getInstance().getConfigurationManager().getConfiguration();
            Configuration language = Main.getInstance().getConfigurationManager().getLanguage();

            player.setHealthScale(20);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setLevel(0);
            player.setExp(0);
            player.setGameMode(GameMode.CREATIVE);

            if(configuration.getBoolean("Extras.Tablist") && language.getString("Tablist.Header") != null && language.getString("Tablist.Footer") != null){
                Main.getInstance().getNmsHandler().getChatapi().sendTablist(player, AEStringBuilder.replaceDefaults(language.getString("Tablist.Header")), AEStringBuilder.replaceDefaults(language.getString("Tablist.Footer")));
            }

            Main.getInstance().getLobbyItems().giveItems(player);

            if(player.hasPermission("lobby.notifier")){
                if(configuration.getBoolean("General.Updatecheck") && !Main.getInstance().isUpToDate()){
                    StringManager stringManager = Main.getInstance().getStringManager();
                    player.sendMessage(stringManager.getPrefix() + stringManager.getError() + "There is an " + stringManager.getBold() + "new" + stringManager.getError() + " update available");
                }else if(Main.getInstance().getWarpManager().getWarp("spawn") == null){
                    StringManager stringManager = Main.getInstance().getStringManager();
                    player.sendMessage(stringManager.getPrefix() + stringManager.getError() + "You have to create a spawnpoint('spawn' warp) for the best experience");
                }
            }

            Main.getInstance().getWarpManager().teleportWarp(player, "spawn");

            if(configuration.getBoolean("MySQL.Enabled")){
                LoadDatabase.load(player);
            }else{
                LobbyPlayer lobbyPlayer = Main.getInstance().getPlayerManager().getLobbyPlayer(player);

                lobbyPlayer.setShield(Shield.ALL);
                lobbyPlayer.setVanish(Vanish.ALL);

                for(Player all : Bukkit.getOnlinePlayers()){
                    Settings.vanishPlayers(all);
                }
                Settings.vanishPlayers(player);
            }
        }else{
            Player player = event.getPlayer();
            Configuration configuration = Main.getInstance().getConfigurationManager().getConfiguration();
            if(player.hasPermission("lobby.notifier")){
                if(configuration.getBoolean("General.Updatecheck") && !Main.getInstance().isUpToDate()){
                    StringManager stringManager = Main.getInstance().getStringManager();
                    player.sendMessage(stringManager.getPrefix() + stringManager.getError() + "There is an " + stringManager.getBold() + "new" + stringManager.getError() + " update available");
                }
            }
        }
    }

}
