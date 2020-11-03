package de.ancozockt.lobby.utilitys.startup;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.utilitys.enums.PluginState;
import de.ancozockt.utility.addons.core.AddonManager;
import de.ancozockt.utility.general.IStartUp;
import org.bukkit.Bukkit;

import java.util.List;

public class MainStartUp implements IStartUp {

    private Main instance;

    public MainStartUp(){
        instance = Main.getInstance();
    }

    private void printVersion(){
        if(instance.getConfigurationManager().getConfiguration().getBoolean("General.Updatecheck")){
            if(instance.getConfigurationManager().isUptodate()){
                System.out.println("Version: " + instance.getDescription().getVersion() + " [UP TO DATE]");
                Main.getInstance().setUpToDate(true);
            }else{
                System.out.println("Version: " + instance.getDescription().getVersion()  + " [OUTDATED: " + instance.getConfigurationManager().getUpdate() + "]");
                Main.getInstance().setUpToDate(false);
            }
        }else{
            System.out.println("Version: " + instance.getDescription().getVersion());
        }
    }

    private void printAuthors(){
        List<String> authorList = instance.getDescription().getAuthors();
        if(authorList.size() > 1){
            String authors = "";
            for(String author : authorList){
                authors += author + " ";
            }
            System.out.println("Authors: " + authors);
        }else{
            System.out.println("Author: " + authorList.get(0));
        }
    }

    private void printNMSInformation(){
        if(instance.getNmsHandler().isSuccess()) {
            System.out.println("NMSVersion: " + instance.getNmsHandler().getUsedversion());
        }else{
            System.out.println("NMSVersion: Incompatible server-version [PLUGIN WILL BE DISABLED]");
        }
    }

    private void printAddons(){
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("General.Addons")) {
            AddonManager addonManager = instance.getAddonCore().getAddonManager();
            if (addonManager.getAddons().length > 1) {
                System.out.println("Addons: " + addonManager.getAddons().length + " loaded addons");
            } else if (addonManager.getAddons().length == 0) {
                System.out.println("Addons: no addons found");
            } else {
                System.out.println("Addons: " + addonManager.getAddons().length + " loaded addon");
            }
        }
    }

    private void printErrors(){
        if(instance.getConfigurationManager().getState() == PluginState.NO_WORLD){
            System.out.println("Configuration: World not found [PLUGIN WILL BE DISABLED]");
        }
    }

    @Override
    public void startup() {
        System.out.println(">> Lobby - " + instance.getDescription().getVersion() + " <<");
        System.out.println("  ");
        printVersion();
        printNMSInformation();
        printAuthors();
        printAddons();
        printErrors();
        System.out.println("  ");
        if(instance.getConfigurationManager().getState() == PluginState.NO_WORLD ||
                !instance.getNmsHandler().isSuccess()){
            Bukkit.getPluginManager().disablePlugin(instance);
        }
    }
}
