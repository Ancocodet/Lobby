package de.ancozockt.addons.jumppad;

import de.ancozockt.utility.addons.core.Addon;
import org.bukkit.Bukkit;
import org.bukkit.plugin.PluginManager;

import java.io.File;

public final class Main extends Addon {

    private Configuration configuration;
    private static Main instance;

    @Override
    public void onEnable() {
        this.registerEvents();
        this.loadConfiguration();
    }

    public void onChanging() {
        configuration.load();
    }

    public void loadConfiguration() {
        File config = new File(this.getDataFolder(), "config.yml");
        configuration = new Configuration(config);
    }

    public void registerEvents() {
        PluginManager manager = Bukkit.getPluginManager();

        manager.registerEvents(new JumpPadEvent(), this.getPluginMain());
    }

    public static Main getInstance(){
        return instance;
    }

    public Configuration getConfiguration() {
        return configuration;
    }
}
