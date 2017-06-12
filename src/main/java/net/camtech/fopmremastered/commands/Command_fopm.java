package net.camtech.fopmremastered.commands;

import net.camtech.camutils.CUtils_Methods;
import net.camtech.fopmremastered.FOPMR_DatabaseInterface;
import net.camtech.fopmremastered.FOPMR_Rank;
import net.camtech.fopmremastered.FreedomOpModRemastered;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandParameters(name = "fopm", usage = "/fopm <reload>", description = "Check info about the plugin or reload the configuration file.")
public class Command_fopm
{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {

        if (args.length > 0)
        {
            if (args[0].equalsIgnoreCase("reload"))
            {
                if (!FOPMR_Rank.isAdmin(sender))
                {
                    sender.sendMessage(ChatColor.RED + "Only admins can reload the FOPM: R config.");
                    return true;
                }
                FreedomOpModRemastered.plugin.reloadConfig();
                FOPMR_DatabaseInterface.closeConnection(FOPMR_DatabaseInterface.getConnection());
            }
            return true;
        }
        sender.sendMessage(ChatColor.GREEN + "This is the FreedomOpMod Remastered!");
        sender.sendMessage(CUtils_Methods.randomChatColour() + "an all new form of All-Op management.");
        sender.sendMessage(CUtils_Methods.colour("&-Created in the likes of the TFM but with more " + CUtils_Methods.randomChatColour() + "flexibility&- by " + CUtils_Methods.randomChatColour() + "Camzie99&-!"));

        return true;
    }

}
