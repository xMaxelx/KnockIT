package de.max.knockit.api;

import com.mojang.authlib.GameProfile;
import com.mojang.authlib.properties.Property;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class ItemBuilder {

    private ItemStack item;
    private ItemMeta itemMeta;

    public ItemBuilder(Material material, short subID) {
        item = new ItemStack(material, 1, subID);
        itemMeta = item.getItemMeta();
    }

    public ItemBuilder(Material material) {
        this(material, (short)0);
    }

    public ItemBuilder setAmount(int amount) {
        item.setAmount(amount);
        return this;
    }

    public ItemBuilder setDisplayname(String name) {
        this.itemMeta.setDisplayName(name);
        return this;
    }

    public ItemBuilder addEntchantment(Enchantment e, Integer strenght, boolean b) {
        this.itemMeta.addEnchant(e, strenght, b);
        return this;
    }

    public ItemBuilder setLore(String... lore){
        itemMeta.setLore(Arrays.asList(lore));
        return this;
    }

    public ItemBuilder setLore(List<String> lore){
        itemMeta.setLore(lore);
        return this;
    }

    @SuppressWarnings("deprecation")
    public ItemBuilder setUnbreakable(boolean b){
        itemMeta.spigot().setUnbreakable(b);
        return this;
    }

    public ItemBuilder addItemFlag(ItemFlag... itemFlags){
        itemMeta.addItemFlags(itemFlags);
        return this;
    }

    public ItemStack createHead(String texture, String displayname) {
        ItemStack item = new ItemStack(Material.SKULL_ITEM);
        item.setDurability((short)3);
        ItemMeta meta = item.getItemMeta();
        item.setItemMeta(meta);
        SkullMeta headMeta = (SkullMeta)item.getItemMeta();
        meta.setDisplayName("");
        GameProfile profile = new GameProfile(UUID.randomUUID(), null);
        profile.getProperties().put("textures", new Property("textures", new String(texture)));
        Field profileField = null;
        try {
            profileField = headMeta.getClass().getDeclaredField("profile");
            profileField.setAccessible(true);
            profileField.set(headMeta, profile);
        }
        catch (Exception ex) {}
        item.setItemMeta(headMeta);
        return item;
    }

    @SuppressWarnings("deprecation")
    public ItemStack createPlayerHead(Player p, String displayname) {
        ItemStack is = new ItemStack(Material.SKULL_ITEM, 1, (short)3);
        SkullMeta im = (SkullMeta)is.getItemMeta();
        im.setDisplayName(displayname);
        im.setOwner(p.getName());

        item.setItemMeta(im);
        return item;
    }

    public ItemStack build() {
        this.item.setItemMeta(this.itemMeta);
        return this.item;
    }

    public static void setSkull(Inventory inventory, Integer slot, String skull, String displayname) {
        ItemStack s1 = de.max.knockit.api.ItemSkulls.getSkull("http://textures.minecraft.net/texture/" + skull);
        SkullMeta s1Meta = (SkullMeta) s1.getItemMeta();
        s1Meta.setDisplayName(displayname);
        s1.setItemMeta((ItemMeta) s1Meta);

        inventory.setItem(slot, s1);
    }

}
