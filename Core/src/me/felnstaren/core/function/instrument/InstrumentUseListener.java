package me.felnstaren.core.function.instrument;

import org.bukkit.Particle;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerItemHeldEvent;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.core.util.item.custom.CustomMaterial;

public class InstrumentUseListener implements Listener {

	@EventHandler
	public void onSlotChange(PlayerItemHeldEvent event) {
		Player player = event.getPlayer();
		ItemStack hand = player.getInventory().getItemInOffHand();
		
		if(!CustomMaterial.isCustomMaterial(hand)) return;
		
		CustomMaterial instrument = CustomMaterial.getAsCustomMaterial(hand);
		Sound sound;
		if(instrument == CustomMaterial.BELL_KIT) sound = Sound.BLOCK_NOTE_BLOCK_CHIME;
		else if(instrument == CustomMaterial.BANJO) sound = Sound.BLOCK_NOTE_BLOCK_BANJO;
		else if(instrument == CustomMaterial.GUITAR) sound = Sound.BLOCK_NOTE_BLOCK_GUITAR;
		else return;
		
		float pitch = 0.1875f * event.getNewSlot() + 0.5f;
		double note = (event.getNewSlot() * 2 + 6) / 24D;
		
		player.getWorld().playSound(player.getLocation(), sound, 3, pitch);
		player.getWorld().spawnParticle(Particle.NOTE, player.getEyeLocation().add(0, 0.25, 0), 0, note, 0, 0, 1);
	}
	
}
