package de.ancozockt.lobby.configuration;


import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.configuration.custom.AdvancedConfiguration;
import de.ancozockt.lobby.database.mysql.LobbyDatabase;
import de.ancozockt.lobby.utilitys.background.AEStringBuilder;
import de.ancozockt.lobby.utilitys.enums.PluginState;
import de.ancozockt.utility.api.version.ProductVersion;
import de.ancozockt.utility.api.version.VersionCheck;
import de.ancozockt.utility.configurations.defaults.SimpleConfiguration;
import de.ancozockt.utility.configurations.interfaces.Configuration;
import de.ancozockt.utility.database.AsyncMySQL;
import de.ancozockt.utility.database.FileManager;
import de.ancozockt.utility.database.FileType;

public class ConfigurationManager {

    private Configuration configuration;
    private Configuration language;
    private Configuration shop;
    private Configuration hotbar;

    private FileManager fileManager;

    private PluginState state;
    private boolean uptodate = true;
    private String update = "";

    public ConfigurationManager(){
        fileManager = new FileManager("plugins/Lobby");

        configuration = new SimpleConfiguration(fileManager.getFile("config", FileType.YAML), new DefaultFileReader("defaultconfiguration.yml", fileManager).readAll());
        language = new SimpleConfiguration(fileManager.getFile("languages", FileType.YAML), new DefaultFileReader("defaultlanguage.yml", fileManager).readAll());
        shop = new AdvancedConfiguration(fileManager.getFile("shop", FileType.YAML), new DefaultFileReader("defaultshop.yml", fileManager).readAll());
        hotbar = new SimpleConfiguration(fileManager.getFile("hotbar", FileType.YAML), new DefaultFileReader("defaulthotbar.yml", fileManager).readAll());

        fileManager.deleteTempFiles();
        load();
    }

    public void load(){
        if(!configuration.getBoolean("BungeeCord") && configuration.getString("World") == null){
            state = PluginState.NO_WORLD;
        }else{
            if(configuration.getBoolean("Updatecheck")){
                ProductVersion serverVersion = VersionCheck.getCurrentVersion(1, "n3riff2VWc");
                if(serverVersion != null){
                    ProductVersion currentVersion = new ProductVersion(Main.getInstance().getDescription().getVersion());
                    if(serverVersion.getStable() > currentVersion.getStable()){
                        uptodate = false;
                        update = serverVersion.getStable() + "." + serverVersion.getBeta() + "." + serverVersion.getFix();
                    }else if(serverVersion.getStable() == currentVersion.getStable()){
                        if(serverVersion.getBeta() > currentVersion.getBeta()){
                            uptodate = false;
                            update = serverVersion.getStable() + "." + serverVersion.getBeta() + "." + serverVersion.getFix();
                        }else if(serverVersion.getBeta() == currentVersion.getBeta()){
                            if(serverVersion.getFix() > currentVersion.getFix()){
                                uptodate = false;
                                update = serverVersion.getStable() + "." + serverVersion.getBeta() + "." + serverVersion.getFix();
                            }
                        }
                    }
                }
            }
            if(configuration.getBoolean("MySQL.Enabled")){
                Main.getInstance().setMySQL(new AsyncMySQL(Main.getInstance(), configuration.getString("MySQL.Host"), configuration.getInteger("MySQL.Port"), configuration.getString("MySQL.Username"), configuration.getString("MySQL.Password"), configuration.getString("MySQL.Database")));
                if(Main.getInstance().getMySQL().isConnected()){
                    new LobbyDatabase(configuration.getInteger("MySQL.CurrentVersion"));
                }
            }

            StringManager stringManager = Main.getInstance().getStringManager();

            stringManager.setPrefix(language.getString("Messages.Prefix").replace("&", "§")+ " ");
            stringManager.setError(language.getString("Messages.Error").replace("&", "§"));
            stringManager.setBold(language.getString("Messages.Bold").replace("&", "§"));
            stringManager.setNormal(language.getString("Messages.Normal").replace("&", "§"));

            state = PluginState.ENABLED;
        }
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public Configuration getHotbar() {
        return hotbar;
    }

    public Configuration getLanguage() {
        return language;
    }

    public Configuration getShop() {
        return shop;
    }

    public PluginState getState() {
        return state;
    }

    public String getUpdate() {
        return update;
    }

    public boolean isUptodate() {
        return uptodate;
    }
}
