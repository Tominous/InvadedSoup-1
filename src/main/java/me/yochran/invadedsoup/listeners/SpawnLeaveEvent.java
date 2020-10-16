package me.yochran.invadedsoup.listeners;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.management.SpawnManagement;
import me.yochran.invadedsoup.utils.Utils;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SpawnLeaveEvent implements Listener {

    private InvadedSoup plugin;

    public SpawnLeaveEvent() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String worldname = plugin.getConfig().getString("Spawn.world");
        if (player.getWorld().getName().equalsIgnoreCase(worldname)) {
            if (player.getGameMode() == GameMode.SURVIVAL) {
                if (Utils.regionLeave(player)) {
                    if (!plugin.kit.containsKey(player.getUniqueId())) {
                        SpawnManagement spawn = new SpawnManagement(player);
                        spawn.send();
                        Utils.sendMessage(player, "&cYou need to pick a kit before walking out of spawn!");
                    }
                }
            }
        }
    }
}
