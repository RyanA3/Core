package me.felnstaren.core.function.food;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import me.felnstaren.core.util.item.custom.CustomMaterial;

public class JeweledAppleListener implements Listener {

	@EventHandler
	public void onEat(PlayerItemConsumeEvent event) {
		Player player = event.getPlayer();
		ItemStack food = event.getItem();
		
		if(food.getType() != CustomMaterial.JEWELED_APPLE.getTrueMaterial()) return;
		if(!CustomMaterial.isCustomMaterial(food)) return;
		if(CustomMaterial.getAsCustomMaterial(food) != CustomMaterial.JEWELED_APPLE) return;
		
		player.addPotionEffect(new PotionEffect(PotionEffectType.FAST_DIGGING, 6000, 0, true));
		player.addPotionEffect(new PotionEffect(PotionEffectType.LUCK, 12000, 0), true);
		player.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 12000, 0), true);
	}
	
}
