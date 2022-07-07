package com.division.data;

import org.bukkit.inventory.ItemStack;

import java.util.Map;

public class ChestData {

    private int row;
    private Map<Integer, ItemStack> itemMap;

    public int getRow() {
        return row;
    }

    public ItemStack getItemStack(int index) {
        return itemMap.get(index); //없을경우 null 리턴
    }

    public void setRow(int row) {
        this.row = row;
    }

    public void setItemMapByIndex(int index, ItemStack stack) {
        itemMap.put(index, stack);
    }


}
