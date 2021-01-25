package de.ancozockt.lobby.inventorys.setup.Navigation;

import de.ancozockt.aenmsutility.AnvilGUI.Reflections.AnvilClickEvent;
import de.ancozockt.aenmsutility.AnvilGUI.Reflections.AnvilSlot;
import de.ancozockt.aenmsutility.interfaces.NMSAnvilGUI;
import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.utilitys.view.Items;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class NavSetupUtilitys {

    private static ArrayList<Player> clicked = new ArrayList<>();

    private static void onChangeName(Player player, String name, int position, AnvilClickEvent event){
        if(!clicked.contains(player)) {
            clicked.add(player);
            new BukkitRunnable(){

                @Override
                public void run() {
                    if(clicked.contains(player))
                        clicked.remove(player);
                }
            }.runTaskLaterAsynchronously(Main.getInstance(), 20L);
            Main.getInstance().getInventoryManager().getNavConfig().changeName(name, position, event.getName());
            Main.getInstance().getNmsHandler().getChatapi().sendActionbar(player, "§aName Changed Successfully");
        }
    }

    public static void openNameChange(Player player, int position, String name, String current){
        NMSAnvilGUI anvilGUI = Main.getInstance().getNmsHandler().createAnvilGui(player, event -> {
            if(event.getSlot() == AnvilSlot.OUTPUT){
                event.setWillClose(true);
                event.setWillDestroy(true);
                onChangeName(player, name, position, event);
            }else{
                event.setWillClose(false);
                event.setWillDestroy(false);
            }
        }, Main.getInstance());
        if(anvilGUI != null) {
            anvilGUI.setSlot(AnvilSlot.INPUT_LEFT, Items.getInstance().getItem(current, "", Material.NAME_TAG, 1, 0, null, 0, false));
            anvilGUI.open();
        }
    }

    private static void onChangeLore(Player player, String name, int position, AnvilClickEvent event){
        if(!clicked.contains(player)) {
            clicked.add(player);
            new BukkitRunnable(){

                @Override
                public void run() {
                    if(clicked.contains(player))
                        clicked.remove(player);
                }
            }.runTaskLaterAsynchronously(Main.getInstance(), 20L);
            Main.getInstance().getInventoryManager().getNavConfig().changeLore(name, position, event.getName());
            Main.getInstance().getNmsHandler().getChatapi().sendActionbar(player, "§aLore Changed Successfully");
        }
    }

    public static void openLoreChange(Player player, int position, String name, String current){
        NMSAnvilGUI anvilGUI = Main.getInstance().getNmsHandler().createAnvilGui(player, event -> {
            if(event.getSlot() == AnvilSlot.OUTPUT){
                event.setWillClose(true);
                event.setWillDestroy(true);
                onChangeLore(player, name, position, event);
            }else{
                event.setWillClose(false);
                event.setWillDestroy(false);
            }
        }, Main.getInstance());
        if(anvilGUI != null) {
            anvilGUI.setSlot(AnvilSlot.INPUT_LEFT, Items.getInstance().getItem(current, "", Material.NAME_TAG, 1, 0, null, 0, false));
            anvilGUI.open();
        }
    }

    private static void onChangeWarp(Player player, String name, int position, AnvilClickEvent event){
        if(!clicked.contains(player)) {
            clicked.add(player);
            new BukkitRunnable(){

                @Override
                public void run() {
                    if(clicked.contains(player))
                        clicked.remove(player);
                }
            }.runTaskLaterAsynchronously(Main.getInstance(), 20L);
            Main.getInstance().getInventoryManager().getNavConfig().changeWarp(name, position, event.getName());
            Main.getInstance().getNmsHandler().getChatapi().sendActionbar(player, "§aWarp Changed Successfully");
        }
    }

    public static void openWarpChange(Player player, int position, String name, String current){
        NMSAnvilGUI anvilGUI = Main.getInstance().getNmsHandler().createAnvilGui(player, event -> {
            if(event.getSlot() == AnvilSlot.OUTPUT){
                event.setWillClose(true);
                event.setWillDestroy(true);
                onChangeWarp(player, name, position, event);
            }else{
                event.setWillClose(false);
                event.setWillDestroy(false);
            }
        }, Main.getInstance());
        if(anvilGUI != null) {
            anvilGUI.setSlot(AnvilSlot.INPUT_LEFT, Items.getInstance().getItem(current, "", Material.NAME_TAG, 1, 0, null, 0, false));
            anvilGUI.open();
        }
    }

}
