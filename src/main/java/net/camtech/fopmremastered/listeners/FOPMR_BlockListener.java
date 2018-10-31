package net.camtech.fopmremastered.listeners;

import com.sk89q.worldedit.IncompleteRegionException;
import com.sk89q.worldedit.bukkit.WorldEditPlugin;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldedit.world.World;

import net.camtech.fopmremastered.FOPMR_Rank;
import net.camtech.fopmremastered.FreedomOpModRemastered;
import net.camtech.fopmremastered.protectedareas.FOPMR_ProtectedArea;
import net.camtech.fopmremastered.protectedareas.FOPMR_ProtectedAreas;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;

public class FOPMR_BlockListener implements Listener {

	public FOPMR_BlockListener() {
		Bukkit.getPluginManager().registerEvents(this, FreedomOpModRemastered.plugin);
	}

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		Player player = event.getPlayer();
		for (FOPMR_ProtectedArea area : FOPMR_ProtectedAreas.getFromConfig()) {
			if (area.isInRange(event.getBlock().getLocation())) {
				if (!area.canAccess(player)) {
					player.sendMessage(
							ChatColor.RED + "You do not have permission to break blocks in this area! Please see "
									+ area.getOwner() + " if you wish to gain access.");
					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onBlockPlace(BlockPlaceEvent event) {
		Player player = event.getPlayer();
		if (event.getBlock().getType() == Material.COMMAND_BLOCK && !FOPMR_Rank.isAdmin(player)) {
			player.sendMessage(ChatColor.RED + "Only admins can use command blocks.");
			event.setCancelled(true);
		}
		for (FOPMR_ProtectedArea area : FOPMR_ProtectedAreas.getFromConfig()) {
			if (area.isInRange(event.getBlock().getLocation())) {
				if (!area.canAccess(player)) {
					player.sendMessage(
							ChatColor.RED + "You do not have permission to place blocks in this area! Please see "
									+ area.getOwner() + " if you wish to gain access.");
					event.setCancelled(true);
				}
			}
		}
	}

	@EventHandler
	public void onCommandPreprocess(PlayerCommandPreprocessEvent event) throws IncompleteRegionException {
		WorldEditPlugin plugin = (WorldEditPlugin) Bukkit.getPluginManager().getPlugin("WorldEdit");
		final Player player = event.getPlayer();
		final World world = plugin.getSession(event.getPlayer()).getSelectionWorld();
		if (event.getMessage().replaceAll("/", "").split(" ")[0].equalsIgnoreCase("set")
				&& event.getMessage().replaceAll("/", "").split(" ")[0].equalsIgnoreCase("replace")) {
			Region selection = plugin.getSession(event.getPlayer()).getSelection(world);
			FOPMR_ProtectedAreas.getFromConfig().stream().forEach((area) -> {
				Location location = new Location(event.getPlayer().getWorld(), selection.getMinimumPoint().getBlockX(),
						selection.getMinimumPoint().getBlockY(), selection.getMinimumPoint().getBlockZ());
				if (plugin.getSession(event.getPlayer()).getRegionSelector(world).isDefined()) {
					for (int x = 0; x < selection.getWidth(); x++) {
						for (int y = 0; y < selection.getHeight(); y++) {
							for (int z = 0; z < selection.getLength(); z++) {
								if (area.isInRange(location.add(x, y, z))) {
									event.setCancelled(true);
								}
							}
						}
					}
				}
			});
		}
		if (event.getMessage().replaceAll("/", "").split(" ")[0].equalsIgnoreCase("limit")) {
			if (!FOPMR_Rank.isAdmin(player)) {
				player.sendMessage(
						ChatColor.RED + "Only admins can changes their WorldEdit limit. Ask an admin to change it.");
				event.setCancelled(true);
			} else {
				event.setCancelled(false);
			}
		}
	}
}
