package de.max.knockit;

import de.max.knockit.commands.Build;
import de.max.knockit.commands.setspawn;
import de.max.knockit.events.BlockEvents;
import de.max.knockit.events.Events;
import de.max.knockit.events.build;
import de.max.knockit.utils.SpawnConfig;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Knockit extends JavaPlugin {


    @Override
    public void onEnable() {

        try {
            de.max.knockit.utils.Var.onConfig();
        } catch (Exception exception){
        }

        if(SpawnConfig.getSpawns() == false) {
            SpawnConfig.createSpawns();
        }


        cmd();
        events();
    }

    @Override
    public void onDisable() {

    }
    private void cmd() {
        getCommand("build").setExecutor(new Build());
        getCommand("setspawn").setExecutor(new setspawn());
    }
    private void events() {
        Bukkit.getPluginManager().registerEvents(new Events(), this);
        Bukkit.getPluginManager().registerEvents(new build(), this);
        Bukkit.getPluginManager().registerEvents(new BlockEvents(), this);
    }
}

