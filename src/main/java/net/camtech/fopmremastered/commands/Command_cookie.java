package net.camtech.fopmremastered.commands;

import java.util.Arrays;
import net.camtech.camutils.CUtils_Methods;
import net.camtech.fopmremastered.FOPMR_Rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Command_cookie extends FOPMR_Command
{

    public Command_cookie()
    {
        super("cookie", "/cookie", "For the people who are still alive.", Rank.ADMIN);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        Bukkit.broadcastMessage(CUtils_Methods.randomChatColour() + "But there's no sense crying over every mistake. You just keep on trying till you run out of " + CUtils_Methods.randomChatColour() + "cookies.");
        for (Player player : Bukkit.getOnlinePlayers())
        {
            ItemStack cookie = new ItemStack(Material.COOKIE, 1);
            ItemMeta cookiemeta = cookie.getItemMeta();
            cookiemeta.setLore(Arrays.asList(CUtils_Methods.randomChatColour() + "COOOKKKIEEEEEE", CUtils_Methods.randomChatColour() + " - " + sender.getName()));
            cookie.setItemMeta(cookiemeta);
            player.getInventory().addItem(cookie);
        }
        return true;
    }

}
