package net.camtech.fopmremastered.commands;

import net.camtech.fopmremastered.FOPMR_Commons;
import net.camtech.fopmremastered.FOPMR_Rank;
import net.camtech.fopmremastered.FOPMR_Rank.Rank;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.util.Vector;

public class Command_blowup extends FOPMR_Command
{

    public Command_blowup()
    {
        super("blowup", "/blowup [player]", "BLOW THINGS UP!", Rank.EOMCREATOR);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (args.length != 1)
        {
            return false;
        }

        final Player player = FOPMR_Rank.getPlayer(args[0]);

        if (player == null)
        {
            sender.sendMessage(ChatColor.RED + "Player not found!");
            return true;
        }

        // Trigger blowing them up
        player.setVelocity(player.getVelocity().clone().add(new Vector(0, 100, 0)));
        FOPMR_Commons.explode(player.getLocation(), player, 5F, true, true);
        FOPMR_Commons.explode(player.getLocation(), player, 5F, true, true);
        FOPMR_Commons.explode(player.getLocation(), player, 5F, true, true);
        FOPMR_Commons.explode(player.getLocation(), player, 5F, true, true);
        FOPMR_Commons.explode(player.getLocation(), player, 5F, true, true);
        FOPMR_Commons.explode(player.getLocation(), player, 5F, true, true);
        FOPMR_Commons.explode(player.getLocation(), player, 5F, true, true);
        FOPMR_Commons.explode(player.getLocation(), player, 5F, true, true);
        FOPMR_Commons.explode(player.getLocation(), player, 5F, true, true);
        FOPMR_Commons.explode(player.getLocation(), player, 5F, true, true);
        FOPMR_Commons.explode(player.getLocation(), player, 5F, true, true);
        FOPMR_Commons.explode(player.getLocation(), player, 5F, true, true);
        FOPMR_Commons.explode(player.getLocation(), player, 5F, true, true);
        FOPMR_Commons.explode(player.getLocation(), player, 5F, true, true);

        // Kill them
        player.setHealth(0.0);

        return true;
    }
}
