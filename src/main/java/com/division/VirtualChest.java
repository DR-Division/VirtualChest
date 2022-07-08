package com.division;

import com.division.api.VirtualChestAPI;
import com.division.command.ChestCommand;
import com.division.data.DataManager;
import com.division.file.ChestDao;
import com.division.file.implement.ConfigDao;
import com.division.listener.ChestGUIClickListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class VirtualChest extends JavaPlugin {

    private static final int INIT_TASK_DELAY = 60;
    private DataManager manager;

    @Override
    public void onEnable() {
        // Plugin startup logic
        manager = new DataManager();
        ConfigDao dao = new ConfigDao(getDataFolder().getAbsolutePath());
        dao.init();
        manager.setDataAccessObject(dao);
        VirtualChestAPI.injectDependency(manager);
        ChestCommand command = new ChestCommand(manager, this);
        Objects.requireNonNull(getCommand("virtualchest")).setExecutor(command);
        Objects.requireNonNull(getCommand("virtualchest")).setTabCompleter(command);
        getServer().getPluginManager().registerEvents(new ChestGUIClickListener(manager), this);
        Bukkit.getScheduler().runTaskLaterAsynchronously(this, manager::load, INIT_TASK_DELAY); //3초후 데이터 로드
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        manager.save();
    }

}
