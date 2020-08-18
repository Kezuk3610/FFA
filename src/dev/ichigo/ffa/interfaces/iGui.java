package dev.ichigo.ffa.interfaces;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;

public interface iGui {

    Inventory inventory();

    public static Inventory clone(iGui gui)
    {
        Inventory clone = Bukkit.createInventory(null, gui.inventory().getSize(), gui.inventory().getTitle());
        clone.setContents(gui.inventory().getContents());
        return clone;
    }
}
