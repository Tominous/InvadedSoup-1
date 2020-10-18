package me.yochran.invadedsoup.runnables;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class NinjaCooldown extends BukkitRunnable {

    private InvadedSoup plugin;

    public NinjaCooldown() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @Override
    public void run() {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            if (plugin.ninja_cooldown.containsKey(onlinePlayers.getUniqueId())) {
                double time = plugin.ninja_cooldown.get(onlinePlayers.getUniqueId());
                if (time <= 0.0) {
                    Utils.sendMessage(onlinePlayers, "&aYou can use your ability again.");
                    plugin.ninja_cooldown.remove(onlinePlayers.getUniqueId());
                    return;
                } else {
                    plugin.ninja_cooldown.put(onlinePlayers.getUniqueId(), plugin.ninja_cooldown.get(onlinePlayers.getUniqueId()) - 1.0);
                }
            }
        }
    }
}
