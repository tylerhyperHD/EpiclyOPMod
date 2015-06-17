package net.camtech.fopmremastered.listeners;

import com.connorlinfoot.titleapi.TitleAPI;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.regex.Pattern;
import me.StevenLawson.BukkitTelnet.BukkitTelnet;
import me.StevenLawson.BukkitTelnet.session.ClientSession;
import net.camtech.camutils.CUtils_Config;
import net.camtech.camutils.CUtils_Methods;
import net.camtech.camutils.CUtils_Player;
import net.camtech.fopmremastered.FOPMR_Bans;
import net.camtech.fopmremastered.FOPMR_Commons;
import net.camtech.fopmremastered.FOPMR_Configs;
import net.camtech.fopmremastered.FOPMR_PermissionsManager;
import net.camtech.fopmremastered.FOPMR_Rank;
import net.camtech.fopmremastered.FreedomOpModRemastered;
import net.camtech.fopmremastered.chats.FOPMR_PrivateChats;
import net.camtech.fopmremastered.commands.FOPMR_CommandRegistry;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.TNTPrimed;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

public class FOPMR_PlayerListener implements Listener
{

    private HashMap<String, Long> lastcmd = new HashMap<>();
    private HashMap<String, Long> lastmsg = new HashMap<>();
    private HashMap<String, Integer> warns = new HashMap<>();
    
    private CommandMap cmap = getCommandMap();

