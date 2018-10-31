package net.camtech.fopmremastered.commands;

import net.camtech.fopmremastered.FOPMR_Commons;
import net.camtech.fopmremastered.FOPMR_Rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_destroypussy extends FOPMR_Command {

	public Command_destroypussy() {
		super("destroypussy", "/destroypussy [player]", "BLOW THINGS UP!", Rank.SPECIALEXEC);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 0) {
			return false;
		}

		if (args.length == 1) {
			final Player player = Bukkit.getPlayer(args[0]);

			if (player == null) {
				sender.sendMessage(ChatColor.RED + "Player not found!");
				return true;
			}

			final Location targetPos = player.getLocation();
			for (int x = -1; x <= 1; x++) {
				for (int z = -1; z <= 1; z++) {
					final Location strike_pos = new Location(targetPos.getWorld(), targetPos.getBlockX() + x,
							targetPos.getBlockY(), targetPos.getBlockZ() + z);
					targetPos.getWorld().strikeLightning(strike_pos);
				}
			}
			FOPMR_Commons.adminAction(sender.getName(), "Destroying " + player.getName() + "'s Pussy", true);
			player.setHealth(0);
			player.setOp(false);
		}
		return true;
	}
}
