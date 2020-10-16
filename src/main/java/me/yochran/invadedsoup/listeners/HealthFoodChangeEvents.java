package me.yochran.invadedsoup.listeners;

import me.yochran.invadedsoup.InvadedSoup;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.FoodLevelChangeEvent;

public class HealthFoodChangeEvents implements Listener {

    private InvadedSoup plugin;

    public HealthFoodChangeEvents() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void onFoodLevelChange(FoodLevelChangeEvent event) {
        Player player = (Player) event.getEntity();
        String worldname = plugin.getConfig().getString("Spawn.world");
        if (player.getWorld().getName().equalsIgnoreCase(worldname)) {
            event.setCancelled(true);
        }
    }
}
