package net.camtech.fopmremastered.commands;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import net.camtech.camutils.CUtils_Methods;
import net.camtech.fopmremastered.FOPMR_Commons;
import net.camtech.fopmremastered.FOPMR_Enchantment;
import net.camtech.fopmremastered.FOPMR_Rank;
import static net.camtech.fopmremastered.FreedomOpModRemastered.plugin;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scheduler.BukkitRunnable;

public class Command_personal extends FOPMR_Command {

	public Command_personal() {
		super("personal", "/personal", "Run your personal command", Arrays.asList("psl"), FOPMR_Rank.Rank.ADMIN);
	}

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		if (!(sender instanceof Player)) {
			return false;
		}
		String which;
		if (args.length >= 1) {
			which = args[0];
		} else {
			Player sender_p = Bukkit.getPlayer(sender.getName());
			which = sender_p.getName();
		}
		switch (which) {
		case "Camzie99":
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack wool = new ItemStack(Material.PURPLE_WOOL, 1);
				FOPMR_Enchantment.enchantAll(wool);
				ItemMeta meta = wool.getItemMeta();
				World world = player.getWorld();
				Location loc = player.getLocation();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "The Purple Aura");
				List<String> lore = Arrays
						.asList(ChatColor.BLUE + "The aura should protect you from all possible harm.");
				meta.setLore(lore);
				wool.setItemMeta(meta);
				inv.setHelmet(wool);
				world.strikeLightningEffect(loc);
			}
			Player sender_camzie = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.adminAction(sender_camzie.getName(), "Gracing the world with Purple!", false);
			break;
		case "ItsBricks":
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack brick = new ItemStack(Material.BRICK, 1);
				FOPMR_Enchantment.enchantAll(brick);
				ItemMeta meta = brick.getItemMeta();
				World world = player.getWorld();
				Location loc = player.getLocation();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "DA BRICK");
				List<String> lore = Arrays
						.asList(ChatColor.BLUE + "The brick protects you from getting hit in the head. Strange...");
				meta.setLore(lore);
				brick.setItemMeta(meta);
				inv.setHelmet(brick);
				world.strikeLightningEffect(loc);
			}
			Player sender_bricks = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.adminAction(sender_bricks.getName(), "HAH! You got brick blocked!", true);
			break;
		case "jumpymonkey123":
			FOPMR_Commons.asciiUnicorn();
			break;
		case "dsate1":
			FOPMR_Commons.asciiUnicorn();
			FOPMR_Commons.bcastMsg(" Look", CUtils_Methods.randomChatColour());
			FOPMR_Commons.bcastMsg(" At", CUtils_Methods.randomChatColour());
			FOPMR_Commons.bcastMsg(" The", CUtils_Methods.randomChatColour());
			FOPMR_Commons.bcastMsg(" Pretty", CUtils_Methods.randomChatColour());
			FOPMR_Commons.bcastMsg(" Unicorn", CUtils_Methods.randomChatColour());
			break;
		case "TaahThePenguin":
			for (Player player : Bukkit.getOnlinePlayers()) {
				FOPMR_Commons.spawnMob(player, EntityType.HORSE, 2);
			}
			Player sender_o = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.adminAction(ChatColor.RED + sender_o.getName(), "YOU LIKE DAT ASS?", false);
			break;
		case "xDestroyer217":
			FOPMR_Commons.asciiDog();
			FOPMR_Commons.bcastMsg("hi doggies", CUtils_Methods.randomChatColour());
			FOPMR_Commons.bcastMsg("Now, doggies for everyone :P", ChatColor.AQUA);
			for (Player player : Bukkit.getOnlinePlayers()) {
				FOPMR_Commons.spawnMob(player, EntityType.WOLF, 10);
				LivingEntity dog = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
				dog.setCustomNameVisible(true);
				dog.setCustomName(ChatColor.DARK_AQUA + "Doggie");
				player.setOp(true);
				player.sendRawMessage(ChatColor.YELLOW + "You are now op!");
				FOPMR_Commons.bcastMsg("Except you Robin, you get nothing u whore XD", ChatColor.RED);
				Player sender_robin = Bukkit.getPlayer(sender.getName());
				sender_robin.chat("U whore.");
				sender_robin.setHealth(0.0);
			}
			break;
		case "cowgomooo12":
			for (Player player : Bukkit.getOnlinePlayers()) {
				FOPMR_Commons.spawnMob(player, EntityType.COW, 2);
			}
			Player sender_cow = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.adminAction(sender_cow.getName(), "Let there be cows!", false);
			break;
		case "StygianDiamond":
			for (Player player : Bukkit.getOnlinePlayers()) {
				FOPMR_Commons.spawnMob(player, EntityType.HORSE, 2);
			}
			Player sender_different = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.adminAction(ChatColor.RED + sender_different.getName(), "YOU LIKE DAT ASS?", false);
			break;
		case "BakuSystems":
			FOPMR_Commons.bcastMsg("Incoming Oblivion!", ChatColor.RED);
			for (World world : Bukkit.getWorlds()) {
				for (Entity entity : world.getEntities()) {
					if (entity instanceof LivingEntity && !(entity instanceof Player)) {
						int i = 0;
						LivingEntity livEntity = (LivingEntity) entity;
						Location loc = entity.getLocation();
						do {
							world.strikeLightningEffect(loc);
							i++;
						} while (i <= 2);
						livEntity.setHealth(0);
					}
				}
				for (final Player player : Bukkit.getOnlinePlayers()) {
					for (double percent = 0.0; percent <= 1.0; percent += (1.0 / STEPS)) {
						final float pitch = (float) (percent * 2.0);
						player.playSound(randomOffset(player.getLocation(), 5.0),
								Sound.values()[random.nextInt(Sound.values().length)], 100.0f, pitch);
					}
				}
			}
			break;
		case "thekobester02":
			Player sender_koby = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.adminAction(sender_koby.getName(), "HERE COME THE DRUMS!", false);
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack drumstick = new ItemStack(Material.STICK, 1);
				ItemMeta meta = drumstick.getItemMeta();
				meta.setDisplayName(ChatColor.LIGHT_PURPLE + "1 2 3 4");
				drumstick.setItemMeta(meta);
				inv.addItem(drumstick);
			}
			break;
		case "Got_No_Friends":
			Player sender_pie = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.adminAction(sender_pie.getName(), "Pies for all!", false);
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack pie = new ItemStack(Material.PUMPKIN_PIE, 1);
				ItemMeta meta = pie.getItemMeta();
				meta.setDisplayName(ChatColor.LIGHT_PURPLE + "FREE PIE");
				meta.addEnchant(Enchantment.FIRE_ASPECT, 25, true);
				meta.addEnchant(Enchantment.KNOCKBACK, 10, true);
				pie.setItemMeta(meta);
				inv.addItem(pie);
			}
			break;
		case "myd":
			Player sender_myd = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.adminAction(sender_myd.getName(), "And we gonna let it burn burn burn burn!", true);
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack burn = new ItemStack(Material.FIRE, 1);
				ItemMeta meta = burn.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_RED + "burn burn burn burn burn burn burn burn");
				burn.setItemMeta(meta);
				FOPMR_Enchantment.enchantAll(burn);
				inv.addItem(burn);
			}
			break;
		case "weed":
			Player sender_weed = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.adminAction(sender_weed.getName(), "SMOKE WEED EVERY DAY!", true);
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack weed = new ItemStack(Material.DEAD_BUSH, 1);
				ItemMeta meta = weed.getItemMeta();
				meta.setDisplayName(ChatColor.LIGHT_PURPLE + "" + ChatColor.BOLD + "WEED");
				List<String> lore = Arrays
						.asList(ChatColor.LIGHT_PURPLE + "Don't do drugs kids. I never should have wrote this.");
				meta.setLore(lore);
				meta.addEnchant(Enchantment.FIRE_ASPECT, 10000, true);
				meta.addEnchant(Enchantment.KNOCKBACK, 10000, true);
				weed.setItemMeta(meta);
				inv.addItem(weed);
			}
			break;
		case "minemagic1234":
			Player sender_mine = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.adminAction(sender_mine.getName(), "Oh oh oh, it's magic!", true);
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack magic = new ItemStack(Material.NETHER_STAR, 1);
				ItemMeta meta = magic.getItemMeta();
				meta.setDisplayName(ChatColor.LIGHT_PURPLE + "MAGIC");
				magic.setItemMeta(meta);
				FOPMR_Enchantment.enchantAll(magic);
				inv.addItem(magic);
			}
			break;
		case "book":
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack magic = new ItemStack(Material.ENCHANTED_BOOK, 1);
				ItemMeta meta = magic.getItemMeta();
				meta.setDisplayName(ChatColor.LIGHT_PURPLE + "GIMME DA OP BOOK");
				magic.setItemMeta(meta);
				FOPMR_Enchantment.enchantAll(magic);
				inv.addItem(magic);
			}
			break;
		case "Rex657":
			FOPMR_Commons.bcastMsg("Rex is going on a rampage!", ChatColor.RED);
			FOPMR_Commons.bcastMsg("Take this to kill him!", ChatColor.YELLOW);
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
				FOPMR_Enchantment.enchantAll(sword);
				inv.addItem(sword);
			}
			break;
		case "Blacklepoard":
			FOPMR_Commons.adminAction(sender.getName(), "Quick! The god is above you!", true);
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack sword = new ItemStack(Material.DIAMOND_SWORD, 1);
				ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
				ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
				ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS, 1);
				ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET, 1);
				ItemStack bow = new ItemStack(Material.BOW, 1);
				FOPMR_Enchantment.enchantAll(sword);
				FOPMR_Enchantment.enchantAll(bow);
				inv.addItem(bow);
				inv.addItem(sword);
				FOPMR_Enchantment.enchantAll(chestplate);
				FOPMR_Enchantment.enchantAll(leggings);
				FOPMR_Enchantment.enchantAll(boots);
				FOPMR_Enchantment.enchantAll(helmet);
				inv.setHelmet(helmet);
				inv.setBoots(boots);
				inv.setLeggings(leggings);
				inv.setChestplate(chestplate);
			}
			break;
		case "multiEagle":
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack potato = new ItemStack(Material.POTATO, 1);
				ItemMeta meta = potato.getItemMeta();
				List<String> lore = Arrays.asList(ChatColor.DARK_PURPLE + "It's dangerous to go alone; take this!");
				meta.setLore(lore);
				potato.setItemMeta(meta);
				inv.addItem(potato);
			}
			break;
		case "TheLunarPrincess":
			StringBuilder output = new StringBuilder();
			Random randomGenerator = new Random();

			String[] words = "You have been given a Moonstone from the Moon Princess!".split(" ");
			for (String word : words) {
				String color_code = Integer.toHexString(1 + randomGenerator.nextInt(14));
				output.append(ChatColor.COLOR_CHAR).append(color_code).append(word).append(" ");
			}
			for (Player player : Bukkit.getOnlinePlayers()) {
				FOPMR_Commons.playerMsg(player, output.toString());
				PlayerInventory inv = player.getInventory();
				ItemStack moonstone = new ItemStack(Material.NETHER_STAR, 1);
				ItemMeta meta = moonstone.getItemMeta();
				List<String> lore = Arrays.asList(ChatColor.BLUE + "This mysterious stone",
						ChatColor.BLUE + "was given to you by", ChatColor.GOLD + "the Moon Princess!");
				meta.setDisplayName(CUtils_Methods.randomChatColour() + "" + ChatColor.BOLD + "Moonstone");
				meta.setLore(lore);
				moonstone.setItemMeta(meta);
				inv.addItem(moonstone);
			}
			break;
		case "Stampy100":
			FOPMR_Commons.adminAction("DarkDoge108", "Casting Doom Over All Doge Haters!", true);
			for (World world : Bukkit.getWorlds()) {
				for (Entity entity : world.getEntities()) {
					if (entity instanceof LivingEntity && !(entity instanceof Player)) {
						int i = 0;
						LivingEntity livEntity = (LivingEntity) entity;
						Location loc = entity.getLocation();
						do {
							world.strikeLightningEffect(loc);

							i++;
						} while (i <= 2);
						livEntity.setHealth(0);
					}
				}
			}
			break;
		case "Dev238":
			FOPMR_Commons.adminAction(sender.getName(), "You have been DEV'D!!!", true);
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				inv.addItem(new ItemStack(Material.SNOWBALL, 1));
			}
			break;
		case "CrafterSmith12":
			Player sender_craft = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.adminAction(sender_craft.getName(), "Cookies for all! Don't let others take yours!", true);
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack cookie = new ItemStack(Material.COOKIE, 1);
				cookie.addUnsafeEnchantment(Enchantment.KNOCKBACK, 100);
				ItemMeta meta = cookie.getItemMeta();
				meta.setDisplayName(ChatColor.GREEN + "Crafter's Cookie!");
				cookie.setItemMeta(meta);
				inv.addItem(cookie);
			}
			break;
		case "lukkan99":
			FOPMR_Commons.adminAction(sender.getName(),
					"When life gives you lemons, don't make lemonade! Make life take the lemons back! Get mad!", true);
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack glados = new ItemStack(Material.POTATO, 1);
				ItemMeta meta = glados.getItemMeta();
				meta.setDisplayName(CUtils_Methods.randomChatColour() + "" + ChatColor.BOLD + "GlaDOS");
				glados.setItemMeta(meta);
				inv.addItem(glados);
			}
			break;
		case "robotexplorer":
			Player sender_robot = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.adminAction(sender_robot.getName(), "You think you can outsmart a robot? I think NOT!", true);
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack robot = new ItemStack(Material.REDSTONE_BLOCK, 1);
				ItemMeta meta = robot.getItemMeta();
				meta.setDisplayName(ChatColor.RED + "Robot");
				robot.setItemMeta(meta);
				inv.addItem(robot);
			}
			break;
		case "tylerhyperHD":
			FOPMR_Commons.adminAction(ChatColor.RED + sender.getName(),
					ChatColor.WHITE + "" + ChatColor.BOLD + "BEHOLD! THE " + ChatColor.BLACK + "" + ChatColor.BOLD
							+ "LIE " + ChatColor.WHITE + "" + ChatColor.BOLD + "GOD!",
					true);
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack trip = new ItemStack(Material.CAKE, 1);
				ItemMeta meta = trip.getItemMeta();
				meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "THE " + ChatColor.BLACK + ""
						+ ChatColor.BOLD + "LIE " + ChatColor.WHITE + "" + ChatColor.BOLD + "GOD");
				trip.setItemMeta(meta);
				FOPMR_Enchantment.enchantAll(trip);
				inv.addItem(trip);
			}
			for (World world : Bukkit.getWorlds()) {
				for (Entity entity : world.getEntities()) {
					if (entity instanceof LivingEntity && !(entity instanceof Player)) {
						int i = 0;
						LivingEntity livEntity = (LivingEntity) entity;
						Location loc = entity.getLocation();
						do {
							world.strikeLightningEffect(loc);

							i++;
						} while (i <= 2);
						livEntity.setHealth(0);
					}
				}
				for (final Player player : Bukkit.getOnlinePlayers()) {
					for (double percent = 0.0; percent <= 1.0; percent += (1.0 / STEPS)) {
						final float pitch = (float) (percent * 2.0);

						new BukkitRunnable() {
							@Override
							public void run() {
								player.playSound(randomOffset(player.getLocation(), 5.0),
										Sound.values()[random.nextInt(Sound.values().length)], 100.0f, pitch);
							}
						}.runTaskLater(plugin, Math.round(20.0 * percent * 2.0));
					}
				}
			}
			break;
		case "Alex33856":
			Player sender_alex = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.adminAction(sender_alex.getName(), "Casting Doom All Over the World", true);
			for (Player player : Bukkit.getOnlinePlayers()) {
				FOPMR_Commons.bcastMsg(player.getName() + " has been a naughty, naughty boy.", ChatColor.GREEN);
				final Location targetPos = player.getLocation();
				final World world = player.getWorld();
				for (int x = -1; x <= 1; x++) {
					for (int z = -1; z <= 1; z++) {
						final Location strike_pos = new Location(world, targetPos.getBlockX() + x,
								targetPos.getBlockY(), targetPos.getBlockZ() + z);
						world.strikeLightning(strike_pos);
					}
				}
			}
			FOPMR_Commons.bcastMsg("Alex33856 - Has taken over the world!", ChatColor.RED);
			break;
		case "RedSeaMC":
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack red = new ItemStack(Material.REDSTONE_BLOCK, 1);
				FOPMR_Enchantment.enchantAll(red);
				ItemMeta meta = red.getItemMeta();
				World world = player.getWorld();
				Location loc = player.getLocation();
				meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "THE " + ChatColor.RED + "" + ChatColor.BOLD
						+ "RED " + ChatColor.WHITE + "" + ChatColor.BOLD + "SEA");
				List<String> lore = Arrays.asList(ChatColor.BLUE + "This protects you from the red sea. Wow.");
				meta.setLore(lore);
				red.setItemMeta(meta);
				inv.setHelmet(red);
				FOPMR_Enchantment.enchantAll(red);
				world.strikeLightningEffect(loc);
			}
			Player sender_red = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.adminAction(sender_red.getName(), "Gracing the world with seas of red!", true);
			break;
		case "Triplewer":
			FOPMR_Commons.adminAction(ChatColor.RED + sender.getName(),
					ChatColor.WHITE + "" + ChatColor.BOLD + "BEHOLD! THE " + ChatColor.BLUE + "" + ChatColor.BOLD
							+ "WATER " + ChatColor.WHITE + "" + ChatColor.BOLD + "GOD!",
					true);
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack trip = new ItemStack(Material.WATER_BUCKET, 1);
				ItemMeta meta = trip.getItemMeta();
				meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "THE " + ChatColor.BLUE + ""
						+ ChatColor.BOLD + "WATER " + ChatColor.WHITE + "" + ChatColor.BOLD + "GOD");
				trip.setItemMeta(meta);
				inv.addItem(trip);
			}
			for (World world : Bukkit.getWorlds()) {
				for (Entity entity : world.getEntities()) {
					if (entity instanceof LivingEntity && !(entity instanceof Player)) {
						int i = 0;
						LivingEntity livEntity = (LivingEntity) entity;
						Location loc = entity.getLocation();
						do {
							world.strikeLightningEffect(loc);

							i++;
						} while (i <= 2);
						livEntity.setHealth(0);
					}
				}
				for (final Player player : Bukkit.getOnlinePlayers()) {
					for (double percent = 0.0; percent <= 1.0; percent += (1.0 / STEPS)) {
						final float pitch = (float) (percent * 2.0);

						new BukkitRunnable() {
							@Override
							public void run() {
								player.playSound(randomOffset(player.getLocation(), 5.0),
										Sound.values()[random.nextInt(Sound.values().length)], 100.0f, pitch);
							}
						}.runTaskLater(plugin, Math.round(20.0 * percent * 2.0));
					}
				}
			}
			break;
		case "GigaByte_Jr":
			Player sender_giga = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.asciiDog();
			FOPMR_Commons.adminAction(sender_giga.getName(), "Giving everyone a pet Woofie.\nTame them with the bone!",
					false);
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				inv.addItem(new ItemStack(Material.BONE, 1));
				LivingEntity dog = (LivingEntity) player.getWorld().spawnEntity(player.getLocation(), EntityType.WOLF);
				dog.setCustomNameVisible(true);
				dog.setCustomName(ChatColor.DARK_AQUA + "Woofie!");
			}
			break;
		case "DeerBoo":
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				inv.addItem(new ItemStack(Material.COOKIE, 1));
				Player sender_deer = Bukkit.getPlayer(sender.getName());
				FOPMR_Commons.adminAction(sender_deer.getName(), "There you go my deer", true);
			}
			break;
		case "Ninjaristic":
			FOPMR_Commons.asciiHorse();
			FOPMR_Commons.bcastMsg("NEIGH", ChatColor.RED);
			break;
		case "0sportguy0":
			Player sender_sport = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.adminAction(sender_sport.getName(), "An apple a day keeps the doctor away!", false);
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				inv.addItem(new ItemStack(Material.ENCHANTED_GOLDEN_APPLE, 1));
			}
			break;
		case "Lehctas":
			Player sender_lehctas = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.adminAction(sender_lehctas.getName(), "Giving everyone a wand that doesn't work", true);
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack wand = new ItemStack(Material.STICK, 1);
				ItemMeta meta = wand.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_PURPLE + "Void Wand");
				List<String> lore = Arrays.asList(ChatColor.BLUE
						+ "Void wand given by Lehctas, You wish you can use it. But haha. nerd. You can't only Lehctas can!");
				meta.setLore(lore);
				wand.setItemMeta(meta);
				inv.addItem(wand);
			}
		case "aggelosQQ":
			Player sender_aggy = Bukkit.getPlayer(sender.getName());
			FOPMR_Commons.adminAction(sender_aggy.getName(), "Giving everyone a free egg! EGG FIGHT!", true);
			for (Player player : Bukkit.getOnlinePlayers()) {
				PlayerInventory inv = player.getInventory();
				ItemStack egg = new ItemStack(Material.EGG, 1);
				ItemMeta meta = egg.getItemMeta();
				meta.setDisplayName(ChatColor.DARK_GREEN + "eggelosQQ's" + ChatColor.AQUA + " Egg");
				meta.addEnchant(Enchantment.KNOCKBACK, 320, true);
				egg.setItemMeta(meta);
				inv.addItem(egg);
			}
			break;
		default:
			FOPMR_Commons.playerMsg(sender,
					"Unfortunately, you do not have a personal command defined\nPlease contact tylerhyperHD to request a personal command or\ngo to http://3p1cfreedomcraft.boards.net/ to go get one.",
					ChatColor.AQUA);
			break;
		}
		return true;
	}

	private static final Random random = new Random();
	public static final double STEPS = 10.0;

	private static Location randomOffset(Location a, double magnitude) {
		return a.clone().add(randomDoubleRange(-1.0, 1.0) * magnitude, randomDoubleRange(-1.0, 1.0) * magnitude,
				randomDoubleRange(-1.0, 1.0) * magnitude);
	}

	private static Double randomDoubleRange(double min, double max) {
		return min + (random.nextDouble() * ((max - min) + 1.0));
	}
}