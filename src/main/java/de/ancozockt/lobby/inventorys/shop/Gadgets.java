package de.ancozockt.lobby.inventorys.shop;

import de.ancozockt.aenmsutility.NMSUtils;
import de.ancozockt.lobby.Main;
import de.ancozockt.lobby.utilitys.background.AEStringBuilder;
import de.ancozockt.lobby.utilitys.view.Items;
import de.ancozockt.utility.configurations.interfaces.Configuration;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class Gadgets {

	private Configuration configuration;
	private NMSUtils nmsHandler;

	public Gadgets(Configuration configuration, NMSUtils nmsUtils){
		this.configuration = configuration;
		nmsHandler = nmsUtils;
	}

	public void openGadgetsMenu(Player p){
		Inventory inv = Bukkit.createInventory(null, 3*9, AEStringBuilder.replaceDefaults(Main.getInstance().getConfigurationManager().getLanguage().getString("Shop.InventoryName")));

		inv.setItem(10, Items.getInstance().getItem(Material.ENCHANTMENT_TABLE, 1, 0, configuration.getString("Particles.ItemName"), ""));
		inv.setItem(12, Items.getInstance().getItem(Material.ARMOR_STAND, 1, 0, "§cComing Soon", ""));
		inv.setItem(14, Items.getInstance().getItem(383, 90, "§cComing Soon", 1, ""));
		inv.setItem(16, Items.getInstance().getItem(Material.IRON_HELMET, 1, 0, configuration.getString("Heads.ItemName"), ""));

		p.openInventory(inv);
	}

	public void openParticleMenu(Player p){
		Inventory inv = Bukkit.createInventory(p, 9*6, configuration.getString("Particles.InventoryName"));

		ItemStack pat1 = nmsHandler.getParticlelist().getByShortcut("lava").getItem();
		ItemStack pat2 = nmsHandler.getParticlelist().getByShortcut("hearts").getItem();
		ItemStack pat3 = nmsHandler.getParticlelist().getByShortcut("music").getItem();
		ItemStack pat4 = nmsHandler.getParticlelist().getByShortcut("smoke").getItem();
		ItemStack pat5 = nmsHandler.getParticlelist().getByShortcut("spell").getItem();
		ItemStack pat6 = nmsHandler.getParticlelist().getByShortcut("explosion").getItem();
		ItemStack pat7 = nmsHandler.getParticlelist().getByShortcut("water").getItem();

		inv.setItem(10, pat1);
		inv.setItem(11, pat2);
		inv.setItem(12, pat3);
		inv.setItem(13, pat4);
		inv.setItem(14, pat5);
		inv.setItem(15, pat6);
		inv.setItem(16, pat7);

		ItemStack pat8 = nmsHandler.getParticlelist().getByShortcut("enchantment").getItem();
		ItemStack pat9 = nmsHandler.getParticlelist().getByShortcut("portal").getItem();
		ItemStack pat10 = nmsHandler.getParticlelist().getByShortcut("slime").getItem();
		ItemStack pat11 = nmsHandler.getParticlelist().getByShortcut("shovel").getItem();
		ItemStack pat12 = nmsHandler.getParticlelist().getByShortcut("happy").getItem();
		ItemStack pat13 = nmsHandler.getParticlelist().getByShortcut("snowball").getItem();
		ItemStack pat14 = nmsHandler.getParticlelist().getByShortcut("rage").getItem();

		inv.setItem(19, pat8);
		inv.setItem(20, pat9);
		inv.setItem(21, pat10);
		inv.setItem(22, pat11);
		inv.setItem(23, pat12);
		inv.setItem(24, pat13);
		inv.setItem(25, pat14);

		inv.setItem(37, Items.getInstance().getSkull(AEStringBuilder.replaceDefaults(Main.getInstance().getConfigurationManager().getLanguage().getString("Shop.Back")), "", "http://textures.minecraft.net/texture/bb0f6e8af46ac6faf88914191ab66f261d6726a7999c637cf2e4159fe1fc477", false));
		inv.setItem(40, Items.getInstance().getItem(Material.BARRIER, 1, 0, AEStringBuilder.replaceDefaults(Main.getInstance().getConfigurationManager().getLanguage().getString("Shop.Remove")), ""));

		p.openInventory(inv);
	}

	public void openHeadsMenu(Player p){
		Inventory inv = Bukkit.createInventory(p, 9*6, configuration.getString("Heads.InventoryName"));

		inv.setItem(10, Items.getInstance().getItem(Material.MELON_BLOCK, 1, 0, configuration.getString("Heads.Melon.Name"), ""));
		inv.setItem(11, Items.getInstance().getItem(Material.GLASS, 1, 0, configuration.getString("Heads.Glas.Name"), ""));
		inv.setItem(12, Items.getInstance().getItem(Material.EMERALD_BLOCK, 1, 0, configuration.getString("Heads.Emerald.Name"), ""));
		inv.setItem(13, Items.getInstance().getItem(Material.DIAMOND_BLOCK, 1, 0, configuration.getString("Heads.Diamond.Name"), ""));
		inv.setItem(14, Items.getInstance().getItem(Material.LAPIS_BLOCK, 1, 0, configuration.getString("Heads.Lapis.Name"), ""));
		inv.setItem(15, Items.getInstance().getItem(Material.GOLD_BLOCK, 1, 0, configuration.getString("Heads.Gold.Name"), ""));
		inv.setItem(16, Items.getInstance().getItem(Material.HAY_BLOCK, 1, 0, configuration.getString("Heads.Hay.Name"), ""));

		inv.setItem(19, Items.getInstance().getItem(Material.ICE, 1, 0, configuration.getString("Heads.Ice.Name"), ""));
		inv.setItem(20, Items.getInstance().getItem(Material.OBSIDIAN, 1, 0, configuration.getString("Heads.Obsidian.Name"), ""));
		inv.setItem(21, Items.getInstance().getItem(Material.SPONGE, 1, 0, configuration.getString("Heads.Sponge.Name"), ""));
		inv.setItem(22, Items.getInstance().getItem(Material.BOOKSHELF, 1, 0, configuration.getString("Heads.Bookshelf.Name"), ""));
		inv.setItem(23, Items.getInstance().getItem(Material.PRISMARINE, 1, 2, configuration.getString("Heads.Prismarine.Name"), ""));
		inv.setItem(24, Items.getInstance().getItem(Material.ENDER_STONE, 1, 2, configuration.getString("Heads.Endstone.Name"), ""));
		inv.setItem(25, Items.getInstance().getItem(Material.GRASS, 1, 0, configuration.getString("Heads.Gras.Name"), ""));

		inv.setItem(37, Items.getInstance().getSkull(AEStringBuilder.replaceDefaults(Main.getInstance().getConfigurationManager().getLanguage().getString("Shop.Back")), "", "http://textures.minecraft.net/texture/bb0f6e8af46ac6faf88914191ab66f261d6726a7999c637cf2e4159fe1fc477", false));
		inv.setItem(40, Items.getInstance().getItem(Material.BARRIER, 1, 0, AEStringBuilder.replaceDefaults(Main.getInstance().getConfigurationManager().getLanguage().getString("Shop.Remove")), ""));

		p.openInventory(inv);
	}

}
