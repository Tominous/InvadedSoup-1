package me.yochran.invadedsoup.runnables;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class RefillRunnable extends BukkitRunnable {

    private InvadedSoup plugin;

    public RefillRunnable() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @Override
    public void run() {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            if (plugin.refill.containsKey(onlinePlayers.getUniqueId())) {
                plugin.refill.put(onlinePlayers.getUniqueId(), plugin.refill.get(onlinePlayers.getUniqueId()) - 1);
                Location location = onlinePlayers.getLocation();
                int X = location.getBlockX();
                int Z = location.getBlockZ();

                if (X != plugin.X.get(onlinePlayers.getUniqueId())) {
                    plugin.refill.remove(onlinePlayers.getUniqueId());
                    plugin.X.remove(onlinePlayers.getUniqueId());
                    plugin.Z.remove(onlinePlayers.getUniqueId());
                    Utils.sendMessage(onlinePlayers, "&7Refill timer cancelled.");
                }

                if (Z != plugin.Z.get(onlinePlayers.getUniqueId())) {
                    plugin.refill.remove(onlinePlayers.getUniqueId());
                    plugin.X.remove(onlinePlayers.getUniqueId());
                    plugin.Z.remove(onlinePlayers.getUniqueId());
                    Utils.sendMessage(onlinePlayers, "&7Refill timer cancelled.");
                }

                double time = plugin.refill.get(onlinePlayers.getUniqueId());

                if (time <= 0.0) {
                    plugin.refill.remove(onlinePlayers.getUniqueId());
                    ItemStack soup = XMaterial.MUSHROOM_STEW.parseItem();
                    Inventory refillInv = Bukkit.createInventory(onlinePlayers, 36, ChatColor.GREEN + "Refill Box");
                    for (int i = 0; i < 36; i++) {
                        refillInv.addItem(soup);
                    }
                    onlinePlayers.openInventory(refillInv);
                    Utils.sendMessage(onlinePlayers, "&aYou have opened the refill box.");
                }
            }
        }
    }
}
