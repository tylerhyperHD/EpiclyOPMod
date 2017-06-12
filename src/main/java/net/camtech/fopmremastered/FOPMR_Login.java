package net.camtech.fopmremastered;

import com.connorlinfoot.titleapi.TitleAPI;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import net.camtech.camutils.CUtils_Config;
import net.camtech.camutils.CUtils_Methods;
import net.camtech.fopmremastered.FOPMR_Rank.Rank;
import static net.camtech.fopmremastered.listeners.FOPMR_PlayerListener.MAX_XY_COORD;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.scheduler.BukkitRunnable;

public class FOPMR_Login
{

    public static final List<String> permbannedNames = Arrays.asList(
            "Jellow_",
            "KingSquads",
            "ButterWarrior146",
            "FGL_Karma",
            "Mahl_Trollbait",
            "TheRealTrioligy"
    );
    public static final List<String> permbannedIps = Arrays.asList(
            "70.162.*.*",
            "66.168.*.*",
            "86.100.*.*",
            "120.29.*.*",
            "50.35.*.*"
    );
    public static final List<String> permbannedUuids = Arrays.asList(
            "42711b44-eccc-3115-8a17-4447f5a4febd",
            "31dcd227-9c62-4b6a-98b5-47ee6761ef74",
            "69a38028-1573-494e-b2fb-dca156233649",
            "18d313cb-c5cc-43c7-8838-8a32cce9c987",
            "1033096e-3c1e-494d-bc6b-84d2d5f64c82",
            "e16a08a9-3c66-4979-bb91-387152d30233"
    );

    public static final List<String> minechatIps = Arrays.asList(
            "167.114.97.16"
    );

    private static final String IP_PATTERN = "^([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])\\.([01]?\\d\\d?|2[0-4]\\d|25[0-5])$";

    public static void handleJoinEvent(PlayerJoinEvent event)
    {
        CUtils_Config adminConfig = FOPMR_Configs.getAdmins();
        FileConfiguration config = adminConfig.getConfig();
        FileConfiguration configs = FOPMR_Configs.getMainConfig().getConfig();
        final Player player = event.getPlayer();
        String getme = FOPMR_PlayerUtility.getAddress(player);
        FOPMR_Rank.Rank level = FOPMR_PlayerUtility.getMyLevel();
        if (Bukkit.getPluginManager().getPlugin("TitleAPI") != null)
        {
            TitleAPI.sendTitle(player, 20, 40, 20, CUtils_Methods.colour("&-Hi there " + CUtils_Methods.randomChatColour() + player.getName() + "&-!"), CUtils_Methods.colour("&-Welcome to " + CUtils_Methods.randomChatColour() + FreedomOpModRemastered.plugin.getConfig().getString("general.name") + "&-!"));
            TitleAPI.sendTabTitle(player, CUtils_Methods.colour("&-Welcome to EpicFreedom " + CUtils_Methods.randomChatColour() + player.getName() + "&-!"), CUtils_Methods.colour("&-Running the " + CUtils_Methods.randomChatColour() + "FreedomOpMod: Remastered &-by Camzie99!"));
        }
        if (Math.abs(player.getLocation().getX()) >= MAX_XY_COORD || Math.abs(player.getLocation().getZ()) >= MAX_XY_COORD)
        {
            player.teleport(player.getWorld().getSpawnLocation());
        }
        for (String UUID : config.getKeys(false))
        {
            if (((config.getString(UUID + ".lastName").equals(player.getName()))
                    && !(config.getString(UUID + ".lastIp").equals(player.getAddress().getHostString())))
                    && (!FOPMR_Rank.getRank(player).equals(FOPMR_Rank.Rank.OP) || FOPMR_Rank.isMasterBuilder(player)))

            {
                FOPMR_Commons.imposters.add(player.getName());
                config.set(UUID + ".imposter", true);
            }
        }
        if (config.contains(player.getUniqueId().toString()))
        {
            if (!(config.getString(player.getUniqueId().toString()) + ".lastName").equals(player.getName()))
            {
                config.set(player.getUniqueId().toString() + ".lastName", player.getName());
            }
            if (!(config.getString(player.getUniqueId().toString()) + ".lastIp").equals(player.getAddress().getHostString()) && FOPMR_Rank.getRank(player) == FOPMR_Rank.Rank.OP)
            {
                config.set(player.getUniqueId().toString() + ".lastIp", player.getAddress().getHostString());
            }
            if (player.getName().equals("tylerhyperHD"))
            {
                if (FOPMR_Rank.isImposter(player))
                {
                    event.setJoinMessage(ChatColor.AQUA + "" + "Tyler Hyper" + " is an Imposter");
                }
                else
                {
                    event.setJoinMessage(ChatColor.AQUA + "" + "Tyler Hyper" + " is the " + ChatColor.BLUE + "EOM-Creator");
                }
            }
            else if (!"default".equals(config.getString(player.getUniqueId().toString() + ".login")))
            {
                event.setJoinMessage(ChatColor.AQUA + player.getName() + " " + CUtils_Methods.colour(config.getString(player.getUniqueId().toString() + ".login")));
            }
            else if (FOPMR_Rank.getRank(player) != FOPMR_Rank.Rank.OP)
            {
                event.setJoinMessage(ChatColor.AQUA + player.getName() + " is " + CUtils_Methods.aOrAn(FOPMR_Rank.getRank(player).name) + " " + FOPMR_Rank.getRank(player).name);
            }
            if (FOPMR_Rank.getRank(player) == Rank.IMPOSTER)
            {
                Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " is an imposter!");
                player.sendMessage(ChatColor.RED + "Please verify you are who you are logged in as or you will be banned!");
            }
        }
        else
        {
            player.sendMessage(ChatColor.GREEN + "Hey there! Welcome to " + FOPMR_Configs.getMainConfig().getConfig().getString("general.name") + "!");
            config.set(player.getUniqueId().toString() + ".lastName", player.getName());
            config.set(player.getUniqueId().toString() + ".lastIp", player.getAddress().getHostString());
            config.set(player.getUniqueId().toString() + ".chat", "");
            config.set(player.getUniqueId().toString() + ".rank", "Op");
            config.set(player.getUniqueId().toString() + ".login", "default");
            config.set(player.getUniqueId().toString() + ".votes", 0);
            config.set(player.getUniqueId().toString() + ".imposter", false);
            config.set(player.getUniqueId().toString() + ".chatLevel", 0);
            if (player.getName().equals("tylerhyperHD"))
            {
                config.set(player.getUniqueId().toString() + ".displayName", "Tyler Hyper");
            }
            else
            {
                config.set(player.getUniqueId().toString() + ".displayName", FOPMR_PlayerUtility.getName(player));
            }
            config.set(player.getUniqueId().toString() + ".tag", "default");
            config.set(player.getUniqueId().toString() + ".builder", false);
            config.set(player.getUniqueId().toString() + ".banHammer", false);
            config.set(player.getUniqueId().toString() + ".cmdblock", false);
            config.set(player.getUniqueId().toString() + ".djump", false);
            config.set(player.getUniqueId().toString() + ".muted", false);
            config.set(player.getUniqueId().toString() + ".randomChatColour", false);
            config.set(player.getUniqueId().toString() + ".chatColours", false);
            config.set(player.getUniqueId().toString() + ".lastLogin", System.currentTimeMillis());
        }
        config.set(player.getUniqueId().toString() + ".chatColours", true);
        config.set(player.getUniqueId().toString() + ".randomChatColour", true);
        adminConfig.saveConfig();

