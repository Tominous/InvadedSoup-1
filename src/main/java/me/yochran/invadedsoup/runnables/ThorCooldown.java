package me.yochran.invadedsoup.runnables;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class ThorCooldown extends BukkitRunnable {

    private InvadedSoup plugin;

    public ThorCooldown() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @Override
    public void run() {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            if (plugin.thor_cooldown.containsKey(onlinePlayers.getUniqueId())) {
                double time = plugin.thor_cooldown.get(onlinePlayers.getUniqueId());
                if (time <= 0.0) {
                    Utils.sendMessage(onlinePlayers, "&aYou can use your ability again.");
                    plugin.thor_cooldown.remove(onlinePlayers.getUniqueId());
                    return;
                } else {
                    plugin.thor_cooldown.put(onlinePlayers.getUniqueId(), plugin.thor_cooldown.get(onlinePlayers.getUniqueId()) - 1.0);
                }
            }
        }
    }
}
