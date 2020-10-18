package me.yochran.invadedsoup.listeners.kitlisteners;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class KangarooAbility implements Listener {

    private InvadedSoup plugin;

    public KangarooAbility() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void onRightClickRocket(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInHand().getType() == XMaterial.FIREWORK_ROCKET.parseMaterial()) {
            if (event.getAction() == Action.RIGHT_CLICK_BLOCK) {
                event.setCancelled(true);
            } else {
                if (event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_AIR) {
                    if (player.getInventory().getItemInHand().getType() == XMaterial.FIREWORK_ROCKET.parseMaterial()) {
                        if (plugin.kangaroo.contains(player.getUniqueId())) {
                            if (plugin.kang_cooldown.containsKey(player.getUniqueId())) {
                                return;
                            } else {
                                if (player.isSneaking()) {
                                    double X = player.getLocation().getDirection().getX() * 4.0;
                                    double Z = player.getLocation().getDirection().getZ() * 4.0;
                                    double height = 3.5;
                                    org.bukkit.util.Vector unitVector = new org.bukkit.util.Vector(X, height, Z);
                                    unitVector = unitVector.normalize();
                                    player.setVelocity(unitVector.multiply(1.35));
                                    plugin.kang_cooldown.put(player.getUniqueId(), 0.5);
                                } else {
                                    double X = player.getLocation().getDirection().getX();
                                    double Z = player.getLocation().getDirection().getZ();
                                    double height = 3.0;
                                    org.bukkit.util.Vector unitVector = new org.bukkit.util.Vector(X, height, Z);
                                    unitVector = unitVector.normalize();
                                    player.setVelocity(unitVector.multiply(1.35));
                                    plugin.kang_cooldown.put(player.getUniqueId(), 1.5);
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onFallDamage(EntityDamageEvent event) {
        Entity entity = event.getEntity();
        if (entity instanceof Player) {
            Player player = (Player) event.getEntity();
            if (plugin.kangaroo.contains(player.getUniqueId())) {
                if (event.getCause() == EntityDamageEvent.DamageCause.FALL) {
                    event.setCancelled(true);
                }
            }
        }
    }
}
