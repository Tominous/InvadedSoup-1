package me.yochran.invadedsoup.listeners.kitlisteners;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerToggleSneakEvent;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;

public class NinjaAbility implements Listener {

    private InvadedSoup plugin;

    public NinjaAbility() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity victim = event.getEntity();
        if (damager instanceof Player) {
            if (victim instanceof Player) {
                Player dNew = (Player) event.getDamager();
                Player vNew = (Player) event.getEntity();
                plugin.ninja_last_hit.remove(dNew);
                if (plugin.ninja.contains(vNew.getUniqueId())) {
                    plugin.ninja_last_hit.remove(vNew);
                    plugin.ninja_last_hit.put(vNew, dNew);
                }
            }
        }
    }

    @EventHandler
    public void onAttackerDeath(PlayerDeathEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            Player player = (Player) event.getEntity();
            if (!plugin.ninja.contains(player.getUniqueId())) {
                plugin.ninja_last_hit.remove(player);
            }
        }
    }

    @EventHandler
    public void onSneak(PlayerToggleSneakEvent event) {
        Player player = event.getPlayer();
        if (player.isSneaking()) {
            if (plugin.ninja.contains(player.getUniqueId())) {
                if (plugin.ninja_cooldown.containsKey(player.getUniqueId())) {
                    return;
                } else {
                    if (!plugin.ninja_last_hit.containsKey(player)) {
                        return;
                    } else {
                        Player lastDamager = plugin.ninja_last_hit.get(player);
                        Location ldLocation = lastDamager.getLocation();
                        player.teleport(ldLocation);
                        Utils.sendMessage(lastDamager, "&a" + player.getName() + "&a has used their ninja ability to teleport behind you!");
                        Utils.sendMessage(player, "&aYou have used your ninja ability and teleported behind " + lastDamager.getName() + "&a!");
                        plugin.ninja_cooldown.put(player.getUniqueId(), 15.0);
                    }
                }
            }
        }
    }
}
