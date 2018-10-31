package net.camtech.fopmremastered;

import org.bukkit.entity.Player;

public class FOPMR_ConfigurationUtility {

	public static String getMyUuid(Player player) {
		return player.getUniqueId().toString();
	}

	public static String getAddress(Player player) {
		return player.getAddress().getHostString();
	}
}
