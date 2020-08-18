package dev.ichigo.ffa.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.ichigo.ffa.util.LocationHelper;

public class FFACommand extends Command {
	
	public FFACommand() {
		super("ffa");
		this.setDescription(ChatColor.RED + "Used for set different location :)");
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.DARK_RED + "Sorry but this command is just allowed for player!");
			return false;
		}
		if (!sender.hasPermission("ffa.command.admin")) {
			sender.sendMessage(ChatColor.RED + "FFA Plugin maded by Ichigo " + ChatColor.WHITE + "(V1.0)");
			return false;
		}
		if (args.length == 0) {
			sender.sendMessage(ChatColor.RED + "/ffa set (spawn/potion/uhc)");
			return false;
		}
		if (args[0].equalsIgnoreCase("set")) {
			if (args.length == 1) {
				sender.sendMessage(ChatColor.RED + "Please enter a valid location (spawn/potion/uhc)");
				return false;	
			}
			if (args.length == 2) {
				final Player player = (Player)sender;
				if (args[1].equalsIgnoreCase("spawn")) {
                    LocationHelper.getLocationHelper(args[1]).setLocation(player.getLocation());
					player.sendMessage(ChatColor.GREEN + "You've been set the spawn location for the ffa!");
					return false;
				}
				if (args[1].equalsIgnoreCase("spawn")) {
                    LocationHelper.getLocationHelper(args[1]).rushpoints.add(player.getLocation());
                    LocationHelper.getLocationHelper(args[1]).setLocation(player.getLocation());
					player.sendMessage(ChatColor.GREEN + "You've been set the spawn location for the ffa!");
					return false;
				}
				if (args[1].equalsIgnoreCase("potion")) {
                    LocationHelper.getLocationHelper(args[1]).setLocation(player.getLocation());
					player.sendMessage(ChatColor.GREEN + "You've been set the potion location for the ffa!");
					return false;
				}
				if (args[1].equalsIgnoreCase("uhc")) {
                    LocationHelper.getLocationHelper(args[1]).setLocation(player.getLocation());
					player.sendMessage(ChatColor.GREEN + "You've been set the uhc location for the ffa!");
					return false;
				}
				else {
					player.sendMessage(ChatColor.RED + "Enter a provide location!");
				}
			}
			else {
				sender.sendMessage(ChatColor.RED + "Please enter a valid location (spawn/potion/uhc)");
			}
		}
		return false;
	}

}
