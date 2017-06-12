package net.camtech.fopmremastered.commands;

import net.camtech.camutils.CUtils_Methods;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandParameters(name = "fakevote", usage = "/fakevote", description = "View the links to vote.")
public class Command_fakevote
{

    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        ChatColor sameRandom = CUtils_Methods.randomChatColour();
        sender.sendMessage(sameRandom + "####################");
        sender.sendMessage(sameRandom + "#### Vote Links ####");
        sender.sendMessage(sameRandom + "####################");
        sender.sendMessage(CUtils_Methods.randomChatColour() + "");
        return true;
    }
}
