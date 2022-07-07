package com.division.file.implement;

import com.division.data.ChestData;
import com.division.file.ChestDao;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class ConfigDao implements ChestDao {


    private void init() {

    }

    @Override
    public Map<UUID, List<ChestData>> load() {
        return null; //임시
    }

    @Override
    public void save(Map<UUID, List<ChestData>> data) {

    }
}
