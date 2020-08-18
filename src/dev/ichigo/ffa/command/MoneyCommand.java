package dev.ichigo.ffa.command;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.ichigo.ffa.manager.PlayerManager;

public class MoneyCommand extends Command {
	
	Random random;
	
	public MoneyCommand() {
		super("money");
		this.setDescription(ChatColor.RED + "Used for see a player wallet");
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "Sorry but this command is only allowed to a player!");
			return false;
		}
		final Player player = (Player)sender;
		final PlayerManager pm = PlayerManager.getPlayerManagers().get(player.getUniqueId());
		if (args.length == 0) {
			player.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "Money-FFA" + ChatColor.GRAY + "]" + ChatColor.WHITE + " You have " + ChatColor.DARK_RED + pm.getCoins() + ChatColor.WHITE + " coins");
			return false;
		}
		if (args.length == 1) {
			final Player target = Bukkit.getPlayer(args[0]);
			final PlayerManager pmTarget = PlayerManager.getPlayerManagers().get(target.getUniqueId());
			if (args[0].contains(target.getName())) {
				final Player targetcheck = Bukkit.getPlayer(args[0]);
				if (targetcheck == null) {
					player.sendMessage(ChatColor.RED + "Sorry but this player is not connected!");
					return false;
				}
				if (targetcheck == player) {
					player.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "Money-FFA" + ChatColor.GRAY + "]" + ChatColor.WHITE + " You have " + ChatColor.DARK_RED + pm.getCoins() + ChatColor.WHITE + " coins");
					return false;
				}
				else {
					player.sendMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "Money-FFA" + ChatColor.GRAY + "] " + ChatColor.GREEN + target.getName() + ChatColor.WHITE + " have " + ChatColor.DARK_RED + pmTarget.getCoins() + ChatColor.WHITE + " coins");
					return false;
				}
			}
			else {
				sender.sendMessage(ChatColor.RED + "Enter a correct subcommand!");
				return false;
			}
		}
		if (args.length == 3) {
			if (args[0].equalsIgnoreCase("give")) {
				if (args.length < 3 || args.length > 3) {
					player.sendMessage(ChatColor.RED + "/money give <player> <amount>");
					return false;
				}
				final Player targetcheck = Bukkit.getPlayer(args[1]);
				if (targetcheck == null) {
					player.sendMessage(ChatColor.RED + "This target ins't connected!");
					return false;
				}
				else {
					final int coins = Integer.parseInt(args[2]);
					player.sendMessage(ChatColor.RED + "You have gift " + ChatColor.WHITE + args[2] + ChatColor.RED + " to " + ChatColor.GREEN + args[1]);
					final PlayerManager pmTarget = PlayerManager.getPlayerManagers().get(targetcheck.getUniqueId());
					pmTarget.setCoins(pmTarget.getCoins() + coins);
				}
			}
			if (args[0].equalsIgnoreCase("remove")) {
				final Player targetcheck = Bukkit.getPlayer(args[1]);
				if (targetcheck == null) {
					player.sendMessage(ChatColor.RED + "This target ins't connected!");
					return false;
				}
				else {
					final int coins = Integer.parseInt(args[2]);
					player.sendMessage(ChatColor.RED + "You have removed " + ChatColor.WHITE + args[2] + ChatColor.RED + " to " + ChatColor.GREEN + args[1]);
					final PlayerManager pmTarget = PlayerManager.getPlayerManagers().get(targetcheck.getUniqueId());
					pmTarget.setCoins(pmTarget.getCoins() - coins);
				}
			}
		}
		else {
			sender.sendMessage(ChatColor.RED + "Enter a correct subcommand!");
		}
		return false;
	}

}
