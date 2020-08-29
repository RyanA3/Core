package me.felnstaren.core.util.item;

import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class InventoryEditor {

	public static int getTotalItems(Inventory inventory, Material material) {
		int count = 0;
		
		ItemStack[] contents = inventory.getContents();
		for(int i = 0; i < contents.length; i++) {
			if(contents[i] == null) continue;
			if(contents[i].getType() == material) count += contents[i].getAmount();
		}
		
		return count;
	}
	
	public static boolean removeItems(Inventory inventory, Material material, int amount) {
		ItemStack[] contents = inventory.getContents();
		for(int i = 0; i < contents.length; i++) {
			if(amount == 0) return true;
			if(contents[i] == null) continue;
			if(contents[i].getType() != material) continue;
			
			int stack = contents[i].getAmount();
			if(stack <= amount) {
				amount -= stack;
				stack = 0;
			} else {
				stack -= amount;
				amount = 0;
			}
			
			contents[i].setAmount(stack);
		}
		
		return amount == 0;
	}
	
}
