package de.ancozockt.lobby.configuration.custom;

import de.ancozockt.lobby.utilitys.background.AEStringBuilder;
import de.ancozockt.utility.configurations.defaults.SimpleConfigurationIterator;
import de.ancozockt.utility.configurations.interfaces.Configuration;
import de.ancozockt.utility.configurations.interfaces.ConfigurationIterator;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class AdvancedConfiguration implements Configuration {

    private File file;
    private HashMap<String, Object> values;

    public AdvancedConfiguration(File file, ArrayList<Object[]> defaults){
        this.file = file;
        this.values = new HashMap<>();

        setDefaults(defaults);
        load(new SimpleConfigurationIterator());
    }

    @Override
    public void setDefaults(ArrayList<Object[]> values) {
        FileConfiguration conf = YamlConfiguration.loadConfiguration(file);

        conf.options().copyDefaults(true);

        for(int i = 0; i < values.size(); i++){
            conf.addDefault(values.get(i)[0].toString(), values.get(i)[1]);
        }

        try { conf.save(file);} catch (IOException ignored) {}
    }

    @Override
    public void load(ConfigurationIterator iterator) {
        values = iterator.read(file);
    }

    @Override
    public void clearValues() {
        values.clear();
    }

    @Override
    public void setValue(String key, Object value) {
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        configuration.set(key, value);

        try {
            configuration.save(file);
            values.put(key, value);
        } catch (IOException ignored) {}
    }

    @Override
    public String getString(String key) {
        return AEStringBuilder.replaceDefaults(String.valueOf(getValue(key)));
    }

    @Override
    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(String.valueOf(getValue(key)));
    }

    @Override
    public int getInteger(String key) {
        return Integer.parseInt(String.valueOf(getValue(key)));
    }

    @Override
    public Object getValue(String key) {
        try{
            return values.getOrDefault(key, "");
        }catch (NullPointerException e){
            return false;
        }
    }

}
