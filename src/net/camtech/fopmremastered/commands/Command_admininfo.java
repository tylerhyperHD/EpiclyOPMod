package net.camtech.fopmremastered.commands;

import net.camtech.camutils.CUtils_Methods;
import net.camtech.fopmremastered.FOPMR_Configs;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandParameters(name="admininfo", description="Find how to become admin.", usage="/admininfo",aliases="ai")
public class Command_admininfo
{
    public boolean onCommand(CommandSender sender, Command cmd, String label, String args[])
    {
        if(!FOPMR_Configs.getMainConfig().getConfig().getBoolean("toggles.apps"))
        {
            sender.sendMessage(CUtils_Methods.randomChatColour() + "Unfortunately, applications are currently closed. Please speak with " + FOPMR_Configs.getMainConfig().getConfig().getString("general.adminmanager") + " for more info.");
            return true;
        }
        sender.sendMessage(CUtils_Methods.randomChatColour() + "Interested in becoming an admin?");
        sender.sendMessage(CUtils_Methods.randomChatColour() + "Then apply here: " + FOPMR_Configs.getMainConfig().getConfig().getString("general.admininfo"));
        return true;
    }
}
