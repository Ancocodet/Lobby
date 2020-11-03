package de.ancozockt.lobby.utilitys.view;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.inventorys.settings.enums.Shield;
import de.ancozockt.lobby.inventorys.settings.enums.Vanish;
import de.ancozockt.lobby.utilitys.background.LobbyPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class ShieldHandler {

    private BukkitRunnable runnable;
    private boolean running = false;

    private Player player;
    private LobbyPlayer lobbyPlayer;

    public ShieldHandler(Player player, LobbyPlayer lobbyPlayer){
        this.player = player;
        this.lobbyPlayer = lobbyPlayer;

        createRunnable();
    }

    public ShieldHandler(Player player){
        this.player = player;
        this.lobbyPlayer = Main.getInstance().getPlayerManager().getLobbyPlayer(player);
    }

    public boolean isRunning() {
        return running;
    }

    public void createRunnable(){
        runnable = new BukkitRunnable() {
            @Override
            public void run() {
                if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("BungeeCord") || player.getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("World"))){
                    double radius = 2;
                    for (double o = 0; o < radius * Math.PI; o+=0.22){
                        player.getWorld().playEffect(player.getLocation().add(radius*Math.cos(o), +1, radius * Math.sin(o)), Effect.COLOURED_DUST, 1);
                    }
                    for(Entity entity : player.getNearbyEntities(radius, radius, radius)){
                        if(entity instanceof Player && entity != player){
                            Player nearby = (Player) entity;
                            if(lobbyPlayer.getShield().equals(Shield.ALL)){
                                cancel();
                                running = false;
                                return;
                            }

                            if(nearby.hasPermission("lobby.team"))
                                return;
                            if(lobbyPlayer.getShield().equals(Shield.INFLUENCER) && nearby.hasPermission("lobby.youtuber"))
                                return;
                            if(lobbyPlayer.getShield().equals(Shield.PREMIUM) && nearby.hasPermission("lobby.premium"))
                                return;

                            Location loc1 = player.getLocation();
                            Location loc2 = entity.getLocation();

                            int x1 = loc1.getBlockX();
                            int y1 = loc1.getBlockY();
                            int z1 = loc1.getBlockZ();

                            int x2 = loc2.getBlockX();
                            int y2 = loc2.getBlockY();
                            int z2 = loc2.getBlockZ();

                            int xe = x2-x1;
                            int ye = y2-y1;
                            int ze = z2-z1;

                            Vector vector = new Vector(xe, ye + 0.2, ze);
                            entity.setVelocity(vector);
                        }
                    }
                }else{
                    running = true;
                    cancel();
                }
            }
        };
    }

    public void startRunnable(){
        if(!running) {
            createRunnable();
            runnable.runTaskTimer(Main.getInstance(), 0L, 2L);
            running = true;
        }
    }

    public void stopRunnable(){
        if(running){
            runnable.cancel();
            running = false;
        }
    }

}
