package de.ancozockt.lobby.utilitys.background;

import org.bukkit.entity.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class PlayerManager {

    private HashMap<Player, LobbyPlayer> lobbyPlayer = new HashMap<>();
    private ArrayList<Player> silentLobby = new ArrayList<>();
    private ArrayList<Player> inLobby = new ArrayList<>();

    public PlayerManager(){

    }

    public ArrayList<Player> getSilentLobby() {
        return silentLobby;
    }

    public ArrayList<Player> getInLobby() {
        return inLobby;
    }

    public void joinSilentLobby(Player player){
        if(!silentLobby.contains(player))
            silentLobby.add(player);
    }

    public void leaveSilentLobby(Player player){
        if(silentLobby.contains(player))
            silentLobby.remove(player);
    }

    public void joinLobby(Player player){
        if(!inLobby.contains(player))
            inLobby.add(player);
    }

    public void leaveLobby(Player player){
        if(inLobby.contains(player))
            inLobby.remove(player);
    }

    public boolean knownPlayer(Player player){
        return lobbyPlayer.containsKey(player);
    }

    public void initPlayer(Player player){
        if(!lobbyPlayer.containsKey(player))
            lobbyPlayer.put(player, new LobbyPlayer(player));
    }

    public void unInjectPlayer(Player player){
        if(lobbyPlayer.containsKey(player))
            lobbyPlayer.remove(player);
    }

    public LobbyPlayer getLobbyPlayer(Player player){
        initPlayer(player);
        return lobbyPlayer.get(player);
    }
}
