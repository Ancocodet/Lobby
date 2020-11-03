package de.ancozockt.lobby.utilitys.startup;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.listeners.inventory.HotbarListener;
import de.ancozockt.lobby.listeners.inventory.InventoryListener;
import de.ancozockt.lobby.listeners.inventory.ShopListener;
import de.ancozockt.lobby.listeners.inventory.setup.HotbarSetupListener;
import de.ancozockt.lobby.listeners.inventory.setup.ItemSetupListener;
import de.ancozockt.lobby.listeners.inventory.setup.SetupListener;
import de.ancozockt.lobby.listeners.player.JoinListener;
import de.ancozockt.lobby.listeners.player.QuitListener;
import de.ancozockt.lobby.listeners.utility.CommandListener;
import de.ancozockt.lobby.listeners.utility.StuffListener;
import de.ancozockt.utility.general.IStartUp;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

public class ListenerStartUp implements IStartUp {

    @Override
    public void startup() {
        PluginManager pluginManager = Bukkit.getPluginManager();
        /*
        SETUP LISTENERS
         */
        pluginManager.registerEvents(new HotbarSetupListener(), Main.getInstance());
        pluginManager.registerEvents(new ItemSetupListener(), Main.getInstance());
        pluginManager.registerEvents(new SetupListener(), Main.getInstance());
        /*
        INVENTORY LISTENERS
         */
        pluginManager.registerEvents(new HotbarListener(), Main.getInstance());
        pluginManager.registerEvents(new InventoryListener(), Main.getInstance());
        pluginManager.registerEvents(Main.getInstance().getInventoryManager().getNavigator(), Main.getInstance());
        pluginManager.registerEvents(new ShopListener(), Main.getInstance());
        /*
        PLAYER LISTENERS
         */
        pluginManager.registerEvents(new JoinListener(), Main.getInstance());
        pluginManager.registerEvents(new QuitListener(), Main.getInstance());
        /*
        UTILITY LISTENERS
         */
        pluginManager.registerEvents(new CommandListener(), Main.getInstance());
        pluginManager.registerEvents(new StuffListener(), Main.getInstance());
    }
}
