package me.felnstaren.core.util.crafting;

import java.util.HashMap;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;

import me.felnstaren.core.config.Loader;
import me.felnstaren.core.util.Messenger;
import me.felnstaren.core.util.item.ItemEditor;

public class RecipeMaker {
	
	public static Recipe toRecipe(ItemStack result, RecipeShape shape) {
		NamespacedKey key = new NamespacedKey(Loader.plugin, Messenger.uncolor(ItemEditor.getName(result)).replace(' ', '_'));
		HashMap<Character, Material> matmap = shape.getMatMap();
		ShapedRecipe recipe = new ShapedRecipe(key, result);
		
		recipe.shape(shape.getShape());
		
		for(Character matkey : matmap.keySet()) 
			recipe.setIngredient(matkey, matmap.get(matkey));
		
		return recipe;
	}
	
}
