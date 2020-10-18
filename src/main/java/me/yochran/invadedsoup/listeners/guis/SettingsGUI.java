package me.yochran.invadedsoup.listeners.guis;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.XMaterial;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class SettingsGUI implements Listener {

    private InvadedSoup plugin;

    public SettingsGUI() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void onClick(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        if (player.getInventory().getItemInHand().getType() == XMaterial.CLOCK.parseMaterial()) {
            Inventory generalSettingsInv = Bukkit.createInventory(player, 9, ChatColor.YELLOW + "Settings");
            ItemStack tpm = XMaterial.WRITABLE_BOOK.parseItem();
            ItemMeta tpmMeta = tpm.getItemMeta();
            if (plugin.tpm.contains(player.getUniqueId())) {
                tpmMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&ePrivate Messages: &cDisabled."));
            } else {
                tpmMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&ePrivate Messages: &aEnabled."));
            }
            ArrayList<String> tpmLore = new ArrayList<>();
            tpmLore.add(ChatColor.GRAY + "Click to select");
            tpmMeta.setLore(tpmLore);
            tpm.setItemMeta(tpmMeta);
            generalSettingsInv.setItem(0, tpm);

            // Global Chat Item
            ItemStack globalchat = XMaterial.PAPER.parseItem();
            ItemMeta gcMeta = globalchat.getItemMeta();
            if (plugin.tgc.contains(player.getUniqueId())) {
                gcMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eGlobal Chat: &cDisabled."));
            } else {
                gcMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eGlobal Chat: &aEnabled."));
            }
            ArrayList<String> gcLore = new ArrayList<>();
            gcLore.add(ChatColor.GRAY + "Click to select");
            gcMeta.setLore(gcLore);
            globalchat.setItemMeta(gcMeta);
            generalSettingsInv.setItem(1, globalchat);

            // Message Sound Item
            ItemStack messagesounds = XMaterial.EGG.parseItem();
            ItemMeta msMeta = globalchat.getItemMeta();
            if (plugin.tms.contains(player.getUniqueId())) {
                msMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eMessaging Sounds: &cDisabled."));
            } else {
                msMeta.setDisplayName(ChatColor.translateAlternateColorCodes('&', "&eMessaging Sounds: &aEnabled."));
            }
            ArrayList<String> msLore = new ArrayList<>();
            msLore.add(ChatColor.GRAY + "Click to select");
            msMeta.setLore(msLore);
            messagesounds.setItemMeta(msMeta);
            generalSettingsInv.setItem(2, messagesounds);

            player.openInventory(generalSettingsInv);
        }
    }
}
