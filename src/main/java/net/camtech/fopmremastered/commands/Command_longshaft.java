package net.camtech.fopmremastered.commands;

import net.camtech.fopmremastered.FOPMR_Rank.Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;

public class Command_longshaft extends FOPMR_Command
{

    public Command_longshaft()
    {
        super("longshaft", "/longshaft", "Gives you a long hard shaft.", Rank.ADMIN);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        Player sender_p = Bukkit.getPlayer(sender.getName());
        PlayerInventory inv = sender_p.getInventory();
        ItemStack LongShaft = new ItemStack(Material.STICK, 1);
        for (Enchantment ench : Enchantment.values())
        {
            LongShaft.addUnsafeEnchantment(ench, 10000);
        }
        inv.addItem(LongShaft);
        sender_p.sendMessage(ChatColor.GOLD + "Giving you a long, hard shaft.");

        return true;
    }
}
