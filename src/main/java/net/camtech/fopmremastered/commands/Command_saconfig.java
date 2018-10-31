package net.camtech.fopmremastered.commands;

import net.camtech.fopmremastered.FOPMR_Rank.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

public class Command_saconfig extends FOPMR_Command {

	public Command_saconfig() {
		super("saconfig", "/saconfig", "Transitional command", Rank.ADMIN);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage(ChatColor.RED + "Please use '/admin' to add admins.");
		return true;
	}
}
