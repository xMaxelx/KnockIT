package de.max.knockit.events;

import de.max.knockit.commands.Build;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;

import java.io.File;

public class build implements Listener {

    @EventHandler
    public void onBreak(BlockBreakEvent e) {
        Player p = e.getPlayer();
        if (!Build.build.contains(p)) {
            e.setCancelled(true);
        } else if (Build.build.contains(p)) {
            e.setCancelled(false);
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        Player p = e.getPlayer();
        if (Build.build.contains(p)) {
            e.setCancelled(false);
        } else {
            e.setCancelled(true);
        }
    }
}

