package me.yochran.invadedsoup.listeners;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.XMaterial;
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
        Material type = event.getItemDrop().getItemStack().getType();
        if (plugin.kit.containsKey(player.getUniqueId())) {
            if (!plugin.dropsOn.contains(player.getUniqueId())) {
                if (type == XMaterial.DIAMOND_HELMET.parseMaterial() || type == XMaterial.GOLDEN_HELMET.parseMaterial() || type == XMaterial.CHAINMAIL_HELMET.parseMaterial() || type == XMaterial.IRON_HELMET.parseMaterial() || type == XMaterial.LEATHER_HELMET.parseMaterial()) {
                    player.getInventory().setHelmet(item.getItemStack());
                    event.getItemDrop().remove();
                } else if (type == XMaterial.DIAMOND_CHESTPLATE.parseMaterial() || type == XMaterial.GOLDEN_CHESTPLATE.parseMaterial() || type == XMaterial.CHAINMAIL_CHESTPLATE.parseMaterial() || type == XMaterial.IRON_CHESTPLATE.parseMaterial() || type == XMaterial.LEATHER_CHESTPLATE.parseMaterial()) {
                    player.getInventory().setChestplate(item.getItemStack());
                    event.getItemDrop().remove();
                } else if (type == XMaterial.DIAMOND_LEGGINGS.parseMaterial() || type == XMaterial.GOLDEN_LEGGINGS.parseMaterial() || type == XMaterial.CHAINMAIL_LEGGINGS.parseMaterial() || type == XMaterial.IRON_LEGGINGS.parseMaterial()|| type == XMaterial.LEATHER_LEGGINGS.parseMaterial()) {
                    player.getInventory().setLeggings(item.getItemStack());
                    event.getItemDrop().remove();
                } else if (type == XMaterial.DIAMOND_BOOTS.parseMaterial() || type == XMaterial.GOLDEN_BOOTS.parseMaterial() || type == XMaterial.CHAINMAIL_BOOTS.parseMaterial() || type == XMaterial.IRON_BOOTS.parseMaterial() || type == XMaterial.LEATHER_BOOTS.parseMaterial()) {
                    player.getInventory().setBoots(item.getItemStack());
                    event.getItemDrop().remove();
                } else {
                    if (type == XMaterial.BOWL.parseMaterial()) {
                        event.getItemDrop().remove();
                    } else {
                        event.setCancelled(true);
                    }
                }
                player.updateInventory();
            } else {
                if (type == XMaterial.MUSHROOM_STEW.parseMaterial()) {
                    return;
                } else {
                    event.getItemDrop().remove();
                }
            }
        } else {
            File file = new File(plugin.getDataFolder(), "config.yml");
            FileConfiguration config = YamlConfiguration.loadConfiguration(file);
            String worldname = config.getString("Spawn.world");
            if (player.getWorld().getName().equalsIgnoreCase(worldname)) {
                event.setCancelled(true);
                player.updateInventory();
            }
        }
    }
}
