package dev.ichigo.ffa.listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import dev.ichigo.ffa.enums.ProfileStatus;
import dev.ichigo.ffa.manager.PlayerManager;

public class EntityListener implements Listener {  
	
    @EventHandler
    public void onEntityDamage(final EntityDamageEvent e) {
        if (e.getEntity() instanceof Player) {
            final Player player = (Player)e.getEntity();
            final PlayerManager playerData = PlayerManager.getPlayerManagers().get(player.getUniqueId());
            if (playerData.getStatus() == ProfileStatus.SPAWN) {
            	e.setCancelled(true);
            	return;
            }
            if (playerData.getStatus() == ProfileStatus.POTION) {
            	e.setCancelled(false);
            	return;
            }
            if (playerData.getStatus() == ProfileStatus.UHC) {
            	e.setCancelled(false);
            	return;
            }
        }
    }
    
	@EventHandler
    public void onEntityDamageByEntity(final EntityDamageByEntityEvent e) {
        if (!(e.getEntity() instanceof Player)) {
            e.setCancelled(true);
            return;
        }
        final Player entity = (Player)e.getEntity();
        final PlayerManager pm = PlayerManager.getPlayerManagers().get(entity.getUniqueId());
        if (pm.getStatus() == ProfileStatus.SPAWN) {
            e.setCancelled(true);
            return;
        }
        if (pm.getStatus() == ProfileStatus.UHC) {
        	e.setCancelled(false);
        	return;
        }
        if (pm.getStatus() == ProfileStatus.POTION) {
        	e.setCancelled(false);
        	return;
        }
    }
}
