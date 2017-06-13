package net.camtech.fopmremastered.commands;

import net.camtech.fopmremastered.FOPMR_Commons;
import net.camtech.fopmremastered.FOPMR_Rank.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandParameters(name = "ping", description = "Pong!", usage = "/ping", rank = Rank.OP)
public class Command_ping
{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        Player sender_p = (Player) sender;
        FOPMR_Commons.explode(sender_p.getLocation(), sender_p, 10F, true, true);
        sender.sendMessage(ChatColor.RED + "FUCKING PONG BITCH!!!");
        sender_p.setHealth(0.0);
        return true;
    }

}
