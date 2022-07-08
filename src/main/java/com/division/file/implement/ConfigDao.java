package com.division.file.implement;

import com.division.file.ChestDao;
import com.division.util.ChestUtil;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.*;

public class ConfigDao implements ChestDao {

    private final File file;
    private YamlConfiguration config;

    public ConfigDao(String path) {
        this.file = new File(path + "/data.yml");
        this.config = null;

    }

    public boolean init() {
        if (!file.exists()) {
            try {
                Files.createDirectories(file.getParentFile().toPath());
                return file.createNewFile();
            }
            catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    @Override
    public Map<UUID, List<Inventory>> load() {
        config = YamlConfiguration.loadConfiguration(file);
        ConfigurationSection uuidSection = config.getConfigurationSection("chest");
        Map<UUID, List<Inventory>> inventoryMap = new HashMap<>();
        if (uuidSection != null) {
            for (String key : uuidSection.getKeys(false)) { //chest.#UUID
                UUID uuid = parseUUID(key);
                ConfigurationSection chestSection = config.getConfigurationSection("chest." + key); //chest.#UUID.#CHEST ...첫번째 상자 두번째 상자 섹션
                if (uuid != null && chestSection != null) {
                    inventoryMap.put(uuid, new ArrayList<>());
                    for (String id : chestSection.getKeys(false)) {
                        int row = config.getInt("chest." + key + "." + id + ".row", 1);
                        ConfigurationSection indexSection = config.getConfigurationSection("chest." + key + "." + id + ".items"); //chest.#UUID.#CHEST.item
                        if (indexSection != null) {
                            Map<Integer, ItemStack> indexedMap = new HashMap<>();
                            for (String index : indexSection.getKeys(false)) { //chest.uuid.id.item.1 -> item stack
                                ItemStack stack = config.getItemStack("chest." + key + "." + id + ".items." + index);
                                int parsedIndex = ChestUtil.parseInt(index);
                                if (parsedIndex != -1 && stack != null)
                                    indexedMap.put(parsedIndex, stack);
                            }
                            inventoryMap.get(uuid).add(ChestUtil.createInventory(row, indexedMap));
                        }
                    }
                }
            }
        }
        return inventoryMap;
    }

    @Override
    public void save(Map<UUID, List<Inventory>> data) {
        for (UUID uuid : data.keySet()) {
            List<Inventory> inventories = data.get(uuid);
            for (int i = 0; i < inventories.size(); i++) {
                Inventory inventory = inventories.get(i);
                config.set("chest." + uuid.toString() + "." + i + ".row", inventory.getSize() / 9);
                for (int k = 0; k < inventory.getSize(); k++) {
                    config.set("chest." + uuid + "." + i + ".items." + k, inventory.getItem(k));
                }
            }
        }
        try {
            config.save(file);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private UUID parseUUID(String uuid) {
        try {
            return UUID.fromString(uuid);
        }
        catch (IllegalArgumentException e) {
            return null;
        }
    }
}
