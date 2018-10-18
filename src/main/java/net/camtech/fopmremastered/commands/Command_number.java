/*
 * New command based on skillz
 * 
 */

package net.camtech.fopmremastered.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;

@CommandParameters(name = "number", description = "Damn number program.", usage = "/number [xxx], [xxx], [xxx]")
public class Command_number
{
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args)
    {
    	int[] randomnum = new int[3];
    	
    	if (args.length == 0) {
    		return false;
    	}
    	if (args.length > 1) {
    		for(int argument = args.length; argument > 0; argument = argument - 1) {
    			randomnum[args.length] = Integer.parseInt(args[args.length]);
    			sender.sendMessage(randomnum.toString());
    			return true;
    		}
    	}
    	return false;
    }
}
