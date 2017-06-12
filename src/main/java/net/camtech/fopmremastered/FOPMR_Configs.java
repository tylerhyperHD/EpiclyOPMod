package net.camtech.fopmremastered;


public class FOPMR_Configs
{

    private final FOPMR_Config admins;
    private final FOPMR_Config commands;
    private final FOPMR_Config bans;
    private final FOPMR_Config mainconfig;
    private final FOPMR_Config reports;
    private final FOPMR_Config announcements;
    private final FOPMR_Config chats;
    private final FOPMR_Config areas;
    private final FOPMR_Config worlds;

    public FOPMR_Configs()
    {
        admins = new FOPMR_Config(FreedomOpModRemastered.plugin, "players.yml");
        admins.saveDefaultConfig();
        commands = new FOPMR_Config(FreedomOpModRemastered.plugin, "commands.yml");
        commands.saveDefaultConfig();
        bans = new FOPMR_Config(FreedomOpModRemastered.plugin, "bans.yml");
        bans.saveDefaultConfig();
        mainconfig = new FOPMR_Config(FreedomOpModRemastered.plugin, "config.yml");
        mainconfig.saveDefaultConfig();
        reports = new FOPMR_Config(FreedomOpModRemastered.plugin, "reports.yml");
        reports.saveDefaultConfig();
        announcements = new FOPMR_Config(FreedomOpModRemastered.plugin, "announcements.yml");
        announcements.saveDefaultConfig();
        chats = new FOPMR_Config(FreedomOpModRemastered.plugin, "chats.yml");
        chats.saveDefaultConfig();
        areas = new FOPMR_Config(FreedomOpModRemastered.plugin, "areas.yml");
        areas.saveDefaultConfig();
        worlds = new FOPMR_Config(FreedomOpModRemastered.plugin, "worlds.yml");
        worlds.saveDefaultConfig();
    }
    
    public void reloadConfigs()
    {
        admins.reloadConfig();
        commands.reloadConfig();
        bans.reloadConfig();
        mainconfig.reloadConfig();
        reports.reloadConfig();
        announcements.reloadConfig();
        chats.reloadConfig();
        areas.reloadConfig();
        worlds.reloadConfig();
    }

    public FOPMR_Config getAdmins()
    {
        return admins;
    }

    public FOPMR_Config getCommands()
    {
        return commands;
    }

    public FOPMR_Config getBans()
    {
        return bans;
    }

    public FOPMR_Config getMainConfig()
    {
        return mainconfig;
    }
    
    public FOPMR_Config getReports()
    {
        return reports;
    }
    
    public FOPMR_Config getAnnouncements()
    {
        return announcements;
    }
    
    public FOPMR_Config getChats()
    {
        return chats;
    }
    
    public FOPMR_Config getAreas()
    {
        return areas;
    }
    
    public FOPMR_Config getWorlds()
    {
        return worlds;
    }
}
