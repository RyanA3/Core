package me.felnstaren.core;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import me.felnstaren.core.command.CoreGiveCommand;
import me.felnstaren.core.config.Loader;
import me.felnstaren.core.config.Options;
import me.felnstaren.core.function.ItemPatcherListener;
import me.felnstaren.core.function.block.UnstableTNTListener;
import me.felnstaren.core.function.food.BandageListener;
import me.felnstaren.core.function.food.JeweledAppleListener;
import me.felnstaren.core.function.instrument.InstrumentUseListener;
import me.felnstaren.core.function.tools.farming.FertilizerListener;
import me.felnstaren.core.function.tools.farming.HoeListener;
import me.felnstaren.core.function.weapons.BoosterSwordListener;
import me.felnstaren.core.function.weapons.DartListener;
import me.felnstaren.core.recipes.craftlistener.BandageCraftListener;
import me.felnstaren.core.util.item.custom.CustomMaterial;

public class Core extends JavaPlugin {
	
	public void onEnable() {		
		//Config init
		YamlConfiguration config = Loader.loadOrDefault("config.yml", "config.yml");
		Options.load(config);
		
		
		//Register Listeners
		PluginManager pm = Bukkit.getServer().getPluginManager();
		pm.registerEvents(new ItemPatcherListener(), this);
		pm.registerEvents(new HoeListener(), this);
		pm.registerEvents(new FertilizerListener(), this);
		pm.registerEvents(new DartListener(), this);
		pm.registerEvents(new JeweledAppleListener(), this);
		pm.registerEvents(new BoosterSwordListener(), this);
		pm.registerEvents(new InstrumentUseListener(), this);
		pm.registerEvents(new UnstableTNTListener(), this);
		pm.registerEvents(new BandageListener(), this);
		
		pm.registerEvents(new BandageCraftListener(), this);
		
		//Register Commands
		this.getCommand("cgive").setExecutor(new CoreGiveCommand());
		
		//Register Crafting Recipes
		CustomMaterial.registerRecipes();		
	}
	
	public void onDisable() {
		
	}
	
}
