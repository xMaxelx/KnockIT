package de.max.knockit.commands;

import de.max.knockit.utils.SpawnConfig;
import de.max.knockit.utils.Var;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class setspawn implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Player p = (Player) sender;
        if(sender instanceof Player) {
            if(p.hasPermission("setspawn.use")) {
                SpawnConfig.setSpawn(p);
                p.sendMessage(Var.cfg.getString("Settings.prefix")+ "§eDu hast den Spawn gesetzt!");
            } else {
                p.sendMessage(Var.cfg.getString("Settings.prefix")+ Var.cfg.getString("Settings.noperms"));
            }
        }
        return false;
    }
}
