package de.ancozockt.lobby.utilitys.view;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import de.ancozockt.utility.general.IItems;
import org.apache.commons.codec.binary.Base64;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;

public class Items implements IItems {

    private static IItems instance;

    public Items(){
        instance = this;
    }

    public ItemStack getItem(int ID, int shortid, String name, int amount, String lore){
        short s = (short)shortid;
        @Deprecated
        ItemStack is = new ItemStack(ID, amount, s);
        ItemMeta meta = is.getItemMeta();
        meta.setDisplayName(name);
        if(lore.contains(",")){
            meta.setLore(Arrays.asList(lore.split(",")));
        }else{
            meta.setLore(Collections.singletonList(lore));
        }
        is.setItemMeta(meta);
        return is;
    }

    @Override
    public ItemStack getItem(Material mat, int amount, int shortid, String DisplayName, String lore) {
        short s = (short)shortid;
        ItemStack item = new ItemStack(mat, amount, s);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(DisplayName);
        if(lore.contains(",")){
            meta.setLore(Arrays.asList(lore.split(",")));
        }else{
            meta.setLore(Collections.singletonList(lore));
        }
        item.setItemMeta(meta);
        return item;
    }

    @Override
    public ItemStack getItem(String name, String lore, Material m, int anzahl, int nebenID, Enchantment ench, int level, boolean vrz) {
        ItemStack is = new ItemStack(m, anzahl, (short)nebenID);
        ItemMeta im = is.getItemMeta();
        im.setDisplayName(name);
        if(lore.contains(",")){
            im.setLore(Arrays.asList(lore.split(",")));
        }else{
            im.setLore(Collections.singletonList(lore));
        }

        if (vrz) {
            im.addEnchant(ench, level, true);
            im.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        is.setItemMeta(im);
        return is;
    }

    @Override
    public ItemStack getSkull(String name, String lore, String skinURL, boolean ench) {
        ItemStack head = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        if(skinURL.isEmpty())
            return head;

        ItemMeta headMeta = head.getItemMeta();
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        byte[] encodedData = Base64.encodeBase64(String.format("{textures:{SKIN:{url:\"%s\"}}}", skinURL).getBytes());
        profile.getProperties().put("textures", new Property("textures", new String(encodedData)));

        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
        } catch (NoSuchFieldException | SecurityException ignored) { }
        Objects.requireNonNull(profileField).setAccessible(true);
        try {
            profileField.set(headMeta, profile);
        } catch (IllegalArgumentException | IllegalAccessException ignored) { }

        headMeta.setDisplayName(name);
        if(lore.contains(",")){
            headMeta.setLore(Arrays.asList(lore.split(",")));
        }else{
            headMeta.setLore(Collections.singletonList(lore));
        }

        if (ench) {
            headMeta.addEnchant(Enchantment.DURABILITY, 1, true);
            headMeta.addItemFlags(ItemFlag.HIDE_ENCHANTS);
        }
        head.setItemMeta(headMeta);
        return head;
    }

    public static IItems getInstance() {
        return instance;
    }
}
