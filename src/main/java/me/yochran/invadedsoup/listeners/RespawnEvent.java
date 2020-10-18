package me.yochran.invadedsoup.listeners;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.management.SpawnManagement;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Item;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

public class RespawnEvent implements Listener {

    private InvadedSoup plugin;

    public RespawnEvent() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void onDeath(PlayerDeathEvent event) {
        Player player = event.getEntity();
        event.getDrops().clear();
        World world = player.getWorld();
        ItemStack soupItem = XMaterial.MUSHROOM_STEW.parseItem();
        for (ItemStack soup : player.getInventory().getContents()) {
            world.dropItemNaturally(player.getLocation(), soupItem);
        }
    }

    @EventHandler
    public void onRespawn(PlayerRespawnEvent event) {
        Player player = event.getPlayer();
        plugin.kit.remove(player.getUniqueId());
        plugin.switcher.remove(player.getUniqueId());
        plugin.urgal.remove(player.getUniqueId());
        plugin.potion.remove(player.getUniqueId());
        plugin.stomper.remove(player.getUniqueId());
        plugin.kangaroo.remove(player.getUniqueId());
        plugin.ninja.remove(player.getUniqueId());
        plugin.snowman.remove(player.getUniqueId());
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            SpawnManagement respawn = new SpawnManagement(player);
            respawn.tp();
        }, 1);
        Inventory playerInv = player.getInventory();
        ItemStack kitSelector = XMaterial.NETHER_STAR.parseItem();
        ItemStack help = XMaterial.PAPER.parseItem();
        ItemStack settings = XMaterial.CLOCK.parseItem();

        ItemMeta kitSelectorName = kitSelector.getItemMeta();
        ItemMeta settingsName = settings.getItemMeta();
        ItemMeta helpName = help.getItemMeta();

        kitSelectorName.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&bKit Selector"));
        settingsName.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&5Settings"));
        helpName.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&6Help &7(Right Click&7)"));
        ArrayList<String> helpLore = new ArrayList<>();
        helpLore.add(ChatColor.translateAlternateColorCodes('&', "&7Right click this item for information about the Soup server!"));
        helpName.setLore(helpLore);

        kitSelector.setItemMeta(kitSelectorName);
        settings.setItemMeta(settingsName);
        help.setItemMeta(helpName);

        playerInv.setItem(0, help);
        playerInv.setItem(4, kitSelector);
        playerInv.setItem(8, settings);
        player.removePotionEffect(PotionEffectType.SPEED);
        plugin.thor.remove(player.getUniqueId());
        plugin.viper.remove(player.getUniqueId());
    }
}
