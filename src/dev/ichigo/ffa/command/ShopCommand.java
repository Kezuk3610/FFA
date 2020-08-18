package dev.ichigo.ffa.command;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import dev.ichigo.ffa.FFA;
import dev.ichigo.ffa.enums.ProfileStatus;
import dev.ichigo.ffa.manager.PlayerManager;

public class ShopCommand extends Command {
	
	public ShopCommand() {
		super("shop");
		this.setDescription(ChatColor.RED + "Used for open the gui of shop");
	}

	@Override
	public boolean execute(CommandSender sender, String label, String[] args) {
		if (!(sender instanceof Player)) {
			sender.sendMessage(ChatColor.RED + "This command is only allowed to a player!");
			return false;
		}
		final Player player = (Player)sender;
		final PlayerManager pm = PlayerManager.getPlayerManagers().get(player.getUniqueId());
		if (pm.getStatus() == ProfileStatus.SPAWN) {
			player.openInventory(FFA.getInstance().shop.inventory());
			return false;
		}
		else {
			player.sendMessage(ChatColor.RED + "You can't perform command");
		}
		return false;
	}
}
