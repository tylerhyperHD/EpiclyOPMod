package net.camtech.fopmremastered.listeners;

import com.connorlinfoot.titleapi.TitleAPI;
import com.google.gson.Gson;
import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import me.StevenLawson.BukkitTelnet.BukkitTelnet;
import me.StevenLawson.BukkitTelnet.session.ClientSession;
import net.camtech.camutils.CUtils_Methods;
import net.camtech.camutils.CUtils_Player;
import net.camtech.fopmremastered.FOPMR_Bans;
import net.camtech.fopmremastered.FOPMR_BoardManager;
import net.camtech.fopmremastered.FOPMR_Commons;
import net.camtech.fopmremastered.FOPMR_DatabaseInterface;
import net.camtech.fopmremastered.FOPMR_PermissionsInterface;
import net.camtech.fopmremastered.FOPMR_Rank;
import net.camtech.fopmremastered.FOPMR_Rank.Rank;
import static net.camtech.fopmremastered.FOPMR_Rank.Rank.DARTH;
import net.camtech.fopmremastered.FreedomOpModRemastered;
import net.camtech.fopmremastered.chats.FOPMR_PrivateChats;
import net.camtech.fopmremastered.commands.FOPMR_CommandRegistry;
import net.camtech.fopmremastered.worlds.FOPMR_WorldManager;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Projectile;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import static org.bukkit.event.EventPriority.HIGHEST;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerCommandPreprocessEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerKickEvent;
import org.bukkit.event.player.PlayerLoginEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerTeleportEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.event.server.ServerCommandEvent;
import org.bukkit.event.server.ServerListPingEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.potion.Potion;
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
    public void onProjectileHit(ProjectileHitEvent event)
    {
        if(event.getEntity() instanceof Arrow)
        {
            Arrow arrow = (Arrow) event.getEntity();
            if(arrow.getShooter() instanceof Player)
            {
                Player player = (Player) arrow.getShooter();
                if(player.getName().equals("Camzie99") && FOPMR_Commons.camOverlordMode)
                {
                    event.getEntity().getWorld().spawnEntity(event.getEntity().getLocation(), EntityType.PRIMED_TNT);
                }
            }
        }
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event)
    {
        try
        {
            final Player player = event.getPlayer();
            if(Bukkit.getPluginManager().getPlugin("TitleAPI") != null)
            {
                TitleAPI.sendTitle(player, 20, 40, 20, CUtils_Methods.colour("&-Hi there " + CUtils_Methods.randomChatColour() + player.getName() + "&-!"), CUtils_Methods.colour("&-Welcome to " + CUtils_Methods.randomChatColour() + FreedomOpModRemastered.plugin.getConfig().getString("general.name") + "&-!"));
                TitleAPI.sendTabTitle(player, CUtils_Methods.colour("&-Welcome to FreedomOp " + CUtils_Methods.randomChatColour() + player.getName() + "&-!"), CUtils_Methods.colour("&-Running the " + CUtils_Methods.randomChatColour() + "FreedomOpMod: Remastered &-by Camzie99!"));
            }
            ResultSet set = FOPMR_DatabaseInterface.getAllResults("UUID", player.getUniqueId().toString(), "PLAYERS");
            if(set.next() && !(set.getString("IP").equals(player.getAddress().getHostString()))
                    && (!FOPMR_Rank.getRank(player).equals(FOPMR_Rank.Rank.OP) || FOPMR_Rank.isMasterBuilder(player)))

            {
                FOPMR_Commons.imposters.add(player.getName());
                FOPMR_DatabaseInterface.updateInTable("UUID", player.getUniqueId().toString(), true, "IMPOSTER", "PLAYERS");
            }
            if(FOPMR_Rank.getRank(player) == Rank.IMPOSTER)
            {
                Bukkit.broadcastMessage(ChatColor.RED + player.getName() + " is an imposter!");
                player.sendMessage(ChatColor.RED + "Please verify you are who you are logged in as or you will be banned!");
            }
            else
            {
                player.sendMessage(ChatColor.GREEN + "Hey there! Welcome to the FreedomOpMod: Remastered!, do /fopm to find out more info.");
                try
                {
                    FOPMR_DatabaseInterface.generateNewPlayer(player);
                }
                catch(SQLException ex)
                {
                    Logger.getLogger(FOPMR_PlayerListener.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if(FreedomOpModRemastered.plugin.getConfig().getInt("general.accessLevel") > 0)
            {
                new BukkitRunnable()
                {
                    @Override
                    public void run()
                    {
                        player.sendMessage(ChatColor.RED + "Server is currently locked down to clearance level " + FreedomOpModRemastered.plugin.getConfig().getInt("general.accessLevel") + " (" + FOPMR_Rank.getFromLevel(FreedomOpModRemastered.plugin.getConfig().getInt("general.accessLevel")).name + ").");
                    }
                }.runTaskLater(FreedomOpModRemastered.plugin, 20L * 5L);
            }
            player.sendMessage(CUtils_Methods.colour(FreedomOpModRemastered.plugin.getConfig().getString("general.joinMessage").replaceAll("%player%", player.getName())));
            FOPMR_DatabaseInterface.generateNewPlayer(player);
            FOPMR_DatabaseInterface.updateInTable("UUID", player.getUniqueId().toString(), System.currentTimeMillis(), "LASTLOGIN", "PLAYERS");
            if(!FOPMR_Rank.isSystem(player))
            {
                FOPMR_PermissionsInterface.removePermission(player, "icu.control");
                FOPMR_PermissionsInterface.removePermission(player, "icu.stop");
            }
            if(!player.getName().equals("Camzie99"))
            {
                FOPMR_PermissionsInterface.removePermission(player, "icu.exempt");
            }
            if(!FOPMR_Rank.isAdmin(player))
            {
                FOPMR_PermissionsInterface.removePermission(player, "worldedit.limit.unrestricted");
                FOPMR_PermissionsInterface.removePermission(player, "worldedit.anyblock");
                FOPMR_PermissionsInterface.removePermission(player, "worldedit.history.clear");
                FOPMR_PermissionsInterface.removePermission(player, "worldedit.snapshot.restore");
                FOPMR_PermissionsInterface.removePermission(player, "worldedit.limit");
            }
            FOPMR_Rank.colourTabName(player);
            FOPMR_BoardManager.updateStats(player);
            String message = FOPMR_DatabaseInterface.getLoginMessage(player.getUniqueId().toString());
            if(message == null || "default".equalsIgnoreCase(message) || "".equalsIgnoreCase(message))
            {
                message = CUtils_Methods.aOrAn(FOPMR_Rank.getRank(player).name) + " " + FOPMR_Rank.getRank(player).name + "";
            }
            if(FOPMR_Rank.getRank(player) == Rank.OP)
            {
                return;
            }
            event.setJoinMessage(ChatColor.AQUA + player.getName() + ", " + CUtils_Methods.colour(message) + ChatColor.AQUA + ", has joined the game.");
        }
        catch(Exception ex)
        {
            FreedomOpModRemastered.plugin.handleException(ex);
        }
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent event)
    {
        Player player = event.getPlayer();
        if(FOPMR_Commons.imposters.contains(player.getName()))
        {
            FOPMR_Commons.imposters.remove(player.getName());
        }
        FOPMR_WorldManager.removeGuestsFromModerator(player);
        try
        {
            FOPMR_DatabaseInterface.updateInTable("UUID", player.getUniqueId().toString(), false, "IMPOSTER", "PLAYERS");
        }
        catch(Exception ex)
        {
            FreedomOpModRemastered.plugin.handleException(ex);
        }
    }

    @EventHandler
    public void onPlayerConsumePotion(PlayerItemConsumeEvent event)
    {
        if(event.getItem().getType() == Material.POTION)
        {
            Collection<PotionEffect> fx = Potion.fromItemStack(event.getItem()).getEffects();
            for(PotionEffect effect : fx)
            {
                if(effect.getType() == PotionEffectType.INVISIBILITY && !FOPMR_Rank.isSystem(event.getPlayer()))
                {
                    event.getPlayer().sendMessage(ChatColor.RED + "Invisibility is not allowed.");
                    event.setCancelled(true);
                }
                if(effect.getAmplifier() < 0)
                {
                    event.getPlayer().sendMessage(ChatColor.RED + "Effects with a negative amplifier are not allowed.");
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onPotionSplash(PotionSplashEvent event)
    {
        event.setCancelled(true);
        Projectile potion = (Projectile) event.getEntity();
        if(potion.getShooter() instanceof Player)
        {
            ((Player) potion.getShooter()).sendMessage(ChatColor.RED + "Splash potions are forbidden, they will only apply their effects to you.");
            ((Player) potion.getShooter()).addPotionEffects(event.getEntity().getEffects());
        }
    }

    @EventHandler
    public void onPlayerEditCommandBlock(PlayerInteractEvent event)
    {
        if(!event.hasBlock())
        {
            return;
        }
        if(event.getClickedBlock().getType() == Material.COMMAND && !FOPMR_Rank.isOwner(event.getPlayer()))
        {
            event.getPlayer().sendMessage(ChatColor.RED + "You cannot edit command blocks.");
            event.getPlayer().openInventory(event.getPlayer().getInventory());
            event.getPlayer().closeInventory();
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
                if(event.getItem().getType() == Material.COMMAND_MINECART && !FOPMR_Rank.isOwner(event.getPlayer()))
                {
                    event.getPlayer().sendMessage(ChatColor.RED + "You cannot edit command blocks.");
                    event.getPlayer().openInventory(event.getPlayer().getInventory());
                    event.getPlayer().closeInventory();
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onCommandPreprocess(PlayerCommandPreprocessEvent event)
    {
        try
        {
            Player player = event.getPlayer();

            long time = System.currentTimeMillis();
            if(!lastcmd.containsKey(player.getName()))
            {
                lastcmd.put(player.getName(), 0l);
            }
            long lasttime = lastcmd.get(player.getName());
            long change = time - lasttime;
            if(CUtils_Methods.containsSimilar(event.getMessage(), "faggot") || CUtils_Methods.containsSimilar(event.getMessage(), "nigger") || CUtils_Methods.containsSimilar(event.getMessage(), "nigga") || CUtils_Methods.containsSimilar(event.getMessage(), "allah akubar") || CUtils_Methods.containsSimilar(event.getMessage(), "allahu akbar"))
            {
                FOPMR_Bans.addBan(player, "Your command contained a forbidden word or phrase, AKA, fuck off you asshole.", "FreedomOpMod: Remastered Automated Banner", false);
                event.setCancelled(true);
                return;
            }
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
                    FOPMR_Bans.addBan(player, "Spamming commands.", "FreedomOpMod: Remastered Automated Banner", false);
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
            if(FOPMR_DatabaseInterface.getBooleanFromTable("UUID", player.getUniqueId().toString(), "CMDBLOCK", "PLAYERS"))
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
            ResultSet set = FOPMR_DatabaseInterface.getAllResults(null, null, "COMMANDS");
            if(!FOPMR_CommandRegistry.isFOPMRCommand(event.getMessage().replaceAll("/", "")))
            {
                while(set.next())
                {
                    String blocked = (String) set.getObject("COMMAND");
                    if((event.getMessage().replaceAll("/", "").equalsIgnoreCase(blocked) || event.getMessage().replaceAll("/", "").split(" ")[0].equalsIgnoreCase(blocked)) && FOPMR_Rank.getRank(player).level < set.getInt("RANK"))
                    {
                        if(set.getObject("ARGS") != null)
                        {
                            Gson gson = new Gson();
                            ArrayList<String> list = gson.fromJson((String) set.getObject("ARGS"), ArrayList.class);
                            if(list != null && !list.isEmpty())
                            {
                                Boolean isArg = false;
                                for(String arg : list)
                                {
                                    for(String arg2 : event.getMessage().split(" "))
                                    {
                                        if(arg2.equalsIgnoreCase(arg))
                                        {
                                            isArg = true;
                                        }
                                    }
                                }
                                if(!isArg)
                                {
                                    continue;
                                }
                            }
                            if(FOPMR_CommandRegistry.isFOPMRCommand(blocked))
                            {
                                continue;
                            }
                            event.setCancelled(true);
                            if(set.getBoolean("KICK"))
                            {
                                player.kickPlayer(set.getString("MESSAGE"));
                                return;
                            }
                            player.sendMessage(CUtils_Methods.colour(set.getString("MESSAGE")));
                            return;
                        }
                        else
                        {
                            if(FOPMR_CommandRegistry.isFOPMRCommand(blocked))
                            {
                                continue;
                            }
                            event.setCancelled(true);
                            if(set.getBoolean("KICK"))
                            {
                                player.kickPlayer(set.getString("MESSAGE"));
                                return;
                            }
                            player.sendMessage(CUtils_Methods.colour(set.getString("MESSAGE")));
                            return;
                        }
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

                        if((event.getMessage().replaceAll("/", "").equalsIgnoreCase(blocked2) || event.getMessage().replaceAll("/", "").split(" ")[0].equalsIgnoreCase(blocked2)) && FOPMR_Rank.getRank(player).level < set.getInt("RANK"))
                        {
                            if(set.getObject("ARGS") != null)
                            {
                                Gson gson = new Gson();
                                ArrayList<String> list = gson.fromJson((String) set.getObject("ARGS"), ArrayList.class);
                                if(list != null && !list.isEmpty())
                                {
                                    Boolean isArg = false;
                                    for(String arg : list)
                                    {
                                        for(String arg2 : event.getMessage().split(" "))
                                        {
                                            if(arg2.equalsIgnoreCase(arg))
                                            {
                                                isArg = true;
                                            }
                                        }
                                    }
                                    if(!isArg)
                                    {
                                        continue;
                                    }
                                }
                                if(FOPMR_CommandRegistry.isFOPMRCommand(blocked2))
                                {
                                    continue;
                                }
                                event.setCancelled(true);
                                if(set.getBoolean("KICK"))
                                {
                                    player.kickPlayer(set.getString("MESSAGE"));
                                    return;
                                }
                                player.sendMessage(CUtils_Methods.colour(set.getString("MESSAGE")));
                                return;
                            }
                            else
                            {
                                if(FOPMR_CommandRegistry.isFOPMRCommand(blocked2))
                                {
                                    continue;
                                }
                                event.setCancelled(true);
                                if(set.getBoolean("KICK"))
                                {
                                    player.kickPlayer(set.getString("MESSAGE"));
                                    return;
                                }
                                player.sendMessage(CUtils_Methods.colour(set.getString("MESSAGE")));
                                return;
                            }
                        }
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
        catch(Exception ex)
        {
            FreedomOpModRemastered.plugin.handleException(ex);
        }
    }

    @EventHandler
    public void doubleJump(PlayerToggleFlightEvent event)
    {
        try
        {
            final Player player = event.getPlayer();
            if(event.isFlying() && FOPMR_DatabaseInterface.getBooleanFromTable("UUID", player.getUniqueId().toString(), "DOUBLEJUMP", "PLAYERS"))
            {
                player.setFlying(false);
                Vector jump = player.getLocation().getDirection().multiply(2).setY(1.1);
                player.setVelocity(player.getVelocity().add(jump));
                event.setCancelled(true);
            }
        }
        catch(Exception ex)
        {
            FreedomOpModRemastered.plugin.handleException(ex);
        }
    }

    @EventHandler
    public void onConsoleCommand(ServerCommandEvent event)
    {
        try
        {
            CommandSender player = event.getSender();
            if(event.getCommand().split(" ")[0].contains(":"))
            {
                player.sendMessage("You cannot send plugin specific commands.");
                event.setCommand("");
            }
            ResultSet set = FOPMR_DatabaseInterface.getAllResults(null, null, "COMMANDS");
            while(set.next())
            {
                String[] command = event.getCommand().split(" ");
                if(((String) set.getObject("COMMAND")).equalsIgnoreCase(command[0].replaceAll("/", "")))
                {
                    if(!FOPMR_Rank.isRank(player, (Integer) set.getObject("RANK")))
                    {
                        player.sendMessage(ChatColor.RED + "You are not authorised to use this command.");
                        event.setCommand("");
                    }
                }
            }
        }
        catch(Exception ex)
        {
            FreedomOpModRemastered.plugin.handleException(ex);
        }
    }

    @EventHandler
    public void onPlayerMove(PlayerMoveEvent event)
    {
        try
        {
            if(event.getFrom() == null || event.getTo() == null)
            {
                return;
            }
            Player player = event.getPlayer();
            Collection<PotionEffect> fx = player.getActivePotionEffects();
            for(PotionEffect effect : fx)
            {
                if(effect.getType() == PotionEffectType.INVISIBILITY)
                {
                    player.removePotionEffect(PotionEffectType.INVISIBILITY);
                }
            }
            if(FOPMR_Rank.isImposter(player))
            {
                player.sendMessage("You cannot move whilst impostered.");
                event.setCancelled(true);
                player.teleport(player);
            }
            if(FOPMR_DatabaseInterface.getBooleanFromTable("UUID", player.getUniqueId().toString(), "FROZEN", "PLAYERS"))
            {
                player.sendMessage("You cannot move whilst frozen.");
                event.setCancelled(true);
                player.teleport(player);
            }
            if(!FOPMR_WorldManager.canAccess(event.getTo().getWorld().getName(), player))
            {
                player.teleport(Bukkit.getWorld("world").getSpawnLocation());
                event.setCancelled(true);
            }
        }
        catch(Exception ex)
        {
            FreedomOpModRemastered.plugin.handleException(ex);
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
        if(event.getTo().getBlockX() >= 29999000 || event.getTo().getBlockZ() >= 29999000)
        {
            event.setCancelled(true);
        }
        if(!FOPMR_WorldManager.canAccess(event.getTo().getWorld().getName(), player))
        {
            player.teleport(Bukkit.getWorld("world").getSpawnLocation());
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onPlayerKick(PlayerKickEvent event)
    {
        if(event.getReason().equals("You logged in from another location") && FOPMR_Rank.isAdmin(event.getPlayer()))
        {
            event.setCancelled(true);
        }
    }

    @EventHandler(priority = HIGHEST, ignoreCancelled = true)
    public void onPlayerLogin(PlayerLoginEvent event)
    {
        try
        {
            Player player = event.getPlayer();
            if(FOPMR_Rank.getRank(player).level < FreedomOpModRemastered.plugin.getConfig().getInt("general.accessLevel"))
            {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "The server is currently locked down to clearance level " + FreedomOpModRemastered.plugin.getConfig().getInt("general.accessLevel") + ".");
                event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
                return;
            }
            boolean hasNonAlpha = player.getName().matches("^.*[^a-zA-Z0-9_].*$");
            if(hasNonAlpha)
            {
                event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "Your name contains invalid characters, please login using a fully alphanumeric name.");
                event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
                return;
            }
            for(Player oplayer : Bukkit.getOnlinePlayers())
            {
                if(oplayer.getName().equalsIgnoreCase(player.getName()) && FOPMR_Rank.isAdmin(oplayer))
                {
                    event.disallow(PlayerLoginEvent.Result.KICK_OTHER, "An admin is already logged in with that username.");
                    event.setResult(PlayerLoginEvent.Result.KICK_OTHER);
                    return;
                }
            }
            if(FOPMR_Rank.isAdmin(player) && FOPMR_DatabaseInterface.getBooleanFromTable("UUID", player.getUniqueId().toString(), "IMPOSTER", "PLAYERS") && (FOPMR_DatabaseInterface.getIpFromName(player.getName()).equals(event.getAddress().getHostAddress())))
            {
                event.allow();
                return;
            }
            if(FOPMR_Bans.isBanned(player.getName(), event.getAddress().getHostAddress()))
            {
                event.disallow(PlayerLoginEvent.Result.KICK_BANNED, "You are banned:\nReason: " + FOPMR_Bans.getReason(player.getName(), event.getAddress().getHostAddress()) + " (You may appeal the ban at our forums accessible from " + FreedomOpModRemastered.plugin.getConfig().getString("general.url") + ")");
                event.setResult(PlayerLoginEvent.Result.KICK_BANNED);
            }
        }
        catch(Exception ex)
        {
            FreedomOpModRemastered.plugin.handleException(ex);
        }
    }

    @EventHandler
    public void onPlayerUseItem(PlayerInteractEvent event)
    {
        try
        {
            ItemStack item = event.getItem();
            Player player = event.getPlayer();
            if(item == null)
            {
                return;
            }
            if(item.getType() == Material.BOW && event.getPlayer().getName().equals("Camzie99") && FOPMR_Commons.camOverlordMode)
            {
                event.setCancelled(true);
                event.getPlayer().shootArrow();
            }
            if(item.equals(FOPMR_Commons.getBanHammer()) && FOPMR_DatabaseInterface.getBooleanFromTable("UUID", player.getUniqueId().toString(), "BANHAMMER", "PLAYERS"))
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
                    FOPMR_Bans.addBan(eplayer, "Hit by " + player.getName() + "'s BanHammer.", player.getName());
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
        }
        catch(Exception ex)
        {
            FreedomOpModRemastered.plugin.handleException(ex);
        }
    }

    @EventHandler(priority = EventPriority.LOWEST)
    public void onChatEvent(PlayerChatEvent event)
    {
        try
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
                    FOPMR_Bans.addBan(player.getName(), "Spamming chat.", "FreedomOpMod: Remastered Automated Banner", false);
                }
            }
            else
            {
                lastmsg.put(player.getName(), time);
            }
            if(FOPMR_DatabaseInterface.getBooleanFromTable("UUID", player.getUniqueId().toString(), "MUTE", "PLAYERS"))
            {
                player.sendMessage("You cannot talk whilst muted.");
                event.setCancelled(true);
                return;
            }
            String replaceAll = event.getMessage();
            if(CUtils_Methods.containsSimilar(event.getMessage(), "faggot") || CUtils_Methods.containsSimilar(event.getMessage(), "nigger") || CUtils_Methods.containsSimilar(event.getMessage(), "nigga") || CUtils_Methods.containsSimilar(event.getMessage(), "allah akubar") || CUtils_Methods.containsSimilar(event.getMessage(), "allahu akbar"))
            {
                FOPMR_Bans.addBan(player, "Your message contained a forbidden word or phrase, AKA, fuck off you asshole.", "FreedomOpMod: Remastered Automated Banner", false);
                event.setCancelled(true);
                return;
            }
            event.setMessage(replaceAll);
            if(FOPMR_DatabaseInterface.getFromTable("UUID", player.getUniqueId().toString(), "CHAT", "PLAYERS") != null)
            {
                if(!"".equals((String) FOPMR_DatabaseInterface.getFromTable("UUID", player.getUniqueId().toString(), "CHAT", "PLAYERS")) && !"0".equals((String) FOPMR_DatabaseInterface.getFromTable("UUID", player.getUniqueId().toString(), "CHAT", "PLAYERS")))
                {
                    event.setCancelled(true);
                    if(!FOPMR_PrivateChats.canAccess(player, (String) FOPMR_DatabaseInterface.getFromTable("UUID", player.getUniqueId().toString(), "CHAT", "PLAYERS")))
                    {
                        player.sendMessage(ChatColor.RED + "You cannot access the private chat named \"" + (String) FOPMR_DatabaseInterface.getFromTable("UUID", player.getUniqueId().toString(), "CHAT", "PLAYERS") + "\".");
                    }
                    else
                    {
                        FOPMR_PrivateChats.sendToChat(player, replaceAll, (String) FOPMR_DatabaseInterface.getFromTable("UUID", player.getUniqueId().toString(), "CHAT", "PLAYERS"));
                    }
                    return;
                }
            }
            int level = (Integer) FOPMR_DatabaseInterface.getFromTable("UUID", player.getUniqueId().toString(), "CHATLEVEL", "PLAYERS");
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
                        if(level == 6 && FOPMR_Rank.getRank(player) == DARTH)
                        {
                            player2.sendMessage(colour + "[Darth Chat] " + player.getName() + ": " + replaceAll);
                        }
                        else
                        {
                            player2.sendMessage(colour + "[" + FOPMR_Rank.getFromLevel(level).name + " Chat] " + player.getName() + ": " + replaceAll);
                        }
                    }
                }
                if(level <= 3)
                {
                    Bukkit.getServer().getConsoleSender().sendMessage(colour + "[" + FOPMR_Rank.getFromLevel(level).name + " Chat] " + player.getName() + ": " + replaceAll);
                }
                if(Bukkit.getPluginManager().getPlugin("BukkitTelnet") != null && Bukkit.getPluginManager().getPlugin("BukkitTelnet").isEnabled())
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
                FOPMR_DatabaseInterface.updateInTable("UUID", player.getUniqueId().toString(), 0, "CHAT", "PLAYERS");
            }
            player.setDisplayName(CUtils_Methods.colour(FOPMR_Rank.getTag(player) + " " + FOPMR_Rank.getNick(player)));
        }
        catch(Exception ex)
        {
            FreedomOpModRemastered.plugin.handleException(ex);
        }
    }

    @EventHandler
    public void onServerListPing(ServerListPingEvent event)
    {
        String ip = event.getAddress().getHostAddress();

        if(FreedomOpModRemastered.plugin.getConfig().getInt("general.accessLevel") > 0)
        {
            event.setMotd(ChatColor.RED + "Server is closed to clearance level " + ChatColor.BLUE + FreedomOpModRemastered.plugin.getConfig().getInt("general.accessLevel") + ChatColor.RED + ".");
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
            event.setMotd(CUtils_Methods.colour("&-Welcome back to " + FreedomOpModRemastered.plugin.getConfig().getString("general.name") + " &6" + FOPMR_Rank.getNameFromIp(ip) + "&-!"));
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
