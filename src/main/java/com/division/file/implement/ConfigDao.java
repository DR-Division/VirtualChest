package com.division.file.implement;

import com.division.file.ChestDao;
import org.bukkit.inventory.Inventory;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ConfigDao implements ChestDao {


    private void init() {

    }

    @Override
    public Map<UUID, List<Inventory>> load() {
        return null; //임시
    }

    @Override
    public void save(Map<UUID, List<Inventory>> data) {

    }
}
