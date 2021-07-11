package de.max.knockit.commands;

import de.max.knockit.utils.Var;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.ArrayList;

public class Build implements CommandExecutor {

    public static ArrayList<Player> build = new ArrayList<>();

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player)sender;
        if (p.hasPermission("build.use")) {
            if (args.length == 0)
                if (build.contains(p)) {
                    build.remove(p);
                    p.setGameMode(GameMode.SURVIVAL);
                    p.sendTitle("§eBuildModus", "§8[§c✖§8]");
                } else {
                    build.add(p);
                    p.setGameMode(GameMode.CREATIVE);
                    p.sendTitle("§eBuildModus", "§8[§a✔§8]");
                }
        } else {
            p.playSound(p.getLocation(), Sound.BLOCK_ANVIL_USE, 1, 0);
            p.sendMessage(Var.cfg.getString("Settings.prefix")+ Var.cfg.getString("Settings.noperms"));
        }
        return false;
    }
}
