package dev.ichigo.ffa.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import dev.ichigo.ffa.interfaces.iGui;
import dev.ichigo.ffa.util.ItemBuilder;

public class InformationsGui implements iGui {

	@Override
	public Inventory inventory() {
		final Inventory inv = Bukkit.createInventory(null, 9, ChatColor.DARK_RED + "Informations:");
		inv.setItem(3, new ItemBuilder(Material.NAME_TAG).setName(ChatColor.RED + "League").addLoreLine(ChatColor.GRAY + "See all league.").toItemStack());
		inv.setItem(5, new ItemBuilder(Material.PAPER).setName(ChatColor.RED + "Your Stats").addLoreLine(ChatColor.GRAY + "Click here for see your statistics").toItemStack());
		return inv;
	}

}
