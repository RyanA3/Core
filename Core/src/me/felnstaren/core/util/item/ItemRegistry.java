package me.felnstaren.core.util.item;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;

public class ItemRegistry {

	private static List<Material> hoes = Arrays.asList(
			Material.WOODEN_HOE,
			Material.STONE_HOE,
			Material.IRON_HOE,
			Material.DIAMOND_HOE,
			Material.GOLDEN_HOE
			);
	
	public static boolean isHoe(Material material) {
		return hoes.contains(material);
	}
	
	
	
	private static List<Material> crops = Arrays.asList(
			Material.WHEAT,
			Material.CARROTS,
			Material.POTATOES,
			Material.BEETROOTS
			);
	
	public static boolean isCrop(Material material) {
		return crops.contains(material);
	}
			
	
}
