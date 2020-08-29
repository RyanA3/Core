package me.felnstaren.core.util.item.custom;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;

import me.felnstaren.core.util.Messenger;
import me.felnstaren.core.util.crafting.RecipeMaker;
import me.felnstaren.core.util.crafting.RecipeShape;
import me.felnstaren.core.util.item.ItemEditor;
import me.felnstaren.core.util.item.ItemNBTEditor;

public enum CustomMaterial {

	WOODEN_SCYTHE(Material.WOODEN_HOE, "Wooden Scythe", 1, new RecipeShape("mm ", "S m", "S  ").material('S', Material.STICK).material('m', Material.OAK_PLANKS)),
	STONE_SCYTHE(Material.STONE_HOE, "Stone Scythe", 2, new RecipeShape("mm ", "S m", "S  ").material('S', Material.STICK).material('m', Material.COBBLESTONE)),
	IRON_SCYTHE(Material.IRON_HOE, "Iron Scythe", 3, new RecipeShape("mm ", "S m", "S  ").material('S', Material.STICK).material('m', Material.IRON_INGOT)),
	GOLDEN_SCYTHE(Material.GOLDEN_HOE, "Golden Scythe", 4, new RecipeShape("mm ", "S m", "S  ").material('S', Material.STICK).material('m', Material.GOLD_INGOT)),
	DIAMOND_SCYTHE(Material.DIAMOND_HOE, "Diamond Scythe", 5, new RecipeShape("mm ", "S m", "S  ").material('S', Material.STICK).material('m', Material.DIAMOND)),
	
	FERTILIZER(Material.BONE_MEAL, "Fertilizer", 6, new RecipeShape("bl", "cs").material('c', Material.COCOA_BEANS).material('b', Material.BONE_MEAL).material('s', Material.WHEAT_SEEDS).material('l', Material.OAK_LOG)),
	
	FLOPPY_BOW(Material.BOW, "Floppy Bow", 7, new RecipeShape("Ss ", "S s", "Ss ").material('S', Material.STICK).material('s', Material.STRING)),
	BOOSTER_SWORD(Material.IRON_SWORD, "Booster Sword", 11, new RecipeShape("ms", "ms", "Sf").material('m', Material.DIAMOND).material('s', Material.SUGAR).material('S', Material.STICK).material('f', Material.FEATHER)),
	
	DART(Material.DEAD_BUSH, "Dart", 8, new RecipeShape("f", "S").material('f', Material.FLINT).material('S', Material.STICK)),
	
	JEWELED_APPLE(Material.APPLE, "&dJeweled Apple", 9, new RecipeShape(" d ", "dad", " d ").material('d', Material.DIAMOND).material('a', Material.APPLE)),
	
	TOP_HAT(Material.LEATHER_HELMET, "Top Hat", 10, new RecipeShape("lll", "lwl").material('l', Material.LEATHER).material('w', Material.BLACK_WOOL)),
	
	BELL_KIT(Material.DEAD_BUSH, "Bell Kit", 12, new RecipeShape("bS").material('b', Material.BELL).material('S', Material.STICK)),
	GUITAR(Material.DEAD_BUSH, "Guitar", 13, new RecipeShape("S ", "Ss", "Ss").material('S', Material.STICK).material('s', Material.STRING)),
	BANJO(Material.DEAD_BUSH, "Banjo", 14, new RecipeShape("S ", "Ss").material('S', Material.STICK).material('s', Material.STRING)),
	
	UNSTABLE_TNT(Material.TNT, "Unstable TNT", 15, new RecipeShape("Tt", "rd").material('T', Material.TNT).material('t', Material.TRIPWIRE_HOOK).material('r', Material.REDSTONE_TORCH).material('d', Material.REDSTONE)),
	
	BANDAGE(Material.MAP, "Bandage", 16, new RecipeShape("gps").material('g', Material.GLISTERING_MELON_SLICE).material('p', Material.PAPER).material('s', Material.STICK));
	
	private int id;
	private String name;
	private Material material;
	private RecipeShape shape;
	private Recipe recipe;
	
	private CustomMaterial(Material material, String name, int id, RecipeShape shape) {
		this.material = material;
		this.name = name;
		this.id = id;
		this.shape = shape;
		this.recipe = RecipeMaker.toRecipe(this.itemize(), shape);
	}
	
	
	public Material getTrueMaterial() {
		return this.material;
	}
	
	public RecipeShape getRecipeShape() {
		return this.shape;
	}
	
	public int getID() {
		return this.id;
	}
	
	public Recipe getRecipe() {
		return recipe;
	}
	
	
	
	public boolean matches(ItemStack item) {
		if(item.getType() != material) return false;
		if(!ItemNBTEditor.hasTag(item, "id")) return false;
		if(Integer.parseInt(ItemNBTEditor.getDataTag(item, "id")) != id) return false;
		if(!ItemNBTEditor.hasTag(item, Messenger.uncolor(name).toLowerCase().replace(' ', '_'))) return false;
		
		return true;
	}
	
	public ItemStack itemize() {
		ItemStack item = new ItemStack(material);
		ItemNBTEditor.setDataTag(item, "id", id + "");
		ItemNBTEditor.setTag(item, Messenger.uncolor(name).toLowerCase().replace(' ', '_'));
		ItemEditor.setName(item, "&f" + Messenger.color(name));
		ItemEditor.setCustomModelData(item, id);
		
		return item;
	}
	
	
	
	
	
	
	public static CustomMaterial getAsCustomMaterial(ItemStack item) {
		if(!ItemNBTEditor.hasTag(item, "id")) return null;
		
		for(CustomMaterial material : CustomMaterial.values()) 
			if(material.matches(item)) return material;
		
		return null;
	}
	
	public static boolean isCustomMaterial(ItemStack item) {
		return getAsCustomMaterial(item) != null;
	}
	
	public static CustomMaterial value(String string) {
		for(CustomMaterial material : CustomMaterial.values())
			if(string.equalsIgnoreCase(material.name())) return material;
		
		return null;
	}
	
	public static void registerRecipes() {
		for(CustomMaterial material : CustomMaterial.values())
			Bukkit.addRecipe(material.getRecipe());
	}
	
}
