package de.ancozockt.lobby.listeners.inventory;

import de.ancozockt.aenmsutility.Particles.ParticleInfo;
import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.utilitys.background.AEStringBuilder;
import de.ancozockt.lobby.utilitys.view.HeadList;
import de.ancozockt.utility.configurations.interfaces.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ShopListener implements Listener {

    @EventHandler
    public void onHeads(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("BungeeCord")
                || player.getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("World"))){
            if(event.getClickedInventory()!=null
                    && event.getClickedInventory().getTitle().equalsIgnoreCase(AEStringBuilder.replaceDefaults(Main.getInstance().getConfigurationManager().getShop().getString("Heads.InventoryName")))) {
                if(event.getCurrentItem() != null
                        && event.getCurrentItem().hasItemMeta()
                            && event.getCurrentItem().getItemMeta().hasDisplayName()){
                    event.setCancelled(true);
                    Configuration language = Main.getInstance().getConfigurationManager().getLanguage();
                    if(event.getCurrentItem().getType() == Material.SKULL_ITEM){
                        Main.getInstance().getInventoryManager().getGadgets().openGadgetsMenu(player);
                    }else if(event.getCurrentItem().getType() == Material.BARRIER){
                        player.getInventory().setHelmet(null);
                        player.closeInventory();
                        if(language.getString("Shop.Heads.Deactivated") != null)
                            Main.getInstance().getNmsHandler().getChatapi().sendActionbar(player, AEStringBuilder.replaceDefaults(language.getString("Shop.Heads.Deactivated")));
                    }else{
                        String head = event.getCurrentItem().getItemMeta().getDisplayName();
                        if(player.hasPermission("lobby.heads.all") || player.hasPermission("lobby.heads." + HeadList.getShortcut(head))){
                            player.getInventory().setHelmet(event.getCurrentItem());
                            player.closeInventory();
                            if(language.getString("Shop.Heads.Activated") != null) {
                                String msg = AEStringBuilder.replaceDefaults(language.getString("Shop.Heads.Activated").replace("%name", head));
                                Main.getInstance().getNmsHandler().getChatapi().sendActionbar(player, msg);
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onParticle(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("BungeeCord")
                || player.getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("World"))){
            if(event.getClickedInventory()!=null && event.getClickedInventory().getTitle().equalsIgnoreCase(AEStringBuilder.replaceDefaults(Main.getInstance().getConfigurationManager().getShop().getString("Particles.InventoryName")))) {
                if(event.getCurrentItem() != null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()){
                    event.setCancelled(true);
                    Configuration language = Main.getInstance().getConfigurationManager().getLanguage();
                    if(event.getCurrentItem().getType() == Material.SKULL_ITEM){
                        Main.getInstance().getInventoryManager().getGadgets().openGadgetsMenu(player);
                    }else if(event.getCurrentItem().getType() == Material.BARRIER){
                        if(Main.getInstance().getNmsHandler().getParticlelauncher().getBoots().containsKey(player)){
                            Main.getInstance().getNmsHandler().getParticlelauncher().getBoots().get(player).cancel();
                            Main.getInstance().getNmsHandler().getParticlelauncher().getBoots().remove(player);
                        }
                        if(Main.getInstance().getNmsHandler().getParticlelauncher().getParticles().containsKey(player)){
                            Main.getInstance().getNmsHandler().getParticlelauncher().getParticles().remove(player);
                        }
                        if(language.getString("Shop.Particles.Deactivated") != null)
                            Main.getInstance().getNmsHandler().getChatapi().sendActionbar(player, AEStringBuilder.replaceDefaults(language.getString("Shop.Particles.Deactivated")));
                        player.closeInventory();
                    }else{
                        String pat = event.getCurrentItem().getItemMeta().getDisplayName();
                        if(Main.getInstance().getNmsHandler().getParticlelist().getByName(pat) != null){
                            if(player.hasPermission("lobby.particle.all") || player.hasPermission("lobby.particle." + Main.getInstance().getNmsHandler().getParticlelist().getShortcut(pat))){
                                ParticleInfo particle = Main.getInstance().getNmsHandler().getParticlelist().getByName(pat);
                                Main.getInstance().getNmsHandler().getParticlelauncher().getParticles().put(player, particle);
                                if(!Main.getInstance().getNmsHandler().getParticlelauncher().getBoots().containsKey(player)){
                                    Main.getInstance().getNmsHandler().getParticlelauncher().launch(player, Main.getInstance(), player.getWorld());
                                }
                                if(language.getString("Shop.Particles.Activated") != null) {
                                    String msg = AEStringBuilder.replaceDefaults(language.getString("Shop.Particles.Activated")).replace("%name", Main.getInstance().getNmsHandler().getParticlelist().getShortcut(pat).toUpperCase());
                                    Main.getInstance().getNmsHandler().getChatapi().sendActionbar(player, msg);
                                }
                                player.closeInventory();
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler
    public void onGadgets(InventoryClickEvent event){
        Player player = (Player) event.getWhoClicked();
        if(Main.getInstance().getConfigurationManager().getConfiguration().getBoolean("BungeeCord")
                || player.getWorld() == Bukkit.getWorld(Main.getInstance().getConfigurationManager().getConfiguration().getString("World"))){
            Configuration language = Main.getInstance().getConfigurationManager().getLanguage();
            if(event.getClickedInventory()!=null && event.getClickedInventory().getTitle().equalsIgnoreCase(AEStringBuilder.replaceDefaults(language.getString("Shop.InventoryName")))) {
                if(event.getCurrentItem()!=null && event.getCurrentItem().hasItemMeta() && event.getCurrentItem().getItemMeta().hasDisplayName()) {
                    event.setCancelled(true);
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(AEStringBuilder.replaceDefaults(Main.getInstance().getConfigurationManager().getShop().getString("Particles.ItemName")))) {
                        Main.getInstance().getInventoryManager().getGadgets().openParticleMenu(player);
                    }
                    if(event.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(AEStringBuilder.replaceDefaults(Main.getInstance().getConfigurationManager().getShop().getString("Heads.ItemName")))) {
                        Main.getInstance().getInventoryManager().getGadgets().openHeadsMenu(player);
                    }
                }
            }
        }
    }

}
