package de.ancozockt.lobby.inventorys.hotbar;

import de.ancozockt.lobby.utilitys.view.Items;
import org.bukkit.inventory.ItemStack;

public class LobbyItem {

    private String name;

    private String displayname;

    private int itemID;
    private int shortID;

    private int position;
    private boolean enabled;

    private ItemStack itemStack;

    public LobbyItem(String name, String displayname, String item, int position, boolean enabled){
        this.name = name;
        this.displayname = displayname;
        this.position = position;
        this.enabled = enabled;

        String[] itemInfo = item.split(":");
        itemID = Integer.valueOf(itemInfo[0]);
        shortID = Integer.valueOf(itemInfo[1]);

        itemStack = Items.getInstance().getItem(itemID, shortID, displayname, 1, "");
    }

    private void initItem(){
        this.itemStack = Items.getInstance().getItem(this.itemID, this.shortID, this.displayname, 1, "");
    }

    public ItemStack getItemStack() {
        return itemStack;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getDisplayname() {
        return displayname;
    }

    public void setDisplayname(String diplayname) {
        this.displayname = diplayname;
        initItem();
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
        initItem();
    }

    public int getShortID() {
        return shortID;
    }

    public void setShortID(int shortID) {
        this.shortID = shortID;
        initItem();
    }
}
