package net.camtech.camutils;

import java.util.ArrayList;
import java.util.List;
import static net.camtech.fopmremastered.FreedomOpModRemastered.plugin;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.event.Event;
import org.bukkit.scheduler.BukkitRunnable;

public class CamUtils
{
    public static List<String> exploded = new ArrayList<String>();
    
    public void checkTime()
    {
        new BukkitRunnable()
        {
            @Override
            public void run()
            {
                for(World world : Bukkit.getWorlds())
                {
                    Event event = new WorldTimeChangeEvent(world, world.getTime());
                    Bukkit.getPluginManager().callEvent(event);
                }
            }
        }.runTaskLater(plugin, 20L * 10L);
    }
}
