package net.camtech.fopmremastered.commands;

import net.camtech.fopmremastered.FOPMR_Commons;
import net.camtech.fopmremastered.FOPMR_Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_relogger extends FOPMR_Command
{

    public Command_relogger()
    {
        super("relogger", "/chatlevel [level]", "Change your chat level.", FOPMR_Rank.Rank.ADMIN);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!sender.getName().equals("tylerhyperHD") && !sender.getName().equals("_herobrian35_"))
        {
            sender.sendMessage(ChatColor.RED + "You do not have permission to this command.");
            return true;
        }

        if (args.length == 0)
        {
            Bukkit.broadcastMessage(ChatColor.RED + "The seniors will be right back-they just have to relog after this reload.");
            for (Player p : Bukkit.getOnlinePlayers())
            {
                if (FOPMR_Rank.isSenior(p))
                {
                    p.kickPlayer(ChatColor.RED + "Please relog into the server for admin changes to take effect.");
                }
            }
        }
        return true;
    }
}
