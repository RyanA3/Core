package me.felnstaren.core.function.tools.farming;

import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFertilizeEvent;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.core.util.block.Farming;
import me.felnstaren.core.util.item.ItemRegistry;
import me.felnstaren.core.util.item.custom.CustomMaterial;

public class FertilizerListener implements Listener {

	@EventHandler
	public void onBonemeal(BlockFertilizeEvent event) {
		Player player = event.getPlayer();
		Block plant = event.getBlock();

		if(!ItemRegistry.isCrop(plant.getType())) return;
		
		ItemStack hand = player.getInventory().getItemInMainHand();
		if(hand == null || CustomMaterial.getAsCustomMaterial(hand) != CustomMaterial.FERTILIZER) 
			hand = player.getInventory().getItemInOffHand();
		if(hand == null || CustomMaterial.getAsCustomMaterial(hand) != CustomMaterial.FERTILIZER) return;

		Farming.fertilize(event.getBlock(), 1, hand.getAmount() * 9);
	}
	
}
