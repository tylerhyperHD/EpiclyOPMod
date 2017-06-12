package net.camtech.fopmremastered.commands;

import net.camtech.fopmremastered.FOPMR_Configs;
import net.camtech.fopmremastered.FOPMR_Rank.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandParameters(name = "commandblocklist", description = "View all currently blocked commands.", usage = "/commandblocklist", rank = Rank.SYSTEM)
public class Command_commandblocklist
{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        sender.sendMessage(ChatColor.RED + "The following commands are blocked:");
        for (String command : FOPMR_Configs.getCommands().getConfig().getKeys(false))
        {
            sender.sendMessage(ChatColor.RED + " - " + command + " (Locked to clearance level " + FOPMR_Configs.getCommands().getConfig().getInt(command + ".rank") + ")");
        }
        return true;
    }
}
