package net.camtech.fopmremastered;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import net.camtech.camutils.CUtils_Methods;
import net.minecraft.server.v1_13_R2.Explosion;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.command.CommandSender;
import org.bukkit.craftbukkit.v1_13_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_13_R2.entity.CraftEntity;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class FOPMR_Commons
{

    public static ArrayList<String> imposters = new ArrayList<>();
    public static HashMap<String, String> guests = new HashMap<>();
    public static boolean camOverlordMode = false;
    public static boolean globalFreeze = false;

    public static ItemStack getBanHammer()
    {
        ItemStack banhammer = new ItemStack(Material.DIAMOND_AXE, 1);
        ItemMeta banhammermeta = banhammer.getItemMeta();
        banhammermeta.setLore(Arrays.asList(ChatColor.BLUE + "Unleash the power of...", ChatColor.YELLOW + "The Ban Hammer!"));
        banhammermeta.setDisplayName(ChatColor.RED + "Ban Hammer!");
        banhammer.setItemMeta(banhammermeta);
        return banhammer;
    }

    public static void playerMsg(CommandSender sender, String msg, ChatColor colour)
    {
        sender.sendMessage(colour + msg);
    }

    public static void playerMsg(CommandSender sender, String msg)
    {
        playerMsg(sender, msg, ChatColor.RED);
    }

    public static void bcastMsg(String message, ChatColor color)
    {
        for (Player player : Bukkit.getOnlinePlayers())
        {
            player.sendMessage((color == null ? "" : color) + message);
        }
    }

    public static void bcastMsg(String message)
    {
        FOPMR_Commons.bcastMsg(message, null);
    }

    public static void asciiDog()
    {
        //This was VERY annoying to make!
        FOPMR_Commons.bcastMsg("                     ,", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("                ,.  | \\ ", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("               |: \\ ; :\\ ", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("               :' ;\\| ::\\", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("                \\ : | `::\\ ", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("                _)  |   `:`. ", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("              ,' , `.    ;: ; ", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("            ,' ;:  ;\"'  ,:: |", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("           /,   ` .    ;::: |:`-.__ ", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("        _,' _o\\  ,::.`:' ;  ;   . ' ", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("    _,-'           `:.          ;\"\"", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg(" ,-'                     ,:         `-;, ", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg(" \\,                       ;:           ;--._ ", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("  `.______,-,----._     ,' ;:        ,/ ,  ,` ", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("         / /,-';'  \\     ; `:      ,'/,::.::: ", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("       ,',;-'-'_,--;    ;   :.   ,',',;:::::: ", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("      ( /___,-'     `.     ;::,,'o/  ,::::::: ", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("       `'             )    ;:,'o /  ;\"-   -:: ", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("                      \\__ _,'o ,'         ,:: ", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("                         ) `--'       ,..:::: ", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("                         ; `.        ,::::::: ", CUtils_Methods.randomChatColour());
        FOPMR_Commons.bcastMsg("                          ;  ``::.    ::::::: ", CUtils_Methods.randomChatColour());
    }

    public static void asciiHorse()
    {
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + ",  ,.~\"\"\"\"\"~~..");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + " )\\,)\\`-,       `~._                                     .--._");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + " \\  \\ | )           `~._                   .-\"\"\"\"\"-._   /     `.");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "/ ('  ( _(\\            `~~,__________..-\"'          `-<        \\");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + " )   )   `   )/)   )        \\                            \\,-.     |");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "') /)`      \\` \\,-')/\\      (                             \\ /     |");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "(_(\\ /7      |.   /'  )'  _(`                              Y      |");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "   \\       (  `.     ')_/`                                |      /");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "     \\       \\   \\                                         |)    (");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "      \\ _  /\\/   /                                         (      `~.");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "       `-._)     |                                        / \\        `,");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                |                          |           .'   )      (`");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                \\                        _,\\          /     \\_    (`");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                  `.,      /       __..'7\"  \\         |       )  (");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                  .'     _/`-..--\"\"      `.   `.        \\      `._/");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                .'    _.j     /            `-.  `.       \\");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "              .'   _.'   \\    |               `.  `.      \\");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "             |   .'       ;   ;               .'  .'`.     \\");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "             \\_  `.       |   \\             .'  .'   /    .'");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "               `.  `-, __ \\   /           .'  .'     |   (");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                 `.  `'` \\|  |           /  .-`     /   .'");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                   `-._.--t  ;          |_.-)      /  .'");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                          ; /           \\  /      / .'");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                         / /             `'     .' /");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                        /,_\\                  .',_(");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                       /___(                 /___(");
    }

    public static void asciiUnicorn()
    {
        for (Player player : Bukkit.getOnlinePlayers())
        {
            player.playSound(player.getLocation(), Sound.ENTITY_FIREWORK_ROCKET_TWINKLE, 1.0F, 1.0F);
        }
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                                                         ,/");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                                                        //");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                                                      ,//");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                                          ___   /|   |//");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                                      `__/\\_ --(/|___/-/");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                                   \\|\\_-\\___ __-_`- /-/ \\.");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                                  |\\_-___,-\\_____--/_)' ) \\");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                                   \\ -_ /     __ \\( `( __`\\|");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                                   `\\__|      |\\)\\ ) /(/|");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "           ,._____.,            ',--//-|      \\  |  '   /");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "          /     __. \\,          / /,---|       \\       /");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "        |  | ( (  \\   |      ,/\\'__/'/          |     |");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "        |  \\  \\`--, `_/_------______/           \\(   )/");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "        | | \\  \\_. \\,                            \\___/\\");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "        | |  \\_   \\  \\                                 \\");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "        \\ \\    \\_ \\   \\   /                             \\");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "         \\ \\  \\._  \\__ \\_|       |                       \\");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "          \\ \\___  \\      \\       |                        \\");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "           \\__ \\__ \\  \\_ |       \\                         |");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "           |  \\_____ \\  ____      |                           |");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "           | \\  \\__ ---' .__\\     |        |                 |");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "           \\  \\__ ---   /   )     |        \\                /");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "            \\   \\____/ / ()(      \\          `---_         /|");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "             \\__________/(,--__    \\_________.    |       ./ |");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "               |     \\ \\  `---_\\--,           \\   \\_,./   |");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "               |      \\  \\_ ` \\    /`---_______-\\   \\\\    /");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                \\      \\.___,`|   /              \\   \\\\   \\");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                 \\     |  \\_ \\|   \\              (   |:    |");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                  \\    \\      \\    |             /  / |    ;");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                   \\    \\      \\    \\          ( `_'   \\  |");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                    \\.   \\      \\.   \\          `__/   |  |");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                      \\   \\       \\.  \\                |  |");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                       \\   \\        \\  \\               (  )");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                        \\   |        \\  |                |  |");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                         |  \\         \\ \\               I  `");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                         ( __;        ( _;                ('-_';");
        FOPMR_Commons.bcastMsg(CUtils_Methods.randomChatColour() + "                         |___\\       \\___:              \\___:");
    }

    public static void adminAction(String name, String msg, boolean isRed)
    {
        Bukkit.broadcastMessage((isRed ? ChatColor.RED : ChatColor.DARK_AQUA) + name + " - " + msg);
    }

    public static void spawnMob(Player player, EntityType entity, int amount)
    {
        int i = 0;
        do
        {
            player.getWorld().spawnEntity(player.getLocation(), entity);
            i++;
        } while (i <= amount);
    }

    public static void openVoteShop(Player player)
    {
        Inventory inv = Bukkit.createInventory(null, 27, ChatColor.GREEN + "" + ChatColor.BOLD + "$$ " + ChatColor.GOLD + "VoteShop " + ChatColor.GREEN + "" + ChatColor.BOLD + "$$");
        ItemStack randomChat = new ItemStack(Material.SIGN, 1);
        ItemMeta randomChatMeta = randomChat.getItemMeta();
        if (!FreedomOpModRemastered.configs.getAdmins().getConfig().getBoolean(player.getUniqueId().toString() + ".randomChatColour"))
        {
            randomChatMeta.setDisplayName(ChatColor.BLUE + "Random Chat Colors");
            randomChatMeta.setLore(Arrays.asList(ChatColor.GREEN + "Gain access to use &- in chat, nicks and tags.", ChatColor.GREEN + "&- Randomly colours the following text!", ChatColor.GREEN + "Price: 3 Votes"));
        }
        else
        {
            randomChatMeta.setDisplayName(ChatColor.RED + "Random Chat Colors");
            randomChatMeta.setLore(Arrays.asList(ChatColor.GREEN + "Gain access to use &- in chat, nicks and tags.", ChatColor.GREEN + "&- Randomly colours the following text!", ChatColor.RED + "[PURCHASED]"));
        }
        randomChat.setItemMeta(randomChatMeta);
        inv.setItem(1, randomChat);
        ItemStack chat = new ItemStack(Material.PAPER, 1);
        ItemMeta chatMeta = randomChat.getItemMeta();
        if (!FreedomOpModRemastered.configs.getAdmins().getConfig().getBoolean(player.getUniqueId().toString() + ".chatColours"))
        {
            chatMeta.setDisplayName(ChatColor.BLUE + "Chat Colors");
            chatMeta.setLore(Arrays.asList(ChatColor.GREEN + "Gain access to use colors in chat, nicks and tags.", ChatColor.GREEN + "Price: 3 Votes"));
        }
        else
        {
            chatMeta.setDisplayName(ChatColor.RED + "Chat Colours");
            chatMeta.setLore(Arrays.asList(ChatColor.GREEN + "Gain access to use colours in chat, nicks and tags.", ChatColor.RED + "[PURCHASED]"));
        }
        chat.setItemMeta(chatMeta);
        inv.setItem(7, chat);
        ItemStack votes = new ItemStack(Material.DIAMOND, 1);
        ItemMeta votesMeta = votes.getItemMeta();
        votesMeta.setDisplayName(ChatColor.BLUE + "You have " + ChatColor.GOLD + FreedomOpModRemastered.configs.getAdmins().getConfig().getInt(player.getUniqueId().toString() + ".votes") + ChatColor.BLUE + " votes.");
        votes.setItemMeta(votesMeta);
        inv.setItem(13, votes);
        player.openInventory(inv);
    }

    public static void explode(Location loc, Entity explodeAs, float radius, boolean setFires, boolean terrainDamage)
    {
        Explosion explosion = new Explosion(((CraftWorld) loc.getWorld()).getHandle(),
                ((CraftEntity) explodeAs).getHandle(), loc.getX(), loc.getY(), loc.getZ(), radius, setFires,
                terrainDamage);
        explosion.a();
        explosion.a(true);
        loc.getWorld().createExplosion(loc, 0F, false);
    }

    public static <T> List<List<T>> chopped(List<T> list, final int L)
    {
        List<List<T>> parts = new ArrayList<>();
        final int N = list.size();
        for (int i = 0; i < N; i += L)
        {
            parts.add(new ArrayList<>(
                    list.subList(i, Math.min(N, i + L)))
            );
        }
        return parts;
    }

    public static boolean intToBoolean(int i)
    {
        return i == 1;
    }
}
