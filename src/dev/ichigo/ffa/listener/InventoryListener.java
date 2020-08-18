package dev.ichigo.ffa.listener;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import dev.ichigo.ffa.FFA;
import dev.ichigo.ffa.enums.ProfileStatus;
import dev.ichigo.ffa.manager.PlayerManager;
import dev.jiraiya.paper.JiraPaper;
import dev.jiraiya.paper.potion.config.PotionValues;

public class InventoryListener implements Listener {
	
	@EventHandler
    public void onClick(InventoryClickEvent event) {
        ItemStack current = event.getCurrentItem();
        if (current == null) return;
        Inventory inventory = event.getClickedInventory();
        Player player = (Player) event.getWhoClicked();
        PlayerManager playerManager = PlayerManager.getPlayerManagers().get(player.getUniqueId());
        if (playerManager.getStatus() == ProfileStatus.SPAWN) {
            event.setCancelled(true);
            if (current.hasItemMeta()) {
            	if (inventory.getName().equals(ChatColor.DARK_RED + "FFA:")) {
            		event.setCancelled(true);
                	if (current.getType() == Material.POTION && current.getItemMeta().getDisplayName().equals(ChatColor.RED.toString() + ChatColor.BOLD + "Potions")) {
                		event.setCancelled(true);
                		playerManager.reset(player, GameMode.SURVIVAL);
                		playerManager.teleport(player, FFA.getInstance().potion);
                		playerManager.sendKit(FFA.getInstance().potionInv);
                		playerManager.setStatus(ProfileStatus.POTION);
                		FFA.getInstance().atSpawn.remove(player.getUniqueId());
                		FFA.getInstance().inPotion.add(player.getUniqueId());
                		for (Player players : Bukkit.getOnlinePlayers()) {
                			final PlayerManager pmAll = PlayerManager.getPlayerManagers().get(players.getUniqueId());
                			if (pmAll.getStatus() == ProfileStatus.POTION) {
                				players.showPlayer(player);
                				player.showPlayer(players);
                			}
                			else {
                				players.hidePlayer(player);
                				player.hidePlayer(players);
                			}
                		}
                	}
                	if (current.getType() == Material.LAVA_BUCKET && current.getItemMeta().getDisplayName().equals(ChatColor.RED.toString() + ChatColor.BOLD + "Build-UHC")) {
                		event.setCancelled(true);
                		player.closeInventory();
                		playerManager.reset(player, GameMode.SURVIVAL);
                		playerManager.teleport(player, FFA.getInstance().uhc);
                		playerManager.sendKit(FFA.getInstance().uhcInv);
                		playerManager.setStatus(ProfileStatus.UHC);
                		FFA.getInstance().atSpawn.remove(player.getUniqueId());
                		FFA.getInstance().inUhc.add(player.getUniqueId());
                		for (Player players : Bukkit.getOnlinePlayers()) {
                			final PlayerManager pmAll = PlayerManager.getPlayerManagers().get(players.getUniqueId());
                			if (pmAll.getStatus() == ProfileStatus.UHC) {
                				players.showPlayer(player);
                				player.showPlayer(players);
                			}
                			else {
                				players.hidePlayer(player);
                				player.hidePlayer(players);
                			}
                		}
                	}
            	}
            	if (inventory.getName().equals(ChatColor.DARK_RED + "Shop:")) {
                	if (current.getType() == Material.IRON_INGOT && current.getItemMeta().getDisplayName().equals(ChatColor.YELLOW + "Basic")) {
                		if (playerManager.getCoins() < 200) {
                			player.closeInventory();
                			player.sendMessage(ChatColor.RED + "Sorry but you didn't have any money for buy this!");
                			player.sendMessage(ChatColor.WHITE + "Your wallet: " + ChatColor.GREEN + playerManager.getCoins() + ChatColor.GRAY + "/" + ChatColor.DARK_RED + "200");
                			return;
                		}
                		if (playerManager.getCoins() >= 200) {
                			player.closeInventory();
                			player.sendMessage(ChatColor.RED + "You've been buy the basic rank!");
                			playerManager.setCoins(playerManager.getCoins() - 200);
                		}
                	}
                	if (current.getType() == Material.GOLD_INGOT && current.getItemMeta().getDisplayName().equals(ChatColor.GOLD + "Elite")) {
                		if (playerManager.getCoins() < 600) {
                			player.closeInventory();
                			player.sendMessage(ChatColor.RED + "Sorry but you didn't have any money for buy this!");
                			player.sendMessage(ChatColor.WHITE + "Your wallet: " + ChatColor.GREEN + playerManager.getCoins() + ChatColor.GRAY + "/" + ChatColor.DARK_RED + "600");
                			return;
                		}
                		if (playerManager.getCoins() >= 600) {
                			player.closeInventory();
                			player.sendMessage(ChatColor.RED + "You've been buy the elite rank!");
                			playerManager.setCoins(playerManager.getCoins() - 600);
                		}
                	}
                	if (current.getType() == Material.DIAMOND && current.getItemMeta().getDisplayName().equals(ChatColor.DARK_AQUA + "Hunter")) {
                		if (playerManager.getCoins() < 1400) {
                			player.closeInventory();
                			player.sendMessage(ChatColor.RED + "Sorry but you didn't have any money for buy this!");
                			player.sendMessage(ChatColor.WHITE + "Your wallet: " + ChatColor.GREEN + playerManager.getCoins() + ChatColor.GRAY + "/" + ChatColor.DARK_RED + "1400");
                			return;
                		}
                		if (playerManager.getCoins() >= 1400) {
                			player.closeInventory();
                			player.sendMessage(ChatColor.RED + "You've been buy the hunter rank!");
                			playerManager.setCoins(playerManager.getCoins() - 1400);
                		}
                	}
            	}
            	if (inventory.getName().equals(ChatColor.DARK_RED + "Informations:")) {
                	if (current.getType() == Material.NAME_TAG && current.getItemMeta().getDisplayName().equals(ChatColor.RED + "League")) {
                		player.openInventory(FFA.getInstance().league.inventory());
                	}
                	if (current.getType() == Material.PAPER && current.getItemMeta().getDisplayName().equals(ChatColor.RED + "Your Stats")) {
                		player.closeInventory();
                    	player.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------");
                    	player.sendMessage(ChatColor.RED + "User: " + ChatColor.DARK_RED + player.getName());
                    	player.sendMessage("");
                    	player.sendMessage(ChatColor.WHITE + "Kills: " + ChatColor.DARK_RED + playerManager.getKill());
                    	player.sendMessage(ChatColor.WHITE + "Death: " + ChatColor.DARK_RED + playerManager.getDeath());
                    	player.sendMessage("");
                    	player.sendMessage(ChatColor.WHITE + "Money: " + ChatColor.DARK_RED + playerManager.getCoins());
                    	player.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "--------------------------");
                	}
            	}
            	if (inventory.getName().equals(ChatColor.DARK_RED + "League:")) {
            		event.setCancelled(true);
                	if (current.getType() == Material.PAPER && current.getItemMeta().getDisplayName().equals(ChatColor.RED + "Your League")) {
                		player.closeInventory();
                		player.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-----------------");
                		player.sendMessage(ChatColor.DARK_RED + "Your actual league" + ChatColor.GRAY + ": " + ChatColor.WHITE + playerManager.getLeague());
                		player.sendMessage(ChatColor.DARK_RED + "Experience" + ChatColor.GRAY + ": " + ChatColor.WHITE + playerManager.getExp());
                		player.sendMessage(ChatColor.GRAY.toString() + ChatColor.STRIKETHROUGH + "-----------------");
                	}
            	}
            }
        }
	}
}
