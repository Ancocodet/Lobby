package de.ancozockt.lobby.database;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.database.mysql.PlayerHandler;
import de.ancozockt.lobby.inventorys.settings.Settings;
import de.ancozockt.lobby.inventorys.settings.enums.Shield;
import de.ancozockt.lobby.inventorys.settings.enums.Vanish;
import de.ancozockt.lobby.utilitys.background.LobbyPlayer;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class LoadDatabase {

    public static void load(Player player){
        Main.getInstance().getNmsHandler().getChatapi().sendActionbar(player, Main.getInstance().getConfigurationManager().getLanguage().getString("Stats.Loading"));
        LobbyPlayer lobbyPlayer = Main.getInstance().getPlayerManager().getLobbyPlayer(player);
        if(lobbyPlayer.getPlayerHandler() == null)
            lobbyPlayer.setPlayerHandler(new PlayerHandler(player.getName(), player.getUniqueId().toString()));
        new BukkitRunnable(){

            @Override
            public void run() {
                if(Main.getInstance().getPlayerManager().knownPlayer(player)){
                    if(lobbyPlayer.getPlayerHandler().isExistsLoaded()){
                        lobbyPlayer.getPlayerHandler().fetch();
                        new BukkitRunnable(){

                            @Override
                            public void run() {
                                initializeVanisher(lobbyPlayer);
                                Main.getInstance().getNmsHandler().getChatapi().sendActionbar(player, Main.getInstance().getConfigurationManager().getLanguage().getString("Stats.Loaded"));
                            }
                        }.runTaskLaterAsynchronously(Main.getInstance(), 40L);
                    }else{
                        Main.getInstance().getNmsHandler().getChatapi().sendActionbar(player, Main.getInstance().getConfigurationManager().getLanguage().getString("Stats.Error"));
                    }
                }
            }
        }.runTaskLaterAsynchronously(Main.getInstance(), 40L);
    }

    private static void initializeVanisher(LobbyPlayer player){
        if(player.getPlayerHandler().isReady()){
            String[] settings = player.getPlayerHandler().getSettings().split(",");

            Vanish vanish = Vanish.valueOf(settings[0].toUpperCase());
            player.setVanish(vanish);
            Settings.vanishPlayers(player.getPlayer());

            Shield shieldEnum = Shield.valueOf(settings[1].toUpperCase());
            player.setShield(shieldEnum);
            if(shieldEnum.equals(Shield.INFLUENCER) ||shieldEnum.equals(Shield.PREMIUM) || shieldEnum.equals(Shield.NONE)){
                player.getShieldHandler().startRunnable();
            }
        }
    }

}
