package de.ancozockt.lobby.configuration;


import de.ancozockt.utility.configurations.DefaultBuilder;
import de.ancozockt.utility.database.FileManager;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.*;
import java.util.ArrayList;

public class DefaultFileReader {

    private DefaultBuilder builder;

    private String filename;
    private FileConfiguration conf;

    public DefaultFileReader(String filename, FileManager manager){
        this.builder = new DefaultBuilder();
        this.filename = "/defaults/" + filename;

        File file = builder.createTempFile(this.filename, filename, manager);
        conf = YamlConfiguration.loadConfiguration(file);
    }

    public ArrayList<Object[]> readAll(){
        ArrayList<Object[]> values = new ArrayList<>();

        values = read("", values);

        return values;
    }

    public ArrayList<Object[]> read(String currentKey, ArrayList<Object[]> values){
        for(String key : conf.getConfigurationSection(currentKey).getKeys(false)){
            String confKey = key;
            if(currentKey.length() > 1){
                confKey = currentKey + "." + key;
            }
            if(!conf.isConfigurationSection(key)) {
                values.add(builder.buildDefault(confKey, conf.get(confKey)));
            }else{
                values = read(confKey, values);
            }
        }

        return values;
    }

}