        if (FreedomOpModRemastered.configs.getMainConfig().getConfig().getInt("general.accessLevel") > 0)
        {
            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    player.sendMessage(ChatColor.RED + "Server is currently locked down to clearance level " + FreedomOpModRemastered.configs.getMainConfig().getConfig().getInt("general.accessLevel") + " (" + FOPMR_Rank.getFromLevel(FreedomOpModRemastered.configs.getMainConfig().getConfig().getInt("general.accessLevel")).name + ").");
                }
            }.runTaskLater(FreedomOpModRemastered.plugin, 20L * 5L);
        }
        player.sendMessage(CUtils_Methods.colour(FreedomOpModRemastered.configs.getMainConfig().getConfig().getString("general.joinMessage").replaceAll("%player%", player.getName())));
        config.set(player.getUniqueId().toString() + ".lastLogin", System.currentTimeMillis());
        FOPMR_PermissionsManager.removeMoreProtectPermissions(player);
        if (!FOPMR_Rank.isSystem(player))
        {
            FOPMR_PermissionsManager.removePermission(player, "icu.control");
            FOPMR_PermissionsManager.removePermission(player, "icu.stop");
        }
        if (!player.getName().equals("tylerhyperHD"))
        {
            FOPMR_PermissionsManager.removePermission(player, "icu.exempt");
        }
        if (!FOPMR_Rank.isAdmin(player))
        {
            FOPMR_PermissionsManager.removePermission(player, "worldedit.limit.unrestricted");
            FOPMR_PermissionsManager.removePermission(player, "worldedit.anyblock");
            FOPMR_PermissionsManager.removePermission(player, "worldedit.history.clear");
            FOPMR_PermissionsManager.removePermission(player, "worldedit.snapshot.restore");
            FOPMR_PermissionsManager.removePermission(player, "worldedit.limit");
        }
        FOPMR_Rank.colourTabName(player);
        FOPMR_BoardManager.updateStats(player);
        FOPMR_Log.info(FOPMR_PlayerUtility.getName(player) + "'s join events ran successfully.");
    }

    public static void handleLogin(PlayerLoginEvent event)
    {
        UUID uuid;
        Player player = event.getPlayer();
        String username = player.getName();
        FileConfiguration mainconfig = FOPMR_Configs.getMainConfig().getConfig();
        FileConfiguration adminconfig = FOPMR_Configs.getAdmins().getConfig();
        String ip = event.getAddress().getHostAddress().trim();
        String pId = FOPMR_ConfigurationUtility.getMyUuid(player);
        String uuidhardcodebyebye = "Your UUID has been hardcoded to a permban list. You may not come back to " + FOPMR_Configs.getMainConfig().getConfig().getString("general.name") + " ever again.";
        String iphardcodebyebye = "Your ip has been hardcoded to a permban list. You may not come back to " + FOPMR_Configs.getMainConfig().getConfig().getString("general.name") + " ever again.";
        String hardcodebyebye = "Your username has been hardcoded to a permban list. You may not come back to " + FOPMR_Configs.getMainConfig().getConfig().getString("general.name") + " ever again.";

        for (Player player2 : Bukkit.getOnlinePlayers())
        {
            if ((player.getName() == null ? player2.getName() == null : player.getName().equals(player2.getName())) && FOPMR_Rank.isAdmin(player2))
            {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "An admin is already logged in with that name.");
                event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            }
        }
        boolean hasNonAlpha = player.getName().matches("^.*[^a-zA-Z0-9_].*$");
        if (hasNonAlpha)
        {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Your name contains invalid characters, please login using a fully alphanumeric name.");
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
        }
        if (FOPMR_Rank.getRank(player).level < mainconfig.getInt("general.accessLevel"))
        {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "The server is currently locked down to clearance level " + mainconfig.getInt("general.accessLevel") + ".");
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            return;
        }
        if (FOPMR_Rank.isAdmin(player) && !adminconfig.getBoolean(pId + ".imposter") && (adminconfig.getString(pId + ".lastIp").equals(event.getAddress().getHostAddress())))
        {
            event.allow();
            return;
        }

        if (FOPMR_Bans.isBanned(FOPMR_PlayerUtility.getName(player), event.getAddress().getHostAddress()))
        {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, FOPMR_Bans.getReason(FOPMR_PlayerUtility.getName(player)) + "(You may appeal the ban at our forums accessible from " + mainconfig.getString("general.url") + ")");
            event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
        }

        // Removes automatic trolling
        if (FOPMR_PlayerUtility.getName(player).equalsIgnoreCase("ZexyZek"))
        {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "You may not troll on this server. Use a different name and do not troll.");
            return;
        }
        if (FOPMR_PlayerUtility.getName(player).equalsIgnoreCase("null"))
        {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "You may not login with a username of null. Come back with a different one.");
            return;
        }
        // Removes venom from ever coming back to the server
        if (FOPMR_PlayerUtility.getName(player).equalsIgnoreCase("Venom_nV"))
        {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "Venom_nV, go away and don't ever come back to this server.");
            return;
        }

        if (player.getUniqueId().equals("9ca58c67-f77b-45c7-984f-9bf6ca8a8941"))
        {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "Venom_nV, go away and don't ever come back to this server.");
            return;
        }

        for (String testPlayer : FOPMR_Login.permbannedNames)
        {
            if (testPlayer.equalsIgnoreCase(username))
            {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + uuidhardcodebyebye);
                return;
            }
        }

        // UUID ban
        if (player.getUniqueId().equals(permbannedUuids.toString()))
        {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + hardcodebyebye);
            return;
        }

        // Get rid of people using minechat
        for (String testIp : FOPMR_Login.minechatIps)
        {
            if (FOPMR_Login.fuzzyIpMatch(testIp, ip, 4))
            {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + "Minechat is not allowed on this server.\nPlease login using Minecraft for PC/Mac/Linux.");
                return;
            }
        }

        for (String testIp : FOPMR_Login.permbannedIps)
        {
            if (FOPMR_Login.fuzzyIpMatch(testIp, ip, 4))
            {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, ChatColor.RED + iphardcodebyebye);
                return;
            }
        }

        player.setOp(true);
        player.setGameMode(GameMode.CREATIVE);
    }

    public static boolean fuzzyIpMatch(String ipA, String ipB, int octets)
    {
        boolean match = true;

        String[] ippartsA = ipA.split("\\.");
        String[] ipPartsB = ipB.split("\\.");

        if (ippartsA.length != 4 || ipPartsB.length != 4)
        {
            return false;
        }

        if (octets > 4)
        {
            octets = 4;
        }
        else if (octets < 1)
        {
            octets = 1;
        }

        for (int i = 0; i < octets && i < 4; i++)
        {
            if (ippartsA[i].equals("*") || ipPartsB[i].equals("*"))
            {
                continue;
            }

            if (!ippartsA[i].equals(ipPartsB[i]))
            {
                match = false;
                break;
            }
        }

        return match;
    }

    public static int getPort(Player player)
    {
        return player.getAddress().getPort();
    }

    public static String getIp(Player player)
    {
        return player.getAddress().getAddress().getHostAddress().trim();
    }

    public static String getIp(PlayerLoginEvent event)
    {
        return event.getAddress().getHostAddress().trim();
    }
}
