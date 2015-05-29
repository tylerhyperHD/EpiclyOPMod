package net.camtech.fopmremastered.commands;

import net.camtech.fopmremastered.FOPMR_Commons;
import net.camtech.fopmremastered.FOPMR_Configs;
import net.camtech.fopmremastered.FOPMR_Rank.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandParameters(name="speedpads", description="Change SpeedPad settings.", usage="/speedpads [[on] | [off]] | [[strength] [double]]", rank=Rank.ADMIN, aliases="speedpad")
public class Command_speedpads
{
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(args.length == 0)
        {
            return false;
        }
        if(args.length == 1)
        {
            if(args[0].equalsIgnoreCase("on"))
            {
                FOPMR_Configs.getMainConfig().getConfig().set("jumppads.speed", true);
                FOPMR_Configs.getMainConfig().saveConfig();
                FOPMR_Commons.adminAction(sender.getName(), "Enabling speedpads!", false);
                return true;
            }
            if(args[0].equalsIgnoreCase("off"))
            {
                FOPMR_Configs.getMainConfig().getConfig().set("jumppads.speed", false);
                FOPMR_Configs.getMainConfig().saveConfig();
                FOPMR_Commons.adminAction(sender.getName(), "Disabling speedpads!", true);
                return true;
            }
        }
        if(args.length == 2)
        {
            if(args[0].equalsIgnoreCase("strength"))
            {
                double strength;
                try
                {
                    strength = Double.parseDouble(args[1]);
                }
                catch(Exception ex)
                {
                    return false;
                }
                if(strength > 10)
                {
                    sender.sendMessage(ChatColor.RED + "The value must be a double below or equal to 10.");
                    return true;
                }
                FOPMR_Configs.getMainConfig().getConfig().set("jumppads.speedstrength", strength);
                FOPMR_Configs.getMainConfig().saveConfig();
                FOPMR_Commons.adminAction(sender.getName(), "Seting speedpad strength to " + strength + "!", false);
                return true;
            }
        }
        return false;
    }
}
