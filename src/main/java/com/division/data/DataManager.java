package com.division.data;

import com.division.file.ChestDao;
import org.bukkit.inventory.Inventory;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DataManager {

    private ChestDao dao;
    private boolean isLoaded;
    private boolean isActive;
    private Map<UUID, List<Inventory>> dataMap;

    public DataManager() {
        isActive = true;
        isLoaded = false;
        this.dataMap = null;
    }

    public void setDataObject(ChestDao dataObject) {
        this.dao = dataObject;
    }

    public void load() {
        isLoaded = true;
        dataMap = dao.load();
    }

    public void save() {
        if (dataMap != null)
            dao.save(dataMap);
    }

    public boolean isLoaded() {
        return isLoaded;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean isActive) {
        this.isActive = isActive;
    }

    public boolean hasData(UUID data) {
        return dataMap.containsKey(data);
    }

    public List<Inventory> getData(UUID data) {
        return dataMap.get(data);
    }

}
