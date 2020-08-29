package me.felnstaren.core.command;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import me.felnstaren.core.util.Messenger;
import me.felnstaren.core.util.item.custom.CustomMaterial;

public class CoreGiveCommand implements CommandExecutor {

	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		if(!sender.hasPermission("core.give")) {
			sender.sendMessage(Messenger.color("&cYou do not have permission to &7core.give&c!"));
			return true;
		}
		
		if(args.length == 0 || args.length > 3) {
			sender.sendMessage(Messenger.color("&c/cgive <player> <core_item>"));
			return true;
		}
		
		if(args.length == 1) {
			sender.sendMessage(Messenger.color("&c/cgive " + args[0] + " &c<core_item>"));
			return true;
		}
		
		Player player = Bukkit.getPlayerExact(args[0]);
		CustomMaterial material = CustomMaterial.value(args[1]);
		
		if(player == null) {
			sender.sendMessage(Messenger.color("&7" + args[0] + " &cis not online, or does not exist!"));
			return true;
		}
		
		if(material == null) {
			sender.sendMessage(Messenger.color("&7" + args[1] + " &cis not a core item!"));
			return true;
		}
		
		ItemStack item = material.itemize();
		
		if(args.length > 2) {
			try {
				item.setAmount(Integer.parseInt(args[2]));
			} catch (Exception e) {
				player.sendMessage(Messenger.color("&cInvalid item count &7" + args[2]));
			}
		}
		
		player.getInventory().addItem(item);
		
		return true;
	}

}
