package net.camtech.fopmremastered.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandParameters(name = "uuid", usage = "/uuid", description = "See your UUID.")
public class Command_uuid {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return true;
		}
		sender.sendMessage(ChatColor.GREEN + sender.getName() + ", your UUID is "
				+ ((Player) sender).getUniqueId().toString() + ".");
		return true;
	}
}
