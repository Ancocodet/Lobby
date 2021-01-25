package de.ancozockt.addons.jumppad;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class Configuration {

    private File file;

    private boolean enabled;
    private boolean playEffect;
    private int jumpPadBlockID;
    private int jumpPadBlockSubID;
    private double multiplier;
    private double height;

    public Configuration(final File file) {
        this.file = file;
        this.setDefaults();
        this.load();
    }

    public void setDefaults() {
        FileConfiguration cfg = YamlConfiguration.loadConfiguration(this.file);
        
        cfg.options().copyDefaults(true);

        cfg.addDefault("enabled", true);
        cfg.addDefault("playEffect", false);
        cfg.addDefault("jumpPadBlock", "70:0");
        cfg.addDefault("multiplier", 2.5);
        cfg.addDefault("height", 1.2);

        try {
            cfg.save(this.file);
        } catch (IOException ignored) {}
    }

    public void load() {
        final FileConfiguration cfg = YamlConfiguration.loadConfiguration(this.file);

        this.enabled = cfg.getBoolean("enabled");
        this.playEffect = cfg.getBoolean("playEffect");

        final String jump = cfg.getString("jumpPadBlock");
        if (jump.contains(":")) {
            final String[] jumppadblock = jump.split(":");
            this.jumpPadBlockID = Integer.parseInt(jumppadblock[0]);
            this.jumpPadBlockSubID = Integer.parseInt(jumppadblock[1]);
        } else {
            this.jumpPadBlockID = Integer.parseInt(jump);
            this.jumpPadBlockSubID = 0;
        }

        this.multiplier = cfg.getDouble("multiplier");
        this.height = cfg.getDouble("height");
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public double getHeight() {
        return this.height;
    }

    public double getMultiplier() {
        return this.multiplier;
    }

    public boolean shouldPlayEffect(){
        return this.playEffect;
    }

    public int getJumpPadBlockID() {
        return jumpPadBlockID;
    }

    public int getJumpPadBlockSubID() {
        return jumpPadBlockSubID;
    }
}
