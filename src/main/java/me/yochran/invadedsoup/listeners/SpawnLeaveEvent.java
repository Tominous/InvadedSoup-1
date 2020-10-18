package me.yochran.invadedsoup.listeners;

import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.management.SpawnManagement;
import me.yochran.invadedsoup.utils.Utils;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.PotionEffectType;

import java.util.ArrayList;

public class SpawnLeaveEvent implements Listener {

    private InvadedSoup plugin;

    public SpawnLeaveEvent() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler (priority = EventPriority.HIGH)
    public void onMove(PlayerMoveEvent event) {
        Player player = event.getPlayer();
        String worldname = plugin.getConfig().getString("Spawn.world");
        if (player.getWorld().getName().equalsIgnoreCase(worldname)) {
            if (player.getGameMode() == GameMode.SURVIVAL) {
                if (Utils.regionLeave(player)) {
                    if (!plugin.kit.containsKey(player.getUniqueId())) {
                        SpawnManagement back = new SpawnManagement(player);
                        back.tp();
                        Utils.sendMessage(player, "&cYou need to pick a kit before walking out of spawn!");
                        player.getInventory().clear();
                        player.getInventory().setArmorContents(null);
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
                    }
                }
            }
        }
    }
}
