package net.camtech.fopmremastered.commands;

import net.camtech.fopmremastered.FOPMR_Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandParameters(name = "o", usage = "/o", description = "Depreciation listener", rank = FOPMR_Rank.Rank.ADMIN)
public class Command_o
{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        sender.sendMessage(ChatColor.RED + "For regular admin chat, use '/cl 1' to toggle admin chat.");
        if (FOPMR_Rank.isSuper(sender))
        {
            sender.sendMessage(ChatColor.RED + "If you are a super admin or higher rank, use '/cl 2' to toggle super admin chat.");
        }
        else if (FOPMR_Rank.isSenior(sender))
        {
            sender.sendMessage(ChatColor.RED + "If you are a senior admin or higher rank, use '/cl 3' to toggle senior admin chat.");
        }
        return true;
    }
}
