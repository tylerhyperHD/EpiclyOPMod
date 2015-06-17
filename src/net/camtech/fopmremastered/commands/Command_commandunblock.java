package net.camtech.fopmremastered.commands;

import net.camtech.fopmremastered.FOPMR_Configs;
import net.camtech.fopmremastered.FOPMR_Rank.Rank;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandParameters(name="commandunblock", usage="/commandunblock [command]", description="Unblock a command.", rank=Rank.SYSTEM)
public class Command_commandunblock
{
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if(args.length != 1)
        {
            return false;
        }
        if(!FOPMR_Configs.getCommands().getConfig().contains(args[0].toLowerCase()))
        {
            return false;
        }
        FOPMR_Configs.getCommands().getConfig().set(args[0].toLowerCase(), null);
        FOPMR_Configs.getCommands().saveConfig();
        return true;
    }
}
