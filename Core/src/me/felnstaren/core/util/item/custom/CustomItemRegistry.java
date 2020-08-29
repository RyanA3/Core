package me.felnstaren.core.util.item.custom;

import java.util.Arrays;
import java.util.List;

public class CustomItemRegistry {

	private static List<CustomMaterial> scythes = Arrays.asList(
			CustomMaterial.WOODEN_SCYTHE,
			CustomMaterial.STONE_SCYTHE,
			CustomMaterial.IRON_SCYTHE,
			CustomMaterial.DIAMOND_SCYTHE,
			CustomMaterial.GOLDEN_SCYTHE
			);
	
	public static boolean isScythe(CustomMaterial material) {
		return scythes.contains(material);
	}
	
}
