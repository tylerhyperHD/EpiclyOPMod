package net.camtech.fopmremastered.commands;

import java.util.Arrays;
import net.camtech.fopmremastered.FOPMR_Rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Ambient;
import org.bukkit.entity.Creature;
import org.bukkit.entity.EnderDragon;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Slime;

public class Command_mobpurge extends FOPMR_Command {

	public Command_mobpurge() {
		super("mobpurge", "/mobpurge", "Purge all mobs", Arrays.asList("mp"), Rank.ADMIN);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		sender.sendMessage(purgeMeSomeMobs() + " mobs removed.");
		return true;
	}

	public static int purgeMeSomeMobs() {
		int removed = 0;
		for (World world : Bukkit.getWorlds()) {
			for (Entity ent : world.getLivingEntities()) {
				if (ent instanceof Creature || ent instanceof Slime || ent instanceof EnderDragon
						|| ent instanceof Ambient) {
					ent.remove();
					removed++;
				}
			}
		}

		return removed;
	}
}
