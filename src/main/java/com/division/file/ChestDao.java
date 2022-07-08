package com.division.file;

import org.bukkit.inventory.Inventory;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface ChestDao {

    Map<UUID, List<Inventory>> load(); //데이터 로드

    void save(Map<UUID, List<Inventory>> data); //데이터 저장
}
