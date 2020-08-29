package me.felnstaren.core.function.block;

import org.bukkit.block.Block;
import org.bukkit.block.data.type.TNT;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;

import me.felnstaren.core.util.item.custom.CustomMaterial;

public class UnstableTNTListener implements Listener {

	@EventHandler
	public void onPlaceUnstableTNT(BlockPlaceEvent event) {
		if(event.getBlockPlaced().getType() != CustomMaterial.UNSTABLE_TNT.getTrueMaterial()) return;
		if(!CustomMaterial.isCustomMaterial(event.getItemInHand())) return;
		Block block = event.getBlockPlaced();
		TNT tnt = (TNT) block.getBlockData();
		tnt.setUnstable(true);
		block.setBlockData(tnt);
	}
	
}
