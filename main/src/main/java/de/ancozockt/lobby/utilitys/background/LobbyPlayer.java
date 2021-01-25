package de.ancozockt.lobby.utilitys.background;

import de.ancozockt.lobby.database.mysql.PlayerHandler;
import de.ancozockt.lobby.inventorys.settings.enums.Shield;
import de.ancozockt.lobby.inventorys.settings.enums.Vanish;
import de.ancozockt.lobby.utilitys.view.ShieldHandler;
import org.bukkit.entity.Player;

public class LobbyPlayer {

    private Player player;

    private Vanish vanish;
    private Shield shield;

    private ShieldHandler shieldHandler;
    private PlayerHandler playerHandler = null;

    public LobbyPlayer(Player player){
        this.player = player;

        this.vanish = Vanish.ALL;
        this.shield = Shield.ALL;

        shieldHandler = new ShieldHandler(player, this);
    }

    public Player getPlayer() {
        return player;
    }

    public ShieldHandler getShieldHandler() {
        return shieldHandler;
    }

    public PlayerHandler getPlayerHandler() {
        return playerHandler;
    }

    public void setPlayerHandler(PlayerHandler playerHandler) {
        this.playerHandler = playerHandler;
    }

    public Shield getShield() {
        return shield;
    }

    public Vanish getVanish() {
        return vanish;
    }

    public void setShield(Shield shield) {
        this.shield = shield;
    }

    public void setVanish(Vanish vanish) {
        this.vanish = vanish;
    }
}
