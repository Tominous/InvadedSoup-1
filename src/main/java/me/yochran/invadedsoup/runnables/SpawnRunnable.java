package me.yochran.invadedsoup.runnables;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.management.SpawnManagement;
import me.yochran.invadedsoup.utils.Utils;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;

public class SpawnRunnable extends BukkitRunnable {

    private InvadedSoup plugin;

    public SpawnRunnable() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @Override
    public void run() {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            if (plugin.spawn.containsKey(onlinePlayers.getUniqueId())) {
                plugin.spawn.put(onlinePlayers.getUniqueId(), plugin.spawn.get(onlinePlayers.getUniqueId()) - 1);
                Location location = onlinePlayers.getLocation();
                int X = location.getBlockX();
                int Z = location.getBlockZ();

                if (X != plugin.X.get(onlinePlayers.getUniqueId())) {
                    plugin.spawn.remove(onlinePlayers.getUniqueId());
                    plugin.X.remove(onlinePlayers.getUniqueId());
                    plugin.Z.remove(onlinePlayers.getUniqueId());
                    Utils.sendMessage(onlinePlayers, "&7Spawn teleportation cancelled.");
                }

                if (Z != plugin.Z.get(onlinePlayers.getUniqueId())) {
                    plugin.spawn.remove(onlinePlayers.getUniqueId());
                    plugin.X.remove(onlinePlayers.getUniqueId());
                    plugin.Z.remove(onlinePlayers.getUniqueId());
                    Utils.sendMessage(onlinePlayers, "&7Spawn teleportation cancelled.");
                }
                double time = plugin.spawn.get(onlinePlayers.getUniqueId());

                if (time <= 0.0) {
                    plugin.spawn.remove(onlinePlayers.getUniqueId());
                    plugin.X.remove(onlinePlayers.getUniqueId());
                    plugin.Z.remove(onlinePlayers.getUniqueId());
                    plugin.kangaroo.remove(onlinePlayers.getUniqueId());
                    SpawnManagement spawn = new SpawnManagement(onlinePlayers);
                    spawn.tp();
                    onlinePlayers.getInventory().clear();
                    onlinePlayers.getInventory().setArmorContents(null);
                    Inventory playerInv = onlinePlayers.getInventory();
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
                    onlinePlayers.removePotionEffect(PotionEffectType.SPEED);
                    plugin.kit.remove(onlinePlayers.getUniqueId());
                    plugin.potion.remove(onlinePlayers.getUniqueId());
                    plugin.urgal.remove(onlinePlayers.getUniqueId());
                    plugin.switcher.remove(onlinePlayers.getUniqueId());
                    plugin.stomper.remove(onlinePlayers.getUniqueId());
                    plugin.kangaroo.remove(onlinePlayers.getUniqueId());
                    plugin.ninja.remove(onlinePlayers.getUniqueId());
                    plugin.snowman.remove(onlinePlayers.getUniqueId());
                    plugin.thor.remove(onlinePlayers.getUniqueId());
                    plugin.viper.remove(onlinePlayers.getUniqueId());
                }
            }
        }
    }
}
