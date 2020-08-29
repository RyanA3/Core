package me.felnstaren.core.function.weapons;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.felnstaren.core.util.item.ItemEditor;
import me.felnstaren.core.util.item.custom.CustomMaterial;

public class BoosterSwordListener implements Listener {

	@EventHandler
	public void onSwordBoost(PlayerInteractEvent event) {
		if(event.getAction() != Action.RIGHT_CLICK_BLOCK && event.getAction() != Action.RIGHT_CLICK_AIR) return;
		
		Player player = event.getPlayer();
		ItemStack hand;
		
		if(event.getHand() == EquipmentSlot.OFF_HAND) hand = player.getInventory().getItemInOffHand();
		else if(event.getHand() == EquipmentSlot.HAND) hand = player.getInventory().getItemInMainHand();
		else return;
		
		if(hand.getType() != CustomMaterial.BOOSTER_SWORD.getTrueMaterial()) return;
		if(!CustomMaterial.isCustomMaterial(hand)) return;
		if(CustomMaterial.getAsCustomMaterial(hand) != CustomMaterial.BOOSTER_SWORD) return;
		
		ItemEditor.damage(hand, ItemEditor.getDamage(hand) + 10);
		
		player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 1200, 0), true);
	}
	
}
