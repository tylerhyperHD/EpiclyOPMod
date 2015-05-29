package net.camtech.fopmremastered;

import net.camtech.camutils.CUtils_Config;

public class FOPMR_Configs
{

    private static CUtils_Config admins;
    private static CUtils_Config commands;
    private static CUtils_Config bans;
    private static CUtils_Config mainconfig;
    private static CUtils_Config reports;
    private static CUtils_Config announcements;
    private static CUtils_Config chats;
    private static CUtils_Config areas;

    public FOPMR_Configs()
    {
        admins = new CUtils_Config(FreedomOpModRemastered.plugin, "players.yml");
        admins.saveDefaultConfig();
        commands = new CUtils_Config(FreedomOpModRemastered.plugin, "commands.yml");
        commands.saveDefaultConfig();
        bans = new CUtils_Config(FreedomOpModRemastered.plugin, "bans.yml");
        bans.saveDefaultConfig();
        mainconfig = new CUtils_Config(FreedomOpModRemastered.plugin, "config.yml");
        mainconfig.saveDefaultConfig();
        reports = new CUtils_Config(FreedomOpModRemastered.plugin, "reports.yml");
        reports.saveDefaultConfig();
        announcements = new CUtils_Config(FreedomOpModRemastered.plugin, "announcements.yml");
        announcements.saveDefaultConfig();
        chats = new CUtils_Config(FreedomOpModRemastered.plugin, "chats.yml");
        chats.saveDefaultConfig();
        areas = new CUtils_Config(FreedomOpModRemastered.plugin, "areas.yml");
        areas.saveDefaultConfig();
    }

    public static CUtils_Config getAdmins()
    {
        return admins;
    }

    public static CUtils_Config getCommands()
    {
        return commands;
    }

    public static CUtils_Config getBans()
    {
        return bans;
    }

    public static CUtils_Config getMainConfig()
    {
        return mainconfig;
    }
    
    public static CUtils_Config getReports()
    {
        return reports;
    }
    
    public static CUtils_Config getAnnouncements()
    {
        return announcements;
    }
    
    public static CUtils_Config getChats()
    {
        return chats;
    }
    
    public static CUtils_Config getAreas()
    {
        return areas;
    }
}
