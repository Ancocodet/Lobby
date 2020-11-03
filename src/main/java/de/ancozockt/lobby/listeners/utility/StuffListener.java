package de.ancozockt.lobby.listeners.utility;

import de.ancozockt.lobby.Main;
import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.*;
import org.bukkit.event.weather.WeatherChangeEvent;

public class StuffListener implements Listener {

    @EventHandler
    public void onSigns(SignChangeEvent event){
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || event.getBlock().getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
            event.setLine(0, event.getLine(0).replace("&", "§"));
            event.setLine(1, event.getLine(1).replace("&", "§"));
            event.setLine(2, event.getLine(2).replace("&", "§"));
            event.setLine(3, event.getLine(3).replace("&", "§"));
        }
    }

    @EventHandler
    public void onExplosion(EntityExplodeEvent event) {
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || event.getEntity().getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onEntityChangeBlock(EntityChangeBlockEvent event) {
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || event.getEntity().getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
            event.setCancelled(true);
        }
    }


    @EventHandler
    public void onEntityTeleport(EntityTeleportEvent event) {
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || event.getEntity().getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
            event.setCancelled(true);
        }else if(!Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord") && event.getEntity() instanceof Player){
            Player player = (Player) event.getEntity();
            if(Main.getInstance().getPlayerManager().getInLobby().contains(player) && event.getTo().getWorld() != Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
                Main.getInstance().getLobbyManager().joinLobby(player);
            }
        }
    }

    @EventHandler
    public void onEntity(EntityDamageByEntityEvent event) {
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || event.getEntity().getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
            if ((event.getEntity() instanceof Player)) {
                event.setCancelled(true);
            } else {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onEntity(EntityDamageEvent event) {
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || event.getEntity().getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
            if ((event.getEntity() instanceof Player)) {
                event.setCancelled(true);
            } else {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onFoodChange(FoodLevelChangeEvent event) {
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || event.getEntity().getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent event) {
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || event.getPlayer().getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
            Player player = event.getPlayer();
            if((player.hasPermission("lobby.build") || player.hasPermission("lobby.admin")) && player.getGameMode()== GameMode.CREATIVE){
                event.setCancelled(false);
            }else{
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBreak(BlockBreakEvent event) {
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || event.getPlayer().getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
            Player player = event.getPlayer();
            if((player.hasPermission("lobby.build") || player.hasPermission("lobby.admin")) && player.getGameMode()== GameMode.CREATIVE){
                event.setCancelled(false);
            }else{
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onPickup(PlayerPickupItemEvent event) {
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || event.getPlayer().getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
            Player player = event.getPlayer();
            if((player.hasPermission("lobby.build") || player.hasPermission("lobby.admin")) && player.getGameMode()== GameMode.CREATIVE){
                event.setCancelled(false);
            }else{
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || event.getPlayer().getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
            Player player = event.getPlayer();
            if((player.hasPermission("lobby.build") || player.hasPermission("lobby.admin")) && player.getGameMode()== GameMode.CREATIVE){
                event.setCancelled(false);
            }else{
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onWeather(WeatherChangeEvent event) {
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || event.getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onAchiev(PlayerAchievementAwardedEvent event){
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || event.getPlayer().getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBucket(PlayerBucketFillEvent event) {
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || event.getPlayer().getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onFish(PlayerFishEvent event) {
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || event.getPlayer().getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onBed(PlayerBedEnterEvent event) {
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                || event.getPlayer().getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("General.World"))){
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event){
        Player player = event.getPlayer();
        if(!Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")
                && Main.getInstance().getPlayerManager().getInLobby().contains(player)){
            if(Main.getInstance().getPlayerManager().getSilentLobby().contains(player)){
                event.setCancelled(true);
            }else{
                Main.getInstance().getPlayerManager().getSilentLobby().forEach(player1 -> event.getRecipients().remove(player1));
            }
        }else if(!Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.BungeeCord")){
            Main.getInstance().getPlayerManager().getSilentLobby().forEach(player1 -> event.getRecipients().remove(player1));
        }else{
            if(Main.getInstance().getPlayerManager().getSilentLobby().contains(player)){
                event.setCancelled(true);
            }else{
                Main.getInstance().getPlayerManager().getSilentLobby().forEach(player1 -> event.getRecipients().remove(player1));
            }
        }
    }

}
