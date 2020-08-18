package dev.ichigo.ffa.listener;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockBurnEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerItemConsumeEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import dev.ichigo.ffa.FFA;
import dev.ichigo.ffa.enums.ProfileStatus;
import dev.ichigo.ffa.manager.PlayerManager;
import dev.ichigo.ffa.util.ChunkLoader;

public class PlayerListener implements Listener {
	
	@EventHandler
	public void PlayerJoin(final PlayerJoinEvent event) {
		event.setJoinMessage(null);
		final Player player = event.getPlayer();
		ChunkLoader.loadChunks(player.getLocation());
        PlayerManager pm = new PlayerManager(player.getUniqueId());
        pm.setUuid(player.getUniqueId());
        pm.createPlayerData(player);
    	pm.reset(player, GameMode.SURVIVAL);
    	pm.setStatus(ProfileStatus.SPAWN);
    	pm.teleport(player, FFA.getInstance().spawn);
    	pm.sendKit(FFA.getInstance().spawnInv);
    	FFA.getInstance().atSpawn.add(player.getUniqueId());
    	for (Player players : Bukkit.getOnlinePlayers()) {
    		final PlayerManager pmAll = PlayerManager.getPlayerManagers().get(players.getUniqueId());
    		if (pmAll.getStatus() != ProfileStatus.SPAWN) {
        		players.hidePlayer(player);
        		player.hidePlayer(players);
    		}
    		else {
    			players.showPlayer(player);
    			player.showPlayer(players);
    		}
    	}
	}
	
	@EventHandler
	public void PlayerQuit(final PlayerQuitEvent event) {
		event.setQuitMessage(null);
		
		final Player player = event.getPlayer();
		PlayerManager.getPlayerManagers().get(player.getUniqueId()).removePlayerData(player.getUniqueId());
		if (FFA.getInstance().inPotion.contains(player.getUniqueId())) {
			FFA.getInstance().inPotion.remove(player.getUniqueId());
		}
		if (FFA.getInstance().inUhc.contains(player.getUniqueId())) {
			FFA.getInstance().inUhc.remove(player.getUniqueId());
		}
		if (FFA.getInstance().atSpawn.contains(player.getUniqueId())) {
			FFA.getInstance().atSpawn.remove(player.getUniqueId());
		}
	}

    @SuppressWarnings({ "incomplete-switch", "deprecation" })
	@EventHandler
    public void onPlayerConsumeItem(PlayerItemConsumeEvent event) {
        Player player = event.getPlayer();
        PlayerManager playerData = PlayerManager.getPlayerManagers().get(player.getUniqueId());
        Material drop = event.getItem().getType();
        switch (playerData.getStatus()) {
            case POTION: {
                if (drop.getId() != 373) break;
                Bukkit.getServer().getScheduler().runTaskLaterAsynchronously((Plugin)FFA.getInstance(), () -> {
                    player.setItemInHand(new ItemStack(Material.AIR));
                    player.updateInventory();
                }, 1L);
                break;
            }
        }
    }
    
