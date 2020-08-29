package me.felnstaren.core.util.item;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

import me.felnstaren.core.config.Loader;

public class ItemNBTEditor {

	//Flags
	public static void setTag(ItemStack item, String tag) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(Loader.plugin, tag), PersistentDataType.STRING, tag);
        item.setItemMeta(meta);
	}
	
	
	
	//Data holding flags
	public static void setDataTag(ItemStack item, String tag, String value) {
        ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().set(new NamespacedKey(Loader.plugin, tag), PersistentDataType.STRING, value);
        item.setItemMeta(meta);
	}
	
	public static String getDataTag(ItemStack item, String tag) {
		return item.getItemMeta().getPersistentDataContainer().get(new NamespacedKey(Loader.plugin, tag), PersistentDataType.STRING);
	}
	
	
	
	//Generic
	public static boolean hasTag(ItemStack item, String tag) {
		if(item.getItemMeta() == null) return false;
		return item.getItemMeta().getPersistentDataContainer().has(new NamespacedKey(Loader.plugin, tag), PersistentDataType.STRING);
	}
	
	public static void removeTag(ItemStack item, String tag) {
		ItemMeta meta = item.getItemMeta();
        meta.getPersistentDataContainer().remove(new NamespacedKey(Loader.plugin, tag));
        item.setItemMeta(meta);
	}
}
