package de.max.knockit.events;

import de.max.knockit.utils.SpawnConfig;
import de.max.knockit.utils.Var;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.Objects;

public class Events implements Listener {

    public static void setAttackSpeed(Player p, int i) {
        Objects.requireNonNull(p.getAttribute(Attribute.GENERIC_ATTACK_SPEED)).setBaseValue(i);
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e) {
        Player p = e.getPlayer();
        setAttackSpeed(p, 100);
        e.setJoinMessage(null);

        ItemStack stick = new ItemStack(Material.STICK, 1);
        ItemMeta stickmeta = stick.getItemMeta();
        stickmeta.setDisplayName("§eStick");
        stickmeta.setUnbreakable(true);
        stickmeta.addEnchant(Enchantment.KNOCKBACK, 1, true);
        stick.setItemMeta(stickmeta);

            p.getInventory().clear();
            Location spawn = SpawnConfig.getSpawn(p);
            p.teleport(spawn);
            p.setGameMode(GameMode.SURVIVAL);
            p.setMaxHealth(6);
            p.setFoodLevel(40);
            p.getInventory().setItem(1, stick);

    }
    @EventHandler
    public void onLeave(PlayerQuitEvent e) {

        e.setQuitMessage(null);
    }
    @EventHandler
    public void onDamage(EntityDamageEvent e) {
            e.setDamage(0);
        }
    @EventHandler
    public void onFall(EntityDamageEvent e) {
        if (e.getCause() == EntityDamageEvent.DamageCause.FALL) {
            e.setCancelled(true);
        }
    }

    public static ArrayList<Player> pvp = new ArrayList<>();

    @EventHandler
    public void onMove(PlayerMoveEvent event){
        Player player = event.getPlayer();

        if(player.getLocation().getY() <= 85){
            player.teleport(SpawnConfig.getSpawn(player));
            Bukkit.broadcastMessage(Var.cfg.getString("Settings.prefix")+ "§7Der Spieler §c" + player.getName() + " §7ist gestorben!");
            pvp.remove(player);
        }
        if(player.getLocation().getY() <= 108){
            if(!(pvp.contains(player))){
                pvp.add(player);
            }
        }
    }

    @EventHandler
    public void damagePlayer(EntityDamageByEntityEvent event){
        Player player = (Player) event.getEntity();
        if(!(pvp.contains(player))){
            event.setCancelled(true);
        }else{
            event.setCancelled(false);
        }
    }
}

