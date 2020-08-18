package dev.ichigo.ffa.gui;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;

import dev.ichigo.ffa.interfaces.iGui;
import dev.ichigo.ffa.util.ItemBuilder;

public class LeagueGui implements iGui {

	@Override
	public Inventory inventory() {
		final Inventory inv = Bukkit.createInventory(null, 9*5, ChatColor.DARK_RED + "League:");
		inv.setItem(0, new ItemBuilder(Material.IRON_INGOT).setName(ChatColor.RED + "Silver I").addLoreLine(ChatColor.GRAY + "The first league").toItemStack());
		inv.setItem(1, new ItemBuilder(Material.IRON_INGOT).setName(ChatColor.RED + "Silver II").addLoreLine(ChatColor.GRAY + "The second league").toItemStack());
		inv.setItem(2, new ItemBuilder(Material.IRON_INGOT).setName(ChatColor.RED + "Silver III").addLoreLine(ChatColor.GRAY + "The third league").toItemStack());
		inv.setItem(9, new ItemBuilder(Material.GOLD_INGOT).setName(ChatColor.RED + "Gold I").addLoreLine(ChatColor.GRAY + "The fourth league").toItemStack());
		inv.setItem(10, new ItemBuilder(Material.GOLD_INGOT).setName(ChatColor.RED + "Gold II").addLoreLine(ChatColor.GRAY + "The fifth league").toItemStack());
		inv.setItem(11, new ItemBuilder(Material.GOLD_INGOT).setName(ChatColor.RED + "Gold III").addLoreLine(ChatColor.GRAY + "The sixth league").toItemStack());
		inv.setItem(18, new ItemBuilder(Material.DIAMOND).setName(ChatColor.RED + "Diamond I").addLoreLine(ChatColor.GRAY + "The seventh league").toItemStack());
		inv.setItem(19, new ItemBuilder(Material.DIAMOND).setName(ChatColor.RED + "Diamond II").addLoreLine(ChatColor.GRAY + "The eigth league").toItemStack());
		inv.setItem(20, new ItemBuilder(Material.DIAMOND).setName(ChatColor.RED + "Diamond III").addLoreLine(ChatColor.GRAY + "The ninth league").toItemStack());
		inv.setItem(44, new ItemBuilder(Material.PAPER).setName(ChatColor.RED + "Your League").addLoreLine(ChatColor.GRAY + "Click here for see your actual league!").toItemStack());
		return inv;
	}

}
