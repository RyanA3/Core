package me.felnstaren.core.recipes.craftlistener;

import org.bukkit.Material;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.CraftItemEvent;

import me.felnstaren.core.util.item.InventoryEditor;
import me.felnstaren.core.util.item.ItemEditor;
import me.felnstaren.core.util.item.custom.CustomMaterial;
import me.felnstaren.core.util.logger.Level;
import me.felnstaren.core.util.logger.Logger;

public class BandageCraftListener implements Listener {

	//TODO: Test This
	
	@EventHandler
	public void onCraft(CraftItemEvent event) {
		if(!event.getRecipe().getResult().isSimilar(CustomMaterial.BANDAGE.itemize())) return;
		if(event.getResult() == Result.DENY) return;
		
		Logger.log(Level.DEBUG, "Bandage Crafted");
		if(event.getClick() == ClickType.SHIFT_LEFT || event.getClick() == ClickType.SHIFT_RIGHT) {
			event.setCancelled(true);
			event.getWhoClicked().getInventory().addItem(ItemEditor.makeUnique(CustomMaterial.BANDAGE.itemize()));
			InventoryEditor.removeItems(event.getWhoClicked().getOpenInventory().getTopInventory(), Material.GLISTERING_MELON_SLICE, 1);
			InventoryEditor.removeItems(event.getWhoClicked().getOpenInventory().getTopInventory(), Material.PAPER, 1);
			InventoryEditor.removeItems(event.getWhoClicked().getOpenInventory().getTopInventory(), Material.STICK, 1);
		} else {
			event.setCurrentItem(ItemEditor.makeUnique(CustomMaterial.BANDAGE.itemize()));
		}
	}
	
}
