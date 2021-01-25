package de.ancozockt.lobby.database.mysql;

import de.ancozockt.lobby.Main;

import java.util.HashMap;

public class LobbyDatabase {

    private int databaseVersion = 1;
    private int currentVersion;

    private String create = "CREATE TABLE IF NOT EXISTS lobby ( id INT AUTO_INCREMENT PRIMARY KEY , uuid VARCHAR(40) , name TEXT, owned TEXT, loot TEXT, dayli LONG, coins INT, settings TEXT)";
    private HashMap<Integer, String> update = new HashMap<>();

    public LobbyDatabase(int currentVersion){
        this.currentVersion = currentVersion;
        if(currentVersion == -1){
            create();
            currentVersion = databaseVersion;
            Main.getInstance().getConfigurationManager().getConfiguration().setValue("MySQL.CurrentVersion", databaseVersion);
        }else{
            if(currentVersion < databaseVersion){
                for(int i = currentVersion; i < databaseVersion; i++){
                    updateTable(currentVersion+1);
                }
                currentVersion = databaseVersion;
                Main.getInstance().getConfigurationManager().getConfiguration().setValue("MySQL.CurrentVersion", databaseVersion);
            }
        }
    }

    public void create(){
        Main.getInstance().getMySQL().update(create);
    }

    public void updateTable(int version){
        Main.getInstance().getMySQL().update(update.get(version));
    }

}
