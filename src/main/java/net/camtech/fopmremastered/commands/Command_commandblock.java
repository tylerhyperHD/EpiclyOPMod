package net.camtech.fopmremastered.commands;

import net.camtech.fopmremastered.FOPMR_Configs;
import net.camtech.fopmremastered.FOPMR_Rank;
import static net.camtech.fopmremastered.FOPMR_Rank.Rank.SYSTEM;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandParameters(name = "commandblock", usage = "/commandblock [command] [rank] [true | false] [message]", description = "Block a command in-game! (Created entirely because I'm lazy.)", rank = SYSTEM)
public class Command_commandblock
{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (args.length < 4)
        {
            return false;
        }
        int rank = FOPMR_Rank.getFromName(args[1]).level;
        String message = StringUtils.join(ArrayUtils.subarray(args, 3, args.length), " ", 0, ArrayUtils.subarray(args, 3, args.length).length);
        FOPMR_Configs.getCommands().getConfig().set(args[0].toLowerCase() + ".rank", rank);
        FOPMR_Configs.getCommands().getConfig().set(args[0].toLowerCase() + ".kick", Boolean.parseBoolean(args[2]));
        FOPMR_Configs.getCommands().getConfig().set(args[0].toLowerCase() + ".message", message);
        FOPMR_Configs.getCommands().saveConfig();
        String not = " ";
        if (!Boolean.parseBoolean(args[2]))
        {
            not = "not ";
        }
        sender.sendMessage(ChatColor.RED + "Blocking command " + args[0].toLowerCase() + " to clearance level " + rank + " or above with the message \"" + message + "\". This command will " + not + "kick the user.");
        return true;
    }
}
