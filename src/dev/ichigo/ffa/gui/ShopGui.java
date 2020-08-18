package dev.ichigo.ffa.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import dev.ichigo.ffa.interfaces.iGui;
import dev.ichigo.ffa.util.ItemBuilder;

public class ShopGui implements iGui {

	@Override
	public Inventory inventory() {
		final Inventory inv = Bukkit.createInventory(null, 9*3, ChatColor.DARK_RED + "Shop:");
		inv.setItem(11, new ItemBuilder(Material.IRON_INGOT).setName(ChatColor.YELLOW + "Basic").addLoreLine(ChatColor.WHITE + "Price: " + ChatColor.DARK_RED + "200coins").toItemStack());
		inv.setItem(13, new ItemBuilder(Material.GOLD_INGOT).setName(ChatColor.GOLD + "Elite").addLoreLine(ChatColor.WHITE + "Price: " + ChatColor.DARK_RED + "600coins").toItemStack());
		inv.setItem(15, new ItemBuilder(Material.DIAMOND).setName(ChatColor.DARK_AQUA + "Hunter").addLoreLine(ChatColor.WHITE + "Price: " + ChatColor.DARK_RED + "1400coins").toItemStack());
		return inv;
	}

}
