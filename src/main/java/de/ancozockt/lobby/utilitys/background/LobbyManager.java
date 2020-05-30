package de.ancozockt.lobby.utilitys.background;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.configuration.StringManager;
import de.ancozockt.lobby.database.LoadDatabase;
import de.ancozockt.lobby.inventorys.settings.Settings;
import de.ancozockt.lobby.inventorys.settings.enums.Shield;
import de.ancozockt.lobby.inventorys.settings.enums.Vanish;
import de.ancozockt.utility.configurations.interfaces.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;

public class LobbyManager {

    public void joinLobby(Player player){
        if(!Main.getInstance().getPlayerManager().getInLobby().contains(player)){
            Configuration configuration = Main.getInstance().getConfigurationManager().getConfiguration();
            Configuration language = Main.getInstance().getConfigurationManager().getLanguage();

            Main.getInstance().getPlayerManager().joinLobby(player);

            player.setHealthScale(20);
            player.setHealth(20);
            player.setFoodLevel(20);
            player.setLevel(0);
            player.setExp(0);
            player.setGameMode(GameMode.CREATIVE);

            if(configuration.getBoolean("Tablist") && language.getString("Tablist.Header") != null && language.getString("Tablist.Footer") != null){
                Main.getInstance().getNmsHandler().getChatapi().sendTablist(player, AEStringBuilder.replaceDefaults(language.getString("Tablist.Header")), AEStringBuilder.replaceDefaults(language.getString("Tablist.Footer")));
            }

            Main.getInstance().getLobbyItems().giveItems(player);

            if(player.hasPermission("lobby.notifier")){
                if(configuration.getBoolean("Updatecheck") && !Main.getInstance().isUpToDate()){
                    StringManager stringManager = Main.getInstance().getStringManager();
                    player.sendMessage(stringManager.getPrefix() + stringManager.getError() + "There is an " + stringManager.getBold() + "new" + stringManager.getError() + " update available");
                }else if(Main.getInstance().getWarpManager().getWarp("spawn") == null){
                    StringManager stringManager = Main.getInstance().getStringManager();
                    player.sendMessage(stringManager.getPrefix() + stringManager.getError() + "You have to create a spawnpoint('spawn' warp) for the best experience\"");
                }
            }

            Main.getInstance().getWarpManager().teleportWarp(player, "spawn");

            if(configuration.getBoolean("MySQL.Enabled")){
                LoadDatabase.load(player);
            }else{
                Main.getInstance().getPlayerManager().initPlayer(player);
                LobbyPlayer lobbyPlayer = Main.getInstance().getPlayerManager().getLobbyPlayer(player);

                lobbyPlayer.setShield(Shield.NONE);
                lobbyPlayer.setVanish(Vanish.ALL);

                for(Player all : Bukkit.getOnlinePlayers()){
                    Settings.vanishPlayers(all);
                }
                Settings.vanishPlayers(player);
            }
        }
    }

    public void quitLobby(Player player){
        Main.getInstance().getPlayerManager().leaveLobby(player);
        Main.getInstance().getPlayerManager().leaveSilentLobby(player);

        if(Main.getInstance().getNmsHandler().getParticlelauncher().getBoots().containsKey(player)) {
            Main.getInstance().getNmsHandler().getParticlelauncher().getBoots().get(player).cancel();
            Main.getInstance().getNmsHandler().getParticlelauncher().getBoots().remove(player);
        }

        if(Main.getInstance().getNmsHandler().getParticlelauncher().getParticles().containsKey(player)){
            Main.getInstance().getNmsHandler().getParticlelauncher().getParticles().remove(player);
        }

        LobbyPlayer lobbyPlayer = Main.getInstance().getPlayerManager().getLobbyPlayer(player);
        lobbyPlayer.getShieldHandler().stopRunnable();

        Main.getInstance().getPlayerManager().unInjectPlayer(player);
    }

}
