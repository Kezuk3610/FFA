package dev.ichigo.ffa.interfaces;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public interface iKit {
	
    ItemStack[] content();

    ItemStack[] armor();

    ItemStack air = new ItemStack(Material.AIR);

}
