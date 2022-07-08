package com.division;

import com.division.data.DataManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class VirtualChest extends JavaPlugin {

    private static final int INIT_TASK_DELAY = 60;
    private DataManager manager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        manager = new DataManager();
        Bukkit.getScheduler().runTaskLaterAsynchronously(this, manager::load, INIT_TASK_DELAY); //3초후 데이터 로드
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
