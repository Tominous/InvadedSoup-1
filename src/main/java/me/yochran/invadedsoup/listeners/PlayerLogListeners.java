package me.yochran.invadedsoup.listeners;

import com.sun.jdi.connect.Connector;
import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.management.SpawnManagement;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class PlayerLogListeners implements Listener {

    private InvadedSoup plugin;

    public PlayerLogListeners() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.setHealth(20.0);
        player.setFoodLevel(20);
        player.getInventory().clear();
        player.getInventory().setHelmet(null);
        player.getInventory().setChestplate(null);
        player.getInventory().setLeggings(null);
        player.getInventory().setBoots(null);
        Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, () -> {
            SpawnManagement login = new SpawnManagement(player);
            login.tp();
        }, 1);
        Inventory playerInv = player.getInventory();
        ItemStack kitSelector = XMaterial.NETHER_STAR.parseItem();
        ItemStack help = XMaterial.PAPER.parseItem();
        ItemStack settings = XMaterial.BOOK.parseItem();

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
        plugin.kit.remove(player.getUniqueId());
        plugin.potion.remove(player.getUniqueId());
        plugin.urgal.remove(player.getUniqueId());
        plugin.switcher.remove(player.getUniqueId());
        plugin.stomper.remove(player.getUniqueId());
        plugin.kangaroo.remove(player.getUniqueId());
        plugin.ninja.remove(player.getUniqueId());
    }
}
