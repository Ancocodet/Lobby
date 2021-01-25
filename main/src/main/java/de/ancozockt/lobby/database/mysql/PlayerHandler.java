package de.ancozockt.lobby.database.mysql;

import de.ancozockt.lobby.Main;
import org.bukkit.scheduler.BukkitRunnable;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Consumer;

public class PlayerHandler {

    private String name;
    private String uuid;

    private String owned;
    private String loot;
    private long dayli;
    private int coins;
    private String settings;

    private boolean exists = false;
    private boolean exists_loaded = false;
    private boolean loaded = false;

    public PlayerHandler(String name, String uuid){
        this.name = name;
        this.uuid = uuid;

        checkName();
        checkUUID();
        updateName();
    }

    public void checkName(){
        Main.getInstance().getMySQL().query("SELECT * FROM lobby WHERE name='" + name + "';", new Consumer<ResultSet>() {
            @Override
            public void accept(ResultSet resultSet) {
                try{
                    if(resultSet.next()){
                        if(resultSet.getString("name") != null){
                            if(!resultSet.getString("uuid").equals(uuid)){
                                updateUUID();
                            }
                        }
                    }
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void checkUUID(){
        Main.getInstance().getMySQL().query("SELECT * FROM lobby WHERE uuid='" + uuid + "';", new Consumer<ResultSet>() {
            @Override
            public void accept(ResultSet resultSet) {
                try{
                    if(resultSet.next()){
                        if(resultSet.getString("uuid") != null){
                            exists = true;
                        }else{
                            exists = false;
                        }
                    }else{
                        exists = false;
                    }
                    exists_loaded = true;
                }catch (SQLException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public void fetch(){
        if(exists_loaded && exists){
            Main.getInstance().getMySQL().query("SELECT * FROM lobby WHERE uuid='" + uuid + "';", new Consumer<ResultSet>() {
                @Override
                public void accept(ResultSet resultSet) {
                    try {
                        while (resultSet.next()){
                            if(resultSet.getString("owned") != null){
                                owned = resultSet.getString("owned");
                            }
                            if(resultSet.getString("loot") != null){
                                loot = resultSet.getString("loot");
                            }
                            if(resultSet.getString("settings") != null){
                                settings = resultSet.getString("settings");
                            }
                            dayli = resultSet.getLong("dayli");
                            coins = resultSet.getInt("coins");
                            loaded = true;
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            });
        }else if(exists_loaded){
            long millis = System.currentTimeMillis();
            millis = millis - 86400000;
            dayli = millis;
            Main.getInstance().getMySQL().update("INSERT INTO lobby (uuid, name, owned, loot, dayli, coins, settings) VALUES ('"+uuid+"', '"+name+"', '', '', '" + millis + "', '150', 'all,all');");
            new BukkitRunnable(){

                @Override
                public void run() {
                    exists =  true;
                    exists_loaded = true;

                    owned = "";
                    loot = "";
                    coins = 150;
                    settings = "all,all";

                    loaded = true;
                }
            }.runTaskLaterAsynchronously(Main.getInstance(), 25L);
        }
    }

    public void updateUUID(){
        Main.getInstance().getMySQL().update("UPDATE lobby SET uuid='" + uuid + "' WHERE name='" + name + "';");
    }

    public void updateName(){
        Main.getInstance().getMySQL().update("UPDATE lobby SET name='" + name + "' WHERE uuid='" + uuid + "';");
    }

    public boolean isReady(){
        return (exists_loaded && exists && loaded);
    }

    public boolean isExistsLoaded() {
        return exists_loaded;
    }

    public String getOwned() {
        return owned;
    }

    public void setOwned(String owned) {
        if(isReady()){
            this.owned = owned;
            Main.getInstance().getMySQL().update("UPDATE lobby SET owned='" + owned + "' WHERE uuid='" + uuid + "';");
        }
    }

    public String getLoot() {
        return loot;
    }

    public void setLoot(String loot) {
        if(isReady()){
            this.loot = loot;
            Main.getInstance().getMySQL().update("UPDATE lobby SET loot='" + loot + "' WHERE uuid='" + uuid + "';");
        }
    }

    public long getDayli() {
        return dayli;
    }

    public void setDayli(){
        if(isReady()) {
            this.dayli = dayli;
            Main.getInstance().getMySQL().update("UPDATE lobby SET dayli='" + dayli + "' WHERE uuid='" + uuid + "';");
        }
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        if(isReady()) {
            this.coins = coins;
            Main.getInstance().getMySQL().update("UPDATE lobby SET coins='" + coins + "' WHERE uuid='" + uuid + "';");
        }
    }

    public void addCoins(int coins){
        setCoins(getCoins()+coins);
    }

    public void rmvCoins(int coins){
        setCoins(getCoins()-coins);
    }

    public String getSettings() {
        return settings;
    }

    public void setSettings(String settings) {
        if(isReady()) {
            this.settings = settings;
            Main.getInstance().getMySQL().update("UPDATE lobby SET settings='" + settings + "' WHERE uuid='" + uuid + "';");
        }
    }
    
}
