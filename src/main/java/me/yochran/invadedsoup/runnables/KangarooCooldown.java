package me.yochran.invadedsoup.runnables;

import me.yochran.invadedsoup.InvadedSoup;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class KangarooCooldown extends BukkitRunnable {

    private InvadedSoup plugin;

    public KangarooCooldown() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @Override
    public void run() {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            if (plugin.kangaroo.contains(onlinePlayers.getUniqueId())) {
                if (plugin.kang_cooldown.containsKey(onlinePlayers.getUniqueId())) {
                    double time = plugin.kang_cooldown.get(onlinePlayers.getUniqueId());
                    if (time <= 0) {
                        plugin.kang_cooldown.remove(onlinePlayers.getUniqueId());
                        return;
                    } else {
                        plugin.kang_cooldown.put(onlinePlayers.getUniqueId(), plugin.kang_cooldown.get(onlinePlayers.getUniqueId()) - 0.2);
                    }
                }
            }
        }
    }
}
