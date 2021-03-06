package de.ancozockt.lobby.utilitys.view;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.utilitys.background.AEStringBuilder;
import de.ancozockt.utility.configurations.interfaces.Configuration;

import java.util.HashMap;

public class HeadList {

    public static HashMap<String, String> shortcuts = new HashMap<>();

    public HeadList(){
        Configuration shop = Main.getInstance().getConfigurationManager().getShop();

        shortcuts.put(shop.getString("Heads.Melon.Name"), "melon");
        shortcuts.put(shop.getString("Heads.Glas.Name"), "glas");
        shortcuts.put(shop.getString("Heads.Emerald.Name"), "emerald");
        shortcuts.put(shop.getString("Heads.Diamond.Name"), "diamond");
        shortcuts.put(shop.getString("Heads.Lapis.Name"), "lapis");
        shortcuts.put(shop.getString("Heads.Gold.Name"), "gold");
        shortcuts.put(shop.getString("Heads.Hay.Name"), "hay");
        shortcuts.put(shop.getString("Heads.Ice.Name"), "ice");
        shortcuts.put(shop.getString("Heads.Obsidian.Name"), "obsidian");
        shortcuts.put(shop.getString("Heads.Sponge.Name"), "sponge");
        shortcuts.put(shop.getString("Heads.Bookshelf.Name"), "bookshelf");
        shortcuts.put(shop.getString("Heads.Prismarine.Name"), "prismarine");
        shortcuts.put(shop.getString("Heads.Endstone.Name"), "endstone");
        shortcuts.put(shop.getString("Heads.Gras.Name"), "gras");
    }

    public static String getShortcut(String name){
        if(shortcuts.containsKey(name))
            return shortcuts.get(name);

        return null;
    }

}
