package de.max.knockit.utils;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;

public class SpawnConfig {

    public static void createSpawns() {

        File ordner = new File("plugins//KnockIT");
        File file = new File("plugins//KnockIT//Spawn.yml");

        if(!ordner.exists()) {

            ordner.mkdirs();

        }

        if(!file.exists()) {

            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);


        cfg.set("Spawn.X", "null");
        cfg.set("Spawn.Y", "null");
        cfg.set("Spawn.Z", "null");
        cfg.set("Spawn.World", "null");
        cfg.set("Spawn.Yaw", "null");
        cfg.set("Spawn.Pitch", "null");

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static boolean getSpawns() {

        File file = new File("plugins//KnockIT//Spawn.yml");

        if(file.exists()) {

            return true;

        } else {

            return false;

        }

    }

    public static void setSpawn(Player p) {

        File file = new File("plugins//KnockIT//Spawn.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        cfg.set("Spawn.X", loc.getX());
        cfg.set("Spawn.Y", loc.getY());
        cfg.set("Spawn.Z", loc.getZ());
        cfg.set("Spawn.World", loc.getWorld().getName());
        cfg.set("Spawn.Yaw", loc.getYaw());
        cfg.set("Spawn.Pitch", loc.getPitch());

        try {
            cfg.save(file);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static Location getSpawn(Player p) {

        File file = new File("plugins//KnockIT//Spawn.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        loc.setX(cfg.getDouble("Spawn.X"));
        loc.setY(cfg.getDouble("Spawn.Y"));
        loc.setZ(cfg.getDouble("Spawn.Z"));
        loc.setYaw((float)cfg.getDouble("Spawn.Yaw"));
        loc.setPitch((float)cfg.getDouble("Spawn.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Spawn.World")));

        return loc;

    }

    public static Location getSpawnBlock(Player p) {

        File file = new File("plugins//KnockIT//Spawn.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        loc.setX(cfg.getDouble("Spawn.X"));
        loc.setY(cfg.getDouble("Spawn.Y") - 1);
        loc.setZ(cfg.getDouble("Spawn.Z"));
        loc.setYaw((float)cfg.getDouble("Spawn.Yaw"));
        loc.setPitch((float)cfg.getDouble("Spawn.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Spawn.World")));

        return loc;

    }

    public static Location getSpawnPartikelBlock(Player p) {

        File file = new File("plugins//KnockIT//Spawn.yml");
        Location loc = p.getLocation();

        YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

        loc.setX(cfg.getDouble("Spawn.X"));
        loc.setY(cfg.getDouble("Spawn.Y"));
        loc.setZ(cfg.getDouble("Spawn.Z") - 1.5);
        loc.setYaw((float)cfg.getDouble("Spawn.Yaw"));
        loc.setPitch((float)cfg.getDouble("Spawn.Pitch"));
        loc.setWorld(Bukkit.getWorld(cfg.getString("Spawn.World")));

        return loc;

    }

}