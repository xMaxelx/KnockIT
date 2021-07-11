package de.max.knockit.utils;

import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class Var {

    public static File file = new File("plugins/KnockIT/config.yml");
    public static YamlConfiguration cfg = YamlConfiguration.loadConfiguration(file);

    public static void onConfig() {
        cfg.addDefault("Settings.prefix", "§eKnockIT §8» ");
        cfg.addDefault("Settings.noperms", "§cDazu hast du keine Rechte! ");
        cfg.options().copyDefaults(true);
        try {
            cfg.save(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
