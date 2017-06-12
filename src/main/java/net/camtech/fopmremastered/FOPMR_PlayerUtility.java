// Created by tylerhyperHD in order to shorten the amount of code
package net.camtech.fopmremastered;

import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class FOPMR_PlayerUtility
{

    public static String getAddress(Player player)
    {
        return player.getAddress().getHostString();
    }

    public static FOPMR_Rank.Rank getMyLevel()
    {
        FileConfiguration configs = FOPMR_Configs.getMainConfig().getConfig();
        return FOPMR_Rank.getFromLevel(configs.getInt("general.accessLevel"));
    }

    public static int getMainLevel()
    {
        FileConfiguration configs = FOPMR_Configs.getMainConfig().getConfig();
        return FOPMR_Configs.getMainConfig().getConfig().getInt("general.accessLevel");
    }

    public static String getName(Player player)
    {
        return player.getName();
    }

    public static String getSenderName(CommandSender sender)
    {
        return sender.getName();
    }
}
