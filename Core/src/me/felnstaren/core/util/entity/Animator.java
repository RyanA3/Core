package me.felnstaren.core.util.entity;

import org.bukkit.Bukkit;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftEntity;
import org.bukkit.craftbukkit.v1_15_R1.entity.CraftPlayer;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_15_R1.PacketPlayOutAnimation;

public class Animator {

	public static void swingArm(Entity entity) {
		PacketPlayOutAnimation swing = new PacketPlayOutAnimation(((CraftEntity) entity).getHandle(), 0);
		for(Player player : Bukkit.getOnlinePlayers()) ((CraftPlayer) player).getHandle().playerConnection.sendPacket(swing);
	}
	
}
