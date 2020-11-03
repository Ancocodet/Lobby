package de.ancozockt.lobby.inventorys.hotbar;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.utilitys.background.AEStringBuilder;
import org.bukkit.entity.Player;
import org.bukkit.potion.PotionEffect;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class LobbyItems {

    private ArrayList<LobbyItem> defaultVersion;
    private ArrayList<LobbyItem> extendedVersion;

    private HashMap<LobbyItemRole, LobbyItem> displayNamesExtended;
    private HashMap<LobbyItemRole, LobbyItem> displayNamesDefault;

    private List<String> permissionsExtended;

    public LobbyItems(){
        defaultVersion = new ArrayList<>();
        extendedVersion = new ArrayList<>();

        displayNamesExtended = new HashMap<>();
        displayNamesDefault = new HashMap<>();

        init();
    }

    public ArrayList<LobbyItem> getDefaultVersion() {
        return defaultVersion;
    }

    public ArrayList<LobbyItem> getExtendedVersion() {
        return extendedVersion;
    }

    public boolean isExtended(Player player){
        return hasOneOfPermissions(player, permissionsExtended);
    }

    public void giveItems(Player player){
        clearInv(player);
        if(hasOneOfPermissions(player, permissionsExtended)){
            extendedVersion(player);
        }else{
            defaultVersion(player);
        }
    }

    private void clearInv(Player player){
        player.getInventory().clear();
        player.getInventory().setArmorContents(null);
        for(PotionEffect pe : player.getActivePotionEffects()) {
            player.removePotionEffect(pe.getType());
        }
    }

    private void extendedVersion(Player player){
        for(LobbyItem item : extendedVersion){
            if(item.isEnabled())
                player.getInventory().setItem(item.getPosition(), item.getItemStack());
        }
    }

    private void defaultVersion(Player player){
        for(LobbyItem item : defaultVersion){
            if(item.isEnabled())
                player.getInventory().setItem(item.getPosition(), item.getItemStack());
        }
    }

    private boolean hasOneOfPermissions(Player player, List<String> permissions){
        boolean hasPerms = false;

        for(String perm : permissions){
            if(player.hasPermission(perm)){
                hasPerms = true;
            }
        }

        return hasPerms;
    }

    private boolean hasAllPermissions(Player player, List<String> permissions){
        boolean hasPerms = false;
        boolean stopped = false;

        for(String perm : permissions){
            if(!stopped){
                if(player.hasPermission(perm)){
                    hasPerms = true;
                }else{
                    hasPerms = false;
                    stopped = true;
                }
            }
        }

        return hasPerms;
    }

    public HashMap<LobbyItemRole, LobbyItem> getDisplayNamesDefault() {
        return displayNamesDefault;
    }

    public HashMap<LobbyItemRole, LobbyItem> getDisplayNamesExtended() {
        return displayNamesExtended;
    }

    public void init(){
        displayNamesExtended.clear();
        displayNamesDefault.clear();
        extendedVersion.clear();
        defaultVersion.clear();

        permissionsExtended = (List<String>) Main.getInstance().getConfigurationManager().getHotbar().getValue("Extended.Permissions");

        for(LobbyItemRole itemRole : LobbyItemRole.values()){
            String itemName = itemRole.toString();

            String displayname = AEStringBuilder.replaceDefaults(Main.getInstance().getConfigurationManager().getHotbar().getString("Default.Items."+itemName+".Name"));
            String item = Main.getInstance().getConfigurationManager().getHotbar().getString("Default.Items."+itemName+".Item");

            int position = Main.getInstance().getConfigurationManager().getHotbar().getInteger("Default.Items."+itemName+".Position") - 1;
            boolean enabled = Main.getInstance().getConfigurationManager().getHotbar().getBoolean("Default.Items."+itemName+".Enabled");

            if(position > 9){
                enabled = false;
            }

            LobbyItem lobbyItem = new LobbyItem(itemName, displayname, item, position, enabled);
            defaultVersion.add(lobbyItem);
            displayNamesDefault.put(itemRole, lobbyItem);
        }

        for(LobbyItemRole itemRole : LobbyItemRole.values()){
            String itemName = itemRole.toString();

            String displayname = AEStringBuilder.replaceDefaults(Main.getInstance().getConfigurationManager().getHotbar().getString("Extended.Items."+itemName+".Name"));
            String item = Main.getInstance().getConfigurationManager().getHotbar().getString("Extended.Items."+itemName+".Item");

            int position = Main.getInstance().getConfigurationManager().getHotbar().getInteger("Extended.Items."+itemName+".Position")-1;
            boolean enabled = Main.getInstance().getConfigurationManager().getHotbar().getBoolean("Extended.Items."+itemName+".Enabled");

            if(position > 9){
                enabled = false;
            }

            LobbyItem lobbyItem = new LobbyItem(itemName, displayname, item, position, enabled);
            extendedVersion.add(lobbyItem);
            displayNamesExtended.put(itemRole, lobbyItem);
        }
    }
}
