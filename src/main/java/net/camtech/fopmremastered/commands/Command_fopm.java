package net.camtech.fopmremastered.commands;

import net.camtech.camutils.CUtils_Methods;
import net.camtech.fopmremastered.FOPMR_Rank;
import net.camtech.fopmremastered.FreedomOpModRemastered;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandParameters(name = "fopm", usage = "/fopm <reload>", description = "Check info about the plugin or reload the configuration files.")
public class Command_fopm {

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (args.length == 1) {
			if (args[0].equalsIgnoreCase("reload")) {
				if (!FOPMR_Rank.isAdmin(sender)) {
					sender.sendMessage("Only admins can reload the FOPM: Remastered configuration files.");
					return true;
				}
				FreedomOpModRemastered.configs.getAdmins().reloadConfig();
				FreedomOpModRemastered.configs.getBans().reloadConfig();
				FreedomOpModRemastered.configs.getCommands().reloadConfig();
				FreedomOpModRemastered.configs.getMainConfig().reloadConfig();
				FreedomOpModRemastered.configs.getReports().reloadConfig();
				FreedomOpModRemastered.configs.getAnnouncements().reloadConfig();
				sender.sendMessage(ChatColor.GREEN + "EpiclyOPMod Configs reloaded!");
				FOPMR_CommandRegistry.unregisterCommands();
				FOPMR_CommandRegistry.registerCommands();
				sender.sendMessage(ChatColor.GREEN + "EpiclyOPMod Commands reloaded!");
				return true;
			}
			return false;
		} else {
			sender.sendMessage(ChatColor.GREEN + "This is the FreedomOpMod Remastered!");
			sender.sendMessage(CUtils_Methods.randomChatColour() + "an all new form of All-Op management.");
			sender.sendMessage(CUtils_Methods
					.colour("&-Created in the likes of the TFM but with more " + CUtils_Methods.randomChatColour()
							+ "flexibility&- by " + CUtils_Methods.randomChatColour() + "Camzie99&-!"));
		}
		return true;
	}

}
