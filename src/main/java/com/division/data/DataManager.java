package com.division.data;

import com.division.file.ChestDao;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DataManager {

    private ChestDao dao;
    private boolean isLoaded;
    private boolean isActive;
    private Map<UUID, List<ChestData>> dataMap;

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

}
