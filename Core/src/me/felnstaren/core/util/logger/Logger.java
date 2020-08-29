package me.felnstaren.core.util.logger;

import org.bukkit.ChatColor;
import org.bukkit.command.ConsoleCommandSender;

import me.felnstaren.core.config.Loader;
import me.felnstaren.core.util.Messenger;

public class Logger {
	
	public static ConsoleCommandSender console = Loader.plugin.getServer().getConsoleSender();
	private static String plugin_prefix = ChatColor.AQUA + "[Core]";
	
	public static Level logger_priority = Level.DEBUG;

	public static void log(Level level, String message) {
		if(!level.hasPriority(logger_priority)) return;
		console.sendMessage(Messenger.prefix(Messenger.color(message), plugin_prefix + level.color + "." + level.toString() + " >>   "));
	}
	
}
