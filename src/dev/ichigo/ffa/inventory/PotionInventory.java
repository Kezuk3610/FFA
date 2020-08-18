package dev.ichigo.ffa.inventory;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import dev.ichigo.ffa.interfaces.iKit;
import dev.ichigo.ffa.util.ItemBuilder;

public class PotionInventory implements iKit {

    @Override
    public ItemStack[] content() {
        ItemStack[] Contents = {new ItemBuilder(Material.DIAMOND_SWORD).addEnchant(Enchantment.DAMAGE_ALL, 2).addEnchant(Enchantment.DURABILITY, 5).addEnchant(Enchantment.FIRE_ASPECT, 1).toItemStack(),
                new ItemBuilder(Material.ENDER_PEARL, 16, (short)0).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)8259).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)8226).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.COOKED_BEEF, 64, (short)0).toItemStack(),

                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)8226).toItemStack(),

                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)8226).toItemStack(),

                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)16421).toItemStack(),
                new ItemBuilder(Material.POTION, 1, (short)8226).toItemStack(),

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
