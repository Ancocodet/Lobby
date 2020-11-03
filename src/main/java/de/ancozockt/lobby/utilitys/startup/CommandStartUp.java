package de.ancozockt.lobby.utilitys.startup;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.commands.CMD_Admin;
import de.ancozockt.lobby.commands.CMD_Warps;
import de.ancozockt.utility.general.IStartUp;

public class CommandStartUp implements IStartUp {
    @Override
    public void startup() {
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("BungeeCord")){
            Main.getInstance().getCommand("setwarp").setExecutor(new CMD_Warps());
            Main.getInstance().getCommand("delwarp").setExecutor(new CMD_Warps());
            Main.getInstance().getCommand("warp").setExecutor(new CMD_Warps());

            Main.getInstance().getCommand("setup").setExecutor(new CMD_Admin());
            Main.getInstance().getCommand("loadchanges").setExecutor(new CMD_Admin());
            Main.getInstance().getCommand("addons").setExecutor(new CMD_Admin());
        }
    }
}
