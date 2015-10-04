package net.camtech.fopmremastered.listeners;

import net.camtech.fopmremastered.FOPMR_Rank;
import net.camtech.fopmremastered.FreedomOpModRemastered;
import net.camtech.fopmremastered.protectedareas.FOPMR_ProtectedArea;
import net.camtech.fopmremastered.protectedareas.FOPMR_ProtectedAreas;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPistonExtendEvent;
import org.bukkit.event.block.BlockPistonRetractEvent;
import org.bukkit.event.block.BlockPlaceEvent;

public final class FOPMR_BlockListener implements Listener
{

    public FOPMR_BlockListener()
    {
        init();
    }
    
    public void init()
    {
        Bukkit.getPluginManager().registerEvents(this, FreedomOpModRemastered.plugin);
    }
    
    @EventHandler
    public void onPistonPush(BlockPistonExtendEvent event)
    {
        for(Block block : event.getBlocks())
        {
            if(block.getType() == Material.SLIME_BLOCK)
            {
                event.setCancelled(true);
            }
        }
    }
    
    @EventHandler
    public void onPistonPull(BlockPistonRetractEvent event)
    {
        for(Block block : event.getBlocks())
        {
            if(block.getType() == Material.SLIME_BLOCK)
            {
                event.setCancelled(true);
            }
        }
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event)
    {
        Player player = event.getPlayer();
        for(FOPMR_ProtectedArea area : FOPMR_ProtectedAreas.getFromDatabase())
        {
            if(area.isInRange(event.getBlock().getLocation()))
            {
                if(!area.canAccess(player))
                {
                    player.sendMessage(ChatColor.RED + "You do not have permission to break blocks in this area! Please see " + area.getOwner() + " if you wish to gain access.");  
                    event.setCancelled(true);
                }
            }
        }
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event)
    {
        Player player = event.getPlayer();
        if(event.getBlock().getType() == Material.COMMAND && !FOPMR_Rank.isAdmin(player))
        {
            player.sendMessage(ChatColor.RED + "Only admins can use command blocks.");
            event.setCancelled(true);
        }
        for(FOPMR_ProtectedArea area : FOPMR_ProtectedAreas.getFromDatabase())
        {
            if(area.isInRange(event.getBlock().getLocation()))
            {
                if(!area.canAccess(player))
                {
                    player.sendMessage(ChatColor.RED + "You do not have permission to place blocks in this area! Please see " + area.getOwner() + " if you wish to gain access.");  
                    event.setCancelled(true);
                }
            }
        }
    }
}
