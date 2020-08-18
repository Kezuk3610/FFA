package dev.ichigo.ffa.inventory;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import dev.ichigo.ffa.interfaces.iKit;
import dev.ichigo.ffa.util.ItemBuilder;
import net.md_5.bungee.api.ChatColor;

public class SpawnInventory implements iKit {

    @Override
    public ItemStack[] content() {
        return new ItemStack[]{
        		new ItemBuilder(Material.EMERALD).setUnBreakable().setName(ChatColor.RED + "Shop").toItemStack(),
                air,
                air,
                air,
                new ItemBuilder(Material.GOLD_AXE).setUnBreakable().setName(ChatColor.DARK_RED + "Click for play!").toItemStack(),
                air,
                air,
                air,
                new ItemBuilder(Material.SKULL_ITEM).setUnBreakable().setName(ChatColor.RED + "Informations").toItemStack(),
        };
    }

    @Override
    public ItemStack[] armor() {
        return new ItemStack[0];
    }
}
