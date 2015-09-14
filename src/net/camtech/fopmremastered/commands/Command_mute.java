package net.camtech.fopmremastered.commands;

import net.camtech.fopmremastered.FOPMR_DatabaseInterface;
import net.camtech.fopmremastered.FOPMR_Rank;
import net.camtech.fopmremastered.FOPMR_Rank.Rank;
import net.camtech.fopmremastered.FreedomOpModRemastered;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class Command_mute extends FOPMR_Command
{
    public Command_mute()
    {
        super("mute", "/mute [player]", "Mute a player.", Rank.ADMIN);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (args.length != 1)
        {
            return false;
        }
        Player player = FOPMR_Rank.getPlayer(args[0]);
        if (player == null)
        {
            sender.sendMessage("Player is not online.");
            return true;
        }
        if (FOPMR_Rank.isEqualOrHigher(FOPMR_Rank.getRank(player), FOPMR_Rank.getRank(sender)))
        {
            sender.sendMessage("You can only mute someone of a lower rank than yourself.");
            return true;
        }
        Bukkit.broadcastMessage(ChatColor.AQUA + sender.getName() + " toggling mute for " + player.getName());
        try
        {
            FOPMR_DatabaseInterface.updateInTable("UUID", player.getUniqueId().toString(), !(FOPMR_DatabaseInterface.getBooleanFromTable("UUID", player.getUniqueId().toString(), "MUTE", "PLAYERS")), "MUTE", "PLAYERS");
        }
        catch(Exception ex)
        {
            FreedomOpModRemastered.plugin.handleException(ex);
        }
        return true;
    }

}
