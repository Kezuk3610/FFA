package dev.ichigo.ffa.listener;

import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.scheduler.BukkitRunnable;

import dev.ichigo.ffa.FFA;
import dev.ichigo.ffa.manager.PlayerManager;

public class BlockListeners implements Listener {
	
	@EventHandler
	public void ResetBlock(final BlockPlaceEvent event) {
		if (event.getBlockPlaced().getType() == Material.WOOD) {
			final PlayerManager pm = PlayerManager.getPlayerManagers().get(event.getPlayer().getUniqueId());
			pm.setBlock(pm.getBlock() + 0.5f);
			new BukkitRunnable() {			
				@Override
				public void run() {
					if (event.getBlock().getType() == Material.WOOD) {
						event.getBlock().setType(Material.AIR);
					}
				}
			}.runTaskLater(FFA.getInstance(), 400L);
		}
		if (event.getBlockPlaced().getType() == Material.COBBLESTONE) {
			final PlayerManager pm = PlayerManager.getPlayerManagers().get(event.getPlayer().getUniqueId());
			pm.setBlock(pm.getBlock() + 1.0f);
			new BukkitRunnable() {			
				@Override
				public void run() {
					if (event.getBlock().getType() == Material.COBBLESTONE) {
						event.getBlock().setType(Material.AIR);
					}
				}
			}.runTaskLater(FFA.getInstance(), 400L);
		}
	}

}
