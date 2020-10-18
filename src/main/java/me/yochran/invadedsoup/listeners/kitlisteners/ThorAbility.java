package me.yochran.invadedsoup.listeners.kitlisteners;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;

public class ThorAbility implements Listener {

    private InvadedSoup plugin;

    public ThorAbility() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInHand().getType() == XMaterial.DIAMOND_AXE.parseMaterial()) {
            if (plugin.thor.contains(player.getUniqueId())) {
                if (plugin.thor_cooldown.containsKey(player.getUniqueId())) {
                    Utils.sendMessage(player, "&aYou are on cooldown for that.");
                } else {
                    for (org.bukkit.entity.Entity entity : player.getLocation().getChunk().getEntities()) {
                        if (player.getLocation().distanceSquared(entity.getLocation()) < 50) {
                            if (entity instanceof Player) {
                                Player damaged = (Player) entity;
                                if (damaged != player) {
                                    Location entLoc = damaged.getLocation();
                                    damaged.getWorld().strikeLightning(entLoc);
                                    plugin.thor_cooldown.put(player.getUniqueId(), 15.0);
                                    if (damaged.getHealth() <= 0.0) {
                                        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
                                            Utils.sendMessage(onlinePlayers, "&f" + player.getName() + "&6 has smitten &f" + damaged.getName());
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
}
