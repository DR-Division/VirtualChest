package com.division;

import com.division.data.DataManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class VirtualChest extends JavaPlugin {

    private static final int INIT_TASK_DELAY = 60;

    @Override
    public void onEnable() {
        // Plugin startup logic
        DataManager manager = new DataManager();

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void initTask(DataManager manager) {

    }
}