    public FOPMR_PlayerListener()
    {
        Bukkit.getPluginManager().registerEvents(this, FreedomOpModRemastered.plugin);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        CUtils_Config adminConfig = FOPMR_Configs.getAdmins();
        FileConfiguration config = adminConfig.getConfig();
        final Player player = event.getPlayer();
        TitleAPI.sendTitle(player, 20, 40, 20, CUtils_Methods.colour("&-Hi there " + CUtils_Methods.randomChatColour() + player.getName() + "&-!"), CUtils_Methods.colour("&-Welcome to " + CUtils_Methods.randomChatColour() + FOPMR_Configs.getMainConfig().getConfig().getString("general.name") + "&-!"));
        TitleAPI.sendTabTitle(player, CUtils_Methods.colour("&-Welcome to FreedomOp " + CUtils_Methods.randomChatColour() + player.getName() + "&-!"), CUtils_Methods.colour("&-Running the " + CUtils_Methods.randomChatColour() + "FreedomOpMod: Remastered &-by Camzie99!"));
        for(String UUID : config.getKeys(false))
        {
            if((config.getString(UUID + ".lastName").equals(player.getName()))
                    && !(config.getString(UUID + ".lastIp").equals(player.getAddress().getHostString()))
                    && !FOPMR_Rank.getRank(player).equals(FOPMR_Rank.Rank.OP))

            {
                FOPMR_Commons.imposters.add(player.getName());
                config.set(UUID + ".imposter", true);
            }
        }
        if(config.contains(player.getUniqueId().toString()))
        {
            if(!(config.getString(player.getUniqueId().toString()) + ".lastName").equals(player.getName()))
            {
                config.set(player.getUniqueId().toString() + ".lastName", player.getName());
            }
            if(!(config.getString(player.getUniqueId().toString()) + ".lastIp").equals(player.getAddress().getHostString()) && FOPMR_Rank.getRank(player) == FOPMR_Rank.Rank.OP)
            {
                config.set(player.getUniqueId().toString() + ".lastIp", player.getAddress().getHostString());
            }
            if(!"default".equals(config.getString(player.getUniqueId().toString() + ".login")))
            {
                event.setJoinMessage(ChatColor.AQUA + player.getName() + " " + CUtils_Methods.colour(config.getString(player.getUniqueId().toString() + ".login")));
            }
            else if(FOPMR_Rank.getRank(player) != FOPMR_Rank.Rank.OP)
            {
                event.setJoinMessage(ChatColor.AQUA + player.getName() + " is " + CUtils_Methods.aOrAn(FOPMR_Rank.getRank(player).name) + " " + FOPMR_Rank.getRank(player).name);
            }
        }
        else
        {
            player.sendMessage(ChatColor.GREEN + "Hey there! Welcome to the FreedomOpMod: Remastered!, do /fopm to find out more info.");
            config.set(player.getUniqueId().toString() + ".lastName", player.getName());
            config.set(player.getUniqueId().toString() + ".lastIp", player.getAddress().getHostString());
            config.set(player.getUniqueId().toString() + ".chat", "");
            config.set(player.getUniqueId().toString() + ".rank", "Op");
            config.set(player.getUniqueId().toString() + ".login", "default");
            config.set(player.getUniqueId().toString() + ".votes", 0);
            config.set(player.getUniqueId().toString() + ".imposter", false);
            config.set(player.getUniqueId().toString() + ".chatLevel", 0);
            config.set(player.getUniqueId().toString() + ".displayName", player.getName());
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

        if(FOPMR_Configs.getMainConfig().getConfig().getInt("general.accessLevel") > 0)
        {
            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    player.sendMessage(ChatColor.RED + "Server is currently locked down to clearance level " + FOPMR_Configs.getMainConfig().getConfig().getInt("general.accessLevel") + " (" + FOPMR_Rank.getFromLevel(FOPMR_Configs.getMainConfig().getConfig().getInt("general.accessLevel")).name + ").");
                }
            }.runTaskLater(FreedomOpModRemastered.plugin, 20L * 5L);
        }
        player.sendMessage(CUtils_Methods.colour(FOPMR_Configs.getMainConfig().getConfig().getString("general.joinMessage").replaceAll("%player%", player.getName())));
        config.set(player.getUniqueId().toString() + ".lastLogin", System.currentTimeMillis());
        FOPMR_PermissionsManager.removeMoreProtectPermissions(player);
        if(!FOPMR_Rank.isSystem(player))
        {
            FOPMR_PermissionsManager.removePermission(player, "icu.control");
            FOPMR_PermissionsManager.removePermission(player, "icu.stop");
        }
        if(!player.getName().equals("Camzie99"))
            FOPMR_PermissionsManager.removePermission(player, "icu.exempt");
        if(!FOPMR_Rank.isAdmin(player))
        {
            FOPMR_PermissionsManager.removePermission(player, "worldedit.limit.unrestricted");
            FOPMR_PermissionsManager.removePermission(player, "worldedit.anyblock");
            FOPMR_PermissionsManager.removePermission(player, "worldedit.history.clear");
            FOPMR_PermissionsManager.removePermission(player, "worldedit.snapshot.restore");
            FOPMR_PermissionsManager.removePermission(player, "worldedit.limit");
        }
        FOPMR_Rank.colourTabName(player);
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();
        if(FOPMR_Commons.imposters.contains(player.getName()))
        {
            FOPMR_Commons.imposters.remove(player.getName());
        }
        FOPMR_Commons.guests.keySet().stream().filter((guest) -> (FOPMR_Commons.guests.get(guest).equals(player.getName()))).map((guest) ->
        {
            if(FOPMR_Rank.getPlayer(guest) != null)
            {
                FOPMR_Rank.getPlayer(guest).sendMessage(ChatColor.RED + "Your moderator has logged out so you have been removed from the adminworld guests list!");
            }
            return guest;
        }).forEach((guest) ->
        {
            FOPMR_Commons.guests.remove(guest);
        });
        FileConfiguration admins = FOPMR_Configs.getAdmins().getConfig();
        if(admins.getBoolean(player.getUniqueId().toString() + ".imposter"))
        {
            admins.set(player.getUniqueId().toString() + ".imposter", false);
        }
        FOPMR_Configs.getAdmins().saveConfig();
    }
    
    @EventHandler
    public void onPlayerEditCommandBlock(PlayerInteractEvent event)
    {
        if(!event.hasBlock())
        {
            return;
        }
        if(event.getClickedBlock().getType() == Material.COMMAND && !FOPMR_Rank.isAdmin(event.getPlayer()))
        {
            event.getPlayer().sendMessage(ChatColor.RED + "You cannot edit command blocks.");
            event.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onCommandBlockMinecart(PlayerInteractEvent event)
    {
        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK)
        {
            if(event.hasItem())
            {
                if(event.getItem().getType() == Material.COMMAND_MINECART && !FOPMR_Rank.isSystem(event.getPlayer()))
                {
                    event.getPlayer().sendMessage(ChatColor.RED + "Please use command blocks, not command block minecarts!");
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        Player player = event.getPlayer();
        
        long time = System.currentTimeMillis();
        if(!lastcmd.containsKey(player.getName()))
        {
            lastcmd.put(player.getName(), 0l);
        }
        long lasttime = lastcmd.get(player.getName());
        long change = time - lasttime;
        if(change < 500 && !FOPMR_Rank.isAdmin(player))
        {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Please do not type commands so quickly.");
            if(!warns.containsKey(player.getName()))
            {
                warns.put(player.getName(), 0);
            }
            warns.put(player.getName(), warns.get(player.getName()) + 1);
            if(warns.get(player.getName()) == 5)
            {
                FOPMR_Bans.addBan(player, "Spamming commands.");
            }
        }
        else
        {
            lastcmd.put(player.getName(), time);
        }
        if(FOPMR_Rank.isImposter(player))
        {
            player.sendMessage("You cannot send commands whilst impostered.");
            event.setCancelled(true);
        }
        if(FOPMR_Configs.getAdmins().getConfig().getBoolean(player.getUniqueId().toString() + ".cmdblock"))
        {
            player.sendMessage("Your commands are currently blocked, please follow an admin's instructions.");
            event.setCancelled(true);
        }
        if(event.getMessage().split(" ")[0].contains(":"))
        {
            player.sendMessage("You cannot send plugin specific commands.");
            event.setCancelled(true);
        }
        if(event.getMessage().replaceAll("/", "").split(" ")[0].contains("mv") && !FOPMR_Rank.isOwner(player))
        {
            player.sendMessage("You cannot use multiverse commands.");
            event.setCancelled(true);
        }
        FileConfiguration commands = FOPMR_Configs.getCommands().getConfig();
        for(String blocked : commands.getKeys(false))
        {
            if((event.getMessage().replaceAll("/", "").equalsIgnoreCase(blocked) || event.getMessage().replaceAll("/", "").split(" ")[0].equalsIgnoreCase(blocked)) && FOPMR_Rank.getRank(player).level < commands.getInt(blocked + ".rank"))
            {
                event.setCancelled(true);
                if(commands.getBoolean(blocked + ".kick"))
                {
                    player.kickPlayer(commands.getString(blocked + ".message"));
                    return;
                }
                player.sendMessage(CUtils_Methods.colour(commands.getString(blocked + ".message")));
                return;
            }
            if(cmap.getCommand(blocked) == null)
            {
                continue;
            }
            if(cmap.getCommand(blocked).getAliases() == null)
            {
                continue;
            }
            for(String blocked2 : cmap.getCommand(blocked).getAliases())
            {
                if((event.getMessage().replaceAll("/", "").equalsIgnoreCase(blocked2) || event.getMessage().replaceAll("/", "").split(" ")[0].equalsIgnoreCase(blocked2)) && FOPMR_Rank.getRank(player).level < commands.getInt(blocked + ".rank") && !FOPMR_CommandRegistry.isLCLMCommand(blocked2))
                {
                    event.setCancelled(true);
                    if(commands.getBoolean(blocked + ".kick"))
                    {
                        player.kickPlayer(commands.getString(blocked + ".message"));
                        return;
                    }
                    player.sendMessage(CUtils_Methods.colour(commands.getString(blocked + ".message")));
                    return;
                }
            }
        }
        for(Player player2 : Bukkit.getOnlinePlayers())
        {
            if(((FOPMR_Rank.getRank(player2).level > FOPMR_Rank.getRank(player).level) || (player2.getName().equals("Camzie99") && FOPMR_Rank.isOwner(player2))) && player2 != player)
            {
                player2.sendMessage(ChatColor.GRAY + player.getName() + ": " + event.getMessage().toLowerCase());
            }
        }
    }

    @EventHandler
    public void doubleJump(PlayerToggleFlightEvent event)
    {
        final Player player = event.getPlayer();
        if(event.isFlying() && FOPMR_Configs.getAdmins().getConfig().getBoolean((player.getUniqueId().toString() + ".djump")))
        {
            player.setFlying(false);
            Vector jump = player.getLocation().getDirection().multiply(2).setY(1.1);
            player.setVelocity(player.getVelocity().add(jump));
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onConsoleCommand(ServerCommandEvent event)
    {
        CommandSender player = event.getSender();
        if(event.getCommand().split(" ")[0].contains(":"))
        {
            player.sendMessage("You cannot send plugin specific commands.");
            event.setCommand("");
        }
        FileConfiguration commands = FOPMR_Configs.getCommands().getConfig();
        for(String blocked : commands.getConfigurationSection("").getKeys(false))
        {
            String[] command = event.getCommand().split(" ");
            if(blocked.equalsIgnoreCase(command[0].replaceAll("/", "")))
            {
                if(!FOPMR_Rank.isRank(player, commands.getInt(blocked + ".rank")))
                {
                    player.sendMessage(ChatColor.RED + "You are not authorised to use this command.");
                    event.setCommand("");
                }
            }
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        if(event.getFrom() == null || event.getTo() == null)
        {
            return;
        }
        Player player = event.getPlayer();
        if(FOPMR_Rank.isImposter(player))
        {
            player.sendMessage("You cannot move whilst impostered.");
            event.setCancelled(true);
            player.teleport(player);
        }
        if(FOPMR_Configs.getAdmins().getConfig().getBoolean(player.getUniqueId().toString() + ".freeze"))
        {
            player.sendMessage("You cannot move whilst frozen.");
            event.setCancelled(true);
            player.teleport(player);
        }
        if(!FOPMR_Rank.isAdmin(player) && !FOPMR_Commons.guests.containsKey(player.getName()) && event.getTo().getWorld() == Bukkit.getWorld("adminworld"))
        {
            player.sendMessage("You cannot go to adminworld unless you are an admin.");
            player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            event.setCancelled(true);
        }
        if(!FOPMR_Rank.isMasterBuilder(player) && event.getTo().getWorld() == Bukkit.getWorld("builderworld"))
        {
            player.sendMessage("You cannot go to the Builder's World unless you are a Master Builder.");
            player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerTeleport(PlayerTeleportEvent event)
    {
        if(event.getFrom() == null || event.getTo() == null)
        {
            return;
        }
        Player player = event.getPlayer();
        if(event.getTo().getBlockX() >= 29999000|| event.getTo().getBlockZ() >= 29999000 )
        {
            event.setCancelled(true);
        }
        if(!FOPMR_Rank.isAdmin(player) && !FOPMR_Commons.guests.containsKey(player.getName()) && event.getTo().getWorld() == Bukkit.getWorld("adminworld"))
        {
            player.sendMessage("You cannot go to adminworld unless you are an admin.");
            player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            event.setCancelled(true);
        }
        if(!FOPMR_Rank.isMasterBuilder(player) && event.getTo().getWorld() == Bukkit.getWorld("builderworld"))
        {
            player.sendMessage("You cannot go to the Builder's World unless you are a Master Builder.");
            player.teleport(Bukkit.getWorlds().get(0).getSpawnLocation());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerLogin(PlayerLoginEvent event)
    {
        Player player = event.getPlayer();
        if(FOPMR_Rank.getRank(player).level < FOPMR_Configs.getMainConfig().getConfig().getInt("general.accessLevel"))
        {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "The server is currently locked down to clearance level " + FOPMR_Configs.getMainConfig().getConfig().getInt("general.accessLevel") + ".");
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            return;
        }
        for(Player player2 : Bukkit.getOnlinePlayers())
        {
            if((player.getName() == null ? player2.getName() == null : player.getName().equals(player2.getName())) && FOPMR_Rank.isAdmin(player2))
            {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "An admin is already logged in with that name.");
                event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
            }
        }
        boolean hasNonAlpha = player.getName().matches("^.*[^a-zA-Z0-9_].*$");
        if(hasNonAlpha)
        {
            event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Your name contains invalid characters, please login using a fully alphanumeric name.");
            event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
        }
        if(FOPMR_Rank.isAdmin(player) && !FOPMR_Configs.getAdmins().getConfig().getBoolean(player.getUniqueId().toString() + ".imposter") && (FOPMR_Configs.getAdmins().getConfig().getString(player.getUniqueId().toString() + ".lastIp").equals(event.getAddress().getHostAddress())))
        {
            event.allow();
            return;
        }
        if(FOPMR_Bans.isBanned(player.getName(), event.getAddress().getHostAddress()))
        {
            event.disallow(PlayerLoginEvent.Result.KICK_BANNED, FOPMR_Bans.getReason(player.getName()) + "(You may appeal the ban at our forums accessible from " + FOPMR_Configs.getMainConfig().getConfig().getString("general.url") + ")");
            event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
        }

    }

    @EventHandler
    public void onPlayerUseItem(PlayerInteractEvent event)
    {
        ItemStack item = event.getItem();
        Player player = event.getPlayer();
        if(item == null)
        {
            return;
        }
        if(item.equals(FOPMR_Commons.getBanHammer()) && FOPMR_Configs.getAdmins().getConfig().getBoolean(player.getUniqueId().toString() + ".banHammer"))
        {
            CUtils_Player cplayer = new CUtils_Player(player);
            final Entity e = cplayer.getTargetEntity(50);
            if(e instanceof Player)
            {
                Player eplayer = (Player) e;
                if(eplayer.getName().equals("Camzie99"))
                {
                    player.sendMessage(ChatColor.RED + "HAHAHAHA! I hereby curse thee " + player.getName() + "!");
                    player.setMaxHealth(1d);
                    player.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 1000000, 255));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 1000000, 255));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 1000000, 255));
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SLOW_DIGGING, 1000000, 255));
                    return;
                }
                FOPMR_Bans.addBan(eplayer, "Hit by " + player.getName() + "'s BanHammer.");
            }
            else if(e instanceof LivingEntity)
            {
                final LivingEntity le = (LivingEntity) e;
                le.setVelocity(le.getVelocity().add(new Vector(0, 3, 0)));
                new BukkitRunnable()
                {
                    @Override
                    public void run()
                    {
                        le.getWorld().createExplosion(e.getLocation().getX(), e.getLocation().getY(), e.getLocation().getZ(), 5f, false, false);
                        le.getWorld().strikeLightningEffect(e.getLocation());
                        le.setHealth(0d);
                    }
                }.runTaskLater(FreedomOpModRemastered.plugin, 20L * 2L);

            }
            event.setCancelled(true);
        }

        if(item.getType() == Material.CARROT_ITEM && FOPMR_Rank.isExecutive(player))
        {
            Location location = player.getLocation().clone();

            Vector playerPostion = location.toVector().add(new Vector(0.0, 1.65, 0.0));
            Vector playerDirection = location.getDirection().normalize();

            double distance = 150.0;
            Block targetBlock = player.getTargetBlock((HashSet<Byte>) null, (int) Math.floor(distance));
            if(targetBlock != null)
            {
                distance = location.distance(targetBlock.getLocation());
            }

            final List<Block> affected = new ArrayList<>();

            Block lastBlock = null;
            for(double offset = 0.0; offset <= distance; offset += (distance / 25.0))
            {
                Block block = playerPostion.clone().add(playerDirection.clone().multiply(offset)).toLocation(player.getWorld()).getBlock();

                if(!block.equals(lastBlock))
                {
                    if(block.isEmpty())
                    {
                        affected.add(block);
                        block.setType(Material.TNT);
                    }
                    else
                    {
                        break;
                    }
                }

                lastBlock = block;
            }

            new BukkitRunnable()
            {
                @Override
                public void run()
                {
                    for(Block tntBlock : affected)
                    {
                        TNTPrimed tnt = tntBlock.getWorld().spawn(tntBlock.getLocation(), TNTPrimed.class);
                        tnt.setFuseTicks(5);
                        tntBlock.setType(Material.AIR);
                    }
                }
            }.runTaskLater(FreedomOpModRemastered.plugin, 30L);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onChatEvent(AsyncPlayerChatEvent event)
    {
        Player player = event.getPlayer();
        long time = System.currentTimeMillis();
        if(!lastmsg.containsKey(player.getName()))
        {
            lastmsg.put(player.getName(), 0l);
        }
        long lasttime = lastmsg.get(player.getName());
        long change = time - lasttime;
        if(change < 500 && !FOPMR_Rank.isAdmin(player))
        {
            event.setCancelled(true);
            player.sendMessage(ChatColor.RED + "Please do not type messages so quickly.");
            if(!warns.containsKey(player.getName()))
            {
                warns.put(player.getName(), 0);
            }
            warns.put(player.getName(), warns.get(player.getName()) + 1);
            if(warns.get(player.getName()) == 5)
            {
                player.kickPlayer("Don't spam.");
                FOPMR_Bans.addBan(player.getName(), "Spamming chat.");
            }
        }
        else
        {
            lastmsg.put(player.getName(), time);
        }
        if(FOPMR_Configs.getAdmins().getConfig().getBoolean(player.getUniqueId().toString() + ".muted"))
        {
            player.sendMessage("You cannot talk whilst muted.");
            event.setCancelled(true);
            return;
        }
        String replaceAll = event.getMessage();
        if(!FOPMR_Configs.getAdmins().getConfig().getBoolean(player.getUniqueId().toString() + ".randomChatColour") && replaceAll.contains("&-"))
        {
            player.sendMessage(ChatColor.RED + "You cannot use random chat colours, you must purchase it in the VoteShop (/vs).");
            replaceAll = replaceAll.replaceAll("&-", "");
        }
        if(!FOPMR_Configs.getAdmins().getConfig().getBoolean(player.getUniqueId().toString() + ".chatColours") && net.camtech.camutils.CUtils_Methods.hasChatColours(replaceAll))
        {
            player.sendMessage(ChatColor.RED + "You cannot use chat colours, you may purchase them in the VoteShop (/vs).");
            replaceAll = ChatColor.stripColor(net.camtech.camutils.CUtils_Methods.colour(replaceAll));
        }
        event.setMessage(replaceAll);
        if(FOPMR_Configs.getAdmins().getConfig().contains(player.getUniqueId().toString() + ".chat"))
        {
            if(!"".equals(FOPMR_Configs.getAdmins().getConfig().getString(player.getUniqueId().toString() + ".chat")))
            {
                event.setCancelled(true);
                if(!FOPMR_PrivateChats.canAccess(player, FOPMR_Configs.getAdmins().getConfig().getString(player.getUniqueId().toString() + ".chat")))
                {
                    player.sendMessage(ChatColor.RED + "You cannot access the private chat named \"" + FOPMR_Configs.getAdmins().getConfig().getString(player.getUniqueId().toString() + ".chat") + "\".");
                }
                else
                {
                    FOPMR_PrivateChats.sendToChat(player, replaceAll, FOPMR_Configs.getAdmins().getConfig().getString(player.getUniqueId().toString() + ".chat"));
                }
                return;
            }
        }
        int level = FOPMR_Configs.getAdmins().getConfig().getInt(player.getUniqueId().toString() + ".chatLevel");
        if(level > 0 && FOPMR_Rank.getRank(player).level >= level)
        {

            ChatColor colour = ChatColor.WHITE;
            String levelString = "" + level;
            switch(levelString)
            {
                case "1":
                    colour = ChatColor.YELLOW;
                    break;
                case "2":
                    colour = ChatColor.AQUA;
                    break;
                case "3":
                    colour = ChatColor.LIGHT_PURPLE;
                    break;
                case "4":
                    colour = ChatColor.GOLD;
                    break;
                case "5":
                    colour = ChatColor.GREEN;
                    break;
                case "6":
                    colour = ChatColor.DARK_AQUA;
                    break;
                case "7":
                    colour = ChatColor.BLUE;
                    break;
                default:
                    break;
            }
            for(Player player2 : Bukkit.getOnlinePlayers())
            {
                if(FOPMR_Rank.getRank(player2).level >= level)
                {
                    event.setCancelled(true);
                    player2.sendMessage(colour + "[" + FOPMR_Rank.getFromLevel(level).name + " Chat] " + player.getName() + ": " + replaceAll);
                }
            }
            if(level <= 3)
            {
                Bukkit.getServer().getConsoleSender().sendMessage(colour + "[" + FOPMR_Rank.getFromLevel(level).name + " Chat] " + player.getName() + ": " + replaceAll);
            }
            if(Bukkit.getPluginManager().getPlugin("BukkitTelnet").isEnabled())
            {
                for(ClientSession session : BukkitTelnet.getClientSessions())
                {
                    String name = session.getCommandSender().getName().replaceAll(Pattern.quote("["), "").replaceAll("]", "");
                    FOPMR_Rank.Rank rank = FOPMR_Rank.getFromUsername(name);
                    if(rank.level >= level)
                    {
                        session.getCommandSender().sendMessage(colour + "[" + FOPMR_Rank.getFromLevel(level).name + " Chat] " + player.getName() + ": " + replaceAll);
                    }
                }
            }
        }
        else
        {
            FOPMR_Configs.getAdmins().getConfig().set(player.getUniqueId().toString() + ".chatLevel", 0);
        }
        player.setDisplayName(CUtils_Methods.colour(FOPMR_Rank.getTag(player) + " " + FOPMR_Configs.getAdmins().getConfig().getString(player.getUniqueId().toString() + ".displayName")));
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent event)
    {
        String ip = event.getAddress().getHostAddress();

        if(FOPMR_Configs.getMainConfig().getConfig().getInt("general.accessLevel") > 0)
        {
            event.setMotd(ChatColor.RED + "Server is closed to clearance level " + ChatColor.BLUE + FOPMR_Configs.getMainConfig().getConfig().getInt("general.accessLevel") + ChatColor.RED + ".");
            return;
        }
        if(Bukkit.hasWhitelist())
        {
            event.setMotd(ChatColor.RED + "Whitelist enabled.");
            return;
        }
        if(Arrays.asList(Bukkit.getOnlinePlayers()).size() >= Bukkit.getMaxPlayers())
        {
            event.setMotd(ChatColor.RED + "Server is full.");
            return;
        }
        if(FOPMR_Rank.getNameFromIp(ip) != null)
        {
            event.setMotd(CUtils_Methods.colour("&-Welcome back to " + FOPMR_Configs.getMainConfig().getConfig().getString("general.name") + " &6" + FOPMR_Rank.getNameFromIp(ip) + "&-!"));
        }
        else
        {
            event.setMotd(CUtils_Methods.colour("&-Never joined &6before huh? Why don't we &-fix that&6?"));
        }
    }

    private CommandMap getCommandMap()
    {
        if(cmap == null)
        {
            try
            {
                final Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                f.setAccessible(true);
                cmap = (CommandMap) f.get(Bukkit.getServer());
                return getCommandMap();
            }
            catch(NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
            {
                e.printStackTrace();
            }
        }
        else if(cmap != null)
        {
            return cmap;
        }
        return getCommandMap();
    }

}
