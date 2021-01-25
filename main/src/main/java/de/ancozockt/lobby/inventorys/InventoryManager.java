package de.ancozockt.lobby.inventorys;

import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.inventorys.navigation.NavConfig;
import de.ancozockt.lobby.inventorys.navigation.Navigator;
import de.ancozockt.lobby.inventorys.shop.Gadgets;

public class InventoryManager {

    private NavConfig navConfig;
    private Navigator navigator;

    private Gadgets gadgets;

    public InventoryManager(){
        navConfig = new NavConfig();
        navigator = new Navigator(navConfig);

        gadgets = new Gadgets(Main.getInstance().getConfigurationManager().getShop(), Main.getInstance().getNmsHandler());
    }

    public NavConfig getNavConfig() {
        return navConfig;
    }

    public Navigator getNavigator() {
        return navigator;
    }

    public Gadgets getGadgets() {
        return gadgets;
    }
}
