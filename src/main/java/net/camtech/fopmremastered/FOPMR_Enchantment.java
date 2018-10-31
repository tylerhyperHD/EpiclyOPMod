/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.camtech.fopmremastered;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

/**
 *
 * @author tyler
 */
public class FOPMR_Enchantment {

	public static ItemStack enchantAll(ItemStack type) {
		for (Enchantment ench : Enchantment.values()) {
			if (ench.equals(Enchantment.LOOT_BONUS_MOBS) || ench.equals(Enchantment.LOOT_BONUS_BLOCKS)) {
				continue;
			}
			type.addUnsafeEnchantment(ench, 32767);
		}
		return type;
	}
}
