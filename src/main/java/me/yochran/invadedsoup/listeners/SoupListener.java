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
                }, 1);
                return;
            }
            case DIAMOND_AXE:
            case IRON_AXE:
            case BOW:
            case ARROW:
            case SNOWBALL:
            case FISHING_ROD:
            case MUSHROOM_STEW:
            case STONE_SWORD:
            case IRON_SWORD:
            case DIAMOND_SWORD: {
                if (!plugin.dropsOn.contains(player.getUniqueId())) {
                    event.setCancelled(true);
                    player.updateInventory();
                    return;
                } else {
                    return;
                }
            }
            case NETHER_STAR:
            case PAPER:
            case BOOK: {
                event.setCancelled(true);
                player.updateInventory();
            }
            case DIAMOND_HELMET:
            case CHAINMAIL_HELMET:
            case GOLDEN_HELMET:
            case IRON_HELMET:
            case LEATHER_HELMET: {
                if (!plugin.dropsOn.contains(player.getUniqueId())) {
                    player.getInventory().setHelmet(item.getItemStack());
                    player.updateInventory();
                    item.remove();
                    return;
                } else {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                        item.remove();
                        player.updateInventory();
                    }, 1);
                }
            }
            case CHAINMAIL_CHESTPLATE:
            case DIAMOND_CHESTPLATE:
            case GOLDEN_CHESTPLATE:
            case IRON_CHESTPLATE:
            case LEATHER_CHESTPLATE: {
                if (!plugin.dropsOn.contains(player.getUniqueId())) {
                    player.getInventory().setChestplate(item.getItemStack());
                    player.updateInventory();
                    item.remove();
                    return;
                } else {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                        item.remove();
                        player.updateInventory();
                    }, 1);
                }
            }
            case LEATHER_LEGGINGS:
            case CHAINMAIL_LEGGINGS:
            case DIAMOND_LEGGINGS:
            case GOLDEN_LEGGINGS:
            case IRON_LEGGINGS: {
                if (!plugin.dropsOn.contains(player.getUniqueId())) {
                    player.getInventory().setLeggings(item.getItemStack());
                    player.updateInventory();
                    item.remove();
                    return;
                } else {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                        item.remove();
                        player.updateInventory();
                    }, 1);
                }
            }
            case CHAINMAIL_BOOTS:
            case DIAMOND_BOOTS:
            case GOLDEN_BOOTS:
            case IRON_BOOTS:
            case LEATHER_BOOTS: {
                if (!plugin.dropsOn.contains(player.getUniqueId())) {
                    player.getInventory().setBoots(item.getItemStack());
                    player.updateInventory();
                    item.remove();
                } else {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                        item.remove();
                        player.updateInventory();
                    }, 1);
                }
            }
        }
    }
}
