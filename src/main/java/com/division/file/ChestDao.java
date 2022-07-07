package com.division.file;

import com.division.data.ChestData;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ChestDao {

    Map<UUID, List<ChestData>> load(); //데이터 로드

    void save(Map<UUID, List<ChestData>> data); //데이터 저장
}
