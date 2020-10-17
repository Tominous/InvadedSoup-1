package me.yochran.invadedsoup.runnables;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.scheduler.BukkitRunnable;

public class PotionRunnable extends BukkitRunnable {

    private InvadedSoup plugin;

    public PotionRunnable() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @Override
    public void run() {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            if (plugin.potion.contains(onlinePlayers.getUniqueId())) {
                if (onlinePlayers.getInventory().contains(XMaterial.MUSHROOM_STEW.parseItem())) {
                    onlinePlayers.getInventory().removeItem(XMaterial.MUSHROOM_STEW.parseItem());
                    ItemStack potion = new ItemStack(Material.POTION, 1, (short) 16421);
                    onlinePlayers.getInventory().addItem(potion);
                }
            }
        }
    }
}
