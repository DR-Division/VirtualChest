package com.division.api;

import com.division.data.DataManager;

public class VirtualChestAPI {

    private static DataManager manager;

    public static void injectDependency(DataManager inject) {
        manager = inject;
    }

    public static DataManager getManager() {
        return manager;
    }
}
