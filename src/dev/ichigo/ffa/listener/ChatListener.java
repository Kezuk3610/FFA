package dev.ichigo.ffa.listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import dev.ichigo.ffa.manager.PlayerManager;

public class ChatListener implements Listener {
	
	@EventHandler
	public void PlayerChat(AsyncPlayerChatEvent event) {
		final Player player = event.getPlayer();
		final PlayerManager pm = PlayerManager.getPlayerManagers().get(player.getUniqueId());
		event.setFormat(ChatColor.GRAY + "[" + ChatColor.RED + pm.getLeague() + ChatColor.GRAY + "] " + ChatColor.GREEN + "%1$s" + ChatColor.GRAY + ": " + ChatColor.WHITE + "%2$s");
	}

}
