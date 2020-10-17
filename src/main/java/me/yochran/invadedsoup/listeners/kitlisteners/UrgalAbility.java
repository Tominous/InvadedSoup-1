package me.yochran.invadedsoup.listeners.kitlisteners;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

public class UrgalAbility implements Listener {

    private InvadedSoup plugin;

    public UrgalAbility() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void onKill(PlayerDeathEvent event) {
        Entity attacker = event.getEntity().getKiller();
        Entity victim = event.getEntity();
        if (attacker instanceof Player) {
            if (victim instanceof Player) {
                Player attackerNew = event.getEntity().getKiller();
                Player victimNew = event.getEntity();
                if (plugin.kit.containsKey(attackerNew.getUniqueId())) {
                    if (plugin.urgal.contains(attackerNew.getUniqueId())) {
                        attackerNew.addPotionEffect(new PotionEffect(PotionEffectType.INCREASE_DAMAGE, 120, 0));
                        Utils.sendMessage(attackerNew, "&aYou have killed a player and recieved strength 1 for 5 seconds.");
                    }
                }
            }
        }
    }
}
