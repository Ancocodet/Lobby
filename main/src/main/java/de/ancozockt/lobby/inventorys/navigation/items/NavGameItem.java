
package de.ancozockt.lobby.inventorys.navigation.items;

import de.ancozockt.lobby.utilitys.view.Items;
import org.bukkit.inventory.ItemStack;

public class NavGameItem {

	private String name;

	private String displayname;
	private String lore;

	private int position;
	private String warp;
	private ItemStack item;

	private int itemid;
	private int shortid;
	
	public NavGameItem(String name, int position, String displayname, String lore, String warp, int itemid, int shortid) {
		this.name = name;
		this.displayname = displayname;
		this.lore = lore;

		this.itemid = itemid;
		this.shortid = shortid;

		this.position = position;
		this.warp = warp;
		createItem(displayname, lore, itemid, shortid);
	}
	
	private void createItem(String displayname, String lore, int itemid, int shortid){
		this.item = Items.getInstance().getItem(itemid, shortid, displayname, 1, lore);
	}

	public void createItem(){
		this.item = Items.getInstance().getItem(itemid, shortid, displayname, 1, lore);
	}
	
	public ItemStack getItem(){
		return this.item;
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public String getWarp() {
		return warp;
	}

	public void setWarp(String warp) {
		this.warp = warp;
	}

	public String getName() {
		return name;
	}

	public String getDisplayname() {
		return displayname;
	}

	public void setDisplayname(String displayname) {
		this.displayname = displayname;
	}

	public void setLore(String lore) {
		this.lore = lore;
	}

	public String getLore() {
		return lore;
	}

	public int getItemid() {
		return itemid;
	}

	public void setItemid(int itemid) {
		this.itemid = itemid;
	}

	public int getShortid() {
		return shortid;
	}

	public void setShortid(int shortid) {
		this.shortid = shortid;
	}
}
