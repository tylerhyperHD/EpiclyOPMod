package net.camtech.fopmremastered;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;

@SuppressWarnings("deprecation")
public class FOPMR_DeprecationAggregator {

	public static OfflinePlayer getOfflinePlayer(String name) {
		return Bukkit.getOfflinePlayer(name);
	}
}