    @EventHandler
    public void onPlayerItemConsume(final PlayerItemConsumeEvent event) {
        if (event.getItem().getType() == Material.GOLDEN_APPLE) {
            if (!event.getItem().hasItemMeta() || !event.getItem().getItemMeta().getDisplayName().contains("Golden Head")) {
                return;
            }
            final PlayerManager playerData = PlayerManager.getPlayerManagers().get(event.getPlayer().getUniqueId());
            if (playerData.getStatus() == ProfileStatus.UHC) {
                final Player player = event.getPlayer();
                if (event.getItem().getType() == Material.GOLDEN_APPLE) {
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.REGENERATION, 200, 1));
                    event.getPlayer().addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 2400, 0));
                    player.setFoodLevel(Math.min(player.getFoodLevel() + 6, 20));
                }
            }
        }
    }
	
	@EventHandler
	public void DeathEvent(final PlayerDeathEvent event) {
		event.setDeathMessage(null);
		event.getDrops().clear();	
		final Player player = event.getEntity().getPlayer();
		final Player killer = event.getEntity().getKiller();
        new BukkitRunnable() {
            public void run() {
                try {
                    final Object nmsPlayer = event.getEntity().getClass().getMethod("getHandle", (Class<?>[])new Class[0]).invoke(event.getEntity(), new Object[0]);
                    final Object con = nmsPlayer.getClass().getDeclaredField("playerConnection").get(nmsPlayer);
                    final Class<?> EntityPlayer = Class.forName(nmsPlayer.getClass().getPackage().getName() + ".EntityPlayer");
                    final Field minecraftServer = con.getClass().getDeclaredField("minecraftServer");
                    minecraftServer.setAccessible(true);
                    final Object mcserver = minecraftServer.get(con);
                    final Object playerlist = mcserver.getClass().getDeclaredMethod("getPlayerList", (Class<?>[])new Class[0]).invoke(mcserver, new Object[0]);
                    final Method moveToWorld = playerlist.getClass().getMethod("moveToWorld", EntityPlayer, Integer.TYPE, Boolean.TYPE);
                    moveToWorld.invoke(playerlist, nmsPlayer, 0, false);
                    killer.hidePlayer(player);
                    player.hidePlayer(killer);
            		final PlayerManager pm = PlayerManager.getPlayerManagers().get(player.getUniqueId());
            		final PlayerManager pmTarget = PlayerManager.getPlayerManagers().get(killer.getUniqueId());
            		if (FFA.getInstance().inPotion.contains(player.getUniqueId())) {
            			FFA.getInstance().inPotion.remove(player.getUniqueId());
            		}
            		if (FFA.getInstance().inUhc.contains(player.getUniqueId())) {
            			FFA.getInstance().inUhc.remove(player.getUniqueId());
            		}
            		pm.setDeath(pm.getDeath() + 1);
            		pm.setEnderPearl(0);
            		pm.setExp(pm.getExp() - 5.0f);
            		player.setLevel(0);
            		pm.setKillstreak(0);
            		pmTarget.setExp(pmTarget.getExp() + 10.0f);
            		pmTarget.setKill(pmTarget.getKill() + 1);
            		pmTarget.setCoins(pmTarget.getCoins() + 5);
            		killer.sendMessage(ChatColor.GREEN + "You received five coins!");
            		if (pmTarget.getExp() >= 25.0f) {
            			pmTarget.setLeague("Silver II");
            			killer.sendMessage(ChatColor.RED + "You'r now to Silver II league!");
            		}
            		if (pm.getExp() >= 250.75f) {
            			pm.setLeague("Silver II");
            			player.sendMessage(ChatColor.RED + "You'r now to Silver II league!");
            		}
            		if (pm.getExp() >= 750.50f) {
            			pm.setLeague("Silver III");
            			player.sendMessage(ChatColor.RED + "You'r now to Silver III league!");
            		}
            		if (pm.getExp() >= 1250.50f) {
            			pm.setLeague("Gold I");
            			player.sendMessage(ChatColor.RED + "You'r now to Gold I league!");
            		}
            		if (pmTarget.getKill() == 5) {
            			pmTarget.setKillstreak(1);
            			Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "FFA" + ChatColor.GRAY + "]" + " " + ChatColor.GREEN + killer.getName() + ChatColor.WHITE + " is walking on killstreak of " + ChatColor.DARK_RED + "5");
            			pmTarget.setExp(pmTarget.getExp() + 50.75f);
            		}
            		if (pmTarget.getKill() > 5) {
            			pmTarget.setKillstreak(pmTarget.getKillstreak() + 1);
            		}
            		if (pmTarget.getKill() == 25) {
            			pmTarget.setKillstreak(pmTarget.getKillstreak() + 1);
            			Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "FFA" + ChatColor.GRAY + "]" + " " + ChatColor.GREEN + killer.getName() + ChatColor.WHITE + " is walking on killstreak of " + ChatColor.DARK_RED + "25");
            			pmTarget.setExp(pmTarget.getExp() + 500.25f);
            		}
                	FFA.getInstance().atSpawn.add(player.getUniqueId());
            		Bukkit.broadcastMessage(ChatColor.GRAY + "[" + ChatColor.DARK_RED + "FFA" + ChatColor.GRAY + "]" + " " + ChatColor.RED + player.getName() + ChatColor.WHITE + " was killed by " + ChatColor.GREEN + killer.getName());
            		player.sendMessage(ChatColor.RED + "You are killed by " + ChatColor.GREEN + killer.getName());
                	pm.reset(player, GameMode.SURVIVAL);
                	pm.sendKit(FFA.getInstance().spawnInv);
                	pm.teleport(player, FFA.getInstance().spawn);
                	pm.setStatus(ProfileStatus.SPAWN);
                }
                catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }.runTaskLater((Plugin)FFA.getInstance(), 2L);
        return;
	}
	
    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        PlayerManager playerManager = PlayerManager.getPlayerManagers().get(e.getPlayer().getUniqueId());
        if(playerManager.getStatus() == ProfileStatus.POTION || playerManager.getStatus() == ProfileStatus.UHC) {
            if(e.getItemDrop().getItemStack().getType() == Material.DIAMOND_SWORD
                    || e.getItemDrop().getItemStack().getType() == Material.IRON_AXE
                    || e.getItemDrop().getItemStack().getType() == Material.DIAMOND_SWORD
                    || e.getItemDrop().getItemStack().getType() == Material.STONE_SWORD){ e.setCancelled(true);
                    return; }
            Bukkit.getScheduler().runTaskLater(FFA.getInstance(), (Runnable) new Runnable() {
                public void run() {
                    e.getItemDrop().remove();
                }
            }, 200);
        }
        else{
            e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onBreak(final BlockBreakEvent e) {
    	if (PlayerManager.getPlayerManagers().get(e.getPlayer().getUniqueId()).getStatus() == ProfileStatus.SPAWN) {
    		e.setCancelled(true);
    		return;
    	}
    	if (PlayerManager.getPlayerManagers().get(e.getPlayer().getUniqueId()).getStatus() == ProfileStatus.POTION) {
    		e.setCancelled(true);
    		return;
    	}
        if (PlayerManager.getPlayerManagers().get(e.getPlayer().getUniqueId()).getStatus() == ProfileStatus.UHC) {
        	if (e.getBlock().getType() == Material.COBBLESTONE || e.getBlock().getType() == Material.WOOD) {
        		e.setCancelled(false);
        		return;
        	}
        	else {
        		e.setCancelled(true);
        	}
        }
    }
    
    @EventHandler
    public void onPlace(final BlockPlaceEvent e) {
        if(PlayerManager.getPlayerManagers().get(e.getPlayer().getUniqueId()).getStatus() != ProfileStatus.UHC) {
        	e.setCancelled(true);
        }
    }
    
    @EventHandler
    public void onEnder(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        PlayerManager pm = PlayerManager.getPlayerManagers().get(p.getUniqueId());
        ItemStack current = e.getItem();

        if (current == null) return;

        if (current.getType() == Material.ENDER_PEARL) {
            if(pm.getStatus() == ProfileStatus.POTION) {

                if (e.getAction() == Action.RIGHT_CLICK_AIR || e.getAction() == Action.RIGHT_CLICK_BLOCK) {

                    if(pm.getEnderPearl() == 0) {
                        pm.setEnderPearl(15);
                        p.setLevel(15);
                        new BukkitRunnable() {
                            public void run() {
                                    pm.setEnderPearl(pm.getEnderPearl()-1);
                                    p.setLevel(pm.getEnderPearl());
                                    if(pm.getEnderPearl() == 0) {
                                        this.cancel();
                                    }
                            }
                        }.runTaskTimer((Plugin) FFA.getInstance(), 20L, 20L);
                    }
                    else {
                        e.setCancelled(true);
                        p.updateInventory();
                        p.sendMessage(ChatColor.LIGHT_PURPLE + "You can use enderpearl in " + ChatColor.GRAY + pm.getEnderPearl() + ChatColor.LIGHT_PURPLE + " second(s)");
                    }
                }
            }
            else {
                e.setCancelled(true);
                p.updateInventory();
            }
        }
    }
    
    @EventHandler
    public void onFood(FoodLevelChangeEvent event) {
    	Player player = (Player) event.getEntity();
        PlayerManager playerData = PlayerManager.getPlayerManagers().get(event.getEntity().getUniqueId());
        if (playerData.getStatus() != ProfileStatus.POTION || playerData.getStatus() != ProfileStatus.UHC) {
            event.setCancelled(true);
            return;
        }
        if (!event.isCancelled()) {
            if (event.getFoodLevel() < player.getFoodLevel()) {
                event.setCancelled(new Random().nextInt(100) < 66);
            }
        }
    }
    
	@EventHandler
    public void onInteract(PlayerInteractEvent event) {
        ItemStack current = event.getItem();
        if(current == null) return;
        Action action = event.getAction();
        if(current.hasItemMeta()) {
            if (action == Action.RIGHT_CLICK_AIR || action == Action.RIGHT_CLICK_BLOCK) {
                Player player = event.getPlayer();
                PlayerManager playerManager = PlayerManager.getPlayerManagers().get(player.getUniqueId());
                if(playerManager.getStatus() == ProfileStatus.SPAWN) {
                    event.setCancelled(true);
                    if(current.getType() == Material.GOLD_AXE && current.getItemMeta().getDisplayName().equals(ChatColor.DARK_RED + "Click for play!")) {
                    	player.openInventory(FFA.getInstance().chooseKit.inventory());
                    }
                    if(current.getType() == Material.SKULL_ITEM && current.getItemMeta().getDisplayName().equals(ChatColor.RED + "Informations")) {
                    	player.openInventory(FFA.getInstance().info.inventory());
                    }
                    if(current.getType() == Material.EMERALD && current.getItemMeta().getDisplayName().equals(ChatColor.RED + "Shop")) {
                    	player.openInventory(FFA.getInstance().shop.inventory());
                    }
                }
                else {
                	event.setCancelled(false);
                }
            }
        }
	}
}
