package de.ancozockt.addons.jumppad;

import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class JumpPadEvent implements Listener {

    @EventHandler
    public void onMove(final PlayerMoveEvent e) {
        if (Main.getInstance().getConfiguration().isEnabled()) {
            new BukkitRunnable() {
                public void run() {
                    final Player p = e.getPlayer();
                    if (p.getLocation().getBlock().getType().getId() == Main.getInstance().getConfiguration().getJumpPadBlockID() && p.getLocation().getBlock().getData() == Main.getInstance().getConfiguration().getJumpPadBlockSubID()) {
                        if (Main.getInstance().getConfiguration().shouldPlayEffect()) {
                            p.playEffect(p.getLocation(), Effect.ENDER_SIGNAL, 2);
                        }
                        p.setVelocity(p.getLocation().getDirection().multiply(Main.getInstance().getConfiguration().getMultiplier()).setY(Main.getInstance().getConfiguration().getHeight()));
                    }
                }
            }.runTaskAsynchronously(Main.getInstance().getPluginMain());
        }
    }

}
