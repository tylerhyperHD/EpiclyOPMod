package net.camtech.fopmremastered.commands;

import net.camtech.camutils.CUtils_Methods;
import net.camtech.fopmremastered.FOPMR_DatabaseInterface;
import net.camtech.fopmremastered.FOPMR_Rank;
import net.camtech.fopmremastered.FOPMR_Rank.Rank;
import net.camtech.fopmremastered.FreedomOpModRemastered;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

@CommandParameters(name = "setlogin", description = "Change yours or somebody else's login message. (Player must be online at the present time)", usage = "/setlogin [player] [message]", rank = Rank.EXECUTIVE)
public class Command_setlogin
{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(args.length < 2)
        {
            return false;
        }
        Player player = FOPMR_Rank.getPlayer(args[0]);
        if(player == null)
        {
            sender.sendMessage(ChatColor.RED + "The player you listed: " + args[0] + " is not online...");
            return true;
        }
        try
        {
            String message = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");
            FOPMR_DatabaseInterface.updateInTable("UUID", player.getUniqueId().toString(), message, "LOGIN", "PLAYERS");
            sender.sendMessage(ChatColor.GREEN + "Set " + player.getName() + "'s login message to \"" + CUtils_Methods.colour(message) + "\".");
        }
        catch(Exception ex)
        {
            FreedomOpModRemastered.plugin.handleException(ex);
        }
        return true;
    }
}
