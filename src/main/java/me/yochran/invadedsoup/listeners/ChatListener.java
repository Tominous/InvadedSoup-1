package me.yochran.invadedsoup.listeners;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private InvadedSoup plugin;

    public ChatListener() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        Player player = event.getPlayer();
        if (plugin.tgc.contains(player.getUniqueId())) {
            event.setCancelled(true);
            Utils.sendMessage(player, plugin.getConfig().getString("CannotChatMsg"));
        } else {
            return;
        }
    }

    @EventHandler
    public void onOtherChat(AsyncPlayerChatEvent event) {
        for (Player onlinePlayers : Bukkit.getOnlinePlayers()) {
            if (plugin.tgc.contains(onlinePlayers.getUniqueId())) {
                event.getRecipients().remove(onlinePlayers);
            }
        }
    }
}
