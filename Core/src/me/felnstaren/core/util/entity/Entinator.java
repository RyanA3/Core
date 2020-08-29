package me.felnstaren.core.util.entity;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.LivingEntity;

public class Entinator {

	public static void heal(LivingEntity entity, double hp) {
		if(entity.getHealth() + hp > entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue()) entity.setHealth(entity.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
		else entity.setHealth(entity.getHealth() + hp);
	}
	
}
