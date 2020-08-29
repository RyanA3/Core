package me.felnstaren.core.function.weapons;

import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerPickupArrowEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.core.util.item.custom.CustomMaterial;

public class DartListener implements Listener {

	@EventHandler
	public void onDartThrow(PlayerInteractEvent event) {
		if(event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) return;
		
		Player player = event.getPlayer();
		ItemStack hand;
		
		if(event.getHand() == EquipmentSlot.OFF_HAND) hand = player.getInventory().getItemInOffHand();
		else if(event.getHand() == EquipmentSlot.HAND) hand = player.getInventory().getItemInMainHand();
		else return;
		
		if(hand.getType() != CustomMaterial.DART.getTrueMaterial()) return;
		if(!CustomMaterial.isCustomMaterial(hand)) return;
		if(CustomMaterial.getAsCustomMaterial(hand) != CustomMaterial.DART) return;
		
		Arrow arrow = player.launchProjectile(Arrow.class);
		arrow.setVelocity(arrow.getVelocity().multiply(0.4));
		arrow.setCustomName("dart");
		
		hand.setAmount(hand.getAmount() - 1);
		
		player.getWorld().playSound(player.getLocation(), Sound.ENTITY_ARROW_SHOOT, 1, 1);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onDartPickup(PlayerPickupArrowEvent event) {
		Arrow arrow = (Arrow) event.getArrow();
		
		if(arrow.getCustomName() == null) return;
		if(!arrow.getCustomName().equals("dart")) return;
		
		arrow.remove();
		event.getItem().setItemStack(CustomMaterial.DART.itemize());
	}
	
}
