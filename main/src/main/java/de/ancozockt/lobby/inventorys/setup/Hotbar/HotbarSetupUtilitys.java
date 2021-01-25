package de.ancozockt.lobby.inventorys.setup.Hotbar;

import de.ancozockt.aenmsutility.AnvilGUI.Reflections.AnvilClickEvent;
import de.ancozockt.aenmsutility.AnvilGUI.Reflections.AnvilSlot;
import de.ancozockt.aenmsutility.interfaces.NMSAnvilGUI;
import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.inventorys.hotbar.LobbyItem;
import de.ancozockt.lobby.inventorys.hotbar.LobbyItemRole;
import de.ancozockt.lobby.utilitys.background.AEStringBuilder;
import de.ancozockt.lobby.utilitys.view.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class HotbarSetupUtilitys {

    private static ArrayList<Player> clicked = new ArrayList<>();

    private static void onChangeName(Player player, LobbyItemRole role, boolean extended, AnvilClickEvent event){
        if(!clicked.contains(player)) {
            clicked.add(player);
            new BukkitRunnable(){

                @Override
                public void run() {
                    if(clicked.contains(player))
                        clicked.remove(player);
                }
            }.runTaskLaterAsynchronously(Main.getInstance(), 20L);

            LobbyItem item = Main.getInstance().getLobbyItems().getDisplayNamesDefault().get(role);
            if(extended)
                item = Main.getInstance().getLobbyItems().getDisplayNamesExtended().get(role);

            if(extended){
                Main.getInstance().getLobbyItems().getExtendedVersion().remove(item);

                item.setDisplayname(AEStringBuilder.replaceDefaults(event.getName()));
                Main.getInstance().getConfigurationManager().getHotbar().setValue("Extended.Items." + item.getName() + ".Name", event.getName());

                Main.getInstance().getLobbyItems().getExtendedVersion().add(item);
                Main.getInstance().getLobbyItems().getDisplayNamesExtended().put(role, item);

            }else{
                Main.getInstance().getLobbyItems().getDefaultVersion().remove(item);

                item.setDisplayname(AEStringBuilder.replaceDefaults(event.getName()));
                Main.getInstance().getConfigurationManager().getHotbar().setValue("Default.Items." + item.getName() + ".Name", event.getName());

                Main.getInstance().getLobbyItems().getDefaultVersion().add(item);
                Main.getInstance().getLobbyItems().getDisplayNamesDefault().put(role, item);
            }

            Main.getInstance().getNmsHandler().getChatapi().sendActionbar(player, "§3Name §aChanged");

            new BukkitRunnable(){

                @Override
                public void run() {
                    HotbarSetup.openSetup(player, extended);
                }
            }.runTaskLater(Main.getInstance(), 2L);
        }
    }

    public static void openNameChange(Player player, LobbyItemRole role, boolean extended){
        LobbyItem item = Main.getInstance().getLobbyItems().getDisplayNamesDefault().get(role);
        if(extended)
            item = Main.getInstance().getLobbyItems().getDisplayNamesExtended().get(role);

        String current = item.getDisplayname().replace("§", "&");
        NMSAnvilGUI anvilGUI = Main.getInstance().getNmsHandler().createAnvilGui(player, event -> {
            if(event.getSlot() == AnvilSlot.OUTPUT){
                event.setWillClose(true);
                event.setWillDestroy(true);
                onChangeName(player, role, extended, event);
            }else{
                event.setWillClose(false);
                event.setWillDestroy(false);
            }
        }, Main.getInstance());

        if(anvilGUI != null){
            anvilGUI.setSlot(AnvilSlot.INPUT_LEFT, Items.getInstance().getItem(current, "", Material.NAME_TAG, 1, 0, null, 0, false));
            anvilGUI.open();
        }
    }
}
