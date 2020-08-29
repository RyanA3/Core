package me.felnstaren.core.function.tools.farming;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.core.util.block.Farming;
import me.felnstaren.core.util.entity.Animator;
import me.felnstaren.core.util.item.InventoryEditor;
import me.felnstaren.core.util.item.ItemRegistry;
import me.felnstaren.core.util.item.custom.CustomItemRegistry;
import me.felnstaren.core.util.item.custom.CustomMaterial;

public class HoeListener implements Listener {

	@EventHandler
	public void onHarvest(PlayerInteractEvent event) {
		if(event.getAction() != Action.RIGHT_CLICK_BLOCK) return;
		if(event.getHand() != EquipmentSlot.HAND) return;
		if(event.getClickedBlock() == null) return;
		
		Player player = event.getPlayer();
		ItemStack hand = player.getInventory().getItemInMainHand();
		if(!ItemRegistry.isHoe(hand.getType())) return;
		
		Block block = event.getClickedBlock();
		if(block.getType() != Material.FARMLAND && !ItemRegistry.isCrop(block.getType())) return;
		
		boolean success = false;
		if(ItemRegistry.isCrop(block.getType())) {
			//Harvest
			if(CustomItemRegistry.isScythe(CustomMaterial.getAsCustomMaterial(hand))) 
				success = (Farming.harvest(block, 1) > 0);
			else
				success = (Farming.harvest(block));
		} 
		
		//Plant
		Material seed = Material.WHEAT_SEEDS;
		if(ItemRegistry.isCrop(block.getType())) {
			if(!CustomItemRegistry.isScythe(CustomMaterial.getAsCustomMaterial(hand))) return;
			seed = Farming.pts.get(block.getType());
			block = block.getRelative(0, -1, 0);
		}
			
		int planted = 0;
		int seeds = InventoryEditor.getTotalItems(player.getInventory(), seed);
		if(seeds == 0) return;

		if(CustomItemRegistry.isScythe(CustomMaterial.getAsCustomMaterial(hand)))
			planted = Farming.seed(seed, block, 1, seeds);
		else
			if(Farming.seed(seed, block)) planted = 1;
			
		InventoryEditor.removeItems(player.getInventory(), seed, planted);
		
		if(!success) success = planted > 0;
		if(success) Animator.swingArm(player);
	}
	
}
