package de.max.knockit.events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerSwapHandItemsEvent;
import org.bukkit.event.weather.WeatherChangeEvent;

public class BlockEvents implements Listener {

    @EventHandler
    public void onDrop(PlayerDropItemEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onF(PlayerSwapHandItemsEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onWeather(WeatherChangeEvent e) {
        e.setCancelled(true);
    }
    @EventHandler
    public void onFoodlevel(FoodLevelChangeEvent e) {
        e.setCancelled(true);
    }
}

