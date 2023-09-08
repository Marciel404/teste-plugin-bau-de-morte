package com.github.marciel404.pldefdp;

import com.github.marciel404.pldefdp.events.Die;
import org.bukkit.plugin.java.JavaPlugin;

public final class PLDEFDP extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new Die(), this);

    }
    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
