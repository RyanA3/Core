package me.felnstaren.core.function.food;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.core.util.entity.Entinator;
import me.felnstaren.core.util.item.custom.CustomMaterial;

public class BandageListener implements Listener {

	@EventHandler
	public void onBandage(PlayerInteractEvent event) {
		if(event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) return;
		
		Player player = event.getPlayer();
		ItemStack hand;
		
		if(event.getHand() == EquipmentSlot.OFF_HAND) hand = player.getInventory().getItemInOffHand();
		else if(event.getHand() == EquipmentSlot.HAND) hand = player.getInventory().getItemInMainHand();
		else return;
		
		if(hand.getType() != CustomMaterial.BANDAGE.getTrueMaterial()) return;
		if(!CustomMaterial.isCustomMaterial(hand)) return;
		if(CustomMaterial.getAsCustomMaterial(hand) != CustomMaterial.BANDAGE) return;
		
		if(event.getHand() == EquipmentSlot.OFF_HAND) player.getInventory().setItemInOffHand(new ItemStack(Material.AIR));
		else if(event.getHand() == EquipmentSlot.HAND) player.getInventory().setItemInMainHand(new ItemStack(Material.AIR));
		event.setCancelled(true);

		Entinator.heal(player, 1.5);
	}
	
}
