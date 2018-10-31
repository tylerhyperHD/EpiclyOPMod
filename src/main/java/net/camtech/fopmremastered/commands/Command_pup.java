package net.camtech.fopmremastered.commands;

import net.camtech.fopmremastered.FOPMR_Rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Command_pup extends FOPMR_Command {

	public Command_pup() {
		super("pup", "/pup", "For dlg666999.", Rank.ADMIN);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!sender.getName().equals("dlg666999")) {
			sender.sendMessage(ChatColor.RED + "You do not have permission to use this command.");
			return true;
		}
		Player player = (Player) sender;
		Bukkit.broadcastMessage(ChatColor.RED + "dlg666999 is a little doggie");
		Bukkit.broadcastMessage(ChatColor.RED + "THAT IS ON A RAMPAGE!");
		player.getWorld().strikeLightning(player.getLocation());
		player.getWorld().strikeLightning(player.getLocation());
		player.setVelocity(new Vector(0, 10, 0));
		// ItemStack bone = new ItemStack(Material.BONE, 1);
		// ItemMeta bonemeta = bone.getItemMeta();

		return true;
	}
}
