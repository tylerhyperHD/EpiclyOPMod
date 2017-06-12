package net.camtech.fopmremastered.commands;

import net.camtech.fopmremastered.FOPMR_Commons;
import net.camtech.fopmremastered.FOPMR_Rank;
import net.camtech.fopmremastered.worlds.FOPMR_WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandParameters(name = "adminworld", description = "Teleport to and from adminworld!", usage = "/adminworld <[guest] [add | remove] [player]>")
public class Command_adminworld
{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            sender.sendMessage(ChatColor.RED + "You must be ingame to use this command.");
            return true;
        }
        if (args.length == 3)
        {
            if (!FOPMR_Rank.isAdmin(sender))
            {
                sender.sendMessage(ChatColor.RED + "Only admins can manage the guest list.");
                return true;
            }
            Player player = FOPMR_Rank.getPlayer(args[2]);
            if (player == null)
            {
                sender.sendMessage(ChatColor.RED + "That player cannot be found!");
                return true;
            }
            if (args[1].equalsIgnoreCase("add"))
            {
                FOPMR_Commons.guests.put(player.getName(), sender.getName());
                FOPMR_Commons.adminAction(sender.getName(), "Adding " + player.getName() + " to the adminworld guest list!", false);
                return true;
            }
            if (args[1].equalsIgnoreCase("remove"))
            {
                if (!FOPMR_Commons.guests.containsKey(player.getName()))
                {
                    sender.sendMessage(ChatColor.RED + "That player is not an adminworld guest.");
                    return true;
                }
                FOPMR_Commons.guests.remove(player.getName());
                FOPMR_Commons.adminAction(sender.getName(), "Removing " + player.getName() + " from the adminworld guest list!", true);
                return true;
            }
            return false;
        }
        else if (args.length != 0)
        {
            return false;
        }
        if (((Player) sender).getWorld() == FOPMR_WorldManager.getAdminWorld())
        {
            ((Player) sender).teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            sender.sendMessage(ChatColor.GREEN + "Welcome to the main world.");
            return true;
        }
        try
        {
            ((Player) sender).teleport(FOPMR_WorldManager.getAdminWorld().getSpawnLocation());
            sender.sendMessage(ChatColor.GREEN + "Welcome to adminworld.");
            return true;
        }
        catch (Exception ex)
        {
            sender.sendMessage("The adminworld cannot be found. Please contact Camzie99.");
        }
        return true;
    }
}
