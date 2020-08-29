package me.felnstaren.core.config;

import org.bukkit.configuration.file.YamlConfiguration;

import me.felnstaren.core.util.logger.Level;
import me.felnstaren.core.util.logger.Logger;

public class Options {

	public static void load(YamlConfiguration config) {
		Logger.log(Level.DEBUG, "Loading config options from file; " + config.getCurrentPath());
		
		Logger.logger_priority = Level.valueOf(config.getString("logger-priority"));
	}
	
}
