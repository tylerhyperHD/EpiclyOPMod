package net.camtech.fopmremastered.commands;

import net.camtech.fopmremastered.FOPMR_Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_feg extends FOPMR_Command {

	public Command_feg() {
		super("feg", "/feg", "Ur a feg.", FOPMR_Rank.Rank.ADMIN);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		int times = 0;

		sender.sendMessage(ChatColor.RED + sender.getName() + ", ur a feg.");

		Player sender_p = (Player) sender;

		while (times < 10) {
			sender_p.getWorld().strikeLightning(sender_p.getLocation());
			times++;
		}
		return true;
	}
}
