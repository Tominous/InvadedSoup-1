package me.yochran.invadedsoup.listeners.kitlisteners;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;

public class StomperAbility implements Listener {

    private InvadedSoup plugin;

    public StomperAbility() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void onLand(EntityDamageEvent event) {
        if (event.getEntity() instanceof Player) {
            Player playerNew = (Player) event.getEntity();
            if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                for (org.bukkit.entity.Entity entity : playerNew.getLocation().getChunk().getEntities()) {
                    if (playerNew.getLocation().distanceSquared(entity.getLocation()) < 5 ) {
                        if (entity instanceof Player) {
                            if (plugin.stomper.contains(playerNew.getUniqueId())) {
                                Player damaged = (Player) entity;
                                damaged.damage(15.0);
                                if (damaged.getHealth() <= 0.0) {
                                    for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                                        Utils.sendMessage(onlinePlayers, "&f" + playerNew.getName() + "&6 stomped &f" + damaged.getName());
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}
