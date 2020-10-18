package me.yochran.invadedsoup.commands;

import me.yochran.invadedsoup.InvadedSoup;
import me.yochran.invadedsoup.utils.Utils;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class ToggleGlobalChat implements CommandExecutor {

    private InvadedSoup plugin;

    public ToggleGlobalChat() {
        plugin = InvadedSoup.getPlugin(InvadedSoup.class);
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
        if (cmd.getName().equalsIgnoreCase("toggleglobalchat")) {
            if (!(sender instanceof Player)) {
                Utils.sendMessage(sender, "&cYou must be a player to use that command.");
            } else {
                Player player = (Player) sender;
                if (plugin.tgc.contains(player.getUniqueId())) {
                    plugin.tgc.remove(player.getUniqueId());
                    Utils.sendMessage(player, plugin.getConfig().getString("ToggleOnChat"));
                } else {
                    plugin.tgc.add(player.getUniqueId());
                    Utils.sendMessage(player, plugin.getConfig().getString("ToggleOffChat"));
                }
            }
        }
        return true;
    }
}
