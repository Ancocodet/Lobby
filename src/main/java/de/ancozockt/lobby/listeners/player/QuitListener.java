package de.ancozockt.lobby.listeners.player;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.utilitys.background.LobbyPlayer;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

public class QuitListener implements Listener {

    @EventHandler
    public void onQuit(PlayerQuitEvent event){
        Player player = event.getPlayer();
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("BungeeCord")){
            event.setQuitMessage(null);
        }else{
            Main.getInstance().getPlayerManager().leaveLobby(player);
        }

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
