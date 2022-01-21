package de.ancozockt.lobby.configuration;

import de.ancozockt.lobby.warps.Warp;
import de.ancozockt.lobby.warps.WarpManager;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.junit.Test;

public class WarpManagerTest {

    @Test
    public void testWarpManager(){
        WarpManager warpManager = new WarpManager();

        assert warpManager.getWarp("test") == null;

        warpManager.warplist.put("test", new Warp("test", new Location(null, 0, 0, 0)));

        assert warpManager.getWarp("test") != null;
    }

}
