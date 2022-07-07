package com.division.data;

import com.division.file.ChestDao;

public class DataManager {

    private ChestDao dao;

    public DataManager() {

    }

    public void setDataObject(ChestDao dataObject) {
        this.dao = dataObject;
    }

}
