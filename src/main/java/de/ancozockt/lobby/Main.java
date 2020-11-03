package de.ancozockt.lobby;

import de.ancozockt.aenmsutility.NMSUtils;
import de.ancozockt.lobby.configuration.ConfigurationManager;
import de.ancozockt.lobby.configuration.StringManager;
import de.ancozockt.lobby.inventorys.hotbar.LobbyItems;
import de.ancozockt.lobby.inventorys.InventoryManager;
import de.ancozockt.lobby.utilitys.background.LobbyManager;
import de.ancozockt.lobby.utilitys.startup.CommandStartUp;
import de.ancozockt.lobby.utilitys.startup.ListenerStartUp;
import de.ancozockt.lobby.utilitys.view.Items;
import de.ancozockt.lobby.utilitys.background.PlayerManager;
import de.ancozockt.lobby.utilitys.startup.MainStartUp;
import de.ancozockt.lobby.warps.WarpManager;
import de.ancozockt.utility.addons.AddonCore;
import de.ancozockt.utility.api.version.ProductVersion;
import de.ancozockt.utility.database.AsyncMySQL;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public class Main extends JavaPlugin {

    private static Main pl;

    private ConfigurationManager configurationManager;
    private StringManager stringManager;
    private InventoryManager inventoryManager;

    private PlayerManager playerManager;

    private AsyncMySQL mySQL;
    private AddonCore addonCore;

    private LobbyItems lobbyItems;
    private WarpManager warpManager;
    private LobbyManager lobbyManager;

    private NMSUtils nmsUtils;
    private boolean upToDate;
    private ProductVersion version;

    public void onLoad(){
        pl = this;
        new Items();
        stringManager = new StringManager();
    }

    public void onEnable(){
        configurationManager = new ConfigurationManager();

        playerManager = new PlayerManager();
        nmsUtils = new NMSUtils(configurationManager.getShop(), Items.getInstance());

        lobbyItems = new LobbyItems();
        warpManager = new WarpManager();
        inventoryManager = new InventoryManager();


        if(configurationManager.getConfiguration().getBoolean("Addons")){
            addonCore = new AddonCore(this, new File("plugins/Lobby/Addons"), 0.1);
        }

        new MainStartUp().startup();
        new ListenerStartUp().startup();
        new CommandStartUp().startup();
    }

    public boolean isUpToDate() {
        return upToDate;
    }

    public ProductVersion getVersion() {
        return version;
    }

    public ConfigurationManager getConfigurationManager() {
        return configurationManager;
    }

    public StringManager getStringManager() {
        return stringManager;
    }

    public InventoryManager getInventoryManager() {
        return inventoryManager;
    }

    public LobbyItems getLobbyItems() {
        return lobbyItems;
    }

    public WarpManager getWarpManager() {
        return warpManager;
    }

    public AddonCore getAddonCore() { return addonCore; }

    public AsyncMySQL getMySQL() { return mySQL; }

    public void setMySQL(AsyncMySQL mySQL) {
        this.mySQL = mySQL;
    }

    public static Main getInstance(){
        return pl;
    }

    public NMSUtils getNmsHandler() {
        return nmsUtils;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public LobbyManager getLobbyManager() {
        return lobbyManager;
    }
}
