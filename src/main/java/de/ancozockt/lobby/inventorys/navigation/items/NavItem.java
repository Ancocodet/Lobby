package de.ancozockt.lobby.inventorys.navigation.items;

import de.ancozockt.lobby.utilitys.view.Items;
import org.bukkit.inventory.ItemStack;

public class NavItem {

	private String name;

	private int itemid;
	private int shortid;
	
	private int position;
	private ItemStack item;
	
	public NavItem(String name, int position, int itemid, int shortid) {
		this.name = name;

		this.itemid = itemid;
		this.shortid = shortid;

		this.position = position;
		createItem();
	}
	
	public void createItem(){
		this.item = Items.getInstance().getItem(itemid, shortid, " ", 1, "");
	}

	public int getPosition() {
		return position;
	}

	public void setPosition(int position) {
		this.position = position;
	}

	public ItemStack getItem() {
		return item;
	}

	public String getName() {
		return name;
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
