package me.yochran.invadedsoup.listeners;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;

public class SoupListener implements Listener {

    private InvadedSoup plugin;

    public SoupListener() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void soupClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType() == XMaterial.MUSHROOM_STEW.parseMaterial()) {
            if (player.getHealth() == 20.0) {
                return;
            } else {
                event.setCancelled(true);
                double newHealth = Math.min(player.getHealth() + 7.0, player.getMaxHealth());
                player.setHealth(newHealth);
                int slot = player.getInventory().getHeldItemSlot();
                player.getInventory().getItem(slot).setType(Material.BOWL);
                player.updateInventory();
            }
        }
    }

    @EventHandler
    public void playerClick(PlayerInteractAtEntityEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType() == XMaterial.MUSHROOM_STEW.parseMaterial()) {
            if (player.getHealth() == 20.0) {
                return;
            } else {
                event.setCancelled(true);
                double newHealth = Math.min(player.getHealth() + 7.0, player.getMaxHealth());
                player.setHealth(newHealth);
                int slot = player.getInventory().getHeldItemSlot();
                player.getInventory().getItem(slot).setType(Material.BOWL);
                player.updateInventory();
            }
        }
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getItemInHand().getType() == XMaterial.MUSHROOM_STEW.parseMaterial()) {
            if (event.getAction() == Action.LEFT_CLICK_AIR) {
                if (player.getHealth() == 20.0) {
                    return;
                } else {
                    event.setCancelled(true);
                    double newHealth = Math.min(player.getHealth() + 7.0, player.getMaxHealth());
                    player.setHealth(newHealth);
                    int slot = player.getInventory().getHeldItemSlot();
                    player.getInventory().getItem(slot).setType(Material.BOWL);
                    player.updateInventory();
                }
            }
        }
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent event) {
        Player player = event.getPlayer();
        Item item = event.getItemDrop();
        Material type = item.getItemStack().getType();
        switch (type) {
            case BOWL: {
                Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                    item.remove();
                    player.updateInventory();
                }, 5);
                return;
            }
            case STONE_SWORD:
            case IRON_SWORD:
            case DIAMOND_SWORD: {
                event.setCancelled(true);
                return;
            }
        }
    }
}
