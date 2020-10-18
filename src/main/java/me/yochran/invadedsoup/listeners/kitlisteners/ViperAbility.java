package me.yochran.invadedsoup.listeners.kitlisteners;

import me.yochran.invadedsoup.InvadedSoup;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class ViperAbility implements Listener {

    private InvadedSoup plugin;

    public ViperAbility() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        Entity damager = event.getDamager();
        Entity damaged = event.getEntity();
        if (damager instanceof Player) {
            if (plugin.viper.contains(damager.getUniqueId())) {
                if (damaged instanceof Player) {
                    Player attacker = (Player) event.getDamager();
                    Player victim = (Player) event.getEntity();
                    if (!plugin.viper_hit_count.containsKey(attacker.getUniqueId())) {
                        plugin.viper_hit_count.put(attacker.getUniqueId(), 1.0);
                    } else {
                        plugin.viper_hit_count.put(attacker.getUniqueId(), plugin.viper_hit_count.get(attacker.getUniqueId()) + 1.0);
                    }
                    double amount = plugin.viper_hit_count.get(attacker.getUniqueId());
                    if (amount < 10.0) {
                        return;
                    } else if (amount >= 10.0) {
                        plugin.viper_hit_count.remove(attacker.getUniqueId());
                        victim.addPotionEffect(new PotionEffect(PotionEffectType.POISON, 60, 1));
                    }
                }
            }
        }
    }
}
