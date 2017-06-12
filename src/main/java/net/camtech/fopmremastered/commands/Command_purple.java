package net.camtech.fopmremastered.commands;

import java.util.Arrays;
import java.util.List;
import net.camtech.fopmremastered.FOPMR_Commons;
import net.camtech.fopmremastered.FOPMR_Enchantment;
import net.camtech.fopmremastered.FOPMR_Rank;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

public class Command_purple extends FOPMR_Command
{

    public Command_purple()
    {
        super("purple", "/purple", "Only tyler can harness this power.", FOPMR_Rank.Rank.ADMIN);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
        if (!(sender instanceof Player))
        {
            return false;
        }
        if (!sender.getName().equals("tylerhyperHD"))
        {
            Player sender_p = Bukkit.getPlayer(sender.getName());
            sender_p.sendMessage(ChatColor.RED + "Only Tyler may use this command.\nNo permissions for the people who aren't purple.");
            sender_p.setHealth(0.0);
            return true;
        }
        if (args.length == 0)
        {
            for (Player player : Bukkit.getOnlinePlayers())
            {
                PlayerInventory inv = player.getInventory();
                ItemStack CamWool = new ItemStack(Material.WOOL, 1, (short) 10);
                FOPMR_Enchantment.enchantAll(CamWool);
                ItemMeta meta = CamWool.getItemMeta();
                meta.setDisplayName(ChatColor.YELLOW + "Purple Aura");
                Object lore = Arrays.asList(new String[]
                {
                    ChatColor.BLUE + "This aura should protect", ChatColor.BLUE + "you from all possible harm."
                });
                meta.setLore((List) lore);
                CamWool.setItemMeta(meta);
                World world = player.getWorld();
                Location loc = player.getLocation();
                inv.setHelmet(CamWool);
                world.strikeLightningEffect(loc);
            }
            for (Player player : Bukkit.getOnlinePlayers())
            {
                World world = player.getWorld();
                Location loc = player.getLocation();
                world.strikeLightningEffect(loc);
                Bukkit.broadcastMessage(ChatColor.RED + sender.getName() + " - Gracing the world with purple!");
                world.strikeLightningEffect(loc);
                Bukkit.broadcastMessage(ChatColor.RED + sender.getName() + " - Gracing the world with purple!");
                world.strikeLightningEffect(loc);
                Bukkit.broadcastMessage(ChatColor.RED + sender.getName() + " - Gracing the world with purple!");
                world.strikeLightningEffect(loc);
                Bukkit.broadcastMessage(ChatColor.RED + sender.getName() + " - Gracing the world with purple!");
                world.strikeLightningEffect(loc);
                Bukkit.broadcastMessage(ChatColor.RED + sender.getName() + " - Gracing the world with purple!");
                world.strikeLightningEffect(loc);
                Bukkit.broadcastMessage(ChatColor.RED + sender.getName() + " - Gracing the world with purple!");
                world.strikeLightningEffect(loc);
                Bukkit.broadcastMessage(ChatColor.RED + sender.getName() + " - Gracing the world with purple!");
                world.strikeLightningEffect(loc);
                Bukkit.broadcastMessage(ChatColor.RED + sender.getName() + " - Gracing the world with purple!");
                world.strikeLightningEffect(loc);
            }
            for (Player player : Bukkit.getOnlinePlayers())
            {
                PlayerInventory inv = player.getInventory();
                ItemStack CamBow = new ItemStack(Material.BOW, 1);
                FOPMR_Enchantment.enchantAll(CamBow);
                ItemMeta meta = CamBow.getItemMeta();
                meta.setDisplayName(ChatColor.DARK_AQUA + "The Purple Shot");
                Object lore = Arrays.asList(new String[]
                {
                    ChatColor.BLUE + "Legend has it, this bow", ChatColor.BLUE + "can only shoot purple arrows!"
                });
                meta.setLore((List) lore);
                CamBow.setItemMeta(meta);
                inv.addItem(CamBow);
                ItemStack CamSword = new ItemStack(Material.GOLD_SWORD, 1);
                FOPMR_Enchantment.enchantAll(CamSword);
                ItemMeta sword = CamSword.getItemMeta();
                sword.setDisplayName(ChatColor.DARK_GREEN + "The Purple Blade");
                Object a = Arrays.asList(new String[]
                {
                    ChatColor.BLUE + "The purple has the power", ChatColor.BLUE + "to wield this legendary blade!"
                });
                sword.setLore((List) a);
                CamSword.setItemMeta(sword);
                inv.addItem(CamSword);
                ItemStack CamArrow = new ItemStack(Material.ARROW, 1);
                FOPMR_Enchantment.enchantAll(CamArrow);
                ItemMeta arrow = CamArrow.getItemMeta();
                arrow.setDisplayName(ChatColor.DARK_PURPLE + "Purple Arrow");
                Object b = Arrays.asList(new String[]
                {
                    ChatColor.BLUE + "This arrow has a mysterious", ChatColor.BLUE + "purple aura around it..."
                });
                arrow.setLore((List) b);
                CamArrow.setItemMeta(arrow);
                inv.addItem(CamArrow);
                ItemStack CamChest = new ItemStack(Material.LEATHER_CHESTPLATE, 1);
                FOPMR_Enchantment.enchantAll(CamChest);
                LeatherArmorMeta chest = (LeatherArmorMeta) CamChest.getItemMeta();
                chest.setDisplayName(ChatColor.YELLOW + "Purple Chestplate");
                Object c = Arrays.asList(new String[]
                {
                    ChatColor.BLUE + "This aura should protect", ChatColor.BLUE + "you from all possible harm."
                });
                chest.setLore((List) c);
                chest.setColor(Color.fromRGB(125, 20, 240));
                CamChest.setItemMeta(chest);
                inv.setChestplate(CamChest);
                ItemStack CamLegs = new ItemStack(Material.LEATHER_LEGGINGS, 1);
                FOPMR_Enchantment.enchantAll(CamLegs);
                LeatherArmorMeta legs = (LeatherArmorMeta) CamLegs.getItemMeta();
                legs.setDisplayName(ChatColor.YELLOW + "Purple Leggings");
                Object d = Arrays.asList(new String[]
                {
                    ChatColor.BLUE + "This aura should protect", ChatColor.BLUE + "you from all possible harm."
                });
                legs.setLore((List) d);
                legs.setColor(Color.fromRGB(125, 20, 240));
                CamLegs.setItemMeta(legs);
                inv.setLeggings(CamLegs);
                ItemStack CamBoots = new ItemStack(Material.LEATHER_BOOTS, 1);
                FOPMR_Enchantment.enchantAll(CamBoots);
                LeatherArmorMeta boots = (LeatherArmorMeta) CamBoots.getItemMeta();
                boots.setDisplayName(ChatColor.YELLOW + "Purple Boots");
                Object e = Arrays.asList(new String[]
                {
                    ChatColor.BLUE + "This aura should protect", ChatColor.BLUE + "you from all possible harm."
                });
                boots.setLore((List) e);
                boots.setColor(Color.fromRGB(125, 20, 240));
                CamBoots.setItemMeta(boots);
                inv.setBoots(CamBoots);
            }
        }
        return true;
    }
}
