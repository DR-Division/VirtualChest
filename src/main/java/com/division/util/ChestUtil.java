package com.division.util;

import com.division.data.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class ChestUtil {


    public static Inventory createInventory(int row) {
        return Bukkit.createInventory(null, 9 * row, "§0가상 창고");
    }

    public static Inventory createInventory(int row, Inventory from) {
        Inventory inventory = Bukkit.createInventory(null, 9 * row, "§0가상 창고");
        Arrays.stream(from.getContents()).filter(Objects::nonNull).forEach(inventory::addItem);
        return inventory;
    }


    public static Inventory createInventory(int row, Map<Integer, ItemStack> itemData) {
        Inventory inventory = Bukkit.createInventory(null, 9 * row, "§0가상 창고");
        for (int index : itemData.keySet()) {
            inventory.setItem(index, itemData.get(index));
        }
        return inventory;
    }

    public static ItemStack makeItemStack(Material type, String name, String... lore) {
        ItemStack stack = new ItemStack(type);
        ItemMeta meta = stack.getItemMeta();
        if (meta != null) {
            meta.setDisplayName(name);
            meta.setLore(Arrays.asList(lore));
            stack.setItemMeta(meta);
        }
        return stack;
    }

    public static void openChestMenu(DataManager manager, Player p, String name) {
        Player target = Bukkit.getPlayer(name);
        if (target != null) {
            List<Inventory> data = manager.getData(target.getUniqueId());
            Inventory selectInventory = Bukkit.createInventory(null, 54, "§0창고 선택");
            if (data != null) {
                for (int i = 0; i < data.size(); i++) {
                    selectInventory.addItem(ChestUtil.makeItemStack(Material.CHEST, "§b" + (i+1) + "§f번째 창고", " ", " §8-  §f클릭시 해당 창고를 엽니다.", " "));
                }
            }
            p.openInventory(selectInventory);
        }
    }

    public static void openVirtualChest(DataManager manager, Player p, String name, int index) {
        Player target = Bukkit.getPlayer(name);
        if (target != null) {
            List<Inventory> data = manager.getData(target.getUniqueId());
            if (data != null && data.size() > index)
                p.openInventory(data.get(index));
        }
    }

    public static int parseInt(String input) {
        try {
            return Integer.parseInt(input);
        }
        catch (NumberFormatException e) {
            return -1;
        }
    }

}
