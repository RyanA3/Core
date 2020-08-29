package me.felnstaren.core.util.block;

import java.util.HashMap;
import java.util.Random;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.data.Ageable;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.core.util.item.ItemRegistry;

public class Farming {
	
	private static Random random = new Random();
	
	public static HashMap<Material, Material> stp = new HashMap<Material, Material>();
	public static HashMap<Material, Material> pts = new HashMap<Material, Material>();
	static {
		stp.put(Material.WHEAT_SEEDS, Material.WHEAT);
		stp.put(Material.BEETROOT_SEEDS, Material.BEETROOTS);
		stp.put(Material.CARROT, Material.CARROTS);
		stp.put(Material.POTATO, Material.POTATOES);
		
		for(Material key : stp.keySet())
			pts.put(stp.get(key), key);
	}
	
	

	public static boolean harvest(Block plant) {
		if(!ItemRegistry.isCrop(plant.getType())) return false;

		Ageable crop = (Ageable) plant.getBlockData();
		if(crop.getAge() != crop.getMaximumAge()) return false;

		Location location = plant.getLocation().add(0.5, 0.5, 0.5);

		//Actually harvest crop below
		crop.setAge(0);
		for(ItemStack drop : plant.getDrops()) 
			location.getWorld().dropItemNaturally(location, drop);

		plant.setBlockData(crop);

		return true;
	}
	
	public static int harvest(Block plant, int radius) {
		int success = 0;

		if(radius <= 0) {
			if(harvest(plant)) 
				success++;
		} else {
			for(int offX = -radius; offX <= radius; offX++) 
				for(int offZ = -radius; offZ <= radius; offZ++) 
					if(harvest(plant.getRelative(offX, 0, offZ))) 
						success++;
		}

		return success;
	}
	
	
	
	
	
	
	
	public static boolean seed(Material seed, Block land) {
		Block plant = land.getRelative(0, 1, 0);
		if(land == null || land.getType() != Material.FARMLAND) return false;
		if(plant.getType() != Material.AIR) return false;

		plant.setType(stp.get(seed));
		return true;
	}
	
	public static int seed(Material seed, Block land, int radius, int max) {
		int success = 0;

		if(radius <= 0) {
			if(seed(seed, land)) 
				success++;
		} else {
			for(int offX = -radius; offX <= radius; offX++) 
				for(int offZ = -radius; offZ <= radius; offZ++) 
					if(success < max) 
						if(seed(seed, land.getRelative(offX, 0, offZ))) 
							success++;
		}
		
		return success;
	}
	
	
	
	
	
	
	public static boolean fertilize(Block plant) {
		if(plant == null || !ItemRegistry.isCrop(plant.getType())) return false;
		if(isGrown(plant)) return false;
		Ageable crop = (Ageable) plant.getBlockData();
		int grow = 1 + random.nextInt(2);
		
		if(crop.getAge() < crop.getMaximumAge() - grow)
			crop.setAge(crop.getAge() + grow);
		else 
			crop.setAge(crop.getMaximumAge());
		
		plant.setBlockData(crop);
		
		return true;
	}
	
	public static int fertilize(Block plant, int radius, int max) {
		int success = 0;

		if(radius <= 0) {
			if(fertilize(plant)) 
				success++;
		} else {
			for(int offX = -radius; offX <= radius; offX++) 
				for(int offZ = -radius; offZ <= radius; offZ++) 
					if(success < max) 
						if(fertilize(plant.getRelative(offX, 0, offZ))) 
							success++;
		}
		
		return success;
	}
	
	
	
	
	
	
	public static int getCropAge(Block block) {
		if(!ItemRegistry.isCrop(block.getType())) return -1;
		Ageable crop = (Ageable) block.getBlockData();
		return crop.getAge();
	}
	
	public static boolean isGrown(Block block) {
		if(!ItemRegistry.isCrop(block.getType())) return false;
		Ageable crop = (Ageable) block.getBlockData();
		return crop.getAge() == crop.getMaximumAge();
	}
	
}
