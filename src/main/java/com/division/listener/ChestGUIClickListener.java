package com.division.listener;

import com.division.data.DataManager;
import com.division.util.ChestUtil;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.metadata.MetadataValue;

import java.util.List;

public class ChestGUIClickListener implements Listener {

    private final DataManager manager;

    public ChestGUIClickListener(DataManager manager) {
        this.manager = manager;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player p = (Player) event.getWhoClicked();
        ItemStack stack = event.getCurrentItem();
        int index = event.getRawSlot();
        List<MetadataValue> valueList = p.getMetadata("virtual_chest_id");
        if (valueList.size() != 0 && valueList.get(0) != null && event.getView().getTitle().contains("창고 선택")) {
            event.setCancelled(true);
            String target = valueList.get(0).asString();
            if (stack != null && stack.getType() == Material.CHEST) {
                ChestUtil.openVirtualChest(manager, p, target, index);
            }
        }
    }

}
