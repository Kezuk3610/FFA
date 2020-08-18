package dev.ichigo.ffa.inventory;

import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import dev.ichigo.ffa.interfaces.iKit;
import dev.ichigo.ffa.util.ItemBuilder;

public class UHCInventory implements iKit {

    @Override
    public ItemStack[] content() {
        ItemStack[] Contents = {new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 1).addEnchant(Enchantment.DURABILITY, 5).toItemStack(),
                new ItemBuilder(Material.FISHING_ROD).setUnBreakable().toItemStack(),
                new ItemBuilder(Material.GOLDEN_APPLE, 16, (short)0).toItemStack(),
                new ItemBuilder(Material.GOLDEN_APPLE, 6, (short)0).setName(ChatColor.GOLD + "Golden Head").toItemStack(),
                new ItemBuilder(Material.BOW).toItemStack(),
                new ItemBuilder(Material.DIAMOND_AXE).toItemStack(),
                new ItemBuilder(Material.COBBLESTONE, 64, (short)0).toItemStack(),
                new ItemBuilder(Material.WOOD, 64, (short)0).toItemStack(),
                new ItemBuilder(Material.COOKED_BEEF, 64, (short)0).toItemStack(),

                air,
                new ItemBuilder(Material.DIAMOND_PICKAXE).toItemStack(),
                air,
                air,
                air,
                air,
                air,
                air,
                air,

                air,
                air,
                air,
                air,
                air,
                air,
                air,
                air,
                air,

                air,
                air,
                air,
                air,
                air,
                air,
                air,
                air,
                new ItemBuilder(Material.ARROW, 64, (short)0).toItemStack(),

        };
        return Contents;
    }

    @Override
    public ItemStack[] armor() {
        ItemStack[] Armor = {new ItemBuilder(Material.DIAMOND_BOOTS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 3).addEnchant(Enchantment.PROTECTION_FALL, 4).toItemStack(),
                new ItemBuilder(Material.DIAMOND_LEGGINGS).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 3).toItemStack(),
                new ItemBuilder(Material.DIAMOND_CHESTPLATE).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 3).toItemStack(),
                new ItemBuilder(Material.DIAMOND_HELMET).addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchant(Enchantment.DURABILITY, 3).toItemStack()
        };
        return Armor;
    }
}
