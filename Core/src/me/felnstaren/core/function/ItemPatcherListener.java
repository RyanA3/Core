package me.felnstaren.core.function;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.felnstaren.core.util.item.custom.CustomMaterial;

public class ItemPatcherListener implements Listener {

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		if(event.getBlockPlaced().getType() != Material.DEAD_BUSH) return;
		if(!CustomMaterial.isCustomMaterial(event.getItemInHand())) return;
		event.setCancelled(true);
	}
	
}
