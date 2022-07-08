package com.division.util;

import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class ChestUtil {

    public static Inventory createInventory(int row, Inventory from) {
        Inventory inventory = Bukkit.createInventory(null, 9 * row, "§0가상 창고");
        inventory.addItem(from.getContents());
        return inventory;
    }


    public static Inventory createInventory(int row, Map<Integer, ItemStack> itemData) {
        Inventory inventory = Bukkit.createInventory(null, 9 * row, "§0가상 창고");
        for (int index : itemData.keySet()) {
            inventory.setItem(index, itemData.get(index));
        }
        return inventory;
    }

}
