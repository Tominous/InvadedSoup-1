package me.yochran.invadedsoup.listeners;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractAtEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;

import java.io.File;

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
                }
            }
        }
    }

    @EventHandler
    public void dropitem(PlayerDropItemEvent event){
        Player player = event.getPlayer();
        Item item = event.getItemDrop();
        if (!Utils.regionLeave(player)) {
            event.setCancelled(true);
            player.updateInventory();
        } else {
            Material type = item.getItemStack().getType();
            if (type == XMaterial.MUSHROOM_STEW.parseMaterial() || type == Material.POTION) {
                if (plugin.dropsOn.contains(player.getUniqueId())) {
                    Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                        item.remove();
                        player.updateInventory();
                    }, 20 * 10);
                } else {
                    event.setCancelled(true);
                    player.updateInventory();
                    return;
                }
            } else if (type == XMaterial.SNOWBALL.parseMaterial() || type == XMaterial.FIREWORK_ROCKET.parseMaterial()) {
                event.setCancelled(true);
                player.updateInventory();
            } else {
                switch (type){
                    case BOWL: {
                        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
                            item.remove();
                            player.updateInventory();
                        }, 0);
                        return;
                    }
                    case FIREWORK_ROCKET:{
                        if (plugin.kangaroo.contains(player.getUniqueId())) {
                            event.setCancelled(true);
                            player.updateInventory();
                            return;
                        }
                    }
                    case CLOCK:{
                        if (player.getWorld().getName().equalsIgnoreCase(plugin.getConfig().getString("Spawn.world"))){
                            event.setCancelled(true);
                            player.updateInventory();
                            return;
                        }
                    }
                    case NETHER_STAR:{
                        if (item.getItemStack().getItemMeta().getDisplayName().equals(ChatColor.AQUA + "Kit Selector")){
                            event.setCancelled(true);
                            player.updateInventory();
                            return;
                        }
                    }
                    case PAPER:{
                        if (item.getItemStack().getItemMeta().getDisplayName().equals(ChatColor.translateAlternateColorCodes('&', "&6Help &7(Right Click)"))){
                            event.setCancelled(true);
                            player.updateInventory();
                            return;
                        }
                    }
                    case STONE_SWORD:
                    case DIAMOND_SWORD:
                    case IRON_SWORD:
                    case DIAMOND_AXE:
                    case IRON_AXE:
                    case BOW:
                    case ARROW:
                    case FISHING_ROD:{
                        event.setCancelled(true);
                        player.updateInventory();
                        return;
                    }
                    case DIAMOND_HELMET:
                    case CHAINMAIL_HELMET:
                    case GOLDEN_HELMET:
                    case IRON_HELMET:
                    case LEATHER_HELMET:{
                        player.getInventory().setHelmet(item.getItemStack());
                        player.updateInventory();
                        item.remove();
                        return;
                    }
                    case CHAINMAIL_CHESTPLATE:
                    case DIAMOND_CHESTPLATE:
                    case GOLDEN_CHESTPLATE:
                    case IRON_CHESTPLATE:
                    case LEATHER_CHESTPLATE:{
                        player.getInventory().setChestplate(item.getItemStack());
                        player.updateInventory();
                        item.remove();
                        return;
                    }
                    case LEATHER_LEGGINGS:
                    case CHAINMAIL_LEGGINGS:
                    case DIAMOND_LEGGINGS:
                    case GOLDEN_LEGGINGS:
                    case IRON_LEGGINGS:{
                        player.getInventory().setLeggings(item.getItemStack());
                        player.updateInventory();
                        item.remove();
                        return;
                    }
                    case CHAINMAIL_BOOTS:
                    case DIAMOND_BOOTS:
                    case GOLDEN_BOOTS:
                    case IRON_BOOTS:
                    case LEATHER_BOOTS:{
                        player.getInventory().setBoots(item.getItemStack());
                        player.updateInventory();
                        item.remove();
                    }
                }
            }
        }
    }
}
