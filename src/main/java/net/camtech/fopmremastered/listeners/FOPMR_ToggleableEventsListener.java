package net.camtech.fopmremastered.listeners;

import net.camtech.fopmremastered.FOPMR_Rank;
import net.camtech.fopmremastered.FreedomOpModRemastered;

import java.util.Collection;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Ghast;
import org.bukkit.entity.Giant;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Slime;
import org.bukkit.entity.Tameable;
import org.bukkit.entity.Wither;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockFromToEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockSpreadEvent;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.SheepRegrowWoolEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.weather.WeatherChangeEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

@SuppressWarnings("deprecation")
public class FOPMR_ToggleableEventsListener implements Listener {

	private Random random;

	public FOPMR_ToggleableEventsListener() {
		Bukkit.getPluginManager().registerEvents(this, FreedomOpModRemastered.plugin);
		checkTime();
	}

	public static void checkTime() {
		if (!FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.time")) {
			for (World world : Bukkit.getWorlds()) {
				world.setGameRuleValue("doDaylightCycle", "false");
			}
		} else {
			for (World world : Bukkit.getWorlds()) {
				world.setGameRuleValue("doDaylightCycle", "true");
			}
		}
	}

	@EventHandler
	public void onExplode(EntityExplodeEvent event) {
		if (!FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.explosions")) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onItemUse(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		if (event.getItem() == null) {
			return;
		}
		ItemStack item = event.getItem();
		if ((item.getType() == Material.WATER || item.getType() == Material.WATER_BUCKET) && !FOPMR_Rank.isAdmin(player)
				&& !FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.waterplace")) {
			event.setCancelled(true);
		}
		if ((item.getType() == Material.LAVA || item.getType() == Material.LAVA_BUCKET) && !FOPMR_Rank.isAdmin(player)
				&& !FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.lavaplace")) {
			event.setCancelled(true);
		}
		if (item.getType() == Material.TNT && !FOPMR_Rank.isAdmin(player)
				&& !FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.lavaplace")) {
			event.setCancelled(true);
		}
		if ((item.getType() == Material.FLINT_AND_STEEL || item.getType() == Material.FIRE
				|| item.getType() == Material.FIRE_CHARGE) && !FOPMR_Rank.isAdmin(player)
				&& !FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.fire")) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockSpread(BlockSpreadEvent event) {
		if (event.getBlock().getType() == Material.FIRE
				&& !FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.fire")) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onBlockIgnite(BlockIgniteEvent event) {
		if (!FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.fire")) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onSheepRegrowWool(SheepRegrowWoolEvent event) {
		random = new Random();
		if (FreedomOpModRemastered.plugin.getConfig().getBoolean("toggles.srgwool")) {
			Collection<? extends Player> p = Bukkit.getServer().getOnlinePlayers();
			Player t = (Player) p.toArray()[random.nextInt(p.toArray().length)];
			Location l = t.getLocation();
			Material m = Material.values()[random.nextInt(Material.values().length)];
			ItemStack is = new ItemStack(m);
			t.getWorld().dropItemNaturally(l, is);
		} else {
			 /*do nothing*/  }
	}

	@EventHandler
	public void onLiquidSpread(BlockFromToEvent event) {
		if ((event.getBlock().getType() == Material.WATER)
				&& !FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.waterspread")) {
			event.setCancelled(true);
		}
		if ((event.getBlock().getType() == Material.LAVA)
				&& !FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.lavaspread")) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityHit(EntityDamageEvent event) {
		Entity entity = event.getEntity();
		if (entity instanceof LivingEntity) {
			LivingEntity lentity = (LivingEntity) entity;
			if (lentity instanceof Tameable) {
				Tameable tentity = (Tameable) lentity;
				if (tentity.isTamed() && !FreedomOpModRemastered.configs.getMainConfig().getConfig()
						.getBoolean("toggles.petdamage")) {
					event.setCancelled(true);
				}
			}
		}
		if (event.getCause() == DamageCause.ENTITY_EXPLOSION
				&& !FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.explosions")) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onEntityDie(EntityDeathEvent event) {
		final Location loc = event.getEntity().getLocation();
		if (!FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.drops")) {
			event.setDroppedExp(0);
			new BukkitRunnable() {
				@Override
				public void run() {
					for (Entity entity : loc.getWorld().getEntities()) {
						if (!(entity instanceof LivingEntity) && entity.getLocation().distance(loc) < 10) {
							entity.remove();
						}
					}
				}
			}.runTaskLater(FreedomOpModRemastered.plugin, 10L);
		}
	}

	@EventHandler
	public void onWeatherChange(WeatherChangeEvent event) {
		if (event.toWeatherState()
				&& !FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.weather")) {
			event.setCancelled(true);
		}
	}

	@EventHandler
	public void onPlayerDropItem(PlayerDropItemEvent event) {
		Entity item = event.getItemDrop();
		if (!FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.drops")) {
			item.remove();
		}
	}

	@EventHandler
	public void onCreatureSpawn(CreatureSpawnEvent event) {
		if (event.getSpawnReason().equals(CreatureSpawnEvent.SpawnReason.EGG)) {
			event.setCancelled(true);
			return;
		}

		Entity spawned = event.getEntity();

		if (spawned instanceof EnderDragon) {
			if (!FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.enderdragon")) {
				event.setCancelled(true);
			}
		} else if (spawned instanceof Ghast) {
			if (!FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.ghast")) {
				event.setCancelled(true);
			}
		} else if (spawned instanceof Slime) {
			if (!FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.slime")) {
				event.setCancelled(true);
			}
		} else if (spawned instanceof Giant) {
			if (!FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.giant")) {
				event.setCancelled(true);
			}
		} else if (spawned instanceof Wither) {
			if (!FreedomOpModRemastered.configs.getMainConfig().getConfig().getBoolean("toggles.wither")) {
				event.setCancelled(true);
			}
		}
	}
}
