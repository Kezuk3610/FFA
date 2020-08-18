package dev.ichigo.ffa.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import dev.ichigo.ffa.FFA;
import dev.ichigo.ffa.interfaces.iGui;
import dev.ichigo.ffa.util.ItemBuilder;

public class KitGui implements iGui {

	@Override
	public Inventory inventory() {
		final Inventory inv = Bukkit.createInventory(null, 9*3, ChatColor.DARK_RED + "FFA:");
		inv.setItem(12, new ItemBuilder(Material.POTION,1 ,(short)16421).setName(ChatColor.RED.toString() + ChatColor.BOLD + "Potions").addLoreLine(ChatColor.WHITE + "Players: " + ChatColor.DARK_RED + FFA.getInstance().inPotion.size()).toItemStack());
		inv.setItem(14, new ItemBuilder(Material.LAVA_BUCKET).setName(ChatColor.RED.toString() + ChatColor.BOLD + "Build-UHC").addLoreLine(ChatColor.WHITE + "Players: " + ChatColor.DARK_RED + FFA.getInstance().inUhc.size()).toItemStack());
		return inv;
	}

}
